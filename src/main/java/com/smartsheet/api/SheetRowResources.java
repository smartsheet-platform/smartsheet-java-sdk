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


import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ObjectExclusion;
import com.smartsheet.api.models.enums.RowCopyInclusion;
import com.smartsheet.api.models.enums.RowInclusion;
import com.smartsheet.api.models.enums.RowMoveInclusion;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * This interface provides methods to access row resources that are associated to a sheet object.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetRowResources {

    /**
     * <p>Insert rows to a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{id}/rows</p>
     *
     * @param sheetId the sheet id
     * @param rows the list of rows to create
     * @return the list of created rows
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Row> addRows(long sheetId, List<Row> rows) throws SmartsheetException;

    /**
     * <p>Insert rows to a sheet, allowing partial success. If a row cannot be inserted, it will fail, while the others may succeed..</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{id}/rows</p>
     *
     * @param sheetId the sheet id
     * @param rows the list of rows to create
     * @return the list of created rows
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PartialRowUpdateResult addRowsAllowPartialSuccess(long sheetId, List<Row> rows) throws SmartsheetException;

    /**
     * <p>Get a row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}</p>
     *
     * @param sheetId the id of the sheet
     * @param rowId the id of the row
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the created row (note that if there is no such resource, this method will throw ResourceNotFoundException rather
     * than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Row getRow(long sheetId, long rowId, EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException;

    /**
     * <p>@deprecated as of API 2.0.2 release, replaced by {@link #deleteRows(long, Set, boolean)}</p>
     *
     * <p>Delete a row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/rows/{rowId}</p>
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
     * @throws SmartsheetException the smartsheet exception
     */
    @Deprecated
    public void deleteRow(long sheetId, long rowId) throws SmartsheetException;

    /**
     * <p>@deprecated as of API V2.0.2, replaced by {@link #sendRows(long, MultiRowEmail)}</p>
     *
     * <p>Send a row via email to the designated recipients.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/emails</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the id of the sheet
     * @param rowId the id of the row
     * @param email the row email
     * @throws SmartsheetException the smartsheet exception
     */
    @Deprecated
    public void sendRow(long sheetId, long rowId, RowEmail email) throws SmartsheetException;

    /**
     * <p>Send a row via email to the designated recipients.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/emails</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the id of the sheet
     * @param email the multi row email
     * @throws SmartsheetException the smartsheet exception
     */
    public void sendRows(long sheetId, MultiRowEmail email) throws SmartsheetException;

    /**
     * <p>Deletes one or more row(s) from the Sheet specified in the URL.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/rows/{rowId}</p>
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
     * @param rowIds the row ids
     * @param ignoreRowsNotFound boolean for ignoring row ids not found
     * @return a list of deleted rows
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Long> deleteRows(long sheetId, Set<Long> rowIds, boolean ignoreRowsNotFound) throws SmartsheetException;

    /**
     * <p>Update rows.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the id of the sheet
     * @param rows the list of rows
     * @return a list of rows
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Row> updateRows(long sheetId, List<Row> rows) throws SmartsheetException;

    /**
     * <p>Update rows, but allow partial success. The PartialRowUpdateResult will contain the successful
     * rows and those that failed, with specific messages for each.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the id of the sheet
     * @param rows the list of rows
     * @return a list of rows
     * @throws SmartsheetException the smartsheet exception
     */
    public PartialRowUpdateResult updateRowsAllowPartialSuccess(long sheetId, List<Row> rows) throws SmartsheetException;

    /**
     * <p>Moves Row(s) from the Sheet specified in the URL to (the bottom of) another sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/move</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID to move
     * @param includes the parameters to include
     * @param ignoreRowsNotFound optional,specifying row Ids that do not exist within the source sheet
     * @param moveParameters   CopyOrMoveRowDirective object
     * @return the result object
     * @throws SmartsheetException the smartsheet exception
     */
    public CopyOrMoveRowResult moveRows(Long sheetId, EnumSet<RowMoveInclusion> includes, Boolean ignoreRowsNotFound, CopyOrMoveRowDirective moveParameters) throws SmartsheetException;

    /**
     * <p>Copies Row(s) from the Sheet specified in the URL to (the bottom of) another sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/move</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null, or path is empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID to move
     * @param includes the parameters to include
     * @param ignoreRowsNotFound optional,specifying row Ids that do not exist within the source sheet
     * @param copyParameters   CopyOrMoveRowDirective object
     * @return the result object
     * @throws SmartsheetException the smartsheet exception
     */
    public CopyOrMoveRowResult copyRows(Long sheetId, EnumSet<RowCopyInclusion> includes, Boolean ignoreRowsNotFound, CopyOrMoveRowDirective copyParameters) throws SmartsheetException;

    /**
     * <p>Creates an object of RowAttachmentResources.</p>
     *
     * @return the created RowAttachmentResources object
     */
    public RowAttachmentResources attachmentResources();

    /**
     * <p>Creates an object of RowDiscussionResources.</p>
     *
     * @return the created RowDiscussionResources object
     */
    public RowDiscussionResources discussionResources();

    /**
     * <p>Creates an object of RowColumnResources.</p>
     *
     * @return the created RowColumnResources object
     */
    public RowColumnResources cellResources();
}
