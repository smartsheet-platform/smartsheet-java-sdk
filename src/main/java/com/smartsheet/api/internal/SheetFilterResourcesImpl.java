package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.*;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.SheetFilter;
import java.util.HashMap;

public class SheetFilterResourcesImpl extends  AbstractResources implements SheetFilterResources {

    /**
     * Constructor.
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     */
    public SheetFilterResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Get a filter.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/filters/{filterId}
     *
     * Parameters: - filterId : the ID
     *
     * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheetId
     * @param filterId the filter ID
     * @return the filter
     * @throws SmartsheetException the smartsheet exception
     */
    public SheetFilter getFilter(long sheetId, long filterId) throws SmartsheetException {
        return this.getResource("sheets/" + sheetId + "/filters/" + filterId, SheetFilter.class);
    }

    /**
     * Delete filter.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/filters/{filterId}
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID
     * @param filterId the filter ID
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteFilter(long sheetId, long filterId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/filters/" + filterId, SheetFilter.class);
    }

    /**
     * Get all filters.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/filters
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID
     * @param pagination the pagination pagination
     * @return all the filters
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<SheetFilter> listFilters(long sheetId, PaginationParameters pagination) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/filters";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, SheetFilter.class);
    }
}
