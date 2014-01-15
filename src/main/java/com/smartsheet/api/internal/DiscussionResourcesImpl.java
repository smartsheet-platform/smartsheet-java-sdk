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
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;

/**
 * This is the implementation of the DiscussionResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionResourcesImpl implements DiscussionResources {
	/**
	 * Represents the AssociatedAttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private AssociatedAttachmentResources attachments;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet); this.attachments = new DiscussionAttachmentResources(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public DiscussionResourcesImpl(SmartsheetImpl smartsheet) {
	}

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
	 * Implementation: return this.getResource("discussion/" + id", Discussion.class);
	 * 
	 * @param id
	 * @return
	 */
	public Discussion getDiscussion(long id) {
		return null;
	}

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
	 * Implementation: return this.createResource("discussion/" + id + "/comments", Comment.class, comment);
	 * 
	 * @param id
	 * @param comment
	 * @return
	 */
	public Comment addDiscussionComment(long id, Comment comment) {
		return null;
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Discussion resources.
	 * 
	 * Returns: the AssociatedAttachmentResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.attachments;
	 * 
	 * @return
	 */
	public AssociatedAttachmentResources attachments() {
		return null;
	}
}
