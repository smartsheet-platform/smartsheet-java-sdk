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
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.User;
import com.smartsheet.api.models.UserProfile;
import javafx.scene.control.Pagination;

import java.util.HashMap;
import java.util.List;

/**
 * This is the implementation of the UserResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class UserResourcesImpl extends AbstractResources implements UserResources {

	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public UserResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

	/**
	 * List all users.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /users
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param email the list of email addresses
	 * @param includeAll the include all flag
	 * @param pageSize the page size
	 * @param page the page
	 * @return all users (note that empty list will be returned if there is none)
	 * @throws SmartsheetException the smartsheet exception
	 */

	public DataWrapper<User> listUsers(List<String> email, boolean includeAll, Integer pageSize, Integer page) throws SmartsheetException {
		// TODO: update method to reflect changes to PaginationParameters.toHashMap();
		//PaginationParameters pagination = new PaginationParameters(includeAll, pageSize, page);
		//HashMap<String, String> parameters = pagination.toHashMap();

		return this.listResourcesWithWrapper("users", User.class);
	}

	/**
	 * Add a user to the organization, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /users
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param user the user object limited to the following attributes: * admin * email * licensedSheetCreator
	 * @return the user
	 * @throws SmartsheetException the smartsheet exception
	 */
	public User addUser(User user) throws SmartsheetException {
		return this.createResource("users", User.class, user);
	}

	/**
	 * Add a user to the organization, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /users
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param user the created user
	 * @param sendEmail whether to send email
	 * @return the user object limited to the following attributes: * admin * email * licensedSheetCreator
	 * @throws SmartsheetException the smartsheet exception
	 */
	public User addUser(User user, boolean sendEmail) throws SmartsheetException {
		return this.createResource("users?sendEmail=" + sendEmail, User.class, user);
	}

	/**
	 * <p>Get the current user.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /users/{userId}</p>
	 *
	 * @param userId the user id
	 * @return the user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public UserProfile getUser(long userId) throws SmartsheetException {
		return this.getResource("users/" + userId, UserProfile.class);
	}

	/**
	 * Get the current user.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /user/me 
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public UserProfile getCurrentUser() throws SmartsheetException {
		return this.getResource("user/me", UserProfile.class);
	}


	@Override
	public User updateUser(User user) throws SmartsheetException {
		return this.updateResource("users/" + user.getId(), User.class, user);
	}

	@Override
	public void deleteUser(long userId, long transferToId, boolean transferSheets, boolean removeFromSharing) throws SmartsheetException {
		this.deleteResource("users/" + userId + "?transferTo=" + transferToId + "&transferSheets=" + transferSheets + "&removeFromSharing="+removeFromSharing, User.class);
	}
}
