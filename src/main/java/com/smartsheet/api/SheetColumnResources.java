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


import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.ColumnInclusion;

import java.util.EnumSet;
import java.util.List;

/**
 * <p>This interface provides methods to access column resources that are associated to a sheet object.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface SheetColumnResources {

    /**
     * <p>List columns of a given sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/columns</p>
     *
     * @param sheetId the sheet id
     * @param includes list of includes
     * @param pagination the object containing the pagination parameters
     * @return the list of Columns (note that an empty list will be returned if there is none)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Column> listColumns(long sheetId, EnumSet<ColumnInclusion> includes, PaginationParameters pagination) throws SmartsheetException;

    /**
     * <p>Add column to a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/columns</p>
     *
     * @param sheetId the sheet id
     * @param columns the list of column object
     * @return the list of created column
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Column> addColumns(long sheetId, List<Column> columns) throws SmartsheetException;

    /**
     * <p>Delete column.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/columns/{columnId}</p>
     *
     * @param sheetId the sheet id
     * @param columnId the column id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteColumn(long sheetId, long columnId) throws SmartsheetException;

    /**
     * <p>Update a column.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/columns/{columnId}</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheetId
     * @param column the column to update limited to the following attributes: index (column's new index in the sheet),
     * title, sheetId, type, options (optional), symbol (optional), systemColumnType (optional),
     * autoNumberFormat (optional)
     * @return the updated sheet (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Column updateColumn(long sheetId, Column column) throws SmartsheetException;

    /**
     * <p>Gets the Column specified in the URL.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/columns/{columnId}</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param columnId the column id
     * @param includes list of includes
     * @return the column (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public Column getColumn(long sheetId, long columnId, EnumSet<ColumnInclusion> includes) throws SmartsheetException;
}
