package com.smartsheet.api;

import com.smartsheet.api.models.CellHistory;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

import java.io.FileNotFoundException;

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

/**
 * This interface provides methods to access row column resources that are associated to a sheet object.
 *
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface RowColumnResources {
    /**
     * <p>Get the cell modification history.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/history</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param rowId the row id
     * @param sheetId the sheet Id
     * @param columnId the column id
     * @param parameters the pagination parameters
     * @return the modification history (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<CellHistory> getCellHistory(long sheetId, long rowId, long columnId, PaginationParameters parameters) throws SmartsheetException;
    
    /**
     * <p>Uploads an image to the specified cell within a sheet.</p>
     * 
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/cellimages</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet Id
     * @param rowId the row id
     * @param columnId the column id
     * @param file the file path
     * @param fileType
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String fileType) throws FileNotFoundException, SmartsheetException;

    /**
     * <p>Uploads an image to the specified cell within a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/cellimages</p>
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet Id
     * @param rowId the row id
     * @param columnId the column id
     * @param file the file path
     * @param fileType
     * @param overrideValidation override column type validation
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String fileType, boolean overrideValidation, String altText) throws FileNotFoundException, SmartsheetException;
}
