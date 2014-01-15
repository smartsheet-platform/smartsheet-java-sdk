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



import com.smartsheet.api.ColumnResources;
import com.smartsheet.api.models.Column;

/**
 * This is the implementation of the ColumnResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class ColumnResourcesImpl implements ColumnResources {
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
	public ColumnResourcesImpl(SmartsheetImpl smartsheet) {
	}

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
	 * Implementation: return this.updateResource("column/" + column.getId(), Column.class, column);
	 * 
	 * @param column
	 * @return
	 */
	public Column updateColumn(Column column) {
		return null;
	}

	/**
	 * Delete a column.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /column/{id}
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
	 * Implementation: HttpRequest request = this.createHttpRequest(getSmartsheet().getBaseURI().resolve("column/" +
	 * id), HttpMethod.DELETE);
	 * 
	 * Column column = new Column(); column.setSheetId(sheetId); ByteArrayOutputStream baos = new
	 * ByteArrayOutputStream(); this.smartsheet.getJsonSerializer().serialize(column, baos); HttpEntity entity = new
	 * HttpEntity(); entity.setContentType("application/json"); entity.setContent(new
	 * ByteArrayInputStream(baos.toByteArray())); entity.setContentLength(baos.size()); request.setEntity(entity);
	 * 
	 * HttpResponse response = this.getSmartsheet().getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200:
	 * this.getSmartsheet().getJsonSerializer().deserializeResult(objectClass, response.getEntity().getContent());
	 * break; default: handleError(response); }
	 * 
	 * @param id
	 * @param sheetId
	 */
	public void deleteColumn(long id, long sheetId) {
	}
}
