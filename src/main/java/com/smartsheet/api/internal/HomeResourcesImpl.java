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
import com.smartsheet.api.HomeResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.enums.SourceInclusion;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the HomeResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class HomeResourcesImpl extends AbstractResources implements HomeResources {
    /**
     * Represents the HomeFolderResources.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private HomeFolderResources folders;

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public HomeResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.folders = new HomeFolderResourcesImpl(smartsheet);
    }

    /**
     * Get a nested list of all Home objects, including sheets, workspaces and folders, and optionally reports and/or
     * templates, as shown on the Home tab..
     *
     * It mirrors to the following Smartsheet REST API method: GET /home
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param includes used to specify the optional objects to include, currently TEMPLATES is supported.
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Home getHome(EnumSet<SourceInclusion> includes) throws SmartsheetException {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));

        String path = QueryUtil.generateUrl("home", parameters);

        return this.getResource(path, Home.class);
    }

    /**
     * Return the HomeFolderResources object that provides access to Folder resources under home.
     *
     * @return the home folder resources
     */
    public HomeFolderResources folderResources() {
        return this.folders;
    }
}
