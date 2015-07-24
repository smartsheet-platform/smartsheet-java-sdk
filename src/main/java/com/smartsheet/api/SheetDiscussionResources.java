package com.smartsheet.api;

import com.smartsheet.api.models.Discussion;

import java.io.File;
import java.io.IOException;

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
public interface SheetDiscussionResources {

    /**
     * <p>Create a discussion on a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * POST /sheets/{sheetId}/discussions</p>
     *
     * @param sheetId the sheet id
     * @param discussion the discussion object
     * @return the created discussion
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Discussion createDiscussion(long sheetId, Discussion discussion) throws SmartsheetException;

    /**
     * <p>Create a discussion with attachments on a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * POST /sheets/{sheetId}/discussions</p>
     *
     * @param sheetId the sheet id
     * @param discussion the discussion object
     * @param file the file to attach
     * @param contentType the type of file
     * @return the created discussion
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Discussion createDiscussionWithAttachment(long sheetId, Discussion discussion, File file, String contentType) throws SmartsheetException, IOException;
}
