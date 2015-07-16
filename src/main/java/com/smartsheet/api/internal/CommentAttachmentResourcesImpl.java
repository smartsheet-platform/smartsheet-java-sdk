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
import com.smartsheet.api.models.Attachment;

/**
 * This is the implementation of the CommentAttachmentResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentAttachmentResourcesImpl extends AbstractResources implements com.smartsheet.api.CommentAttachmentResources{

    public CommentAttachmentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    public Attachment attachUrl(long sheetId, long commentId, Attachment attachment) throws SmartsheetException
    {
        return this.createResource("sheets/" + sheetId + "/comments/" + commentId + "/attachments", Attachment.class, attachment);
    }
}
