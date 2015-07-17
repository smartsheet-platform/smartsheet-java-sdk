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
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.SmartsheetException;
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
			SmartsheetException {
		Util.throwIfNull(objectId, file, contentType);
		Util.throwIfEmpty(contentType);
		
		return attachFile(objectId, new FileInputStream(file), contentType, file.length(), file.getName());
	}
	
	/**
	 * Attach file.
	 *
	 * @param objectId the object id
	 * @param file the file
	 * @param contentType the content type
	 * @param contentLength the content length
	 * @param attachmentName the name of the attachment
	 * @return the attachment
	 * @throws FileNotFoundException the file not found exception
	 * @throws SmartsheetException the smartsheet exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public Attachment attachFile(long objectId, InputStream inputStream, String contentType, long contentLength, String attachmentName)
			throws SmartsheetException {
		Util.throwIfNull(inputStream, contentType);
		return super.attachFile(getMasterResourceType() +"/" + objectId + "/attachments", inputStream, contentType, contentLength, attachmentName);
	}

}
