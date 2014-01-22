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



import java.util.List;

import com.smartsheet.api.ShareResources;
import com.smartsheet.api.models.MultiShare;
import com.smartsheet.api.models.Share;

/**
 * This is the implementation of the ShareResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class ShareResourcesImpl extends AbstractResources implements ShareResources {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl - masterResourceType : the master resource type (e.g. "sheet",
	 * "workspace").
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet, masterResourceType);
	 * 
	 * @param masterResourceType
	 * @param smartsheet
	 */
	public ShareResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
		super(smartsheet);
	}

	/**
	 * List shares of a given object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/shares GET /sheet/{id}/shares
	 * 
	 * 
	 * Parameters: - objectId : the ID of the object to share
	 * 
	 * Returns: the shares (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.listResources(getMasterResourceType() + "/" + objectId + "/shares", Share.class);
	 * 
	 * @param objectId
	 * @return
	 */
	public List<Share> listShares(long objectId) {
		return null;
	}

	/**
	 * Get a Share.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/share/{userId} GET
	 * /sheet/{id}/share/{userId}
	 * 
	 * Parameters: - objectId : the ID of the object to share - userId : the ID of the user to whom the object is shared
	 * 
	 * Returns: the share (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.getResource(getMasterResourceType() + "/" + objectId + "/share/" + userId,
	 * Share.class);
	 * 
	 * @param objectId
	 * @param userId
	 * @return
	 */
	public Share getShare(long objectId, long userId) {
		return null;
	}

	/**
	 * Share the object, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/shares POST /sheet/{id}/shares
	 * 
	 * Parameters: - objectId : the ID of the object to share - share : the share object
	 * 
	 * Returns: the created share
	 * 
	 * Exceptions: - IllegalArgumentException : if share is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource(getMasterResourceType() + "/" + objectId + "/shares", Share.class,
	 * share);
	 * 
	 * @param share
	 * @param objectId
	 * @return
	 */
	public Share shareTo(long objectId, Share share) {
		return null;
	}

	/**
	 * Share the object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/shares POST /sheet/{id}/shares
	 * 
	 * Parameters: - objectId : the ID of the object to share - share : the share object - sendEmail : whether to send
	 * email
	 * 
	 * Returns: the created share
	 * 
	 * Exceptions: - IllegalArgumentException : if share is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource(getMasterResourceType() + "/" + objectId + "/shares?sendEmail=" +
	 * sendEmail, Share.class, share);
	 * 
	 * @param share
	 * @param objectId
	 * @param sendEmail
	 * @return
	 */
	public Share shareTo(long objectId, Share share, boolean sendEmail) {
		return null;
	}

	/**
	 * Share the object with multiple users, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/multishare POST
	 * /sheet/{id}/multishare
	 * 
	 * Parameters: - objectId : the ID of the object to share - multiShare : the MultiShare object
	 * 
	 * Returns: the created shares
	 * 
	 * Exceptions: - IllegalArgumentException : if multiShare is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.postAndReceiveList(getMasterResourceType() + "/" + objectId + "/multishare",
	 * multiShare, Share.class);
	 * 
	 * @param objectId
	 * @param multiShare
	 * @return
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare) {
		return null;
	}

	/**
	 * Share the object with multiple users.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/multishare POST
	 * /sheet/{id}/multishare
	 * 
	 * Parameters: - objectId : the ID of the object to share - multiShare : the MultiShare object - sendEmail : whether
	 * to send email
	 * 
	 * Returns: the created shares
	 * 
	 * Exceptions: - IllegalArgumentException : if multiShare is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.postAndReceiveList(getMasterResourceType() + "/" + objectId +
	 * "/multishare?sendEmail=" + sendEmail, multiShare, Share.class);
	 * 
	 * @param objectId
	 * @param sendEmail
	 * @param multiShare
	 * @return
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare, boolean sendEmail) {
		return null;
	}

	/**
	 * Update a share.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /workspace/{id}/share/{userId} PUT
	 * /sheet/{id}/share/{userId}
	 * 
	 * 
	 * Parameters: - objectId : the ID of the object to share - userId : the ID of the user to whom the object is shared
	 * - share : the share
	 * 
	 * Returns: the updated share (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if share is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.updateResource(getMasterResourceType() + "/" + objectId + "/share/" + userId,
	 * Share.class, share);
	 * 
	 * @param share
	 * @param objectId
	 * @param userId
	 * @return
	 */
	public Share updateShare(long objectId, long userId, Share share) {
		return null;
	}

	/**
	 * Delete a share.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /workspace/{id}/share/{userId} DELETE
	 * /sheet/{id}/share/{userId}
	 * 
	 * 
	 * Parameters: - objectId : the ID of the object to share - userId : the ID of the user to whom the object is shared
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: this.deleteResource(getMasterResourceType() + "/" + objectId + "/share/" + userId, Share.class);
	 * 
	 * @param objectId
	 * @param userId
	 */
	public void deleteShare(long objectId, long userId) {
	}
}
