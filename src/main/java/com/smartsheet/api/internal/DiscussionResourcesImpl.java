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



import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.DiscussionResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.*;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the DiscussionResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionResourcesImpl extends AbstractResources implements DiscussionResources {
	/**
	 * Represents the AssociatedAttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private AssociatedAttachmentResources attachments;

	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public DiscussionResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

	/**
	 * Get a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/discussions/{discussionId}
	 * 
	 * Parameters: - discussionId : the ID
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions:
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheetId the sheetId
	 * @param discussionId the discussionId
	 * @return the discussion
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Discussion getDiscussion(long sheetId, long discussionId) throws SmartsheetException {
		return this.getResource("sheets/" + sheetId + "/discussions/" + discussionId, Discussion.class);
	}

	/**
	 * Add a comment to a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /discussion/{discussionId}/comments
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the discussion ID
	 * @param comment the comment to add, limited to the following required attributes: text
	 * @return the created comment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Comment addDiscussionComment(long id, Comment comment) throws SmartsheetException {
		return this.createResource("discussion/" + id + "/comments", Comment.class, comment);
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Discussion resources.
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments() {
		return this.attachments;
	}

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
	public void deleteDiscussion(long sheetId, long discussionId) throws SmartsheetException{
		this.deleteResource("sheets/" + sheetId + "/discussions/" + discussionId, Discussion.class);
	}

	/**
	 * Get all discussions.
	 *
	 * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/discussions
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
	 * @param pagination the pagination pagination
	 * @param includes the optional include parameters
	 * @return all the discussions
	 * @throws SmartsheetException the smartsheet exception
	 */
	public DataWrapper<Discussion> getAllDiscussions(long sheetId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException{
		String path = "sheets/" + sheetId + "/discussions";
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
		path += QueryUtil.generateUrl(null, parameters);

		if (pagination != null) {
			path += pagination.toQueryString();
		}

		return this.listResourcesWithWrapper(path, Discussion.class);
	}
}
