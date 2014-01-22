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



import com.smartsheet.api.AttachmentResources;
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
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public AttachmentResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
	}

	/**
	 * Get an attachment.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /attachment/{id}
	 * 
	 * Parameters: - id : the ID
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.getResource("attachment/" + id, Attachment.class);
	 * 
	 * @param id
	 * @return
	 */
	public Attachment getAttachment(long id) {
		return null;
	}

	/**
	 * Delete an attachment.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /attachment{id}
	 * 
	 * Parameters: - id : the ID of the attachment
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: this.deleteResource("attachment/" + id, Attachment.class);
	 * 
	 * @param id
	 */
	public void deleteAttachment(long id) {
	}
}
