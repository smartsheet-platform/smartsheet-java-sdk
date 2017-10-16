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
import com.smartsheet.api.DiscussionCommentResources;
import com.smartsheet.api.DiscussionResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Comment;

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
     * Represents the DiscussionCommentResources.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private DiscussionCommentResources comments;

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
     * Return the DiscussionCommentResources object that provides access to attachment resources associated with
     * Discussion resources.
     *
     * @return the associated attachment resources
     */
    public DiscussionCommentResources comments() {
        return this.comments;
    }
}
