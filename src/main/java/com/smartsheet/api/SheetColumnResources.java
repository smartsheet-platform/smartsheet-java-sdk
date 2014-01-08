package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.Column;

/**
 * This interface provides methods to access column resources that are associated to a sheet object.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /sheet/{id}/columns POST /sheet/{id}/columns
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetColumnResources {
	/**
	 * List columns of a given sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/columns
	 * 
	 * Parameters: - sheetId : the ID of the sheet
	 * 
	 * Returns: the columns (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * @param sheetId
	 * @return
	 */
	public List<Column> listColumns(long sheetId);

	/**
	 * Add column to a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/columns
	 * 
	 * Parameters: - sheetId : the ID of the sheet - column : the coluimn object limited to the following attributes: *
	 * title * type * symbol (optional) * options (optional) - array of options * index (zero-based) * systemColumnType
	 * (optional) * autoNumberFormat (optional)
	 * 
	 * Returns: the created column
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param sheetId
	 * @param column
	 * @return
	 */
	public Column addColumn(long sheetId, Column column);
}
