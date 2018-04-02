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
import com.smartsheet.api.models.Error;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.Closeable;

/**
 * This interface defines methods to make an HTTP request.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface HttpClient extends Closeable {
    /**
     * Make an HTTP request and return the response.
     *
     * Parameters: - request : the HTTP request
     *
     * Returns: the HTTP response
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - HttpClientException : if there is any other
     * error occurred during the operation
     *
     * @param request the request
     * @return the http response
     * @throws HttpClientException the http client exception
     */
    public HttpResponse request(HttpRequest request) throws HttpClientException;

    /**
     * Allocate an Apache request object based upon the request method specified in smartsheetRequest.
     * Override this method to inject headers or set proxy information in request
     *
     * @param smartsheetRequest (request method and base URI come from here)
     * @return the Apache request
     */
    public HttpRequestBase createApacheRequest(HttpRequest smartsheetRequest);

    /**
     * The backoff calculation routine. Uses exponential backoff. If the maximum elapsed time
     * has expired, this calculation returns -1 causing the caller to fall out of the retry loop.
     *
     * @param previousAttempts
     * @param totalElapsedTimeMillis
     * @param error
     * @return -1 to fall out of retry loop, positive number indicates backoff time
     */
    public long calcBackoff(int previousAttempts, long totalElapsedTimeMillis, Error error);

    /**
     * Called when an API request fails to determine if it can retry the request.
     * Calls calcBackoff to determine the time to wait in between retries.
     *
     * @param previousAttempts number of attempts (including this one) to execute request
     * @param totalElapsedTimeMillis total time spent in millis for all previous (and this) attempt
     * @param response the failed HttpResponse
     * @return true if this request can be retried
     */
    public boolean shouldRetry(int previousAttempts, long totalElapsedTimeMillis, HttpResponse response);

    /**
     * Release connection.
     */
    public void releaseConnection();

    /**
     * set the traces for this client
     * @param traces the fields to include in trace-logging
     */
    public void setTraces(Trace... traces);

    /**
     * set whether to use nicely-formatted JSON or more compact format JSON in trace logging
     * @param pretty whether to print JSON in a "pretty" format or compact
     */
    public void setTracePrettyPrint(boolean pretty);
}
