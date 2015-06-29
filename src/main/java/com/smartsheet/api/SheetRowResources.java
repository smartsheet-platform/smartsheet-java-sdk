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



import java.util.EnumSet;
import java.util.List;

import com.smartsheet.api.models.*;

/**
 * This interface provides methods to access row resources that are associated to a sheet object.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetRowResources {
	
	/**
	 * <p>Insert rows to a sheet.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/rows</p>
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
	public List<Row> insertRows(long sheetId, List<Row> rows) throws SmartsheetException;

	/**
	 * Get a row.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}
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
	 * Delete a row.
	 *
	 * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/rows/{rowId}
	 *
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
	public void deleteRow(long sheetId, long rowId) throws SmartsheetException;

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
	public void sendRow(long sheetId, long rowId, RowEmail email) throws SmartsheetException;

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
	public List<Row> updateRows(long sheetId, List<Row> rows) throws SmartsheetException;
}
