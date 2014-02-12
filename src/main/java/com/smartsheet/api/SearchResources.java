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



import java.io.UnsupportedEncodingException;

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
	 * @param query the query
	 * @return the search result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws SmartsheetException the smartsheet exception
	 */
	public SearchResult search(String query) throws UnsupportedEncodingException, SmartsheetException;

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
	 * @param sheetId the sheet id
	 * @param query the query
	 * @return the search result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws SmartsheetException the smartsheet exception
	 */
	public SearchResult searchSheet(long sheetId, String query) throws UnsupportedEncodingException, SmartsheetException;
}
