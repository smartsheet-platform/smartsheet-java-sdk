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


import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.WorkspaceFolderResources;
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * This is the implementation of the WorkspaceFolderResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class WorkspaceFolderResourcesImpl extends AbstractResources implements WorkspaceFolderResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is
     *
     * @param smartsheet the smartsheet
     */
    public WorkspaceFolderResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * List folders of a given workspace.
     *
     * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/folders
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param workspaceId the ID of the workspace
     * @param parameters the pagination parameters
     * @return the folders (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Folder> listFolders(long workspaceId, PaginationParameters parameters) throws SmartsheetException {
        String path = "workspaces/" + workspaceId + "/folders";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Folder.class);
    }

    /**
     * Create a folder in the workspace.
     *
     * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/folders
     *
     * Exceptions:
     *   - IllegalArgumentException : if folder is null
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param workspaceId the workspace id
     * @param folder the folder to create
     * @return the created folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder createFolder(long workspaceId, Folder folder) throws SmartsheetException {
        return this.createResource("workspaces/" + workspaceId + "/folders", Folder.class, folder);
    }
}
