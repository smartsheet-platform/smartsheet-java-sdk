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

package com.smartsheet.api;

import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Sight;
import com.smartsheet.api.models.SightPublish;

import java.util.Date;

public interface SightResources {

    /**
     * <p>Gets the list of all Sights where the User has access.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sights</p>
     *
     * @param paging the pagination parameters
     * @param modifiedSince include sights modified on or after this date
     * @return IndexResult object containing an array of Sight objects limited to the following attributes:
     *     id, name, accessLevel, permalink, createdAt, modifiedAt.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Sight> listSights(PaginationParameters paging, Date modifiedSince) throws SmartsheetException;

    /**
     * <p>Get a specified Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sights/{sightId}</p>
     *
     * @param sightId the Id of the Sight
     * @return the Sight resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Sight getSight(long sightId) throws SmartsheetException;

    /**
     * <p>Get a specified Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sights/{sightId}</p>
     *
     * @param sight - the Sight to update
     * @return the updated Sight resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Sight updateSight(Sight sight) throws SmartsheetException;

    /**
     * <p>Delete a specified Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sights/{sightId}</p>
     *
     * @param sightId the Id of the Sight
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteSight(long sightId) throws SmartsheetException;

    /**
     * <p>Creates s copy of the specified Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/copy</p>
     *
     * @param sightId the Id of the Sight
     * @param destination the destination to copy to
     * @return the newly created Sight resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Sight copySight(long sightId, ContainerDestination destination) throws SmartsheetException;

    /**
     * <p>Creates s copy of the specified Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/move</p>
     *
     * @param sightId the Id of the Sight
     * @param destination the destination to copy to
     * @return the newly created Sight resource.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Sight moveSight(long sightId, ContainerDestination destination) throws SmartsheetException;

    /**
     * <p>Get the publish status of a Sight.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/publish</p>
     *
     * @param sightId the Id of the Sight
     * @return the Sight's publish status.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SightPublish getPublishStatus(long sightId) throws SmartsheetException;

    /**
     * <p>Sets the publish status of a Sight and returns the new status, including the URLs of any enabled publishing.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /sights/{sightId}/publish</p>
     *
     * @param sightId the Id of the Sight
     * @param sightPublish the SightPublish object containing publish status
     * @return the Sight's publish status.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public SightPublish setPublishStatus(long sightId, SightPublish sightPublish) throws SmartsheetException;

    /**
     * <p>Return the ShareResources object that provides access to share resources associated with
     * Sight resources.</p>
     *
     * @return the associated share resources
     */
    public ShareResources shareResources();
}
