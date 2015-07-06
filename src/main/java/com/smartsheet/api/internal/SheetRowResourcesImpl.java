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



import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;
import java.util.HashMap;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.AssociatedDiscussionResources;
import com.smartsheet.api.SheetRowResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.*;

/**
 * This is the implementation of the SheetRowResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetRowResourcesImpl extends AbstractResources implements SheetRowResources {
	
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
	public List<Row> insertRows(long sheetId, List<Row> rows) throws SmartsheetException {
		return this.postAndReceiveList("sheets/" + sheetId + "/rows", rows, Row.class);
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
	public void deleteRow(long sheetId, long rowId) throws SmartsheetException {
		this.deleteResource("sheets/" + sheetId + "/rows/" + rowId, Row.class);
	}

	/**
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
	public void sendRow(long sheetId, long rowId, RowEmail email) throws SmartsheetException {
		this.createResource("sheets/" + sheetId + "/rows/" + rowId + "/emails", RowEmail.class, email);
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
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Row> updateRows(long sheetId, List<Row> rows) throws SmartsheetException {
		return this.putAndReceiveList("sheets/" + sheetId + "/rows", rows, Row.class);
	}

	// TODO: These methods will still need to be completed
//	/**
//	 * Move a row.
//	 *
//	 * It mirrors to the following Smartsheet REST API method: PUT /row/{id}
//	 *
//	 * Exceptions:
//	 *   IllegalArgumentException : if any argument is null
//	 *   InvalidRequestException : if there is any problem with the REST API request
//	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
//	 *   ResourceNotFoundException : if the resource can not be found
//	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
//	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
//	 *   SmartsheetException : if there is any other error occurred during the operation
//	 *
//	 * @param id the id
//	 * @param rowWrapper the the RowWrapper with one of the following attributes:
//	 *   - toTop : Moves the row (and children rows, if any) to the top of the sheet.
//	 *   - toBottom : Moves the row to the bottom of the sheet
//	 *   - parentId : Moves the row as the first child row of the parent.
//	 *   - toBottom=true can also be set to add the row as the last child of the parent.
//	 *   - siblingId : Moves the row as the next sibling of the row ID provided.
//	 *
//	 * @return the rows that have been moved by the operation
//	 * @throws SmartsheetException the smartsheet exception
//	 */
//	public List<Row> moveRow(long id, RowWrapper rowWrapper) throws SmartsheetException {
//		return this.putAndReceiveList("row/" + id, rowWrapper, Row.class);
//	}
//
//	/**
//	 * Update the values of the Cells in a Row.
//	 *
//	 * It mirrors to the following Smartsheet REST API method: PUT /row/{id}/cells
//	 *
//	 * Exceptions:
//	 *   IllegalArgumentException : if any argument is null
//	 *   InvalidRequestException : if there is any problem with the REST API request
//	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
//	 *   ResourceNotFoundException : if the resource can not be found
//	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
//	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
//	 *   SmartsheetException : if there is any other error occurred during the operation
//	 *
//	 * @param rowId the row id
//	 * @param cells the cells to update (Cells must have the following attributes set: *
//	 * columnId * value * strict (optional)
//	 * @return the updated cells (note that if there is no such resource, this method will throw
//	 * ResourceNotFoundException rather than returning null).
//	 * @throws SmartsheetException the smartsheet exception
//	 */
//	public List<Cell> updateCells(long rowId, List<Cell> cells) throws SmartsheetException {
//		return this.putAndReceiveList("row/" + rowId + "/cells", cells, Cell.class);
//	}
//
//	/**
//	 * Get the cell modification history.
//	 *
//	 * It mirrors to the following Smartsheet REST API method: GET /row/{rowId}/column/{columnId}/history
//	 *
//	 * Exceptions:
//	 *   InvalidRequestException : if there is any problem with the REST API request
//	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
//	 *   ResourceNotFoundException : if the resource can not be found
//	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
//	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
//	 *   SmartsheetException : if there is any other error occurred during the operation
//	 *
//	 * @param rowId the row id
//	 * @param columnId the column id
//	 * @return the modification history (note that if there is no such resource, this method will throw
//	 * ResourceNotFoundException rather than returning null).
//	 * @throws SmartsheetException the smartsheet exception
//	 */
//	public List<CellHistory> getCellHistory(long rowId, long columnId) throws SmartsheetException {
//		return this.listResources("row/" + rowId + "/column/" + columnId + "/history", CellHistory.class);
//	}
//
//	/**
//	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with Row
//	 * resources.
//	 *
//	 * @return the associated attachment resources
//	 */
//	public AssociatedAttachmentResources attachments() {
//		return this.attachments;
//	}
//
//	/**
//	 * Return the AssociatedDiscussionResources object that provides access to discussion resources associated with Row
//	 * resources.
//	 *
//	 * @return the associated discussion resources
//	 */
//	public AssociatedDiscussionResources discussions() {
//		return this.discussions;
//	}
}
