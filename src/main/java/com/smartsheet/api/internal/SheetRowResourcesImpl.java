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
import com.smartsheet.api.internal.http.*;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ObjectExclusion;
import com.smartsheet.api.models.enums.RowCopyInclusion;
import com.smartsheet.api.models.enums.RowInclusion;
import com.smartsheet.api.models.enums.RowMoveInclusion;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * This is the implementation of the SheetRowResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetRowResourcesImpl extends AbstractResources implements SheetRowResources {
    RowAttachmentResources attachments;
    RowDiscussionResources discussions;
    RowColumnResources columns;
    /**
     * Constructor.
     *
     * Parameters: - smartsheet : the SmartsheetImpl
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SheetRowResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.attachments = new RowAttachmentResourcesImpl(smartsheet);
        this.discussions = new RowDiscussionResourcesImpl(smartsheet);
        this.columns = new RowColumnResourcesImpl(smartsheet);
    }

    /**
     * Insert rows to a sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows
     *
     * Exceptions:
     *   - IllegalArgumentException : if any argument is null
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param rows the list of rows to create
     * @return the created rows
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Row> addRows(long sheetId, List<Row> rows) throws SmartsheetException {
        return this.addRows(sheetId, rows, null, null);
    }

    /**
     * Insert rows to a sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows
     *
     * Exceptions:
     *   - IllegalArgumentException : if any argument is null
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param rows the list of rows to create
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the created rows
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Row> addRows(long sheetId, List<Row> rows, EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/rows";

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        path += QueryUtil.generateUrl(null, parameters);
        return this.postAndReceiveList(path, rows, Row.class);
    }

    /**
     * Insert rows to a sheet, allowing partial success. If a row cannot be inserted, it will fail, while the others may succeed.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{id}/rows
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
    @Override
    public PartialRowUpdateResult addRowsAllowPartialSuccess(long sheetId, List<Row> rows) throws SmartsheetException {
        return doPartialRowOperation(sheetId, rows, null, null, HttpMethod.POST);
    }

    /**
     * Insert rows to a sheet, allowing partial success. If a row cannot be inserted, it will fail, while the others may succeed.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{id}/rows
     *
     * @param sheetId the sheet id
     * @param rows the list of rows to create
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the list of created rows
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    @Override
    public PartialRowUpdateResult addRowsAllowPartialSuccess(long sheetId, List<Row> rows,
                                                             EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException {
        return doPartialRowOperation(sheetId, rows, includes, excludes, HttpMethod.POST);
    }

    /**
     * Get a row.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the id of the sheet
     * @param rowId the id of the row
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the row (note that if there is no such resource, this method will throw ResourceNotFoundException rather
     * than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Row getRow(long sheetId, long rowId, EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/rows/" + rowId;

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        path += QueryUtil.generateUrl(null, parameters);
        return this.getResource(path, Row.class);
    }

    /**
     * @deprecated as of API 2.0.2 release, replaced by {@link #deleteRows(long, Set, boolean)}
     * Delete a row.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/rows/{rowId}
     * Parameters: - id : the ID of the row
     *
     * Returns: None
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
    public void deleteRow(long sheetId, long rowId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/rows/" + rowId, Row.class);
    }

    /**
     * Deletes one or more row(s) from the Sheet specified in the URL.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/rows/{rowId}
     * Parameters: - id : the ID of the row
     *
     * Returns: None
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
    public List<Long> deleteRows(long sheetId, Set<Long> rowIds, boolean ignoreRowsNotFound) throws SmartsheetException {
        Util.throwIfNull(rowIds);
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        String path = "sheets/" + sheetId + "/rows/";
        parameters.put("ids", QueryUtil.generateCommaSeparatedList(rowIds));
        parameters.put("ignoreRowsNotFound", ignoreRowsNotFound);

        path += QueryUtil.generateUrl(null, parameters);

        return this.deleteListResources(path, Long.class);
    }

    /**
     * @deprecated as of API V2.0.2, replaced by {@link #sendRows(long, MultiRowEmail)}
     * Send a row via email to the designated recipients.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/emails
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
    public void sendRow(long sheetId, long rowId, RowEmail email) throws SmartsheetException {
        this.createResource("sheets/" + sheetId + "/rows/" + rowId + "/emails", RowEmail.class, email);
    }

    /**
     * Send a row via email to the designated recipients.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/emails
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
    public void sendRows(long sheetId, MultiRowEmail email) throws SmartsheetException {
        this.createResource("sheets/" + sheetId + "/rows/emails", MultiRowEmail.class, email);
    }

    /**
     * Update rows.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows
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
    public List<Row> updateRows(long sheetId, List<Row> rows) throws SmartsheetException {
        return this.updateRows(sheetId, rows, null, null);
    }

    /**
     * Update rows.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows
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
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return a list of rows
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Row> updateRows(long sheetId, List<Row> rows, EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/rows";

        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        path += QueryUtil.generateUrl(null, parameters);
        return this.putAndReceiveList(path, rows, Row.class);
    }

    /**
     * Update rows, but allow partial success. The PartialRowUpdateResult will contain the successful
     * rows and those that failed, with specific messages for each.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows
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
    @Override
    public PartialRowUpdateResult updateRowsAllowPartialSuccess(long sheetId, List<Row> rows) throws SmartsheetException {
        return doPartialRowOperation(sheetId, rows, null, null, HttpMethod.PUT);
    }

    /**
     * Update rows, but allow partial success. The PartialRowUpdateResult will contain the successful
     * rows and those that failed, with specific messages for each.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/rows
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
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return a list of rows
     * @throws SmartsheetException the smartsheet exception
     */
    @Override
    public PartialRowUpdateResult updateRowsAllowPartialSuccess(long sheetId, List<Row> rows,
                                                                EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes) throws SmartsheetException {
        return doPartialRowOperation(sheetId, rows, includes, excludes, HttpMethod.PUT);
    }

    private PartialRowUpdateResult doPartialRowOperation(long sheetId, List<Row> rows,
                                                         EnumSet<RowInclusion> includes, EnumSet<ObjectExclusion> excludes, HttpMethod method) throws SmartsheetException {
        Util.throwIfNull(rows, method);
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            throw new IllegalArgumentException();
        }

        String path = "sheets/" + sheetId + "/rows";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("allowPartialSuccess", "true");
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        path = QueryUtil.generateUrl(path, parameters);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), method);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        this.smartsheet.getJsonSerializer().serialize(rows, baos);

        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
        entity.setContentLength(baos.size());
        request.setEntity(entity);

        HttpResponse response = this.smartsheet.getHttpClient().request(request);

        PartialRowUpdateResult result = null;
        switch (response.getStatusCode()) {
            case 200:
                BulkItemResult<Row> bulkItemResult;
                bulkItemResult = this.smartsheet.getJsonSerializer().deserializeBulkItemResult(Row.class,
                        response.getEntity().getContent());
                result = new PartialRowUpdateResult();
                result.setResult(bulkItemResult.getResult());
                result.setResultCode(bulkItemResult.getResultCode());
                result.setMessage(bulkItemResult.getMessage());
                result.setVersion(bulkItemResult.getVersion());
                if(bulkItemResult.getFailedItems() != null) {
                    List<BulkRowFailedItem> failedItems = new ArrayList<BulkRowFailedItem>();
                    for (BulkItemFailure bulkItemFailure : bulkItemResult.getFailedItems()) {
                        BulkRowFailedItem bulkRowFailedItem = new BulkRowFailedItem();
                        bulkRowFailedItem.setError(bulkItemFailure.getError());
                        bulkRowFailedItem.setIndex(bulkItemFailure.getIndex());
                        bulkRowFailedItem.setRowId(bulkItemFailure.getRowId());
                        failedItems.add(bulkRowFailedItem);
                    }
                    result.setFailedItems(failedItems);
                }
                break;
            default:
                handleError(response);
        }

        smartsheet.getHttpClient().releaseConnection();

        return result;
    }

    /**
     * Moves Row(s) from the Sheet specified in the URL to (the bottom of) another sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/move
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
    public CopyOrMoveRowResult moveRows(Long sheetId, EnumSet<RowMoveInclusion> includes, Boolean ignoreRowsNotFound, CopyOrMoveRowDirective moveParameters) throws SmartsheetException {
        String path = "sheets/" + sheetId +"/rows/move";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));

        if (ignoreRowsNotFound != null){
            parameters.put("ignoreRowsNotFound", ignoreRowsNotFound.toString());
        }

        path += QueryUtil.generateUrl(null, parameters);
        return this.postAndReceiveRowObject(path, moveParameters);
    }


    /**
     * Copies Row(s) from the Sheet specified in the URL to (the bottom of) another sheet.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/copy
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
    public CopyOrMoveRowResult copyRows(Long sheetId, EnumSet<RowCopyInclusion> includes, Boolean ignoreRowsNotFound, CopyOrMoveRowDirective copyParameters) throws SmartsheetException {
        String path = "sheets/" + sheetId +"/rows/copy";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));

        if (ignoreRowsNotFound != null){
            parameters.put("ignoreRowsNotFound", ignoreRowsNotFound.toString());
        }

        path += QueryUtil.generateUrl(null, parameters);
        return this.postAndReceiveRowObject(path, copyParameters);
    }


    /**
    /**
     * @deprecated replaced by {@link #updateRows(long, List)}
     * Update the values of the Cells in a Row.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /row/{id}/cells
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param rowId the row id
     * @param cells the cells to update (Cells must have the following attributes set: *
     * columnId * value * strict (optional)
     * @return the updated cells (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    @Deprecated
    public List<Cell> updateCells(long rowId, List<Cell> cells) throws SmartsheetException {
        return this.putAndReceiveList("row/" + rowId + "/cells", cells, Cell.class);
    }

    /**
     * Creates an object of RowAttachmentResources.
     *
     * @return the created RowAttachmentResources object
     */
    public RowAttachmentResources attachmentResources(){
        return attachments;
    }

    /**
     * Creates an object of RowDiscussionResources.
     *
     * @return the created RowDiscussionResources object
     */
    public RowDiscussionResources discussionResources(){
        return discussions;
    }

    /**
     * Creates an object of RowColumnResources.
     *
     * @return the created RowColumnResources object
     */
    public RowColumnResources cellResources(){
        return columns;
    }
}
