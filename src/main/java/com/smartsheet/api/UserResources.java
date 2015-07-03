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



import com.smartsheet.api.models.*;

import java.util.List;
import java.util.Set;

/**
 * <p>This interface provides methods to access User resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface UserResources {

	/**
	 * <p>List all users.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /users</p>
	 *
	 * @return the list of all users
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public DataWrapper<User> listUsers() throws SmartsheetException;

	/**
	 * <p>List all users.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: GET /users</p>
	 *
	 * @param email the list of email addresses
	 * @param pagination object containing pagination query parameters
	 * @return the list of all users
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public DataWrapper<User> listUsers(Set<String> email, PaginationParameters pagination) throws SmartsheetException;

	/**
	 * <p>Add a user to the organization, without sending email.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /users</p>
	 *
	 * @param user the user object
	 * @return the user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public User addUser(User user) throws SmartsheetException;

	/**
	 * <p>Add a user to the organization, without sending email.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /users</p>
	 *
	 * @param user the user
	 * @param sendEmail the send email flag
	 * @return the user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public User addUser(User user, boolean sendEmail) throws SmartsheetException;

	/**
	 * <p>Get the current user.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /users/{userId}</p>
	 *
	 * @param userId the user id
	 * @return the current user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public UserProfile getUser(long userId) throws SmartsheetException;

	/**
	 * <p>Get the current user.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: GET /user/me</p>
	 *
	 * @return the current user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public UserProfile getCurrentUser() throws SmartsheetException;

	/**
	 * <p>Update a user.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: PUT /user/{id}</p>
	 *
	 * @param user the user to update
	 * @return the updated user
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public User updateUser(User user) throws SmartsheetException;
	
	/**
	 * <p>Delete a user in the organization.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: DELETE /user/{id}</p>
	 *
	 * @param id the id of the user
	 * @param parameters the object containing parameters for deleting users
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void deleteUser(long id, DeleteUserParameters parameters) throws SmartsheetException;
}
