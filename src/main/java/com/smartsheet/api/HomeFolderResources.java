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
 * <p>This interface provides methods to access Folder resources under home.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface HomeFolderResources {
	
	/**
	 * List folders under home.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /home/folders
	 * 
	 * Parameters: None
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
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Folder> listFolders() throws SmartsheetException;

	/**
	 * Create a folder in home.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /home/folders
	 * 
	 * Parameters: - folder : the folder to create
	 * 
	 * Returns: the created folder
	 * 
	 * Exceptions: - IllegalArgumentException : if folder is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param folder the folder
	 * @return the folder
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Folder createFolder(Folder folder) throws SmartsheetException;
}
