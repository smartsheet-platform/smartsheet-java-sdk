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



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.models.MultiShare;
import com.smartsheet.api.models.Share;

/**
 * This interface provides methods to access Share resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /workspace/{id}/shares GET /workspace/{id}/share/{userId} POST /workspace/{id}/shares POST
 * /workspace/{id}/multishare PUT /workspace/{id}/share/{userId} DELETE /workspace/{id}/share/{userId} GET
 * /sheet/{id}/shares GET /sheet/{id}/share/{userId} POST /sheet/{id}/shares POST /sheet/{id}/multishare PUT
 * /sheet/{id}/share/{userId} DELETE /sheet/{id}/share/{userId}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface ShareResources {
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
	 * @param objectId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Share> listShares(long objectId) throws SmartsheetException;

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
	 * @param objectId
	 * @param userId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Share getShare(long objectId, long userId) throws SmartsheetException;

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
	 * @param share
	 * @param objectId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Share shareTo(long objectId, Share share) throws SmartsheetException;

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
	 * @param share
	 * @param objectId
	 * @param sendEmail
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Share shareTo(long objectId, Share share, boolean sendEmail) throws SmartsheetException;

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
	 * @param objectId
	 * @param multiShare
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare) throws SmartsheetException;

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
	 * @param objectId
	 * @param sendEmail
	 * @param multiShare
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Share> shareTo(long objectId, MultiShare multiShare, boolean sendEmail) throws SmartsheetException;

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
	 * @param share
	 * @param objectId
	 * @param userId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Share updateShare(long objectId, long userId, Share share) throws SmartsheetException;

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
	 * @param objectId
	 * @param userId
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void deleteShare(long objectId, long userId) throws SmartsheetException;
}
