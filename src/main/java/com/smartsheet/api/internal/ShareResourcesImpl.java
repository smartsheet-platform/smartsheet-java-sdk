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

import com.smartsheet.api.*;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Share;

import java.util.HashMap;
import java.util.List;

/**
 * This is the implementation of the ShareResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class ShareResourcesImpl extends AbstractAssociatedResources implements ShareResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     * @param masterResourceType the master resource type (e.g. "sheet", "workspace")
     */
    public ShareResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
        super(smartsheet, masterResourceType);
    }

    /**
     * List shares of a given object.
     *
     * It mirrors to the following Smartsheet REST API method:
     *     GET /workspace/{id}/shares
     *     GET /sheet/{id}/shares
     *     GET /sights/{id}/shares
     *     GET /reports/{id}/shares
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param objectId the id of the object to share.
     * @param pagination the pagination parameters
     * @param includeWorkspaceShares include workspace shares in enumeration
     * @return the shares (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Share> listShares(long objectId, PaginationParameters pagination) throws SmartsheetException {
        return this.listShares(objectId, pagination, false);
    }

    public PagedResult<Share> listShares(long objectId, PaginationParameters pagination, Boolean includeWorkspaceShares) throws SmartsheetException {
        String path = getMasterResourceType() + "/" + objectId + "/shares";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        if (includeWorkspaceShares != null && includeWorkspaceShares == true) {
            parameters.put("include", "workspaceShares");
        }
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, Share.class);
    }

    /**
     * Get a Share.
     *
     * It mirrors to the following Smartsheet REST API method:
     *     GET /workspaces/{workspaceId}/shares/{shareId}
     *     GET /sheets/{sheetId}/shares/{shareId}
     *     GET /sights/{sightId}/shares
     *     GET /reports/{reportId}/shares
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
    public Share getShare(long objectId, String shareId) throws SmartsheetException{
        return this.getResource(getMasterResourceType() + "/" + objectId + "/shares/" + shareId, Share.class);
    }

    /**
     * Shares the object with the specified Users and Groups.
     *
     * It mirrors to the following Smartsheet REST API method:
     *     POST /workspaces/{id}/shares
     *     POST /sheets/{id}/shares
     *     POST /sights/{id}/shares
     *     POST /reports/{reportId}/shares
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
    public List<Share> shareTo(long objectId, List<Share> shares, Boolean sendEmail) throws SmartsheetException {
        String path = getMasterResourceType() + "/" + objectId + "/shares";
        if (sendEmail != null){
            path += "?sendEmail=" + sendEmail;
        }
        return this.postAndReceiveList(path, shares, Share.class);
    }

    /**
     * <p>Update a share.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:</p>
     * <p>PUT /workspaces/{workspaceId}/shares/{shareId}</p>
     * <p>PUT /sheets/{sheetId}/shares/{shareId}</p>
     * <p>PUT /sights/{sheetId}/shares/{shareId}</p>
     * <p>PUT /reports/{reportId}/shares/{shareId}</p>
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
    public Share updateShare(long objectId, Share share) throws SmartsheetException {
        Util.throwIfNull(share);
        return this.updateResource(getMasterResourceType() + "/" + objectId + "/shares/" + share.getId(), Share.class, share);
    }

    /**
     * Delete a share.
     *
     * It mirrors to the following Smartsheet REST API method:
     *     DELETE /workspaces/{workspaceId}/shares/{shareId}
     *     DELETE /sheets/{sheetId}/shares/{shareId}
     *     DELETE /sights/{sheetId}/shares/{shareId}
     *     DELETE /reports/{reportId}/shares/{shareId}
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
    public void deleteShare(long objectId, String shareId) throws SmartsheetException {
        this.deleteResource(getMasterResourceType() + "/" + objectId + "/shares/" + shareId, Share.class);
    }
}
