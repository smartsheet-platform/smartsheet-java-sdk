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

/**
 * @deprecated As of release 2.0
 * <p>This interface provides methods to access Attachment resources by their id.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
@Deprecated
public interface AttachmentResources {

    /**
     * @deprecated As of release 2.0
     * @param attachmentId the id
     * @param file the file
     * @param contentType the content type
     * @return the attachment (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     */
    @Deprecated
    public Attachment attachNewVersion(long attachmentId, File file, String contentType);

    /**
     * @deprecated As of release 2.0
     * @param attachmentId the id of the attachment to upload a new version.
     * @param inputStream the file to attach
     * @param contentType the content type of the file
     * @param attachmentName attachment name
     * @param contentLength content length
     * @return the created attachment
     */
    @Deprecated
    public Attachment attachNewVersion (long attachmentId, InputStream inputStream, String contentType, long contentLength, String attachmentName);
}
