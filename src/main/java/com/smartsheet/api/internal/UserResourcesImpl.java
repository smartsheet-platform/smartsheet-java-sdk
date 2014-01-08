package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.UserResources;
import com.smartsheet.api.models.User;
import com.smartsheet.api.models.UserProfile;

/**
 * This is the implementation of the UserResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class UserResourcesImpl implements UserResources {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public UserResourcesImpl(SmartsheetImpl smartsheet) {
	}

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
	 * Implementation: return this.listResources("users", User.class);
	 * 
	 * @return
	 */
	public List<User> listUsers() {
		return null;
	}

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
	 * Implementation: return this.createResource("users", User.class, user);
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		return null;
	}

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
	 * Implementation: return this.createResources("users?sendEmail=" + sendEmail, User.class, user);
	 * 
	 * @param sendEmail
	 * @param user
	 * @return
	 */
	public User addUser(User user, boolean sendEmail) {
		return null;
	}

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
	 * 
	 * Implementation: return this.getResource("user/me", UserProfile.class);
	 * 
	 * @return
	 */
	public UserProfile getCurrentUser() {
		return null;
	}

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
	 * Implementation: return this.updateResource("user/" + user.getId(), User.class, user);
	 * 
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		return null;
	}

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
	 * Implementation: return this.deleteResource("user/" + id, User.class);
	 * 
	 * @param id
	 */
	public void deleteUser(long id) {
	}
}
