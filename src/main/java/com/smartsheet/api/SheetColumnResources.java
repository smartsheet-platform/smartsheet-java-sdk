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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
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
	 * @param sheetId the sheet id
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Column> listColumns(long sheetId) throws SmartsheetException;

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
	 * @param sheetId the sheet id
	 * @param column the column
	 * @return the column
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Column addColumn(long sheetId, Column column) throws SmartsheetException;
}
