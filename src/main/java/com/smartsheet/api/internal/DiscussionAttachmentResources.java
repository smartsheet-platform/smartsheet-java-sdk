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
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Attachment;

/**
 * This is the implementation of the AssociatedAttachmentResources for discussions.
 * 
 * It extends AssociatedAttachmentResourcesImpl and overrides attachFile/attachURL methods by throwing
 * UnsupportedOperationException (since they're not supported for discussions).
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class DiscussionAttachmentResources extends AssociatedAttachmentResourcesImpl {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet, "comment");
	 * 
	 * @param smartsheet
	 */
	public DiscussionAttachmentResources(SmartsheetImpl smartsheet) {
		super(smartsheet, "discussion");
	}

	/**
	 * Attach a file to the object.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/attachments POST /row/{id}/attachments
	 * POST /comment/{idd}/attachments
	 * 
	 * Parameters: - objectId : the ID of the object - file : the file to attach - contentType : the content type of the
	 * file
	 * 
	 * Returns: the created attachment
	 * 
	 * Exceptions: - UnsupportedOperationException : this exception is always thrown since this method is not supported
	 * by discussion resources.
	 * 
	 * Implementation: Simply throw UnsupportedOperationException.
	 * 
	 * @param file
	 * @param objectId
	 * @param contentType
	 * @return
	 */
	@Override
	public Attachment attachFile(long objectId, File file, String contentType) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Attachment attachFile(long objectId, File file, String contentType, Long contentLength) {
		throw new UnsupportedOperationException();
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
	 * Exceptions: - UnsupportedOperationException : this exception is always thrown since this method is not supported
	 * by discussion resources.
	 * 
	 * Implementation: Simply throw UnsupportedOperationException.
	 * 
	 * @param objectId
	 * @param attachment
	 * @return
	 */
	@Override
	public Attachment attachURL(long objectId, Attachment attachment) {
		throw new UnsupportedOperationException();
	}
}
