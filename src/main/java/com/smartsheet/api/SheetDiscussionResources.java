package com.smartsheet.api;

import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.DiscussionInclusion;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

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
public interface SheetDiscussionResources {

    /**
     * <p>Create a discussion on a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/discussions</p>
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
    public Discussion createDiscussion(long sheetId, Discussion discussion) throws SmartsheetException;

    /**
     * <p>Create a discussion with attachments on a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/discussions</p>
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
    public Discussion createDiscussionWithAttachment(long sheetId, Discussion discussion, File file, String contentType) throws SmartsheetException, IOException;

    /**
     * <p>Get a discussion.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/discussions/{discussionId}</p>
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
     * <p>Delete discussion.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/discussions/{discussionId}</p>
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
    public void deleteDiscussion(long sheetId, long discussionId) throws SmartsheetException;

    /**
     * <p>Get all discussions</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/discussions</p>
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
     * @param pagination the pagination parameters
     * @param includes the optional paramters to include
     * @return a list of discussions
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Discussion> listDiscussions(long sheetId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException;

    /**
     * <p>Creates an object of DiscussionCommentResources for access to discussion comments through SheetDiscussionResources.</p>
     *
     * @return the created DiscussionCommentResources object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public DiscussionCommentResources commentResources() throws SmartsheetException;

    /**
     * <p>Creates an object of DiscussionAttachmentResources for access to discussion attachments through SheetDiscussionResources.</p>
     *
     * @return the created DiscussionAttachmentResources object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public DiscussionAttachmentResources attachmentResources() throws SmartsheetException;
}
