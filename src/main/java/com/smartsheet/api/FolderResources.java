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
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.FolderCopyInclusion;
import com.smartsheet.api.models.enums.FolderRemapExclusion;
import com.smartsheet.api.models.enums.SourceInclusion;

import java.util.EnumSet;

/**
 * <p>This interface provides methods to access Folder resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface FolderResources {

    /**
     * <p>Get a folder.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /folder/{id}</p>
     *
     * @param folderId the folder id
     * @param includes the include parameters
     * @return the folder (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Folder getFolder(long folderId, EnumSet<SourceInclusion> includes) throws SmartsheetException;

    /**
     * <p>Update a folder.</p>
     *
     * @param folder the folder to update
     * @return the updated folder (note that if there is no such folder, this method will throw Resource Not Found
     * Exception rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Folder updateFolder(Folder folder) throws SmartsheetException;

    /**
     * <p>Delete a folder.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /folder{id}</p>
     *
     * @param folderId the folder id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteFolder(long folderId) throws SmartsheetException;

    /**
     * <p>List child folders of a given folder.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /folder/{id}/folders</p>
     *
     * @param parentFolderId the parent folder id
     * @param parameters the parameters for pagination
     * @return the child folders (note that an empty list will be returned if no child folder is found).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<Folder> listFolders(long parentFolderId, PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>Create a folder.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /folder/{id}/folders</p>
     *
     * @param parentFolderId the parent folder id
     * @param folder the folder to create
     * @return the created folder
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Folder createFolder(long parentFolderId, Folder folder) throws SmartsheetException;

    /**
     * <p>Creates a copy of the specified Folder.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/copy</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folderId the folder id
     * @param containerDestination describes the destination container
     * @param includes optional parameters to include
     * @param skipRemap optional parameters to exclude
     * @return the folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder copyFolder(long folderId, ContainerDestination containerDestination, EnumSet<FolderCopyInclusion> includes, EnumSet<FolderRemapExclusion> skipRemap) throws SmartsheetException;

    /**
     * <p>Moves the specified Folder to another location.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/move</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folderId the folder id
     * @param containerDestination describes the destination container
     * @return the folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder moveFolder(long folderId, ContainerDestination containerDestination) throws SmartsheetException;
}
