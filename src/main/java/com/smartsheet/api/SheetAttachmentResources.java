package com.smartsheet.api;

import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.PaginationParameters;

import java.util.List;

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

    /**
     * Delete an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/attachments/{attachmentId}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the ID of the sheet
     * @param attachmentId the ID of the attachment
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteAttachment(long sheetId, long attachmentId) throws SmartsheetException;

    /**
     * Get an attachment.
     *
     * It mirrors to the following Smartsheet REST API method: GET /attachment/{id}
     *
     * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param attachmentId the attachment id
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public Attachment getAttachment(long sheetId, long attachmentId) throws SmartsheetException;

    /**
     * Gets a list of all Attachments that are on the Sheet, including Sheet, Row, and Discussion level Attachments.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/attachments
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the ID of the sheet to which the attachments are associated
     * @param parameters the pagination parameters
     * @return the attachments (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public DataWrapper<Attachment> listAttachments(long sheetId, PaginationParameters parameters) throws SmartsheetException;

    /**
     * Get discussion attachment.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/discussions/{discussionId}/attachments
     *
     * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet id
     * @param discussionId the discussion id
     * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public DataWrapper<Attachment> getDiscussionAttachments(long sheetId, long discussionId, PaginationParameters parameters) throws SmartsheetException;
}
