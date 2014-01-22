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



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.SheetRowResources;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.RowWrapper;

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
	 * Implementation: super(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public SheetRowResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

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
	 * Implementation: return this.postAndReceiveList("sheet/" + sheetId + "/rows", rowWrapper, Row.class);
	 * 
	 * @param sheetId
	 * @param rowWrapper
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Row> insertRows(long sheetId, RowWrapper rowWrapper) throws JsonParseException, JsonMappingException, JSONSerializerException, HttpClientException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return this.postAndReceiveList("sheet/" + sheetId + "/rows", rowWrapper, Row.class);
	}

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
	 * Implementation: return this.getResource("sheet/" + sheetId + "/row/" + rowNumber, Row.class);
	 * 
	 * @param id
	 * @param rowNumber
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Row getRow(long id, int rowNumber) throws JsonParseException, JsonMappingException, HttpClientException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return this.getResource("sheet/" + id + "/row/" + rowNumber, Row.class);
	}
}
