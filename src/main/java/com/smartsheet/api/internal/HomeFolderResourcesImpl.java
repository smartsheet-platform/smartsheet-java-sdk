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



import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * This is the implementation of the HomeFolderResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class HomeFolderResourcesImpl extends AbstractResources implements HomeFolderResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public HomeFolderResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * List folders under home.
     *
     * It mirrors to the following Smartsheet REST API method: GET /home/folders
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parameters the pagination parameters
     * @return the folders (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Folder> listFolders(PaginationParameters parameters) throws SmartsheetException {

        String path = "home/folders";

        if (parameters != null) {
            path += parameters.toQueryString();
        }
        return this.listResourcesWithWrapper(path, Folder.class);
    }

    /**
     * Create a folder in home.
     *
     * It mirrors to the following Smartsheet REST API method: POST /home/folders
     *
     * Exceptions:
     *   IllegalArgumentException : if folder is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param folder the folder to create
     * @return the created folder
     * @throws SmartsheetException the smartsheet exception
     */
    public Folder createFolder(Folder folder) throws SmartsheetException {
        return this.createResource("home/folders", Folder.class, folder);
    }
}
