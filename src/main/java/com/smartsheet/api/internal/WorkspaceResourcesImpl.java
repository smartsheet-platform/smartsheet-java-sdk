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
 *      http://www.apache.org/licenses/LICENSE
     *   -2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


import com.smartsheet.api.ShareResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.WorkspaceFolderResources;
import com.smartsheet.api.WorkspaceResources;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Workspace;
import com.smartsheet.api.models.enums.SourceInclusion;
import com.smartsheet.api.models.enums.WorkspaceCopyInclusion;
import com.smartsheet.api.models.enums.WorkspaceRemapExclusion;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the WorkspaceResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class WorkspaceResourcesImpl extends AbstractResources implements WorkspaceResources {
    /**
     * Represents the WorkspaceFolderResources.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private WorkspaceFolderResources folders;

    /**
     * Represents the ShareResources.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private ShareResources shares;

    /**
     * Constructor.
     *
     * Exceptions:
     *   - IllegalArgumentException : if any argument is
     *
     * @param smartsheet the smartsheet
     */
    public WorkspaceResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.shares = new ShareResourcesImpl(smartsheet, "workspaces");
        this.folders = new WorkspaceFolderResourcesImpl(smartsheet);
    }

    /**
     * List all workspaces.
     *
     * It mirrors to the following Smartsheet REST API method: GET /workspaces
     *
     * Exceptions:
     *
     *   - InvalidRequestException : if there is any problem with the REST API request
     *
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parameters the object containing the pagination parameters
     * @return all workspaces (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Workspace> listWorkspaces(PaginationParameters parameters) throws SmartsheetException {
        String path = "workspaces";

        if (parameters != null) {
            path += parameters.toQueryString();
        }
        return this.listResourcesWithWrapper(path, Workspace.class);
    }

    /**
     * Get a workspace.
     *
     * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}
     *
     * Exceptions:
     *
     *   - InvalidRequestException : if there is any problem with the REST API request
     *
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *
     *   - ResourceNotFoundException : if the resource can not be found
     *
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param id the id
     * @param loadAll load all contents in a workspace
     * @param includes used to specify the optional objects to include
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Workspace getWorkspace(long id, Boolean loadAll, EnumSet<SourceInclusion> includes) throws SmartsheetException {
        String path = "workspaces/" + id;

        // Add the parameters to a map and build the query string at the end
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        if (loadAll != null) {
            parameters.put("loadAll", Boolean.toString(loadAll));
        }

        path += QueryUtil.generateUrl(null, parameters);

        return this.getResource(path, Workspace.class);
    }

    /**
     * Create a workspace.
     *
     * It mirrors to the following Smartsheet REST API method: POST /workspaces
     *
     * Exceptions:
     *
     *   - IllegalArgumentException : if any argument is null
     *
     *   - InvalidRequestException : if there is any problem with the REST API request
     *
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param workspace the workspace to create, limited to the following required attributes: * name (string)
     * @return the created workspace
     * @throws SmartsheetException the smartsheet exception
     */
    public Workspace createWorkspace(Workspace workspace) throws SmartsheetException {
        return this.createResource("workspaces", Workspace.class, workspace);
    }

    /**
     * Update a workspace.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /workspace/{id}
     *
     * Exceptions:
     *
     *   - IllegalArgumentException : if any argument is null
     *
     *   - InvalidRequestException : if there is any problem with the REST API request
     *
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *
     *   - ResourceNotFoundException : if the resource can not be found
     *
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param workspace the workspace to update limited to the following attribute: * name (string)
     * @return the updated workspace (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Workspace updateWorkspace(Workspace workspace) throws SmartsheetException {
        return this.updateResource("workspaces/" + workspace.getId(), Workspace.class, workspace);
    }

    /**
     * Delete a workspace.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /workspace{id}
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param id the ID of the workspace
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteWorkspace(long id) throws SmartsheetException {
        this.deleteResource("workspaces/" + id, Workspace.class);
    }

    /**
     * Creates a copy of the specified workspace.
     *
     * It mirrors to the following Smartsheet REST API method: POST /workspaces/{workspaceId}/copy
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
    public Workspace copyWorkspace(long workspaceId, ContainerDestination containerDestination, EnumSet<WorkspaceCopyInclusion> includes, EnumSet<WorkspaceRemapExclusion> skipRemap) throws SmartsheetException {

        String path = "workspaces/" + workspaceId + "/copy";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("skipRemap", QueryUtil.generateCommaSeparatedList(skipRemap));

        path += QueryUtil.generateUrl(null, parameters);

        return this.createResource(path, Workspace.class, containerDestination);
    }
    /**
     * Return the WorkspaceFolderResources object that provides access to Folder resources associated with Workspace
     * resources.
     *
     * @return the workspace folder resources
     */
    public WorkspaceFolderResources folderResources() {
        return this.folders;
    }

    /**
     * Return the ShareResources object that provides access to Share resources associated with Workspace resources.
     *
     * @return the share resources
     */
    public ShareResources shareResources() {
        return this.shares;
    }
}
