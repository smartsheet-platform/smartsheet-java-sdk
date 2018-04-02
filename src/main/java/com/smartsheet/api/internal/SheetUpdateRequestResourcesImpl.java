package com.smartsheet.api.internal;
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

import java.util.HashMap;

import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SheetUpdateRequestResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.SentUpdateRequest;
import com.smartsheet.api.models.Sheet;
import com.smartsheet.api.models.UpdateRequest;

public class SheetUpdateRequestResourcesImpl extends AbstractResources implements SheetUpdateRequestResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SheetUpdateRequestResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }
    
    /**
     * Gets a list of all Update Requests that have future schedules associated with the specified Sheet.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/updaterequests
     *
     * @param paging the object containing the pagination parameters
     * @return A list of all UpdateRequests (note that an empty list will be returned if there are none).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<UpdateRequest> listUpdateRequests(long sheetId, PaginationParameters paging) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/updaterequests";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (paging != null) {
            parameters = paging.toHashMap();
        }
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, UpdateRequest.class);
    }

    /**
     * Gets the specified Update Request for the Sheet that has a future schedule.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/updaterequests/{updateRequestId}
     *
     * @param sheetId the Id of the sheet
     * @param updateRequestId the update request Id
     * @return the update request resource (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public UpdateRequest getUpdateRequest(long sheetId, long updateRequestId) throws SmartsheetException {
        return this.getResource("sheets/" + sheetId + "/updaterequests/" + updateRequestId, UpdateRequest.class);
    }

    /**
     * Creates an Update Request for the specified Row(s) within the Sheet. An email notification
     * (containing a link to the update request) will be asynchronously sent to the specified recipient(s).
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/updaterequests
     *
     * @param sheetId the Id of the sheet
     * @param updateRequest the update request object
     * @return the update request resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public UpdateRequest createUpdateRequest(long sheetId, UpdateRequest updateRequest) throws SmartsheetException {
        return this.createResource("sheets/" + sheetId + "/updaterequests", UpdateRequest.class, updateRequest);
    }

    /**
     * Terminates the future scheduled delivery of the Update Request specified in the URL.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/updaterequests/{updateRequestId}
     *
     * @param sheetId the Id of the sheet
     * @param updateRequestId the update request Id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteUpdateRequest(long sheetId, long updateRequestId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/updaterequests/" + updateRequestId, UpdateRequest.class);
    }

    /**
     * Changes the specified Update Request for the Sheet.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/updaterequests/{updateRequestId}
     *
     * @param sheetId the Id of the sheet
     * @param updateRequest the update request object
     * @return the update request resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public UpdateRequest updateUpdateRequest(long sheetId, UpdateRequest updateRequest) throws SmartsheetException {
        return this.updateResource("sheets/" + sheetId + "/updaterequests/" + updateRequest.getId(),
                UpdateRequest.class, updateRequest);
    }

    /**
     * Gets a list of all Sent Update Requests that have future schedules associated with the specified Sheet.
     *
     * It mirrors To the following Smartsheet REST API method: GET /sheets/{sheetId}/sentupdaterequests
     *
     * @param sheetId the Id of the sheet
     * @param paging the object containing the pagination parameters
     * @return A list of all UpdateRequests (note that an empty list will be returned if there are none).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<SentUpdateRequest> listSentUpdateRequests(long sheetId, PaginationParameters paging) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/sentupdaterequests";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (paging != null) {
            parameters = paging.toHashMap();
        }
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, SentUpdateRequest.class);
    }

    /**
     * Gets the specified sent update request on the Sheet.
     *
     * It mirrors To the following Smartsheet REST API method: GET /sheets/{sheetId}/sentupdaterequests/{updateRequestId}
     *
     * @param sheetId the Id of the sheet
     * @param sentUpdateRequestId the sent update request Id
     * @return the sent update request resource (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SentUpdateRequest getSentUpdateRequest(long sheetId, long sentUpdateRequestId) throws SmartsheetException {
        return this.getResource("sheets/" + sheetId + "/sentupdaterequests/" + sentUpdateRequestId, 
                SentUpdateRequest.class);
    }

    /**
     * Deletes the specified sent update request.
     *
     * It mirrors To the following Smartsheet REST API method: DELETE /sheets/{sheetId}/sentupdaterequests/{sentUpdateRequestId}
     *
     * @param sheetId the Id of the sheet
     * @param sentUpdateRequestId the sent update request Id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteSentUpdateRequest(long sheetId, long sentUpdateRequestId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/sentupdaterequests/" + sentUpdateRequestId, SentUpdateRequest.class);
    }
}
