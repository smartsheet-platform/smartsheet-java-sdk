package com.smartsheet.api.internal;

import com.smartsheet.api.internal.*;

import java.io.File;
import java.lang.*;
import java.util.List;

import com.smartsheet.api.models.*;

/**
 * This is the implementation of the AssociatedAttachmentResources for discussions.
 * 
 * It extends AssociatedAttachmentResourcesImpl and overrides attachFile/attachURL methods by throwing UnsupportedOperationException (since they're not supported for discussions).
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionAttachmentResources extends AssociatedAttachmentResourcesImpl {
    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null or empty string
     * 
     * Implementation:
     * super(smartsheet, "comment");
     * @param smartsheet 
     */
    public DiscussionAttachmentResources(SmartsheetImpl smartsheet) {
    	super(smartsheet, "comment");
    }

    /**
     * Attach a file to the object.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/attachments
     * POST /row/{id}/attachments
     * POST /comment/{idd}/attachments
     * 
     * Parameters:
     * - objectId : the ID of the object
     * - file : the file to attach
     * - contentType : the content type of the file
     * 
     * Returns:
     * the created attachment
     * 
     * Exceptions:
     * - UnsupportedOperationException : this exception is always thrown since this method is not supported by discussion resources.
     * 
     * Implementation:
     * Simply throw UnsupportedOperationException.
     * @param file 
     * @param objectId 
     * @param contentType 
     * @return 
     */
    public Attachment attachFile(long objectId, File file, String contentType) {
        return null;
    }

    /**
     * Attach a URL to the object.
     * 
     * The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a Box.com URL (attachmentType "BOX_COM").  
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/attachments
     * POST /row/{id}/attachments
     * POST /comment/{idd}/attachments
     * 
     * Parameters:
     * - objectId : the ID of the object
     * - attachment : the attachment object limited to the following attributes:
     * * name
     * * description (applicable when attaching to sheet or row only)
     * * url
     * * attachmentType
     * * attachmentSubType
     * 
     * Returns:
     * the created attachment
     * 
     * Exceptions:
     * - UnsupportedOperationException : this exception is always thrown since this method is not supported by discussion resources.
     * 
     * Implementation:
     * Simply throw UnsupportedOperationException.
     * @param objectId 
     * @param attachment 
     * @return 
     */
    public Attachment attachURL(long objectId, Attachment attachment) {
        return null;
    }
}

