package com.smartsheet.api.internal;

import com.smartsheet.api.AttachmentResources;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.*;

/**
 * This is the implementation of the AttachmentResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AttachmentResourcesImpl implements AttachmentResources {
    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * 
     * Implementation:
     * super(smartsheet);
     * @param smartsheet 
     */
    public AttachmentResourcesImpl(SmartsheetImpl smartsheet) {
    }

    /**
     * Get an attachment.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /attachment/{id}
     * 
     * Parameters:
     * - id : the ID
     * 
     * Returns:
     * the resource (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.getResource("attachment/" + id, Attachment.class);
     * @param id 
     * @return 
     */
    public Attachment getAttachment(long id) {
        return null;
    }

    /**
     * Delete an attachment.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * DELETE /attachment{id}
     * 
     * Parameters:
     * - id : the ID of the attachment
     * 
     * Returns:
     * None
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * this.deleteResource("attachment/" + id, Attachment.class);
     * @param id 
     */
    public void deleteAttachment(long id) {
    }
}

