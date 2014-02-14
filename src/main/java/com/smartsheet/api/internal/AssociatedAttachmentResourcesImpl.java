package com.smartsheet.api.internal;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Attachment;

/**
 * This is the implementation of the AssociatedAttachmentResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AssociatedAttachmentResourcesImpl extends AbstractAssociatedResources 
	implements AssociatedAttachmentResources {
	
	/**
	 * Constructor.
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null or empty string
	 *
	 * @param smartsheet the smartsheet
	 * @param masterResourceType the master resource type (e.g. "sheet", "workspace")
	 */
	public AssociatedAttachmentResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
		super(smartsheet, masterResourceType);
	}

	/**
	 * List attachments of a given object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/attachments GET /row/{id}/attachments GET
	 * /comment/{id}/attachments
	 * 
	 * Exceptions:
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param objectId the ID of the object to which the attachments are associated
	 * @return the attachments (note that empty list will be returned if there is none)
	 * @throws SmartsheetException the smartsheet exception
	 */
	public List<Attachment> listAttachments(long objectId) throws SmartsheetException {
		return this.listResources(getMasterResourceType() + "/" + objectId + "/attachments", Attachment.class);
	}

	/**
	 * Attach a file to the object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/attachments POST /row/{id}/attachments
	 * POST /comment/{idd}/attachments
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null or empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param objectId the object id
	 * @param file the file to attach
	 * @param contentType the content type of the file
	 * @return the created attachment
	 * @throws FileNotFoundException the file not found exception
	 * @throws SmartsheetException the smartsheet exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public Attachment attachFile(long objectId, File file, String contentType) throws FileNotFoundException,
			SmartsheetException, UnsupportedEncodingException {
		Util.throwIfNull(objectId, file, contentType);
		Util.throwIfEmpty(contentType);
		
		return attachFile(objectId, file, contentType, file.length());
	}
	
	/**
	 * Attach file.
	 *
	 * @param objectId the object id
	 * @param file the file
	 * @param contentType the content type
	 * @param contentLength the content length
	 * @return the attachment
	 * @throws FileNotFoundException the file not found exception
	 * @throws SmartsheetException the smartsheet exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public Attachment attachFile(long objectId, File file, String contentType, Long contentLength) throws FileNotFoundException,
	SmartsheetException, UnsupportedEncodingException {
		Util.throwIfNull(file, contentType);
		
		HttpRequest request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(getMasterResourceType() + 
				"/" + objectId + "/attachments"), HttpMethod.POST);
		request.getHeaders().put("Content-Disposition", "attachment; filename=" + file.getName());
		HttpEntity entity = new HttpEntity();
		entity.setContentType(contentType);
		entity.setContent(new FileInputStream(file));
		entity.setContentLength(contentLength);
		request.setEntity(entity);

		HttpResponse response = this.getSmartsheet().getHttpClient().request(request);

		Attachment attachment = null;
		switch (response.getStatusCode()) {
		case 200:
			attachment = this.getSmartsheet().getJsonSerializer().deserializeResult(Attachment.class, 
					response.getEntity().getContent()).getResult();
			break;
		default:
			handleError(response);
		}
		
		this.getSmartsheet().getHttpClient().releaseConnection();

		return attachment;
	}

	/**
	 * Attach a URL to the object.
	 * 
	 * The URL can be a normal URL (attachmentType "URL"), a Google Drive URL (attachmentType "GOOGLE_DRIVE") or a
	 * Box.com URL (attachmentType "BOX_COM").
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/attachments POST /row/{id}/attachments
	 * POST /comment/{idd}/attachments
	 * 
	 * Parameters: - objectId : the ID of the object - attachment : the attachment object limited to the following
	 * attributes: * name * description (applicable when attaching to sheet or row only) * url * attachmentType *
	 * attachmentSubType
	 * 
	 * Returns: the created attachment
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param objectId the object id
	 * @param attachment the attachment
	 * @return the attachment
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Attachment attachURL(long objectId, Attachment attachment) throws SmartsheetException {
		Util.throwIfNull(objectId, attachment);
		
		return this.createResource(getMasterResourceType() + "/" + objectId + "/attachments",
				Attachment.class, attachment);
	}
}
