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



import java.util.List;

import com.smartsheet.api.models.Folder;

/**
 * This interface provides methods to access Folder resources that are associated to a workspace object.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /workspace/{id}/folders POST /workspace/{id}/folders
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface WorkspaceFolderResources {
	/**
	 * List folders of a given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/folders
	 * 
	 * Parameters: - workspaceId : the ID of the workspace
	 * 
	 * Returns: the folders (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * @param workspaceId
	 * @return
	 */
	public List<Folder> listFolders(long workspaceId);

	/**
	 * Create a folder in the workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/folders
	 * 
	 * Parameters: - workspaceId : the workspace ID - folder : the folder to create
	 * 
	 * Returns: the created folder
	 * 
	 * Exceptions: - IllegalArgumentException : if folder is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param folder
	 * @param workspaceId
	 * @return
	 */
	public Folder createFolder(long workspaceId, Folder folder);
}
