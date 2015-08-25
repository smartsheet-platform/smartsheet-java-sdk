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


import com.smartsheet.api.models.Attachment;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @deprecated As of release 2.0
 */
@Deprecated
public interface AssociatedAttachmentResources {
    /**
     * @deprecated As of release 2.0
     * @param objectId the ID of the object to which the attachments are associated
     * @return the attachments (note that empty list will be returned if there is none)
     */
    @Deprecated
    public List<Attachment> listAttachments(long objectId);

    /**
     * @deprecated As of release 2.0
     * @param objectId the id of the object
     * @param file the file to attach
     * @param contentType the content type of the file
     * @return the created attachment
     */
    @Deprecated
    public Attachment attachFile(long objectId, File file, String contentType);

    /**
     * @deprecated As of release 2.0
     * @param objectId the id of the object
     * @param inputStream the file to attach
     * @param contentType the content type of the file
     * @param contentLength the size of the file in bytes.
     * @param attachmentName the name of the file.
     * @return the created attachment
     */
    @Deprecated
    public Attachment attachFile(long objectId, InputStream inputStream, String contentType, long contentLength, String attachmentName);

    /**
     * @deprecated As of release 2.0
     * @param objectId the object id
     * @param attachment the attachment object
     * @return the created attachment
     */
    @Deprecated
    public Attachment attachURL(long objectId, Attachment attachment);
}
