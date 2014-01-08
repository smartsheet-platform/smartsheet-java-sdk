package com.smartsheet.api;

import com.smartsheet.api.models.Discussion;

/**
 * This interface provides methods to access Discussion resources that are associated to a resource object.
 * 
 * Note that various Smartsheet resources support discussions.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * POST /sheet/{id}/discussions POST /row/{id}/discussions
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface AssociatedDiscussionResources {
	/**
	 * Create a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/discussions POST /row/{id}/discussions
	 * 
	 * Parameters: - objectId : the ID of the object - discussion : the discussion object limited to the following
	 * attributes: * title * comment
	 * 
	 * Returns: the created discussion
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param discussion
	 * @param objectId
	 * @return
	 */
	public Discussion createDiscussion(long objectId, Discussion discussion);
}
