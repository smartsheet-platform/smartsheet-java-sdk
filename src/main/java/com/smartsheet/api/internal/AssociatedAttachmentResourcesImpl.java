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

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.models.Attachment;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @deprecated As of release 2.0
 */
@Deprecated
public class AssociatedAttachmentResourcesImpl extends AbstractAssociatedResources 
    implements AssociatedAttachmentResources {

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public AssociatedAttachmentResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
        super(smartsheet, masterResourceType);
    }

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public List<Attachment> listAttachments(long objectId){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public Attachment attachFile(long objectId, File file, String contentType){
        throw new UnsupportedOperationException();
    }


    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public Attachment attachFile(long objectId, InputStream inputStream, String contentType, long contentLength, String attachmentName){
        throw new UnsupportedOperationException();
    }


    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public Attachment attachURL(long objectId, Attachment attachment){
        throw new UnsupportedOperationException();
    }

}
