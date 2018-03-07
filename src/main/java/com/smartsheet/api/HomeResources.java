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


import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.enums.SourceInclusion;

import java.util.EnumSet;

/**
 * <p>This interface provides methods to access Home resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface HomeResources {

    /**
     * <p>Get a nested list of all Home objects, including sheets, workspaces and folders, and optionally reports and/or
     * templates, as shown on the Home tab.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /home</p>
     *
     * @param includes used to specify the optional objects to include.
     * @return the home resource (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Home getHome(EnumSet<SourceInclusion> includes) throws SmartsheetException;

    /**
     * <p>Return the HomeFolderResources object that provides access to Folder resources under home.</p>
     *
     * @return the home folder resources
     */
    public HomeFolderResources folderResources();
}
