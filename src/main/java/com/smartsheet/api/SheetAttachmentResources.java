package com.smartsheet.api;

import com.smartsheet.api.models.Attachment;

/**
 * Created by anioding on 7/16/15.
 */
public interface SheetAttachmentResources {

    /**
     * <p>Attach a URL to a sheet.</p>
     *
     * <p>The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
     * Box.com URL (attachmentType "BOX_COM").</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     *   POST /sheets/{sheetId}/attachments
     *
     * @param sheetId the sheet id
     * @param attachment the attachment object
     * @return the attachment object
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Attachment attachUrl(long sheetId, Attachment attachment) throws SmartsheetException;
}
