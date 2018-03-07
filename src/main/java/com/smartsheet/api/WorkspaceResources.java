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

import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Workspace;
import com.smartsheet.api.models.enums.SourceInclusion;
import com.smartsheet.api.models.enums.WorkspaceCopyInclusion;
import com.smartsheet.api.models.enums.WorkspaceRemapExclusion;

import java.util.EnumSet;

/**
 * <p>This interface provides methods to access Workspace resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface WorkspaceResources {

    /**
     * <p>List all workspaces.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /workspaces</p>
     *
     * @param parameters the object containing the pagination parameters
     * @return the list of workspaces (note that an empty list will be returned if there are none)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Workspace> listWorkspaces(PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>Get a workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /workspace/{id}</p>
     *
     * @param id the id
     * @param includes the include parameters
     * @param loadAll the loadAll boolean value
     * @return the workspace (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Workspace getWorkspace(long id, Boolean loadAll, EnumSet<SourceInclusion> includes ) throws SmartsheetException;

    /**
     * <p>Create a workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /workspaces</p>
     *
     * @param workspace the workspace to create
     * @return the created workspace
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Workspace createWorkspace(Workspace workspace) throws SmartsheetException;

    /**
     * <p>Update a workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /workspace/{id}</p>
     *
     * @param workspace the workspace to update
     * @return the updated workspace (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Workspace updateWorkspace(Workspace workspace) throws SmartsheetException;

    /**
     * <p>Delete a workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /workspace{id}</p>
     *
     * @param id the id of the workspace
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteWorkspace(long id) throws SmartsheetException;

    /**
     * <p>Creates a copy of the specified workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /workspaces/{workspaceId}/copy</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param workspaceId the folder id
     * @param containerDestination describes the destination container
     * @param includes optional parameters to include
     * @param skipRemap optional parameters to exclude
     * @return the folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Workspace copyWorkspace(long workspaceId, ContainerDestination containerDestination, EnumSet<WorkspaceCopyInclusion> includes, EnumSet<WorkspaceRemapExclusion> skipRemap) throws SmartsheetException;

    /**
     * <p>Return the WorkspaceFolderResources object that provides access to Folder resources associated with Workspace
     * resources.</p>
     *
     * @return the workspace folder resources
     */
    public WorkspaceFolderResources folderResources();

    /**
     * <p>Return the ShareResources object that provides access to Share resources associated with Workspace
     * resources.</p>
     *
     * @return the share resources object
     */
    public ShareResources shareResources();
}
