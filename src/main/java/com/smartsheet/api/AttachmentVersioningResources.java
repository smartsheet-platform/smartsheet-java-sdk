package com.smartsheet.api;

import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.PaginationParameters;

import java.util.List;

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

    /**
     * <p>Get all versions of an attachment.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * GET /sheets/{sheetId}/attachments/{attachmentId}/versions</p>
     *
     * @param sheetId the id
     * @param attachmentId the attachment id
     * @param parameters the pagination paramaters
     * @return the attachment (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public DataWrapper<Attachment> listAllVersions(long sheetId, long attachmentId, PaginationParameters parameters) throws SmartsheetException;
}
