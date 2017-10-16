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


import com.smartsheet.api.models.Group;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * <p>This interface provides methods to access Group resources </p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface GroupResources {

    /**
     * <p>List all group.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /groups</p>
     *
     * @param parameters the paging parameters object
     * @return A list of all {@link Group}s. Note that the groups do not contain the membership details. You must get each group individually for group memebership.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Group> listGroups(PaginationParameters parameters) throws SmartsheetException;


    /**
     * <p>Get a {@link Group}.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /group/{id}</p>
     *
     * @param groupId the {@link Group} id
     * @return the {@link Group} (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Group getGroup(long groupId) throws SmartsheetException;

    /**
     * <p>Create a {@link Group}. Use {@link Group.CreateGroupBuilder} to create the model for a new {@link Group}</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /groups</p>
     *
     * @param group the {@link Group} to create. Use {@link Group.CreateGroupBuilder} to create this model.
     * @return the newly created {@link Group}
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Group createGroup(Group group) throws SmartsheetException;

    /**
     * <p>Update a {@link Group}. Use {@link Group.CreateGroupBuilder} to create the model for a new {@link Group}</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /group/{groupId}</p>
     *
     * @param group the {@link Group} to create. Use {@link Group.CreateGroupBuilder} to create this model.
     * @return the newly created {@link Group}
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Group updateGroup(Group group) throws SmartsheetException;

    /**
     * <p>Delete a {@link Group}. </p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /group/{groupId}</p>
     *
     * @param groupId the id of the {@link Group} to delete.
     *
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteGroup(long groupId) throws SmartsheetException;

    /**
     * <p>Represents the GroupMemberResources.</p>
     * <p>It will be initialized in constructor and will not change afterwards.</p>
     *
     * @return members object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public GroupMemberResources memberResources() throws SmartsheetException;

}
