package com.smartsheet.api;

import java.io.File;
import java.util.List;

import com.smartsheet.api.models.Attachment;

/**
 * This interface provides methods to access Attachment resources that are associated to a resource object.
 * 
 * Note that various Smartsheet resources support attachments.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /sheet/{id}/attachments POST /sheet/{id}/attachments GET /row/{id}/attachments POST /row/{id}/attachments GET
 * /comment/{id}/attachments POST /comment/{id}/attachments
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface AssociatedAttachmentResources {
	/**
	 * List attachments of a given object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/attachments GET /row/{id}/attachments GET
	 * /comment/{id}/attachments
	 * 
	 * Parameters: - objectId : the ID of the object to which the attachments are associated
	 * 
	 * Returns: the attachments (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * @param objectId
	 * @return
	 */
	public List<Attachment> listAttachments(long objectId);

	/**
	 * Attach a file to the object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/attachments POST /row/{id}/attachments
	 * POST /comment/{idd}/attachments
	 * 
	 * Parameters: - objectId : the ID of the object to share - file : the file to attach - contentType : the content
	 * type of the file
	 * 
	 * Returns: the created attachment
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string - InvalidRequestException : if
	 * there is any problem with the REST API request - AuthorizationException : if there is any problem with the REST
	 * API authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param file
	 * @param objectId
	 * @param contentType
	 * @return
	 */
	public Attachment attachFile(long objectId, File file, String contentType);

	/**
	 * Attach a URL to the object.
	 * 
	 * The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
	 * Box.com URL (attachmentType "BOX_COM").
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/attachments POST /row/{id}/attachments
	 * POST /comment/{idd}/attachments
	 * 
	 * Parameters: - objectId : the ID of the object to share - attachment : the attachment object limited to the
	 * following attributes: * name * description (applicable when attaching to sheet or row only) * url *
	 * attachmentType * attachmentSubType
	 * 
	 * Returns: the created attachment
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param objectId
	 * @param attachment
	 * @return
	 */
	public Attachment attachURL(long objectId, Attachment attachment);
}
