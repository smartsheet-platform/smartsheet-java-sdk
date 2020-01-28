package com.smartsheet.api;

import com.smartsheet.api.models.CellHistory;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.CellHistoryInclusion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.EnumSet;

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
     * @param pagination the pagination parameters
     * @return the modification history (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<CellHistory> getCellHistory(long sheetId, long rowId, long columnId, PaginationParameters pagination) throws SmartsheetException;

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
     * @param pagination the pagination parameters
     * @param includes cell history inclusion
     * @param level compatbility level
     * @return the modification history (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<CellHistory> getCellHistory(long sheetId, long rowId, long columnId, PaginationParameters pagination,
                                                   EnumSet<CellHistoryInclusion> includes, Integer level) throws SmartsheetException;

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
     * @param contentType MIME type
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String contentType) throws FileNotFoundException, SmartsheetException;

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
     * @param contentType MIME type
     * @param overrideValidation override column type validation
     * @param altText alternate description for the image
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String contentType, boolean overrideValidation, String altText) throws FileNotFoundException, SmartsheetException;

    /**
     * Add an image to a cell.
     *
     * It mirrors the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/cellimages
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
     * @param file the File object
     * @param contentType MIME type
     * @param overrideValidation override column type validation if true
     * @param altText alternate description for the image
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, File file, String contentType,
                               boolean overrideValidation, String altText) throws FileNotFoundException, SmartsheetException;

    /**
     * Add an image to a cell.
     *
     * It mirrors the following Smartsheet REST API method: POST /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/cellimages
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
     * @param inputStream the input stream of the contents
     * @param contentType MIME type
     * @param contentLength length of the input stream
     * @param overrideValidation override column type validation if true
     * @param altText alternate description for the image
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, InputStream inputStream, String contentType,
                               long contentLength, boolean overrideValidation, String altText) throws SmartsheetException;
}
