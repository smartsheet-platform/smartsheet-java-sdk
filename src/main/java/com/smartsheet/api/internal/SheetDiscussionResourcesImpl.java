package com.smartsheet.api.internal;

import com.smartsheet.api.*;
import com.smartsheet.api.DiscussionAttachmentResources;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.DiscussionInclusion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.HashMap;

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
public class SheetDiscussionResourcesImpl extends  AbstractResources implements SheetDiscussionResources {
    DiscussionAttachmentResources attachments;
    DiscussionCommentResources comments;

    /**
     * Constructor.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     */
    public SheetDiscussionResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.attachments = new DiscussionAttachmentResourcesImpl(smartsheet);
        this.comments = new DiscussionCommentResourcesImpl(smartsheet);
    }

    /**
     * Create a discussion on a sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/discussions
     *
     * @param sheetId the sheet id
     * @param discussion the discussion object
     * @return the created discussion
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Discussion createDiscussion(long sheetId, Discussion discussion) throws SmartsheetException{
        Util.throwIfNull(sheetId, discussion);
        return this.createResource("sheets/" + sheetId + "/discussions",
                Discussion.class, discussion);
    }

    /**
     * Create a discussion with attachments on a sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/discussions
     *
     * @param sheetId the sheet id
     * @param discussion the discussion object
     * @param file the file to attach
     * @param contentType the type of file
     * @return the created discussion
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     * @throws IOException is there is with file
     */
    public Discussion createDiscussionWithAttachment(long sheetId, Discussion discussion, File file, String contentType) throws SmartsheetException, IOException{
        Util.throwIfNull(discussion, file, contentType);
        String path = "sheets/" + sheetId + "/discussions";

        return this.createDiscussionWithAttachment(path, discussion, new FileInputStream(file), contentType, file.getName());
    }

    private Discussion createDiscussionWithAttachment(String path, Discussion discussion, InputStream inputStream, String contentType, String attachmentName) throws SmartsheetException{
        return this.createResourceWithAttachment(path, Discussion.class, discussion, "discussion", inputStream, contentType, attachmentName);
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
    public PagedResult<Discussion> listDiscussions(long sheetId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException{
        String path = "sheets/" + sheetId + "/discussions";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, Discussion.class);
    }

    /**
     * Creates an object of DiscussionCommentResources for access to discussion comments through SheetDiscussionResources.
     *
     * @return the created DiscussionCommentResources object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public DiscussionCommentResources commentResources() throws SmartsheetException{
        return this.comments;
    }

    /**
     * Creates an object of DiscussionAttachmentResources for access to discussion attachments through SheetDiscussionResources.
     *
     * @return the created DiscussionAttachmentResources object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public DiscussionAttachmentResources attachmentResources() throws SmartsheetException{
        return this.attachments;
    }
}
