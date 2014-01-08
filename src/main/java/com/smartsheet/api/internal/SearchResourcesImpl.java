package com.smartsheet.api.internal;

import com.smartsheet.api.SearchResources;
import com.smartsheet.api.models.SearchResult;

/**
 * This is the implementation of the SearchResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SearchResourcesImpl implements SearchResources {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public SearchResourcesImpl(SmartsheetImpl smartsheet) {
	}

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
	 * Implementation: return this.getResource("search?query=" + URLEncoder.encode(query, "utf-8"), SearchResult.class);
	 * 
	 * @param query
	 * @return
	 */
	public SearchResult search(String query) {
		return null;
	}

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
	 * Implementation: return this.getResource("search/sheet/" + sheetId + "?query=" + URLEncoder.encode(query,
	 * "utf-8"), SearchResult.class);
	 * 
	 * @param sheetId
	 * @param query
	 * @return
	 */
	public SearchResult searchSheet(long sheetId, String query) {
		return null;
	}
}
