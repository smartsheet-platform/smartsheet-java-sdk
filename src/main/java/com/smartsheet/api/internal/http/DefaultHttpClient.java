package com.smartsheet.api.internal.http;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

import com.smartsheet.api.Trace;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Error;
import org.apache.http.Header;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * This is the Apache HttpClient (http://hc.apache.org/httpcomponents-client-ga/index.html) based HttpClient
 * implementation.
 *
 * Thread Safety: This class is thread safe because it is immutable and the underlying Apache CloseableHttpClient is
 * thread safe.
 */
public class DefaultHttpClient implements HttpClient {
    /** logger for general errors, warnings, etc */
    private static final Logger logger = LoggerFactory.getLogger(DefaultHttpClient.class);

    // to avoid creating new sets for each call (we use Sets for practical and perf reasons)
    private static final Set<Trace> REQUEST_RESPONSE_SUMMARY = Collections.unmodifiableSet(new HashSet<Trace>(
            Arrays.asList(Trace.RequestHeaders, Trace.RequestBodySummary, Trace.ResponseHeaders, Trace.ResponseBodySummary)));

    // default values for trace-logging extracted from system-properties (can still be overwritten at the instance level)
    private static final boolean TRACE_PRETTY_PRINT_DEFAULT = Boolean.parseBoolean(System.getProperty("Smartsheet.trace.pretty", "true"));

    private static final Set<Trace> TRACE_DEFAULT_TRACE_SET  = Trace.parse(System.getProperty("Smartsheet.trace.parts"));    // empty by default

    /** where to send trace logs */
    private static PrintWriter TRACE_WRITER;
    static {
        setTraceStream(System.out); // default trace stream
        if (TRACE_DEFAULT_TRACE_SET.size() > 0) {
            TRACE_WRITER.println("default trace logging - pretty:" + TRACE_PRETTY_PRINT_DEFAULT + " parts:" + TRACE_DEFAULT_TRACE_SET);
        }
    }

    /**
     * Represents the underlying Apache CloseableHttpClient.
     * <p>
     * It will be initialized in constructor and will not change afterwards.
     */
    private final CloseableHttpClient httpClient;

    /** The apache http response. */
    private CloseableHttpResponse apacheHttpResponse;

    /** the set of Trace levels to use in trace-logging */
    private final Set<Trace> traces = new HashSet<Trace>(TRACE_DEFAULT_TRACE_SET);
    /** whether to log pretty or compact */
    private boolean tracePrettyPrint = TRACE_PRETTY_PRINT_DEFAULT;

    private static final String JSON_MIME_TYPE = ContentType.APPLICATION_JSON.getMimeType();

    private JsonSerializer jsonSerializer;

    private long maxRetryTimeMillis = 15000;

    /**
     * Constructor.
     */
    public DefaultHttpClient() {
        this(HttpClients.createDefault(), new JacksonJsonSerializer());
    }

    /**
     * Constructor.
     * <p>
     * Parameters: - httpClient : the Apache CloseableHttpClient to use
     * <p>
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param httpClient the http client
     */
    public DefaultHttpClient(CloseableHttpClient httpClient, JsonSerializer jsonSerializer) {
        this.httpClient = Util.throwIfNull(httpClient);
        this.jsonSerializer = jsonSerializer;
    }

    /**
     * Make an HTTP request and return the response.
     *
     * @param smartsheetRequest the smartsheet request
     * @return the HTTP response
     * @throws HttpClientException the HTTP client exception
     */
    public HttpResponse request(HttpRequest smartsheetRequest) throws HttpClientException {
        Util.throwIfNull(smartsheetRequest);
        if (smartsheetRequest.getUri() == null) {
            throw new IllegalArgumentException("A Request URI is required.");
        }

        int attempt = 0;
        long start = System.currentTimeMillis();

        HttpRequestBase apacheHttpRequest;
        HttpResponse smartsheetResponse;

        InputStream bodyStream = null;
        if(smartsheetRequest.getEntity() != null && smartsheetRequest.getEntity().getContent() != null) {
            bodyStream = smartsheetRequest.getEntity().getContent();
        }
        // the retry logic will consume the body stream so we make sure it supports mark/reset and mark it
        boolean canRetryRequest = bodyStream == null || bodyStream.markSupported();
        if (!canRetryRequest) {
            try {
                // attempt to wrap the body stream in a input-stream that does support mark/reset
                bodyStream = new ByteArrayInputStream(StreamUtil.readBytesFromStream(bodyStream));
                // close the old stream (just to be tidy) and then replace it with a reset-able stream
                smartsheetRequest.getEntity().getContent().close();
                smartsheetRequest.getEntity().setContent(bodyStream);
                canRetryRequest = true;
            }
            catch(IOException ignore) {
            }
        }

        // the retry loop
        while(true) {

            apacheHttpRequest = createApacheRequest(smartsheetRequest);

            // Set HTTP headers
            if (smartsheetRequest.getHeaders() != null) {
                for (Map.Entry<String, String> header : smartsheetRequest.getHeaders().entrySet()) {
                    apacheHttpRequest.addHeader(header.getKey(), header.getValue());
                }
            }

            HttpEntitySnapshot requestEntityCopy = null;
            HttpEntitySnapshot responseEntityCopy = null;
            // Set HTTP entity
            final HttpEntity entity = smartsheetRequest.getEntity();
            if (apacheHttpRequest instanceof HttpEntityEnclosingRequestBase && entity != null && entity.getContent() != null) {
                try {
                    // we need access to the original request stream so we can log it (in the event of errors and/or tracing)
                    requestEntityCopy = new HttpEntitySnapshot(entity);
                } catch (IOException iox) {
                    logger.error("failed to make copy of original request entity - {}", iox);
                }

                InputStreamEntity streamEntity = new InputStreamEntity(entity.getContent(), entity.getContentLength());
                streamEntity.setChunked(false);    // why?  not supported by library?
                ((HttpEntityEnclosingRequestBase) apacheHttpRequest).setEntity(streamEntity);
            }

            // mark the body so we can reset on retry
            if(canRetryRequest && bodyStream != null) {
                bodyStream.mark((int)smartsheetRequest.getEntity().getContentLength());
            }

            // Make the HTTP request
            smartsheetResponse = new HttpResponse();
            HttpContext context = new BasicHttpContext();
            try {
                apacheHttpResponse = this.httpClient.execute(apacheHttpRequest, context);

                // Set request headers to values ACTUALLY SENT (not just created by us), this would include:
                // 'Connection', 'Accept-Encoding', etc. However, if a proxy is used, this may be the proxy's CONNECT
                // request, hence the test for HTTP method first
                Object httpRequest = context.getAttribute("http.request");
                if(httpRequest != null && HttpRequestWrapper.class.isAssignableFrom(httpRequest.getClass())) {
                    HttpRequestWrapper actualRequest = (HttpRequestWrapper)httpRequest;
                    switch(HttpMethod.valueOf(actualRequest.getMethod())) {
                        case GET:
                        case POST:
                        case PUT:
                        case DELETE:
                            apacheHttpRequest.setHeaders(((HttpRequestWrapper)httpRequest).getAllHeaders());
                            break;
                    }
                }

                // Set returned headers
                smartsheetResponse.setHeaders(new HashMap<String, String>());
                for (Header header : apacheHttpResponse.getAllHeaders()) {
                    smartsheetResponse.getHeaders().put(header.getName(), header.getValue());
                }
                smartsheetResponse.setStatus(apacheHttpResponse.getStatusLine().getStatusCode(),
                        apacheHttpResponse.getStatusLine().toString());

                // Set returned entities
                if (apacheHttpResponse.getEntity() != null) {
                    HttpEntity httpEntity = new HttpEntity();
                    httpEntity.setContentType(apacheHttpResponse.getEntity().getContentType().getValue());
                    httpEntity.setContentLength(apacheHttpResponse.getEntity().getContentLength());
                    httpEntity.setContent(apacheHttpResponse.getEntity().getContent());
                    smartsheetResponse.setEntity(httpEntity);
                    responseEntityCopy = new HttpEntitySnapshot(httpEntity);
                }
                // HTTP-error logging
                if (smartsheetResponse.getStatusCode() != 200) {
                    // log the summary request and response on error
                    logger.warn("{}", RequestAndResponseData.of(apacheHttpRequest, requestEntityCopy, smartsheetResponse,
                            responseEntityCopy, REQUEST_RESPONSE_SUMMARY));
                }

                if (traces.size() > 0) { // trace-logging of request and response (if so configured)
                    RequestAndResponseData requestAndResponseData = RequestAndResponseData.of(apacheHttpRequest,
                            requestEntityCopy, smartsheetResponse, responseEntityCopy, traces);
                    TRACE_WRITER.println(requestAndResponseData.toString(tracePrettyPrint));
                }

                if (smartsheetResponse.getStatusCode() == 200) {
                    // call successful, exit the retry loop
                    break;
                }

                // the retry logic might consume the content stream so we make sure it supports mark/reset and mark it
                InputStream contentStream = smartsheetResponse.getEntity().getContent();
                if (!contentStream.markSupported()) {
                    // wrap the response stream in a input-stream that does support mark/reset
                    contentStream = new ByteArrayInputStream(StreamUtil.readBytesFromStream(contentStream));
                    // close the old stream (just to be tidy) and then replace it with a reset-able stream
                    smartsheetResponse.getEntity().getContent().close();
                    smartsheetResponse.getEntity().setContent(contentStream);
                }
                try {
                    contentStream.mark((int) smartsheetResponse.getEntity().getContentLength());
                    long timeSpent = System.currentTimeMillis() - start;
                    if (!shouldRetry(++attempt, timeSpent, smartsheetResponse)) {
                        // should not retry, or retry time exceeded, exit the retry loop
                        break;
                    }
                } finally {
                    if(bodyStream != null) {
                        bodyStream.reset();
                    }
                    contentStream.reset();
                }
                // moving this to finally causes issues because socket is closed (which means response stream is closed)
                this.releaseConnection();

            } catch (ClientProtocolException e) {
                try {
                    logger.warn("ClientProtocolException " + e.getMessage());
                    logger.warn("{}", RequestAndResponseData.of(apacheHttpRequest, requestEntityCopy, smartsheetResponse,
                            responseEntityCopy, REQUEST_RESPONSE_SUMMARY));
                    // if this is a PUT and was retried by the http client, the body content stream is at the
                    // end and is a NonRepeatableRequest. If we marked the body content stream prior to execute,
                    // reset and retry
                    if (canRetryRequest && e.getCause() instanceof NonRepeatableRequestException) {
                        if (smartsheetRequest.getEntity() != null) {
                            smartsheetRequest.getEntity().getContent().reset();
                        }
                        continue;
                    }
                } catch (IOException ignore) {
                }
                throw new HttpClientException("Error occurred.", e);
            } catch (NoHttpResponseException e) {
                try {
                    logger.warn("NoHttpResponseException " + e.getMessage());
                    logger.warn("{}", RequestAndResponseData.of(apacheHttpRequest, requestEntityCopy, smartsheetResponse,
                            responseEntityCopy, REQUEST_RESPONSE_SUMMARY));
                    // check to see if the response was empty and this was a POST. All other HTTP methods
                    // will be automatically retried by the http client.
                    // (POST is non-idempotent and is not retried automatically, but is safe for us to retry)
                    if (canRetryRequest && smartsheetRequest.getMethod() == HttpMethod.POST) {
                        if (smartsheetRequest.getEntity() != null) {
                            smartsheetRequest.getEntity().getContent().reset();
                        }
                        continue;
                    }
                } catch (IOException ignore) {
                }
                throw new HttpClientException("Error occurred.", e);
            } catch (IOException e) {
                try {
                    logger.warn("{}", RequestAndResponseData.of(apacheHttpRequest, requestEntityCopy, smartsheetResponse,
                            responseEntityCopy, REQUEST_RESPONSE_SUMMARY));
                } catch (IOException ignore) {
                }
                throw new HttpClientException("Error occurred.", e);
            }
        }
        return smartsheetResponse;
    }

    /**
     * Create the Apache HTTP request. Override this function to inject additional
     * haaders in the request or use a proxy.
     *
     * @param smartsheetRequest (request method and base URI come from here)
     * @return the Apache HTTP request
     */
    public HttpRequestBase createApacheRequest(HttpRequest smartsheetRequest) {
        HttpRequestBase apacheHttpRequest;

        // Create Apache HTTP request based on the smartsheetRequest request type
        switch (smartsheetRequest.getMethod()) {
            case GET:
                apacheHttpRequest = new HttpGet(smartsheetRequest.getUri());
                break;
            case POST:
                apacheHttpRequest = new HttpPost(smartsheetRequest.getUri());
                break;
            case PUT:
                apacheHttpRequest = new HttpPut(smartsheetRequest.getUri());
                break;
            case DELETE:
                apacheHttpRequest = new HttpDelete(smartsheetRequest.getUri());
                break;
            default:
                throw new UnsupportedOperationException("Request method " + smartsheetRequest.getMethod()
                        + " is not supported!");
        }

        RequestConfig.Builder builder = RequestConfig.custom();
        if (apacheHttpRequest.getConfig() != null) {
            builder = RequestConfig.copy(apacheHttpRequest.getConfig());
        }
        builder.setRedirectsEnabled(true);
        RequestConfig config = builder.build();
        apacheHttpRequest.setConfig(config);
        return apacheHttpRequest;
    }

    /**
     * Set the max retry time for API calls which fail and are retry-able.
     *
     * @param maxRetryTimeMillis
     */
    public void setMaxRetryTimeMillis(long maxRetryTimeMillis) {
        this.maxRetryTimeMillis = maxRetryTimeMillis;
    }

    /**
     * The backoff calculation routine. Uses exponential backoff. If the maximum elapsed time
     * has expired, this calculation returns -1 causing the caller to fall out of the retry loop.
     *
     * @param previousAttempts
     * @param totalElapsedTimeMillis
     * @param error
     * @return -1 to fall out of retry loop, positive number indicates backoff time
     */
    public long calcBackoff(int previousAttempts, long totalElapsedTimeMillis, Error error) {

        long backoffMillis = (long)(Math.pow(2, previousAttempts) * 1000) + new Random().nextInt(1000);

        if(totalElapsedTimeMillis + backoffMillis > maxRetryTimeMillis) {
            logger.info("Elapsed time " + totalElapsedTimeMillis + " + backoff time " + backoffMillis +
                    " exceeds max retry time " + maxRetryTimeMillis + ", exiting retry loop");
            return -1;
        }
        return backoffMillis;
    }

    /**
     * Called when an API request fails to determine if it can retry the request.
     * Calls calcBackoff to determine the time to wait in between retries.
     *
     * @param previousAttempts number of attempts (including this one) to execute request
     * @param totalElapsedTimeMillis total time spent in millis for all previous (and this) attempt
     * @param response the failed HttpResponse
     * @return true if this request can be retried
     */
    public boolean shouldRetry(int previousAttempts, long totalElapsedTimeMillis, HttpResponse response) {
        String contentType = response.getEntity().getContentType();
        if (contentType != null && !contentType.startsWith(JSON_MIME_TYPE)) {
            // it's not JSON; don't even try to parse it
            return false;
        }
        Error error;
        try {
            error = jsonSerializer.deserialize(Error.class, response.getEntity().getContent());
        }
        catch (IOException e) {
            return false;
        }
        switch(error.getErrorCode()) {
            case 4001: /** Smartsheet.com is currently offline for system maintenance. Please check back again shortly. */
            case 4002: /** Server timeout exceeded. Request has failed */
            case 4003: /** Rate limit exceeded. */
            case 4004: /** An unexpected error has occurred. Please retry your request.
             * If you encounter this error repeatedly, please contact api@smartsheet.com for assistance. */
                break;
            default:
                return false;
        }

        long backoffMillis = calcBackoff(previousAttempts, totalElapsedTimeMillis, error);
        if(backoffMillis < 0)
            return false;

        logger.info("HttpError StatusCode=" + response.getStatusCode() + ": Retrying in " + backoffMillis + " milliseconds");
        try {
            Thread.sleep(backoffMillis);
        }
        catch (InterruptedException e) {
            logger.warn("sleep interrupted", e);
            return false;
        }
        return true;
    }

    /**
     * Close the HttpClient.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void close() throws IOException {
        this.httpClient.close();
    }

    /* (non-Javadoc)
     * @see com.smartsheet.api.internal.http.HttpClient#releaseConnection()
     */
    @Override
    public void releaseConnection() {
        if (apacheHttpResponse != null) {
            try {
                apacheHttpResponse.close();
                apacheHttpResponse = null;
            } catch (IOException e) {
                logger.error("error closing Apache HttpResponse - {}", e);
            }
        }
    }

    @Override
    public void setTraces(Trace... traces) {
        this.traces.clear();
        for (Trace trace : traces) {
            if (!trace.addReplacements(this.traces)) {
                this.traces.add(trace);
            }
        }
    }

    @Override
    public void setTracePrettyPrint(boolean pretty) {
        tracePrettyPrint = pretty;
    }

    /** only included for testing purposes */
    public static void setTraceStream(OutputStream traceStream) {
        TRACE_WRITER = new PrintWriter(traceStream, true);
    }
}