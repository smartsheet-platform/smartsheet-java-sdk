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
 * @deprecated As of release 2.0
 */
@Deprecated
public interface CommentResources {
    /**
     * @deprecated As of release 2.0
     * @param sheetId the id
     * @param commentId the commentid
     * @return the comment (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     */
    @Deprecated
    public Comment getComment(long sheetId, long commentId) ;

    /**
     * @deprecated As of release 2.0
     * @param sheetId the id
     * @param commentId the commentid
     */
    @Deprecated
    public void deleteComment(long sheetId, long commentId);

    /**
     * @deprecated As of release 2.0
     * @return associated resources
     */
    @Deprecated
    public AssociatedAttachmentResources attachments();
}
