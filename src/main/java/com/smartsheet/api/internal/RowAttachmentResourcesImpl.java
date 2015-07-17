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
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.PaginationParameters;

public class RowAttachmentResourcesImpl extends AbstractResources implements RowAttachmentResources{

    public RowAttachmentResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * <p>Attach a URL to a comment.</p>
     *
     * <p>The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
     * Box.com URL (attachmentType "BOX_COM").</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     *   POST /sheets/{sheetId}/rows/{rowId}/attachments
     *
     * @param sheetId the sheet id
     * @param rowId the row id
     * @param attachment the attachment object
     * @return the created attachment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Attachment attachUrl(long sheetId, long rowId, Attachment attachment) throws SmartsheetException
    {
        return this.createResource("sheets/" + sheetId + "/rows/" + rowId + "/attachments", Attachment.class, attachment);
    }

    /**
     * Get row attachment.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}/attachments
     *
     * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param rowId the row id
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public DataWrapper<Attachment> getAttachments(long sheetId, long rowId, PaginationParameters parameters) throws SmartsheetException {
        String path= "sheets/" + sheetId + "/rows/" + rowId + "/attachments";
        if (parameters != null) {
            path += parameters.toQueryString();
        }
        return this.listResourcesWithWrapper(path, Attachment.class);
    }
}
