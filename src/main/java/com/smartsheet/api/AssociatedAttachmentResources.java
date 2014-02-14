package com.smartsheet.api;

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



import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.smartsheet.api.models.Attachment;

/**
 * <p>This interface provides methods to access Attachment resources that are associated to a resource object.</p>
 * 
 * <p>Various Smartsheet resources support attachments. Currently attachments can be added or retrieved
 * from sheets, rows and comments.</p>
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
	 * @param objectId the object id
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Attachment> listAttachments(long objectId) throws SmartsheetException;

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
	 * @param objectId the object id
	 * @param file the file
	 * @param contentType the content type
	 * @return the attachment
	 * @throws FileNotFoundException the file not found exception
	 * @throws SmartsheetException the smartsheet exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public Attachment attachFile(long objectId, File file, String contentType) throws FileNotFoundException, SmartsheetException, UnsupportedEncodingException;

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
	 * @param objectId the object id
	 * @param attachment the attachment
	 * @return the attachment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Attachment attachURL(long objectId, Attachment attachment) throws SmartsheetException;
}
