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



import com.smartsheet.api.models.SearchResult;
import com.smartsheet.api.models.enums.SearchInclusion;
import com.smartsheet.api.models.enums.SearchLocation;
import com.smartsheet.api.models.enums.SearchScope;
import com.smartsheet.api.models.enums.SourceInclusion;

import java.util.Date;
import java.util.EnumSet;

/**
 * This interface provides methods to access search resources.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SearchResources {

    /**
     * <p>Performs a search across all Sheets to which user has access.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /search</p>
     *
     * @param query the query text
     * @return the search result (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SearchResult search(String query) throws SmartsheetException;

    /**
     * <p>Performs a search across all Sheets to which user has access.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /search</p>
     *
     * @param query the query text
     * @param includes enum set of inclusions
     * @param location when specified with a value of "personalWorkspace" limits response to only those
     *                 items in the user's Workspace
     * @param modifiedSince only return items modified since this date
     * @param scopes enum set of search filters
     * @return the search result (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SearchResult search(String query, EnumSet<SearchInclusion> includes, SearchLocation location, Date modifiedSince, EnumSet<SearchScope> scopes) throws SmartsheetException;

    /**
     * <p>Performs a search within a sheet.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /search/sheet/{sheetId}</p>
     *
     * @param sheetId the sheet id
     * @param query the query text
     * @return the search result (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SearchResult searchSheet(long sheetId, String query) throws SmartsheetException;
}
