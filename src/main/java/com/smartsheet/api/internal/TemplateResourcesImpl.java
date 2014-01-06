package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.TemplateResources;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.Template;

/**
 * This is the implementation of the TemplateResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class TemplateResourcesImpl implements TemplateResources {
    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is
     * 
     * Implementation:
     * super(smartsheet);
     * @param smartsheet 
     */
    public TemplateResourcesImpl(SmartsheetImpl smartsheet) {
    }

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
     * 
     * Implementation:
     * return this.listResource("templates", Template.class);
     * @return 
     */
    public List<Template> listTemplates() {
        return null;
    }
}

