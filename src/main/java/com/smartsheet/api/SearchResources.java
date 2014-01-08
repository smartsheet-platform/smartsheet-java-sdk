package com.smartsheet.api;

import com.smartsheet.api.models.SearchResult;

/**
 * This interface provides methods to access search resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /search GET /search/sheet/{sheetId}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SearchResources {
	/**
	 * Performs a search across all Sheets to which user has access.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /search
	 * 
	 * Parameters: - query : the query text
	 * 
	 * Returns: the search result (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if query is null/empty string - InvalidRequestException : if there is
	 * any problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param query
	 * @return
	 */
	public SearchResult search(String query);

	/**
	 * Performs a search within a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /search/sheet/{sheetId}
	 * 
	 * Parameters: - sheetId : the sheet ID - query : the query text
	 * 
	 * Returns: the search result (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if query is null/empty string - InvalidRequestException : if there is
	 * any problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param sheetId
	 * @param query
	 * @return
	 */
	public SearchResult searchSheet(long sheetId, String query);
}
