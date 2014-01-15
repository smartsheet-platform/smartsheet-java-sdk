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

import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.ObjectInclusion;

/**
 * This interface provides methods to access Home resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /home GET /home/folders POST /home/folders
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface HomeResources {
	/**
	 * Get a nested list of all Home objects, including sheets, workspaces and folders, and optionally reports and/or
	 * templates, as shown on the Home tab..
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /home
	 * 
	 * Parameters: - includes : used to specify the optional objects to include, currently TEMPLATES is supported.
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
	 * @param includes
	 * @return
	 */
	public Home getHome(EnumSet<ObjectInclusion> includes);

	/**
	 * Return the WorkspaceFolderResources object that provides access to Folder resources under home.
	 * 
	 * Returns: the WorkspaceFolderResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public HomeFolderResources folders();
}
