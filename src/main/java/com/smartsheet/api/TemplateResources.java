package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.Template;

/**
 * This interface provides methods to access Template resources.
 * 
 * Currently the following resources are supported, please refer to http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /templates
 * 
 * Thread Safety:
 * Implementation of this interface must be thread safe.
 */
public interface TemplateResources {
    /**
     * List all templates.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /templates
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * all templates (note that empty list will be returned if there is none)
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @return 
     */
    public List<Template> listTemplates();
}

