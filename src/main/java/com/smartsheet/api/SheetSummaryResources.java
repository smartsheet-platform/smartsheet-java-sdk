package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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


import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.SummaryFieldExclusion;
import com.smartsheet.api.models.enums.SummaryFieldInclusion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface SheetSummaryResources {

    /**
     * <p>Gets the sheet summary</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{id}/summary</p>
     *
     * @param sheetId the sheet id
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the sheet summary
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SheetSummary getSheetSummary(long sheetId, EnumSet<SummaryFieldInclusion> includes,
                                        EnumSet<SummaryFieldExclusion> excludes) throws SmartsheetException;

    /**
     * <p>Gets the sheet summary fields</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{id}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @param pagination pagination parameters for the response
     * @return the list of sheet summary fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<SummaryField> getSheetSummaryFields(long sheetId, EnumSet<SummaryFieldInclusion> includes,
                                                           EnumSet<SummaryFieldExclusion> excludes,
                                                           PaginationParameters pagination) throws SmartsheetException;

    /**
     * <p>Insert fields into a sheet summary.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param fields list of fields to be added
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return the created fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<SummaryField> addSheetSummaryFields(long sheetId, List<SummaryField> fields, Boolean renameIfConflict) throws SmartsheetException;

    /**
     * <p>Insert fields into a sheet summary.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param fields list of fields to be added
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return a bulk item result containing the created fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public BulkItemResult<SummaryField> addSheetSummaryFieldsWithPartialSuccess(long sheetId, List<SummaryField> fields,
                                                                                Boolean renameIfConflict) throws SmartsheetException;

    /**
     * <p>Update fields in a sheet summary.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param fields list of summary fields to be updated
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return the updated fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<SummaryField> updateSheetSummaryFields(long sheetId, List<SummaryField> fields, Boolean renameIfConflict) throws SmartsheetException;

    /**
     * <p>Update fields in a sheet summary.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param fields list of summary fields to be updated
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return a bulk item result containing the updated fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public BulkItemResult<SummaryField> updateSheetSummaryFieldsWithPartialSuccess(long sheetId, List<SummaryField> fields,
                                                                                   Boolean renameIfConflict) throws SmartsheetException;

    /**
     * <p>Delete fields in a sheet summary.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/summary/fields</p>
     *
     * @param sheetId the sheet id
     * @param fieldIds List of field Ids
     * @param ignoreSummaryFieldsNotFound true if the call should ignore fields not found
     * @return List of field Ids deleted
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Long> deleteSheetSummaryFields(long sheetId, Set<Long> fieldIds, Boolean ignoreSummaryFieldsNotFound) throws SmartsheetException;

    /**
     * <p>Adds an image to the sheet summary field.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields/{fieldId}/images</p>
     *
     * @param sheetId the sheet id
     * @param fieldId the summary field id
     * @param file path to image file to upload
     * @param contentType content-type of the file being uploaded
     * @param altText alternate text for the image
     * @return Result
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Result<SummaryField> addSheetSummaryFieldImage(long sheetId, long fieldId, String file, String contentType, String altText)
            throws SmartsheetException, FileNotFoundException;

    /**
     * Adds an image to the sheet summary field.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields/{fieldId}/images
     *
     * @param sheetId the sheet id
     * @param fieldId the summary field id
     * @param file File to upload
     * @param contentType content-type of the file being uploaded
     * @param altText alternate text for the image
     * @return Result
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Result<SummaryField> addSheetSummaryFieldImage(long sheetId, long fieldId, File file, String contentType, String altText) throws SmartsheetException, FileNotFoundException;

    /**
     * Adds an image to the sheet summary field.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields/{fieldId}/images
     *
     * @param sheetId the sheet id
     * @param fieldId the summary field id
     * @param inputStream File to upload
     * @param contentType content-type of the file being uploaded
     * @param contentLength content length
     * @param altText alternate text for the image
     * @return Result
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Result<SummaryField> addSheetSummaryFieldImage(long sheetId, long fieldId, InputStream inputStream, String contentType,
                                                          long contentLength, String altText) throws SmartsheetException, FileNotFoundException;
}
