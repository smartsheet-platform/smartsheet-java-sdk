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
import com.smartsheet.api.*;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Comment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * This is the implementation of the DiscussionCommentResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionCommentResourcesImpl extends AbstractResources implements DiscussionCommentResources{

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public DiscussionCommentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Add a comment to a discussion.
     *
     * It mirrors to the following Smartsheet REST API method: POST /discussion/{discussionId}/comments</p>
     *
     * @param sheetId the sheet id
     * @param discussionId the discussion id
     * @param comment the comment to add
     * @return the created comment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Comment addComment(long sheetId, long discussionId, Comment comment) throws SmartsheetException{
        return this.createResource("sheets/" + sheetId + "/discussions/" + discussionId + "/comments", Comment.class, comment);
    }

    /**
     * Add a comment to a discussion with an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: POST /discussion/{discussionId}/comments
     *
     * @param sheetId the sheet id
     * @param discussionId the dicussion id
     * @param comment the comment to add
     * @param file the file to be attached
     * @param contentType the type of file
     * @return the created comment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     * @throws IOException is there is any error with file
     */
    public Comment addCommentWithAttachment(long sheetId, long discussionId, Comment comment, File file, String contentType) throws SmartsheetException, IOException{
        String path = "sheets/" + sheetId + "/discussions/" + discussionId + "/comments";
        Util.throwIfNull(sheetId, comment, file, contentType);

        return this.addCommentWithAttachment(path, comment, new FileInputStream(file), contentType, file.getName());
    }

    private Comment addCommentWithAttachment(String path, Comment comment, InputStream inputStream, String contentType, String attachmentName) throws SmartsheetException{
        return this.createResourceWithAttachment(path, Comment.class, comment, "comment", inputStream , contentType, attachmentName);
    }

    /**
     * Update the specified comment
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/comments/{commentId}

     * @param sheetId the sheet id
     * @param comment the new comment object
     * @return the updated comment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Comment updateComment(long sheetId, Comment comment) throws SmartsheetException {
        return this.updateResource("sheets/" + sheetId + "/comments/" + comment.getId(), Comment.class, comment);
    }
}
