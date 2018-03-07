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


import com.smartsheet.api.models.Attachment;

import java.io.File;
import java.io.InputStream;

/**
 * This is the implementation of the AssociatedAttachmentResources for discussions.
 * 
 * It extends AssociatedAttachmentResourcesImpl and overrides attachFile/attachURL methods by throwing
 * UnsupportedOperationException (since they're not supported for discussions).
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionAttachmentResources extends AssociatedAttachmentResourcesImpl {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     */
    public DiscussionAttachmentResources(SmartsheetImpl smartsheet) {
        super(smartsheet, "discussion");
    }

    /**
     * Attach a file to the object.
     *
     * It mirrors to the following Smartsheet REST API method:
     *     POST /sheet/{id}/attachments
     *     POST /row/{id}/attachments
     *     POST /comment/{id}/attachments
     *
     * Returns: the created attachment
     *
     * Exceptions:
     *   UnsupportedOperationException : this exception is always thrown since this method is not supported by
     *   discussion resources.
     *
     * @param objectId the object id
     * @param file the file to attach
     * @param contentType the content type of the file
     * @return the attachment
     */
    public Attachment attachFile(long objectId, File file, String contentType) {
        throw new UnsupportedOperationException("Attachments can only be attached to comments, not discussions.");
    }

    /**
     * Throws an UnsupportedOperationException.
     */
    public Attachment attachFile(long objectId, InputStream inputStream, String contentType, long contentLength, String fileName) {
        throw new UnsupportedOperationException("Attachments can only be attached to comments, not discussions.");
    }
}
