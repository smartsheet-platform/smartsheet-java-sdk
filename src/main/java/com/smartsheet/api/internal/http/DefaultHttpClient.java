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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartsheet.api.Trace;
import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is the Apache HttpClient (http://hc.apache.org/httpcomponents-client-ga/index.html) based HttpClient
 * implementation.
 * <p>
 * Thread Safety: This class is thread safe because it is immutable and the underlying Apache CloseableHttpClient is
 * thread safe.
 */
public class DefaultHttpClient implements HttpClient {
    // to avoid creating new sets for each call (we use sets for perf reasons)
    private static final Set<Trace> REQUEST_BODY_ONLY = Collections.singleton(Trace.RequestBody);
    private static final Set<Trace> REQUEST_ALL_PARTS = new TreeSet<Trace>(Arrays.asList(Trace.RequestHeaders, Trace.RequestBody));
    private static final Set<Trace> RESPONSE_BODY_ONLY = Collections.singleton(Trace.ResponseBody);
    private static final Set<Trace> RESPONSE_ALL_PARTS = new TreeSet<Trace>(Arrays.asList(Trace.ResponseHeaders, Trace.ResponseBody));
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper(); // thread-safe (don't change config after first use)

    /**
     * Represents the underlying Apache CloseableHttpClient.
     * <p>
     * It will be initialized in constructor and will not change afterwards.
     */
    private final CloseableHttpClient httpClient;

    /**
     * The apache http request.
     */
    private HttpRequestBase apacheHttpRequest;

    /**
     * The apache http response.
     */
    private CloseableHttpResponse apacheHttpResponse;

    /**
     * UserAgent string sent with each request
     */
    private final String userAgent;

    private Set<Trace> traces = new TreeSet<Trace>();

    @Deprecated // never used (within SDK)
    public static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    /**
     * Constructor.
     */
    public DefaultHttpClient() {
        this(HttpClients.createDefault());
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
    public DefaultHttpClient(CloseableHttpClient httpClient) {
        Util.throwIfNull(httpClient);
        this.httpClient = httpClient;
        this.userAgent = generateUserAgent(getClass());
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

        HttpResponse smartsheetResponse = new HttpResponse();

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

        // Set HTTP entity
        final HttpEntity entity = smartsheetRequest.getEntity();
        if (apacheHttpRequest instanceof HttpEntityEnclosingRequestBase && entity != null && entity.getContent() != null) {
            // we need access to the original stream so we can log it - once it's wrapped we can't touch it
            logRequest(apacheHttpRequest, entity);
            InputStreamEntity streamEntity = new InputStreamEntity(entity.getContent(), entity.getContentLength());
            streamEntity.setChunked(false);    // why?  not supported by library?
            ((HttpEntityEnclosingRequestBase) apacheHttpRequest).setEntity(streamEntity);
        } else {
            logRequest(apacheHttpRequest, null);
        }

        // Make the HTTP request
        try {
            apacheHttpResponse = this.httpClient.execute(apacheHttpRequest);

            // Set returned headers
            smartsheetResponse.setHeaders(new HashMap<String, String>());
            for (org.apache.http.Header header : apacheHttpResponse.getAllHeaders()) {
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
            }
            logResponse(apacheHttpRequest, smartsheetResponse);

            // log request and response (if so configured)
            if (traces.size() > 0) {
                RequestAndResponseData requestAndResponseData = RequestAndResponseData.of(
                        apacheHttpRequest, entity, smartsheetResponse, smartsheetResponse.getEntity(), traces);
                JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValue(System.out, requestAndResponseData);
            }
        } catch (ClientProtocolException e) {
            throw new HttpClientException("Error occurred.", e);
        } catch (IOException e) {
            throw new HttpClientException("Error occurred.", e);
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
            if (trace == Trace.Request) {
                this.traces.addAll(REQUEST_ALL_PARTS);
            } else if (trace == Trace.Response) {
                this.traces.addAll(RESPONSE_ALL_PARTS);
            } else {
                this.traces.add(trace);
            }
        }
    }

    @Override
    public Set<Trace> getTraces() {
        return Collections.unmodifiableSet(traces);
    }

    static String generateUserAgent(Class<?> clazz) {
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

    private static void logRequest(HttpRequestBase request, HttpEntity entity) {
        // lazy-eval to allow for any config changes before first request is sent
        Logger requestLogger = LoggerFactory.getLogger(HttpClient.class.getName() + ".request");
        if (requestLogger.isTraceEnabled()) {
            StringBuilder buf = new StringBuilder();
            append(buf, request, entity, REQUEST_ALL_PARTS);
            requestLogger.trace("Request ({}) - {}", System.identityHashCode(request), buf.toString());
        } else if (requestLogger.isInfoEnabled()) {
            StringBuilder buf = new StringBuilder();
            append(buf, request, null, REQUEST_BODY_ONLY);
            requestLogger.info("Request ({}) - {}", System.identityHashCode(request), buf.toString());
        }
    }

    private static void logResponse(HttpRequestBase request, HttpResponse response) {
        // lazy-eval to allow for any config changes before first response is received
        Logger responseLogger = LoggerFactory.getLogger(HttpClient.class.getName() + ".response");
        if (response.getStatusCode() != 200) {
            StringBuilder buf = new StringBuilder();
            // verbose logging of non-OK responses
            append(buf, response, response.getEntity(), RESPONSE_ALL_PARTS);
            responseLogger.warn("Response ({}) - {}", System.identityHashCode(request), buf.toString());
        } else if (responseLogger.isTraceEnabled()) {
            StringBuilder buf = new StringBuilder();
            append(buf, response, response.getEntity(), RESPONSE_ALL_PARTS);
            responseLogger.trace("Response ({}) - {}", System.identityHashCode(request), buf.toString());
        } else if (responseLogger.isInfoEnabled()) {
            StringBuilder buf = new StringBuilder();
            append(buf, response, null, RESPONSE_BODY_ONLY);
            responseLogger.info("Response ({}) - {}", System.identityHashCode(request), buf.toString());
        }
    }

    private static void append(StringBuilder buf, HttpRequestBase request, HttpEntity entity, Set<Trace> traces) {
        buf.append("{ request:'").append(request.getRequestLine()).append("',");
        if (traces.contains(Trace.RequestHeaders)) {
            StringBuilder headerBuf = new StringBuilder();
            Header[] headers = request.getAllHeaders();
            for (Header header : headers) {
                headerBuf.append(',').append(header.getName()).append(':');
                if ("Authorization".equals(header.getName())) {
                    headerBuf.append("Bearer ***");
                } else {
                    headerBuf.append(Arrays.toString(header.getElements()));
                }
            }
            buf.append(", headers:[").append(headerBuf.substring(1)).append("]");
        }
        if (entity != null) {
            buf.append(", content:");
            append(buf, entity);
        }
        buf.append("}");
    }

    private static void append(StringBuilder buf, HttpResponse response, HttpEntity entity, Set<Trace> traces) {
        buf.append("{ status:").append(response.getStatusCode());
        if (traces.contains(Trace.ResponseHeaders)) {
            buf.append(", headers:").append(response.getHeaders());
        }
        if (entity != null) {
            buf.append(", entity:");
            append(buf, entity);
        }
        buf.append(" }");
    }

    private static void append(StringBuilder buf, HttpEntity entity) {
        String contentAsText = null;
        try {
            InputStream inputStream = entity.getContent();
            if (inputStream.markSupported()) {
                inputStream.mark(0);
            }
            byte[] contentBytes = StreamUtil.readBytesFromStream(inputStream);
            try {
                contentAsText = new String(contentBytes, "UTF-8");
            } catch (UnsupportedEncodingException badEncodingOrNotText) {
                contentAsText = new String(Hex.encodeHex(contentBytes));
                logger.info("failed to create string with contentType '{}' from bytes '{}'",
                        entity.getContentType(), contentAsText);
            }
            // since we've consumed the stream we have to reset it (note, this will have real perf impact if the stream
            // was to a large file or something else we'd rather not hold entirely in RAM if we can help it)
            if (inputStream.markSupported()) {
                inputStream.reset();
            } else {
                entity.setContent(new ByteArrayInputStream(contentBytes));
            }

        } catch (IOException iox) {
            logger.error("failed to extract content from response - {}", iox);
        }

        buf.append("{ contentType:").append(entity.getContentType())
                .append(", contentLen:").append(entity.getContentLength())
                .append(", content:").append(contentAsText)
                .append(" }");
    }
}
