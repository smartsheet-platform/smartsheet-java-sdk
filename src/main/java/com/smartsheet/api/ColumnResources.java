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



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
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
	 * @param column the column
	 * @return the column
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Column updateColumn(Column column) throws SmartsheetException;

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
	 * @param id the id
	 * @param sheetId the sheet id
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void deleteColumn(long id, long sheetId) throws SmartsheetException;
}
