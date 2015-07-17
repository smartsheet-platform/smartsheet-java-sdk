package com.smartsheet.api;

/**
 * Created by anioding on 7/17/15.
 */
public interface AttachmentVersioningResources {

    /**
     * <p>Deletes an attachment, including all of its versions.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * DELETE /sheets/{sheetId}/attachments/{attachmentId}/versions</p>
     *
     * @param sheetId the sheet id
     * @param attachentId the attachment id
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteAllVersions(long sheetId, long attachentId) throws SmartsheetException;
}
