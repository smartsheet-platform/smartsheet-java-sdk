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
import com.smartsheet.api.models.Share;

import java.util.List;

/**
 * <p>This interface provides methods to access Share resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface ShareResources {

    /**
     * <p>List shares of a given object.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    GET /workspace/{id}/shares </p>
     * <p>    GET /sheet/{id}/shares</p>
     *
     * @param objectId the object id
     * @param parameters the pagination parameters
     * @return the list of Share objects (note that an empty list will be returned if there is none).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    @Deprecated
    public PagedResult<Share> listShares(long objectId, PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>List shares of a given object.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    GET /workspace/{id}/shares </p>
     * <p>    GET /sheet/{id}/shares</p>
     *
     * @param objectId the object id
     * @param parameters the pagination parameters
     * @param includeWorkspaceShares include workspace shares in enumeration
     * @return the list of Share objects (note that an empty list will be returned if there is none).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Share> listShares(long objectId, PaginationParameters parameters, Boolean includeWorkspaceShares) throws SmartsheetException;

    /**
     * <p>Get a Share.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    GET /workspaces/{workspaceId}/shares/{shareId}</p>
     * <p>    GET /sheets/{sheetId}/shares/{shareId}</p>
     * <p>    GET /reports/{reportId}/shares</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param objectId the ID of the object to share
     * @param shareId the ID of the share
     * @return the share (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Share getShare(long objectId, String shareId) throws SmartsheetException;

    /**
     * <p>Shares the object with the specified Users and Groups.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    POST /workspaces/{id}/shares </p>
     * <p>    POST /sheets/{id}/shares</p>
     * <p>    POST /reports/{reportId}/shares</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if multiShare is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param objectId the ID of the object to share
     * @param shares list of share objects
     * @param sendEmail whether to send email
     * @return the created shares
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Share> shareTo(long objectId, List<Share> shares, Boolean sendEmail) throws SmartsheetException;

    /**
     * <p>Update a share.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    PUT /workspaces/{workspaceId}/shares/{shareId}</p>
     * <p>    PUT /sheets/{sheetId}/shares/{shareId}</p>
     * <p>    PUT /reports/{reportId}/shares/{shareId}</p>
     *
     * @param objectId the ID of the object to share
     * @param share the share
     * @return the updated share (note that if there is no such resource, this method will throw
     *  ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Share updateShare(long objectId, Share share) throws SmartsheetException;

    /**
     * <p>Delete a share.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>    DELETE /workspaces/{workspaceId}/shares/{shareId} </p>
     * <p>    DELETE /sheets/{sheetId}/shares/{shareId} </p>
     * <p>    DELETE /reports/{reportId}/shares/{shareId}</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param objectId the ID of the object to share
     * @param shareId the ID of the share to delete
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteShare(long objectId, String shareId) throws SmartsheetException;
}
