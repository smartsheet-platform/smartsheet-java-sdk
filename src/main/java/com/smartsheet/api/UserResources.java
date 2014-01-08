package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.User;
import com.smartsheet.api.models.UserProfile;

/**
 * This interface provides methods to access User resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /users GET /user/me POST /users PUT /user/{userId} DELETE /user/{userId}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface UserResources {
	/**
	 * List all users.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /users
	 * 
	 * Parameters: None
	 * 
	 * Returns: all users (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @return
	 */
	public List<User> listUsers();

	/**
	 * Add a user to the organization, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /users
	 * 
	 * Parameters: - user : the user object limited to the following attributes: * admin * email * licensedSheetCreator
	 * 
	 * Returns: the created user
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user);

	/**
	 * Add a user to the organization, without sending email.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /users
	 * 
	 * Parameters: - user : the user object limited to the following attributes: * admin * email * licensedSheetCreator
	 * - sendEmail : whether to send email
	 * 
	 * Returns: the created user
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param sendEmail
	 * @param user
	 * @return
	 */
	public User addUser(User user, boolean sendEmail);

	/**
	 * Get the current user.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /user/me
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * @return
	 */
	public UserProfile getCurrentUser();

	/**
	 * Update a user.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /user/{id}
	 * 
	 * Parameters: - user : the user to update limited to the following attributes : * admin * licensedSheetCreator
	 * 
	 * Returns: the updated user (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user);

	/**
	 * Delete a user in the organization.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /user{id}
	 * 
	 * Parameters: - id : the ID of the user
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
	 * @param id
	 */
	public void deleteUser(long id);
}
