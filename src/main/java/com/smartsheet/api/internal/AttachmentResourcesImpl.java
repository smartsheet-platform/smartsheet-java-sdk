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
import java.io.InputStream;
import java.util.List;

import com.smartsheet.api.AttachmentResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Attachment;

/**
 * This is the implementation of the AttachmentResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AttachmentResourcesImpl extends AbstractResources implements AttachmentResources {
	
	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public AttachmentResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

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
	 * @param id the id
	 * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException 
	 * rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Attachment getAttachment(long id) throws SmartsheetException {
		return this.getResource("attachment/" + id, Attachment.class);
	}

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
	public void deleteAttachment(long sheetId, long attachmentId) throws SmartsheetException {
		this.deleteResource("sheets/" + sheetId + "/attachment/" + attachmentId, Attachment.class);
	}

	@Override
	public void deleteAllAttachmentVersions(long id) throws SmartsheetException {
		this.deleteResource("attachment/"+ id + "/versions", Attachment.class);
	}

	@Override
	public List<Attachment> listAttachmentVersions(long id) throws SmartsheetException {
		return this.listResources("attachment/" + id + "/versions", Attachment.class);
	}

	@Override
	public Attachment attachNewVersion(long attachmentId, File file, String contentType) throws FileNotFoundException, SmartsheetException {
		Util.throwIfNull(attachmentId, file, contentType);
		Util.throwIfEmpty(contentType);
		
		return attachNewVersion(attachmentId, new FileInputStream(file), contentType, file.length(), file.getName());
	}

	@Override
	public Attachment attachNewVersion (long attachmentId, InputStream inputStream, String contentType, long contentLength, String attachmentName)
			throws SmartsheetException {
		return super.attachFile("attachment/"+ attachmentId +"/versions", inputStream, contentType, contentLength, attachmentName);
	}
}
