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



import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.TemplateResources;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Template;

/**
 * This is the implementation of the TemplateResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class TemplateResourcesImpl extends AbstractResources implements TemplateResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is
     *
     * @param smartsheet the smartsheet
     */
    public TemplateResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * List user-created templates.
     *
     * It mirrors to the following Smartsheet REST API method: GET /templates
     *
     * @param parameters the pagination parameters
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @return all templates (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Template> listUserCreatedTemplates(PaginationParameters parameters) throws SmartsheetException {
        String path = "templates";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Template.class);
    }

    /**
     * List public templates.
     *
     * It mirrors to the following Smartsheet REST API method: GET /templates/public
     *
     * @param parameters the pagination parameters
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @return all templates (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Template> listPublicTemplates(PaginationParameters parameters) throws SmartsheetException {
        String path = "templates/public";

        if (parameters != null) {
            path += parameters.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Template.class);
    }
}
