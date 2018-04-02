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
/**
 * <p>This interface provides methods to access Sheet Comment resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface SheetCommentResources {
    /**
     * <p>Get a comment.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/comments/{commentId}</p>
     *
     * @param sheetId the ID of the sheet
     * @param commentId the ID of the comment
     * @return the comment (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Comment getComment(long sheetId, long commentId) throws SmartsheetException;

    /**
     * <p>Delete a comment.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/comments/{commentId}</p>
     *
     * @param sheetId the ID of the sheet
     * @param commentId the ID of the comment
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteComment(long sheetId, long commentId) throws SmartsheetException;

    /**
     * <p>Creates an object of CommentAttachmentResources.</p>
     *
     * @return the created attachment
     * @throws SmartsheetException if there is any other error during the operation
     */
    public com.smartsheet.api.CommentAttachmentResources attachmentResources() throws SmartsheetException;
}
