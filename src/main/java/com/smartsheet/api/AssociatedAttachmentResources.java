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
import java.io.InputStream;
import java.util.List;

import com.smartsheet.api.models.Attachment;

/**
 * <p>This interface provides methods to access Attachment resources that are associated to a resource object.</p>
 * 
 * <p>Various Smartsheet resources support attachments. Currently attachments can be added or retrieved
 * from sheets, rows and comments.</p>
 */
public interface AssociatedAttachmentResources {
//	/**
//	 * <p>Attach a file to the object.</p>
//	 *
//	 * <p>It mirrors to the following Smartsheet REST API method:<br />
//	 *   POST /sheet/{id}/attachments POST /row/{id}/attachments<br />
//	 *   POST /comment/{idd}/attachments</p>
//	 *
//	 * @param objectId the id of the object
//	 * @param file the file to attach
//	 * @param contentType the content type of the file
//	 * @return the created attachment
//	 * @throws FileNotFoundException the file not found exception
//	 * @throws IllegalArgumentException if any argument is null or empty string
//	 * @throws InvalidRequestException if there is any problem with the REST API request
//	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
//	 * @throws ResourceNotFoundException if the resource cannot be found
//	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
//	 * @throws SmartsheetException if there is any other error during the operation
//	 */
//	public Attachment attachFile(long objectId, File file, String contentType) throws FileNotFoundException, SmartsheetException;

//	/**
//	 * <p>Attach a file to the object.</p>
//	 *
//	 * <p>It mirrors to the following Smartsheet REST API method:<br />
//	 *   POST /sheet/{id}/attachments POST /row/{id}/attachments<br />
//	 *   POST /comment/{idd}/attachments</p>
//	 *
//	 * @param objectId the id of the object
//	 * @param file the file to attach
//	 * @param contentType the content type of the file
//	 * @param contentLength the size of the file in bytes.
//	 * @param attachmentName the name of the file.
//	 * @return the created attachment
//	 * @throws IllegalArgumentException if any argument is null or empty string
//	 * @throws InvalidRequestException if there is any problem with the REST API request
//	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
//	 * @throws ResourceNotFoundException if the resource cannot be found
//	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
//	 * @throws SmartsheetException if there is any other error during the operation
//	 */
//	public Attachment attachFile(long objectId, InputStream inputStream, String contentType, long contentLength, String attachmentName) throws SmartsheetException;
}
