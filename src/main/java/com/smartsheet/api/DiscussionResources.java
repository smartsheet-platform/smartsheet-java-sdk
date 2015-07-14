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
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.PaginationParameters;

/**
 * <p>This interface provides methods to access Discussion resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface DiscussionResources {
	
	/**
	 * <p>Get a discussion.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * GET /sheets/{sheetId}/discussions/{discussionId}</p>
	 *
	 * @param discussionId the discussion id
	 * @param sheetId the sheet id
	 * @return the discussion (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Discussion getDiscussion(long sheetId, long discussionId) throws SmartsheetException;

	/**
	 * <p>Add a comment to a discussion.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * POST /discussion/{discussionId}/comments</p>
	 *
	 * @param id the discussion id
	 * @param comment the comment to add
	 * @return the created comment
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Comment addDiscussionComment(long id, Comment comment) throws SmartsheetException;

	/**
	 * <p>Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Discussion resources.</p>
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments();

	/**
	 * Create discussion on a row.
	 *
	 * It mirrors to the following Smartsheet REST API method: /sheets/{sheetId}/rows/{rowId}/discussions
	 *
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheetId the sheet ID
	 * @param rowId the row ID
	 * @param discussion the comment to add, limited to the following required attributes: text
	 * @return the created comment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Discussion createDiscussionOnRow(long sheetId, long rowId, Discussion discussion) throws SmartsheetException;

	/**
	 * Delete discussion.
	 *
	 * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/discussions/{discussionId}
	 *
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheetId the sheet ID
	 * @param discussionId the discussion ID
	 * @return the created comment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void deleteDiscussion(long sheetId, long discussionId) throws SmartsheetException;

	public DataWrapper<Discussion> getAllDiscussions(long sheetId, PaginationParameters parameters) throws SmartsheetException;
}
