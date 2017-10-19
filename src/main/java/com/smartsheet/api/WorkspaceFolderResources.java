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


import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * T<p>his interface provides methods to access Folder resources that are associated to a workspace object.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface WorkspaceFolderResources {

    /**
     * <p>List folders of a given workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/folders</p>
     *
     * @param workspaceId the workspace id
     * @param parameters the pagination parameters
     * @return the list of folders (note that an empty list will be returned if there are none)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Folder> listFolders(long workspaceId, PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>Create a folder in the workspace.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/folders</p>
     *
     * @param workspaceId the workspace id
     * @param folder the folder to create
     * @return the created folder
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Folder createFolder(long workspaceId, Folder folder) throws SmartsheetException;
}
