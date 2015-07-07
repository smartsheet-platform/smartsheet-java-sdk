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



import java.util.EnumSet;
import java.util.List;

import com.smartsheet.api.models.*;

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
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * DELETE /folder{id}</p>
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
	 * <p>It mirrors to the following Smartsheet REST API method:<br /> GET /folder/{id}/folders</p>
	 *
	 * @param parentFolderId the parent folder id
	 * @return the child folders (note that an empty list will be returned if no child folder is found).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public DataWrapper<Folder> listFolders(long parentFolderId, PaginationParameters parameters) throws SmartsheetException;

	/**
	 * <p>Create a folder.</p>
	 * 
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * POST /folder/{id}/folders</p>
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
}
