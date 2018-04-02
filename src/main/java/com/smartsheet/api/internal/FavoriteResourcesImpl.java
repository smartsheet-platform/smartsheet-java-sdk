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

import com.smartsheet.api.FavoriteResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.Favorite;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.FavoriteType;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * This is the implementation of the FavoriteResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class FavoriteResourcesImpl extends AbstractResources implements FavoriteResources{

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public FavoriteResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Adds one or more items to the user's list of Favorite items.
     *
     * It mirrors to the following Smartsheet REST API method: POST /favorites
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
     * @param favorites the list of favorites object limited to the following attributes: *
     * objectId * type
     * @return a single Favorite object or an array of Favorite objects
     * @throws SmartsheetException the smartsheet exception
     */
    public List<Favorite> addFavorites(List<Favorite> favorites) throws SmartsheetException{
        return this.postAndReceiveList("favorites/", favorites, Favorite.class);
    }

    /**
     * Gets a list of all of the user's Favorite items.
     *
     * It mirrors to the following Smartsheet REST API method: GET /favorites
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
     * @param parameters pagination parameters
     * @return a single Favorite object or an array of Favorite objects
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Favorite> listFavorites(PaginationParameters parameters) throws SmartsheetException{
        String path = "favorites";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Favorite.class);
    }

    /**
     * Deletes a list of favorites (all of the same type)
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /favorites
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
     * @param favoriteType the favorite type
     * @param objectIds a single Favorite object or an array of Favorite objects
     * @throws SmartsheetException the smartsheet exception
     */
    public void removeFavorites(FavoriteType favoriteType, Set<Long> objectIds) throws SmartsheetException{
        String path = "favorites/" + favoriteType.toString();
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        if (objectIds != null) {
            parameters.put("objectIds", QueryUtil.generateCommaSeparatedList(objectIds));
        }

        path += QueryUtil.generateUrl(null, parameters);

        this.deleteResource(path, Favorite.class);
    }
}
