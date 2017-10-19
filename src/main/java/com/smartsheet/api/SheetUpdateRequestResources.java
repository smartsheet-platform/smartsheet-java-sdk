package com.smartsheet.api;

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

import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.SentUpdateRequest;
import com.smartsheet.api.models.UpdateRequest;
public interface SheetUpdateRequestResources {

    /**
     * <p>Gets a list of all Update Requests that have future schedules associated with the specified Sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/updaterequests</p>
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
    PagedResult<UpdateRequest> listUpdateRequests(long sheetId, PaginationParameters paging) throws SmartsheetException;

    /**
     * <p>Gets the specified Update Request for the Sheet that has a future schedule.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/updaterequests/{updateRequestId}</p>
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
    UpdateRequest getUpdateRequest(long sheetId, long updateRequestId) throws SmartsheetException;

    /**
     * <p>Creates an Update Request for the specified Row(s) within the Sheet. An email notification
     * (containing a link to the update request) will be asynchronously sent to the specified recipient(s).</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/updaterequests</p>
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
    UpdateRequest createUpdateRequest(long sheetId, UpdateRequest updateRequest) throws SmartsheetException;

    /**
     * <p>Terminates the future scheduled delivery of the Update Request specified in the URL.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/updaterequests/{updateRequestId}</p>
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
    void deleteUpdateRequest(long sheetId, long updateRequestId) throws SmartsheetException;

    /**
     * <p>Changes the specified Update Request for the Sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/updaterequests/{updateRequestId}</p>
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
    UpdateRequest updateUpdateRequest(long sheetId, UpdateRequest updateRequest) throws SmartsheetException;

    /**
     * <p>Gets a list of all Sent Update Requests that have future schedules associated with the specified Sheet.</p>
     *
     * <p>It mirrors To the following Smartsheet REST API method: GET /sheets/{sheetId}/sentupdaterequests</p>
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
    PagedResult<SentUpdateRequest> listSentUpdateRequests(long sheetId, PaginationParameters paging) throws SmartsheetException;

    /**
     * <p>Gets the specified sent update request on the Sheet.</p>
     *
     * <p>It mirrors To the following Smartsheet REST API method: GET /sheets/{sheetId}/sentupdaterequests/{updateRequestId}</p>
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
    SentUpdateRequest getSentUpdateRequest(long sheetId, long sentUpdateRequestId) throws SmartsheetException;

    /**
     * <p>Deletes the specified sent update request.</p>
     *
     * <p>It mirrors To the following Smartsheet REST API method: DELETE /sheets/{sheetId}/sentupdaterequests/{sentUpdateRequestId}</p>
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
    void deleteSentUpdateRequest(long sheetId, long sentUpdateRequestId) throws SmartsheetException;
}
