package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.*;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;

import java.io.*;
import java.util.HashMap;

public class PassthroughResourcesImpl extends AbstractResources implements PassthroughResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public PassthroughResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Issue an HTTP GET request.
     *
     * @param endpoint the API endpoint
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String getRequest(String endpoint, HashMap<String, Object> parameters) throws SmartsheetException {
        return passthroughRequest(HttpMethod.GET, endpoint, null, parameters);
    }

    /**
     * Issue an HTTP POST request.
     *
     * @param endpoint the API endpoint
     * @param payload a JSON payload string
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String postRequest(String endpoint, String payload, HashMap<String, Object> parameters) throws SmartsheetException {
        Util.throwIfNull(payload);
        return passthroughRequest(HttpMethod.POST, endpoint, payload, parameters);
    }

    /**
     * Issue an HTTP PUT request.
     *
     * @param endpoint the API endpoint
     * @param payload a JSON payload string
     * @param parameters optional list of resource parameters
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String putRequest(String endpoint, String payload,  HashMap<String, Object> parameters) throws SmartsheetException {
        Util.throwIfNull(payload);
        return passthroughRequest(HttpMethod.PUT, endpoint, payload, parameters);
    }

    /**
     * Issue an HTTP DELETE request.
     *
     * @param endpoint the API endpoint
     * @return a JSON response string
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public String deleteRequest(String endpoint) throws SmartsheetException {
        return passthroughRequest(HttpMethod.DELETE, endpoint, null, null);
    }

    /**
     * Passthrough request
     *
     * @param method HTTP method
     * @param endpoint the API endpoint (required)
     * @param payload optional JSON payload
     * @param parameters optional list of resource parameters
     * @return the result string
     * @throws SmartsheetException
     */
    private String passthroughRequest(HttpMethod method, String endpoint, String payload,
                                      HashMap<String, Object> parameters) throws SmartsheetException {
        Util.throwIfNull(endpoint);
        Util.throwIfEmpty(endpoint);

        if(parameters != null)
            endpoint += QueryUtil.generateUrl(null, parameters);

        HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(endpoint), method);

        if(payload != null) {
            HttpEntity entity = new HttpEntity();
            entity.setContentType("application/json");
            entity.setContent(new ByteArrayInputStream(payload.getBytes()));
            entity.setContentLength(payload.getBytes().length);
            request.setEntity(entity);
        }

        String res = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    String readLine;
                    try {
                        BufferedReader br;
                        StringBuilder sb = new StringBuilder();
                        br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        while ((readLine = br.readLine()) != null) {
                            sb.append(readLine);
                        }
                        br.close();
                        res = sb.toString();
                    }
                    catch (IOException e) {
                        res = null;
                    }
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }
        return res;
    }
}
