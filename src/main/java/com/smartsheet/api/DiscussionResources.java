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



import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;

/**
 * This interface provides methods to access Discussion resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /comment/{id} DELETE /comment/{id} POST /comment/{id}/attachments
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface DiscussionResources {
	
	/**
	 * Get a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /discussion/{id}
	 * 
	 * Parameters: - id : the ID
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
	 * @param id the id
	 * @return the discussion
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Discussion getDiscussion(long id) throws SmartsheetException;

	/**
	 * Add a comment to a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /discussion/{discussionId}/comments
	 * 
	 * Parameters: - id : the discussion ID - commentt : the comment to add, limited to the following required
	 * attributes: * text
	 * 
	 * Returns: the created comment
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param comment the comment
	 * @return the comment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Comment addDiscussionComment(long id, Comment comment) throws SmartsheetException;

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Discussion resources.
	 * 
	 * Returns: the AssociatedAttachmentResources object
	 * 
	 * Exceptions: None
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments();
}
