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

import com.smartsheet.api.SheetCommentResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Comment;
/**
 * This is the implementation of the SheetCommentResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetCommentResourcesImpl extends AbstractResources implements SheetCommentResources{

    private com.smartsheet.api.CommentAttachmentResources attachments;
    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SheetCommentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.attachments = new CommentAttachmentResourcesImpl(smartsheet);
    }

    /**
     * Get a comment.
     *
     * It mirrors to the following Smartsheet REST API method: GET /comment/{id}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the ID of the sheet
     * @param commentId the ID of the comment
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws SmartsheetException the smartsheet exception
     */
    public Comment getComment(long sheetId, long commentId) throws SmartsheetException {
        return this.getResource("sheets/" + sheetId + "/comments/" + commentId, Comment.class);
    }

    /**
     * Delete a comment.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /comment{id}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the ID of the sheet
     * @param commentId the ID of the comment
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteComment(long sheetId, long commentId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/comments/" + commentId, Comment.class);
    }

    /**
     * Creates an object of CommentAttachmentResources.
     *
     * @return the created attachment
     * @throws SmartsheetException if there is any other error during the operation
     */
    public com.smartsheet.api.CommentAttachmentResources attachmentResources() throws SmartsheetException{
        return attachments;
    }
}
