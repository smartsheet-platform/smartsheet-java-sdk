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


import com.smartsheet.api.SearchResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.SearchResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This is the implementation of the SearchResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SearchResourcesImpl extends AbstractResources implements SearchResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SearchResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Performs a search across all Sheets to which user has access.
     *
     * It mirrors to the following Smartsheet REST API method: GET /search
     *
     * Exceptions:
     *   IllegalArgumentException : if query is null/empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param query the query text
     * @return the search result (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws SmartsheetException the smartsheet exception
     */
    public SearchResult search(String query) throws SmartsheetException {
        Util.throwIfNull(query);
        Util.throwIfEmpty(query);
        try {
            return this.getResource("search?query=" + URLEncoder.encode(query, "utf-8"), SearchResult.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Performs a search within a sheet.
     *
     * It mirrors to the following Smartsheet REST API method: GET /search/sheet/{sheetId}
     *
     * Exceptions:
     *   IllegalArgumentException : if query is null/empty string
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param query the query
     * @return the search result (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws SmartsheetException the smartsheet exception
     */
    public SearchResult searchSheet(long sheetId, String query) throws SmartsheetException {
        Util.throwIfNull(query);
        Util.throwIfEmpty(query);
        try {
            return this.getResource("search/sheets/" + sheetId + "?query=" + URLEncoder.encode(query,
                    "utf-8"), SearchResult.class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
