package com.smartsheet.api.internal.http;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Error;
import org.apache.commons.io.IOUtils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidHttpClient implements HttpClient {

    /** logger for general errors, warnings, etc */
    protected static final Logger logger = LoggerFactory.getLogger(AndroidHttpClient.class);

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    /**
     * Represents the underlying OkHttpClient.
     * <p>
     * It will be initialized in constructor and will not change afterwards.
     * </p>
     */
    private final OkHttpClient client;

    /** The okhttp http response. */
    private Response currentResponse;

    protected JsonSerializer jsonSerializer;

    protected long maxRetryTimeMillis = 15000;

    /**
     * Constructor.
     */
    public AndroidHttpClient() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.jsonSerializer = new JacksonJsonSerializer();
    }

    /**
     * Log to the SLF4J logger (level based upon response status code). Override this function to add logging
     * or capture performance metrics.
     *
     * @param request request
     * @param response response
     * @param durationMillis response time in ms
     * @throws IOException
     */
    public void logRequest(Request request, HttpResponse response, long durationMillis) {
        logger.info("{} {}, Response Code:{}, Request completed in {} ms", request.method(), request.url(),
                response.getStatusCode(), durationMillis);
        if (response.getStatusCode() != 200) {
            // log the request and response on error
            try {
                logger.warn(this.currentResponse.peekBody(4096).string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Make an HTTP request and return the response.
     *
     * @param smartsheetRequest the smartsheet request
     * @return the HTTP response
     * @throws HttpClientException the HTTP client exception
     */
    @Override
    public HttpResponse request(HttpRequest smartsheetRequest) throws HttpClientException {
        Util.throwIfNull(smartsheetRequest);
        if (smartsheetRequest.getUri() == null) {
            throw new IllegalArgumentException("A Request URI is required.");
        }

        int attempt = 0;
        long start = System.currentTimeMillis();

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

        HttpResponse smartsheetResponse;
        while(true) {

            // Create our new request
            Request.Builder builder = new Request.Builder();
            try {
                builder.url(smartsheetRequest.getUri().toURL());
            } catch (MalformedURLException e) {
                throw new HttpClientException("Error occurred.", e);
            }

            // Clone our headers to request
            for (Map.Entry<String, String> entry : smartsheetRequest.getHeaders().entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }

            try {
                switch (smartsheetRequest.getMethod()) {
                    case GET:
                        builder.get();
                        break;
                    case POST:
                            builder.post(getRequestBody(smartsheetRequest));
                        break;
                    case PUT:
                        builder.put(getRequestBody(smartsheetRequest));
                        break;
                    case DELETE:
                        builder.delete();
                        break;
                }
            } catch (IOException e) {
                throw new HttpClientException("Error occurred.", e);
            }

            // mark the body so we can reset on retry
            if(canRetryRequest && bodyStream != null) {
                bodyStream.mark((int)smartsheetRequest.getEntity().getContentLength());
            }

            try {
                // Create API request
                Request request = builder.build();
                long startTime = System.currentTimeMillis();
                this.currentResponse = client.newCall(request).execute();
                long endTime = System.currentTimeMillis();

                smartsheetResponse = new HttpResponse();
                smartsheetResponse.setStatusCode(this.currentResponse.code());
                if (this.currentResponse.body().contentLength() != 0) {
                    // Package response details
                    HttpEntity entity = new HttpEntity();
                    entity.setContentType(this.currentResponse.body().contentType().toString());
                    entity.setContentLength(this.currentResponse.body().contentLength());
                    entity.setContent(this.currentResponse.body().byteStream());
                    smartsheetResponse.setEntity(entity);
                }

                long responseTime = endTime - startTime;
                logRequest(request, smartsheetResponse, responseTime);

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
                this.releaseConnection();

            } catch (IOException ex) {
                throw new HttpClientException("Error occurred.", ex);
            }
        }
        return smartsheetResponse;
    }

    private RequestBody getRequestBody(HttpRequest apiRequest) throws IOException {
        return RequestBody.create(MEDIA_TYPE_JSON, IOUtils.toByteArray(apiRequest.getEntity().getContent()));
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
        if (contentType != null && !contentType.startsWith("application/json")) {
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
     */
    @Override
    public void close() {
        this.client.connectionPool().evictAll();
    }

    /* (non-Javadoc)
     * @see com.smartsheet.api.internal.http.HttpClient#releaseConnection()
     */
    @Override
    public void releaseConnection() {
        this.closeCurrentResponse();
    }

    private void closeCurrentResponse() {
        Response response = this.currentResponse;
        if (response != null) {
            if (response.body() != null) {
                response.body().close();
            }
            this.currentResponse = null;
        }
    }
}
