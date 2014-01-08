package com.smartsheet.api;

import com.smartsheet.api.models.Column;

/**
 * This interface provides methods to access Column resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * PUT /column/{id} DELETE /column/{id}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface ColumnResources {
	/**
	 * Update a column.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /column/{id}
	 * 
	 * Parameters: - column : the column to update limited to the following attributes : * index (column's new index in
	 * the sheet) * title * sheetId * type * options (optional) * symbol (optional) * systemColumnType (optional) *
	 * autoNumberFormat (optional)
	 * 
	 * 
	 * Returns: the updated sheet (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param column
	 * @return
	 */
	public Column updateColumn(Column column);

	/**
	 * Delete a column.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /coluimn{id}
	 * 
	 * Parameters: - id : the ID of the column - sheetId : the ID of the sheet
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * @param id
	 * @param sheetId
	 */
	public void deleteColumn(long id, long sheetId);
}
