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


import com.smartsheet.api.AttachmentResources;
import com.smartsheet.api.models.Attachment;

import java.io.File;
import java.io.InputStream;

/**
 * @deprecated As of release 2.0
 * This is the implementation of the AttachmentResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
@Deprecated
public class AttachmentResourcesImpl extends AbstractResources implements AttachmentResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public AttachmentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * @deprecated As of release 2.0
     * @return the attachment
     */
    @Deprecated
    public Attachment attachNewVersion(long attachmentId, File file, String contentType){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0
     * @return the attachment
     */
    @Deprecated
    public Attachment attachNewVersion (long attachmentId, InputStream inputStream, String contentType, long contentLength, String attachmentName)
    {
        throw new UnsupportedOperationException();
    }
}
