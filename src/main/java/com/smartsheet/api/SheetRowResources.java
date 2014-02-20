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

import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.RowWrapper;

/**
 * This interface provides methods to access row resources that are associated to a sheet object.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetRowResources {
	
	/**
	 * <p>Insert rows to a sheet.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/rows</p>
	 *
	 * @param sheetId the sheet id
	 * @param rowWrapper the row wrapper
	 * @return the list of created rows
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public List<Row> insertRows(long sheetId, RowWrapper rowWrapper) throws SmartsheetException;

	/**
	 * Get a row.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/row/{number}
	 *
	 * @param id the id
	 * @param rowNumber the row number
	 * @return the created row (note that if there is no such resource, this method will throw ResourceNotFoundException rather
	 * than returning null).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Row getRow(long id, int rowNumber) throws SmartsheetException;
}
