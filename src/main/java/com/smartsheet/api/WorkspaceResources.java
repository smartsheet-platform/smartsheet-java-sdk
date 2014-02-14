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

import com.smartsheet.api.models.Workspace;

/**
 * This interface provides methods to access Workspace resources.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface WorkspaceResources {
	
	/**
	 * List all workspaces.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspaces
	 * 
	 * Parameters: None
	 * 
	 * Returns: all workspaces (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Workspace> listWorkspaces() throws SmartsheetException;

	/**
	 * Get a workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}
	 * 
	 * Parameters: - id : the ID
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 *
	 * @param id the id
	 * @return the workspace
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Workspace getWorkspace(long id) throws SmartsheetException;

	/**
	 * Create a workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspaces
	 * 
	 * Parameters: - worksapace : the workspace to create, limited to the following required attributes: * name (string)
	 * 
	 * Returns: the created workspace
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param workspace the workspace
	 * @return the workspace
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Workspace createWorkspace(Workspace workspace) throws SmartsheetException;

	/**
	 * Update a workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /workspace/{id}
	 * 
	 * Parameters: - workspace : the workspace to update limited to the following attribute: * name (string)
	 * 
	 * Returns: the updated workspace (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param workspace the workspace
	 * @return the workspace
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Workspace updateWorkspace(Workspace workspace) throws SmartsheetException;

	/**
	 * Delete a workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /workspace{id}
	 * 
	 * Parameters: - id : the ID of the workspace
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 *
	 * @param id the id
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void deleteWorkspace(long id) throws SmartsheetException;

	/**
	 * Return the WorkspaceFolderResources object that provides access to Folder resources associated with Workspace
	 * resources.
	 * 
	 * Returns: the WorkspaceFolderResources object
	 * 
	 * Exceptions: None
	 *
	 * @return the workspace folder resources
	 */
	public WorkspaceFolderResources folders();

	/**
	 * Return the ShareResources object that provides access to Share resources associated with Workspace resources.
	 * 
	 * Returns: the ShareResources object
	 * 
	 * Exceptions: None
	 *
	 * @return the share resources
	 */
	public ShareResources shares();
}
