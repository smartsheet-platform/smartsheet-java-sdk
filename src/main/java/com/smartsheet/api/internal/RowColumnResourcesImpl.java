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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import com.smartsheet.api.RowColumnResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.CellHistory;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * This is the implementation of the RowColumnResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class RowColumnResourcesImpl extends AbstractResources implements RowColumnResources{

    /**
     * Constructor.
     *
     * Parameters: - smartsheet : the SmartsheetImpl
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public RowColumnResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Get the cell modification history.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}/columns/{columnId}/history
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
     * @param columnId the column id
     * @param sheetId the sheet Id
     * @param parameters the pagination parameters
     * @return the modification history (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<CellHistory> getCellHistory(long sheetId, long rowId, long columnId, PaginationParameters parameters) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/rows/" + rowId + "/columns/"+columnId + "/history";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, CellHistory.class);
    }

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
     * @param file the file path
     * @param fileType
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String fileType) throws FileNotFoundException, SmartsheetException {
        addImage("sheets/" + sheetId + "/rows/" + rowId + "/columns/" + columnId + "/cellimages", file, fileType, false, null);
    }

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
     * @param file the file path
     * @param fileType
     * @throws SmartsheetException the smartsheet exception
     * @throws FileNotFoundException image file not found
     */
    public void addImageToCell(long sheetId, long rowId, long columnId, String file, String fileType, boolean overrideValidation, String altText) throws FileNotFoundException, SmartsheetException {
        addImage("sheets/" + sheetId + "/rows/" + rowId + "/columns/" + columnId + "/cellimages", file, fileType, overrideValidation, altText);
    }

    private void addImage(String path, String file, String contentType, boolean overrideValidation, String altText) throws SmartsheetException, FileNotFoundException {
        Util.throwIfNull(file);

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if(altText != null) {
            parameters.put("altText", altText);
        }
        if(overrideValidation) {
            parameters.put("overrideValidation", "true");
        }
        path += QueryUtil.generateUrl(null, parameters);

        HttpRequest request = createHttpRequest(this.smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
        try {
            request.getHeaders().put("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file, "UTF-8") + "\"");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        File f = new File(file);
        InputStream is = new FileInputStream(f);

        HttpEntity entity = new HttpEntity();
        entity.setContentType(contentType);
        entity.setContent(is);
        entity.setContentLength(f.length());
        request.setEntity(entity);

        HttpResponse response = this.smartsheet.getHttpClient().request(request);
        switch (response.getStatusCode()) {
            case 200:
                break;
            default:
                handleError(response);
        }
        smartsheet.getHttpClient().releaseConnection();
    }
}
