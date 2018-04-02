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
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * <p>This interface provides methods to access RowAttachment resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface RowAttachmentResources{

    /**
     * <p>Attach a URL to a comment.</p>
     *
     * <p>The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
     * Box.com URL (attachmentType "BOX_COM").</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/attachments
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
    public Attachment attachUrl(long sheetId, long rowId, Attachment attachment) throws SmartsheetException;

    /**
     * <p>Get row attachment.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}/attachments</p>
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
     * @param parameters the pagination parameters
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Attachment> getAttachments(long sheetId, long rowId, PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>Attach a file to a row with simple upload.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/attachments</p>
     *
     * @param sheetId the id of the sheet
     * @param rowId the id of the comment
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
    public Attachment attachFile(long sheetId, long rowId, File file, String contentType) throws FileNotFoundException, SmartsheetException;

    /**
     * <p>Attach file for simple upload.</p>
     *
     * @param sheetId the sheet id
     * @param rowId the row id
     * @param inputStream the attachment data inputStream
     * @param contentType the content type
     * @param contentLength the content length
     * @param attachmentName the name of the attachment
     * @return the attachment
     * @throws SmartsheetException the smartsheet exception
     */
    public Attachment attachFile(long sheetId, long rowId, InputStream inputStream, String contentType, long contentLength, String attachmentName) throws SmartsheetException;
}
