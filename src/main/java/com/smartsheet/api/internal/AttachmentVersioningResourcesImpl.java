package com.smartsheet.api.internal;

import com.smartsheet.api.*;
import com.smartsheet.api.models.Attachment;
import com.sun.javafx.geom.transform.SingularMatrixException;

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
public class AttachmentVersioningResourcesImpl extends AbstractResources implements AttachmentVersioningResources {

    /**
     * Constructor.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     */
    public AttachmentVersioningResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * <p>Deletes an attachment, including all of its versions.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * DELETE /sheets/{sheetId}/attachments/{attachmentId}/versions</p>
     *
     * @param sheetId the sheet id
     * @param attachentId the attachment id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteAllVersions(long sheetId, long attachentId) throws SmartsheetException{
        this.deleteResource("sheets/"+ sheetId+ "/attachments/"+ attachentId + "/versions", Attachment.class);
    }
}
