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
import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.retry.ShouldRetry;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /** UserAgent string sent with each request */
    private final String userAgent;

    /** the set of Trace levels to use in trace-logging */
    private final Set<Trace> traces = new HashSet<Trace>(TRACE_DEFAULT_TRACE_SET);
    /** whether to log pretty or compact */
    private boolean tracePrettyPrint = TRACE_PRETTY_PRINT_DEFAULT;

    @Deprecated // never used (within SDK)
    public static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    private ShouldRetry shouldRetry;

    /**
     * Constructor.
     */
    public DefaultHttpClient() {
        this(HttpClients.createDefault(), new DefaultShouldRetry(null));
    }

    /**
     * Constructor.
     * <p>
     * Parameters: - httpClient : the Apache CloseableHttpClient to use
     * <p>
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param httpClient the http client
     * @param shouldRetry callback routine to determine if failed requests can be retried
     */
    public DefaultHttpClient(CloseableHttpClient httpClient, ShouldRetry shouldRetry) {
        this.httpClient = Util.throwIfNull(httpClient);
        this.userAgent = generateUserAgent(getClass());
        this.shouldRetry = shouldRetry;
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

            // Set HTTP headers
            if (smartsheetRequest.getHeaders() != null) {
                for (Map.Entry<String, String> header : smartsheetRequest.getHeaders().entrySet()) {
                    apacheHttpRequest.addHeader(header.getKey(), header.getValue());
                }
            }

            // Set User Agent
            apacheHttpRequest.setHeader(HttpHeaders.USER_AGENT, userAgent);

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

                // Set request headers to values ACTUALLY SENT (not just created by us)
                HttpRequestWrapper actualRequest = (HttpRequestWrapper) context.getAttribute("http.request");
                if (actualRequest != null) {
                    apacheHttpRequest.setHeaders(actualRequest.getAllHeaders());
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
                    if (!shouldRetry.shouldRetry(++attempt, timeSpent, smartsheetResponse)) {
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

    /**
     *
     * @param clazz the Class used to find the Package (depends on the jar MANIFEST from which this class was loaded)
     * @return a User-Agent string that represents this version of the SDK (along with platform info)
     */
    private static String generateUserAgent(Class<?> clazz) {
        String thisVersion = "";
        String title = "";
        Package thisPackage = clazz.getPackage();
        if (thisPackage != null) {
            thisVersion = thisPackage.getImplementationVersion();
            title = thisPackage.getImplementationTitle();
        }
        return title + "/" + thisVersion + " " + System.getProperty("os.name") + " "
                + System.getProperty("java.vm.name") + " " + System.getProperty("java.vendor") + " "
                + System.getProperty("java.version");
    }
}