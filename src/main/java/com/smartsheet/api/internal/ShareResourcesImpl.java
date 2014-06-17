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
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.MultiShare;
import com.smartsheet.api.models.Share;

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
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/sharesWithGroups GET /sheet/{id}/sharesWithGroups
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
	 * @return the shares (note that empty list will be returned if there is none)
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Share> listShares(long objectId) throws SmartsheetException {
		return this.listResources(getMasterResourceType() + "/" + objectId + "/sharesWithGroups", Share.class);
	}

	/**
	 * Get a Share.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/sharewithgroups/{shareId} GET
	 * /sheet/{id}/sharewithgroups/{shareId}
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
	 * @param userId the ID of the user to whome the object is shared
	 * @return the share (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Share getShare(long objectId, String shareId) throws SmartsheetException{
		return this.getResource(getMasterResourceType() + "/" + objectId + "/sharewithgroups/" + shareId, Share.class);
	}

	/**
	 * Share the object, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/sharesWithGroups POST /sheet/{id}/sharesWithGroups
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if share is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param objectId the ID of the object to share - share : the share object
	 * @param share the share
	 * @return the created share
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Share shareTo(long objectId, Share share) throws SmartsheetException {
		return this.createResource(getMasterResourceType() + "/" + objectId + "/sharesWithGroups", Share.class, share);
	}

	/**
	 * Share the object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/sharesWithGroups POST /sheet/{id}/sharesWithGroups
	 * 
	 * Returns: the created share
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if share is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param objectId the id of the object to share
	 * @param share the share object
	 * @param sendEmail whether to send email
	 * @return the created share
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Share shareTo(long objectId, Share share, boolean sendEmail) throws SmartsheetException {
		return this.createResource(getMasterResourceType() + "/" + objectId + "/sharesWithGroups?sendEmail=" + sendEmail,
				Share.class, share);
	}

	/**
	 * Share the object with multiple users, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/multisharewithgroups POST
	 * /sheet/{id}/multisharewithgroups
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
	 * @param multiShare the MultiShare object
	 * @return the created shares
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare) throws SmartsheetException {
		return this.postAndReceiveList(getMasterResourceType() + "/" + objectId + "/multisharewithgroups", multiShare,
				Share.class);
	}

	/**
	 * Share the object with multiple users.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/multishare POST
	 * /sheet/{id}/multishare
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
	 * @param multiShare the MultiShare object
	 * @param sendEmail whether to send email
	 * @return the created shares
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare, boolean sendEmail) throws SmartsheetException {
		return this.postAndReceiveList(getMasterResourceType() + "/" + objectId + "/multisharewithgroups?sendEmail=" + sendEmail,
				multiShare, Share.class);
	}

	@Override
	public Share updateShare(long objectId, String shareId, Share share) throws SmartsheetException {
		return this.updateResource(getMasterResourceType() + "/" + objectId + "/sharewithgroups/" + shareId, Share.class, share);
	}

	/**
	 * Delete a share.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /workspace/{id}/sharewithgroups/{userId} DELETE
	 * /sheet/{id}/sharewithgroups/{userId}
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
	 * @param userId the ID of the user to whom the object is shared
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void deleteShare(long objectId, String shareId) throws SmartsheetException {
		this.deleteResource(getMasterResourceType() + "/" + objectId + "/sharewithgroups/" + shareId, Share.class);
	}
}
