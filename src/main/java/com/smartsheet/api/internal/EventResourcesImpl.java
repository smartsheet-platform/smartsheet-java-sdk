package com.smartsheet.api.internal;

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


import com.smartsheet.api.*;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Date;

/**
 * This is the implementation of the SheetResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class EventResourcesImpl extends AbstractResources implements EventResources {

    /**
     * Constructor.
     * <p>
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public EventResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * <p>List all events.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /events</p>
     *
     * @param since Starting time for events to return. You must pass in a value for either since or
     *              streamPosition and never both.
     * @param streamPosition Indicates next set of events to return. Use value of nextStreamPosition returned
     *                       from the previous call. You must pass in a value for either since or streamPosition
     *                       and never both.
     * @param maxCount Maximum number of events to return as response to this call. Must be between
     *                 1 through 10,000 (inclusive).
     * @param numericDates If true, dates are accepted and returned in Unix epoch time (milliseconds since midnight
     *                     on January 1, 1970 in UTC time). Default is false, which means ISO-8601 format
     * @return A list of all events (note that an empty list will be returned if there are none).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public EventResult listEvents(Object since, String streamPosition, Integer maxCount,
                                  Boolean numericDates) throws SmartsheetException {
        String path = "events";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if(since instanceof Date) {
            String isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(since);
            parameters.put("since", isoDate);
        }
        else {
            parameters.put("since", since);
        }
        parameters.put("streamPosition", streamPosition);
        parameters.put("maxCount", maxCount);
        parameters.put("numericDates", numericDates);

        path += QueryUtil.generateUrl(null, parameters);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);

        EventResult obj = null;
        try {
            HttpResponse response = this.smartsheet.getHttpClient().request(request);
            switch (response.getStatusCode()) {
                case 200:
                    obj = this.smartsheet.getJsonSerializer().deserializeEventResult(response.getEntity().getContent());
                    break;
                default:
                    handleError(response);
            }
        } finally {
            smartsheet.getHttpClient().releaseConnection();
        }

        return obj;
    }
}
