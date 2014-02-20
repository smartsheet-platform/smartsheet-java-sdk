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



import java.util.List;

import com.smartsheet.api.models.Column;

/**
 * <p>This interface provides methods to access column resources that are associated to a sheet object.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface SheetColumnResources {
	
	/**
	 * <p>List columns of a given sheet.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/columns</p>
	 *
	 * @param sheetId the sheet id
	 * @return the list of Columns (note that an empty list will be returned if there is none)
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public List<Column> listColumns(long sheetId) throws SmartsheetException;

	/**
	 * <p>Add column to a sheet.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/columns</p>
	 *
	 * @param sheetId the sheet id
	 * @param column the column object
	 * @return the created column
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Column addColumn(long sheetId, Column column) throws SmartsheetException;
}
