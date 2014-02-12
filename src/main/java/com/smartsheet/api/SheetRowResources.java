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
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.RowWrapper;

/**
 * This interface provides methods to access row resources that are associated to a sheet object.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * POST /sheet/{id}/rows GET /sheet/{id}/row/{number}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetRowResources {
	
	/**
	 * Insert rows to a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/rows
	 * 
	 * Parameters: - sheetId : the ID of the sheet - rowWrapper : the RowWrapper object, one of the following attributes
	 * should be specified: * toTop : Inserts the rows at the top of the sheet. * toBottom : Inserts the rows at the
	 * bottom of the sheet * parentId : Inserts the rows as the first child row of the parent. toBottom=true can also be
	 * set to add the row as the last child of the parent. * siblingId : Inserts the row as the next sibling of the row
	 * ID provided.
	 * 
	 * Returns: the created rows
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheetId the sheet id
	 * @param rowWrapper the row wrapper
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Row> insertRows(long sheetId, RowWrapper rowWrapper) throws SmartsheetException;

	/**
	 * Get a row.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/row/{number}
	 * 
	 * Parameters: - sheetId : the ID of the sheet - rowNumber : the row number
	 * 
	 * Returns: the row (note that if there is no such resource, this method will throw ResourceNotFoundException rather
	 * than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 *
	 * @param id the id
	 * @param rowNumber the row number
	 * @return the row
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Row getRow(long id, int rowNumber) throws SmartsheetException;
}
