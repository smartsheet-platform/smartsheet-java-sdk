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
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/**
 * This is the implementation of the AssociatedAttachmentResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
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
     * Deletes an attachment, including all of its versions.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/attachments/{attachmentId}/versions
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

    /**
     * Get all versions of an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/attachments/{attachmentId}/versions
     *
     * @param sheetId the id
     * @param attachmentId the attachment id
     * @param parameters the pagination paramaters
     * @return the attachment (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Attachment> listAllVersions(long sheetId, long attachmentId, PaginationParameters parameters) throws SmartsheetException {
        String path = "sheets/"+ sheetId + "/attachments/" + attachmentId + "/versions";

        if (parameters != null) {
            path += parameters.toQueryString();
        }
        return this.listResourcesWithWrapper(path, Attachment.class);
    }

    /**
     * Attach a new version of an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: POST /attachment/{id}/versions
     *
     * @param sheetId the id of the sheet
     * @param attachmentId the id of the attachment to upload a new version.
     * @param file the file to attach
     * @param contentType the content type of the file
     * @return the created attachment
     * @throws FileNotFoundException the file not found exception
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Attachment attachNewVersion(long sheetId ,long attachmentId, File file, String contentType) throws FileNotFoundException, SmartsheetException {
        Util.throwIfNull(attachmentId, file, contentType);
        Util.throwIfEmpty(contentType);

        return attachNewVersion(sheetId ,attachmentId, new FileInputStream(file), contentType, file.length(), file.getName());
    }

    /**
     * Attach a new version of an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: POST /attachment/{id}/versions
     *
     * @param sheetId the id of the sheet
     * @param attachmentId the id of the object
     * @param inputStream the {@link InputStream} of the file to attach
     * @param contentType the content type of the file
     * @param contentLength the size of the file in bytes.
     * @param attachmentName the name of the file.
     * @return the created attachment
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    private Attachment attachNewVersion (long sheetId, long attachmentId, InputStream inputStream, String contentType, long contentLength, String attachmentName)
            throws SmartsheetException {
        return super.attachFile("sheets/"+ sheetId + "/attachments/"+ attachmentId +"/versions", inputStream, contentType, contentLength, attachmentName);
    }
}
