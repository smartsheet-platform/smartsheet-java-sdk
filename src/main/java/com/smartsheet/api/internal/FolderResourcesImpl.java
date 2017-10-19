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

import com.smartsheet.api.FolderResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.FolderCopyInclusion;
import com.smartsheet.api.models.enums.FolderRemapExclusion;
import com.smartsheet.api.models.enums.SourceInclusion;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the FolderResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class FolderResourcesImpl extends AbstractResources implements FolderResources {

    /**
     * Constructor.
     *
     * Parameters: - smartsheet : the SmartsheetImpl
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public FolderResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Get a folder.
     *
     * It mirrors to the following Smartsheet REST API method: GET /folder/{id}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folderId the folder id
     * @param includes the include parameters
     * @return the folder (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder getFolder(long folderId, EnumSet<SourceInclusion> includes) throws SmartsheetException {
        String path = "folders/" + folderId;
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        path += QueryUtil.generateUrl(null, parameters);

        return this.getResource(path, Folder.class);
    }

    /**
     * Update a folder.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /folder/{id}
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folder the folder to update
     * @return the updated folder (note that if there is no such folder, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder updateFolder(Folder folder) throws SmartsheetException {

        return this.updateResource("folders/" + folder.getId(), Folder.class, folder);
    }

    /**
     * Delete a folder.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /folder{id}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folderId the folder id
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteFolder(long folderId) throws SmartsheetException {

        this.deleteResource("folders/" + folderId, Folder.class);
    }

    /**
     * List child folders of a given folder.
     *
     * It mirrors to the following Smartsheet REST API method: GET /folder/{id}/folders
     *
     * Parameters: - parentFolderId : the parent folder ID
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parentFolderId the parent folder id
     * @param parameters the parameters for pagination
     * @return the child folders (note that empty list will be returned if no child folder found)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Folder> listFolders(long parentFolderId, PaginationParameters parameters) throws SmartsheetException {
        String path = "folders/" + parentFolderId + "/folders";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Folder.class);
    }

    /**
     * Create a folder.
     *
     * It mirrors to the following Smartsheet REST API method: POST /folder/{id}/folders
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parentFolderId the parent folder id
     * @param folder the folder to create
     * @return the folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder createFolder(long parentFolderId, Folder folder) throws SmartsheetException {

        return this.createResource("folders/" + parentFolderId + "/folders", Folder.class, folder);
    }

    /**
     * Creates a copy of the specified Folder.
     *
     * It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/copy
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
    public Folder copyFolder(long folderId, ContainerDestination containerDestination, EnumSet<FolderCopyInclusion> includes, EnumSet<FolderRemapExclusion> skipRemap) throws SmartsheetException {

        String path = "folders/" + folderId + "/copy";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("skipRemap", QueryUtil.generateCommaSeparatedList(skipRemap));

        path += QueryUtil.generateUrl(null, parameters);

        return this.createResource(path, Folder.class, containerDestination);
    }

    /**
     * Moves the specified Folder to another location.
     *
     * It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/move
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
    public Folder moveFolder(long folderId, ContainerDestination containerDestination) throws SmartsheetException {

        String path = "folders/" + folderId + "/move";
        return this.createResource(path, Folder.class, containerDestination);
    }
}
