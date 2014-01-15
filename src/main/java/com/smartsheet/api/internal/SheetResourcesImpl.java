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



import java.io.OutputStream;
import java.util.EnumSet;
import java.util.List;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.AssociatedDiscussionResources;
import com.smartsheet.api.ShareResources;
import com.smartsheet.api.SheetColumnResources;
import com.smartsheet.api.SheetResources;
import com.smartsheet.api.SheetRowResources;
import com.smartsheet.api.models.ObjectInclusion;
import com.smartsheet.api.models.PaperSize;
import com.smartsheet.api.models.Sheet;
import com.smartsheet.api.models.SheetEmail;
import com.smartsheet.api.models.SheetPublish;

/**
 * This is the implementation of the SheetResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetResourcesImpl implements SheetResources {
	/**
	 * Represents the ShareResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private ShareResources shares;
	/**
	 * Represents the SheetRowResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private SheetRowResources rows;
	/**
	 * Represents the SheetColumnResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private SheetColumnResources columns;
	/**
	 * Represents the AssociatedAttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private AssociatedAttachmentResources attachments;
	/**
	 * Represents the AssociatedDiscussionResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private AssociatedDiscussionResources discussions;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet); this.shares = new ShareResourcesImpl(smartsheet, "sheet"); this.rows = new
	 * SheetRowResourcesImpl(smartsheet); this.columns = new SheetColumnResourcesImpl(smartsheet); this.attachments =
	 * new AssociatedAttachmentResourcesImpl(smartsheet, "sheet"); this.discussions = new
	 * AssociatedDiscussionResourcesImpl(smartsheet, "sheet");
	 * 
	 * @param smartsheet
	 */
	public SheetResourcesImpl(SmartsheetImpl smartsheet) {
	}

	/**
	 * List all sheets.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheets
	 * 
	 * Parameters: None
	 * 
	 * Returns: all sheets (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.listResources("sheets", Sheet.class);
	 * 
	 * @return
	 */
	public List<Sheet> listSheets() {
		return null;
	}

	/**
	 * List all sheets in the organization.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /users/sheets
	 * 
	 * Parameters: None
	 * 
	 * Returns: all sheets (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.listResources("users/sheets", Sheet.class);
	 * 
	 * @return
	 */
	public List<Sheet> listOrganizationSheets() {
		return null;
	}

	/**
	 * Get a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}
	 * 
	 * Parameters: - id : the ID - includes : used to specify the optional objects to include, currently DISCUSSIONS and
	 * ATTACHMENTS are supported.
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
	 * Implementation: String path = "sheet/" + id; if (includes != null && !includes.isEmpty()) { path += "?include=";
	 * for (ObjectInclusion oi : includes) { path += oi.name().toLowerCase() + ","; } }
	 * 
	 * return this.getResource(path, Sheet.class);
	 * 
	 * @param id
	 * @return
	 */
	public Sheet getSheet(long id, EnumSet<ObjectInclusion> includes) {
		return null;
	}

	/**
	 * Get a sheet as an Excel file.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id} with "application/vnd.ms-excel" Accept
	 * HTTP header
	 * 
	 * Parameters: - id : the ID - paperSize : the optional paper size - outputStream : the OutputStream to which the
	 * Excel file will be written
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if outputStream is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: getSheetAsFile(id, paperSize, outputStream, "application/vnd.ms-excel");
	 * 
	 * @param id
	 * @param outputStream
	 * @param paperSize
	 */
	public void getSheetAsExcel(long id, OutputStream outputStream, PaperSize paperSize) {
	}

	/**
	 * Get a sheet as a PDF file.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id} with "application/pdf" Accept HTTP header
	 * 
	 * Parameters: - id : the ID - paperSize : the optional paper size - outputStream : the OutputStream to which the
	 * PDF file will be written
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if outputStream is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: getSheetAsFile(id, paperSize, outputStream, "application/pdf");
	 * 
	 * @param id
	 * @param outputStream
	 * @param paperSize
	 */
	public void getSheetAsPDF(long id, OutputStream outputStream, PaperSize paperSize) {
	}

	/**
	 * Create a sheet in default "Sheets" collection.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheets
	 * 
	 * Parameters: - sheet : the sheet to create, limited to the following required attributes: * name (string) *
	 * columns (array of Column objects, limited to the following attributes) - title - primary - type - symbol -
	 * options
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource("sheets", Sheet.class, sheet);
	 * 
	 * @param sheet
	 * @return
	 */
	public Sheet createSheet(Sheet sheet) {
		return null;
	}

	/**
	 * Create a sheet (from existing sheet or template) in default "Sheets" collection.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheets
	 * 
	 * Parameters: - sheet : the sheet to create, limited to the following required attributes: * name (string) * fromId
	 * (number): ID of the Sheet or Template from which to create the sheet. - includes : used to specify the optional
	 * objects to include, currently DATA, DISCUSSIONS and ATTACHMENTS are supported.
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: String path = "sheets"; if (includes != null && !includes.isEmpty()) { path += "?include="; for
	 * (ObjectInclusion oi : includes) { path += oi.name().toLowerCase() + ","; } } return this.createResource(path,
	 * Sheet.class, sheet);
	 * 
	 * @param sheet
	 * @return
	 */
	public Sheet createSheetFromExisting(Sheet sheet, EnumSet<ObjectInclusion> includes) {
		return null;
	}

	/**
	 * Create a sheet in given folder.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /folder/{folderId}/sheets
	 * 
	 * Parameters: - folderId : the folder ID - sheet : the sheet to create, limited to the following required
	 * attributes: * name (string) * columns (array of Column objects, limited to the following attributes) - title -
	 * primary - type - symbol - options
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource("folder/" + folderId + "/sheets", Sheet.class, sheet);
	 * 
	 * @param sheet
	 * @param folderId
	 * @return
	 */
	public Sheet createSheetInFolder(long folderId, Sheet sheet) {
		return null;
	}

	/**
	 * Create a sheet in given folder.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /folder/{folderId}/sheets
	 * 
	 * Parameters: - folderId : the folder ID - sheet : the sheet to create, limited to the following required
	 * attributes: * name (string) * columns (array of Column objects, limited to the following attributes) - title -
	 * primary - type - symbol - options
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: String path = "folder/" + folderId + "/sheets"; if (includes != null && !includes.isEmpty()) {
	 * path += "?include="; for (ObjectInclusion oi : includes) { path += oi.name().toLowerCase() + ","; } }
	 * 
	 * return this.createResource(path, Sheet.class, sheet);
	 * 
	 * @param folderId
	 * @return
	 */
	public Sheet createSheetInFolderFromExisting(long folderId, Sheet sheet, EnumSet<ObjectInclusion> includes) {
		return null;
	}

	/**
	 * Create a sheet in given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{workspaceId}/sheets
	 * 
	 * Parameters: - workspaceId : the workspace ID - sheet : the sheet to create, limited to the following required
	 * attributes: * name (string) * columns (array of Column objects, limited to the following attributes) - title -
	 * primary - type - symbol - options
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource("workspace/" + workspaceId + "/sheets", Sheet.class, sheet);
	 * 
	 * @param workspaceId
	 * @param sheet
	 * @return
	 */
	public Sheet createSheetInWorkspace(long workspaceId, Sheet sheet) {
		return null;
	}

	/**
	 * Create a sheet (from existing sheet or template) in given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{workspaceId}/sheets
	 * 
	 * Parameters: - workspaceId : the workspace ID - sheet : the sheet to create, limited to the following required
	 * attributes: * name (string) * fromId (number): ID of the Sheet or Template from which to create the sheet. -
	 * includes : used to specify the optional objects to include, currently DATA, DISCUSSIONS and ATTACHMENTS are
	 * supported.
	 * 
	 * Returns: the created sheet
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: String path = "workspace/" + workspaceId + "/sheets"; if (includes != null &&
	 * !includes.isEmpty()) { path += "?include="; for (ObjectInclusion oi : includes) { path += oi.name().toLowerCase()
	 * + ","; } }
	 * 
	 * return this.createResource(path, Sheet.class, sheet);
	 * 
	 * @param workspaceId
	 * @return
	 */
	public Sheet createSheetInWorkspaceFromExisting(long workspaceId, Sheet sheet, EnumSet<ObjectInclusion> includes) {
		return null;
	}

	/**
	 * Delete a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /sheet{id}
	 * 
	 * Parameters: - id : the ID of the sheet
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
	 * Implementation: this.deleteResource("sheet/" + id, Sheet.class);
	 * 
	 * @param id
	 */
	public void deleteSheet(long id) {
	}

	/**
	 * Update a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /sheet/{id}
	 * 
	 * Parameters: - sheet : the sheet to update limited to the following attribute: * name (string)
	 * 
	 * Returns: the updated sheet (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.updateResource("sheet/" + sheet.getId(), Sheet.class, sheet);
	 * 
	 * @param sheet
	 * @return
	 */
	public Sheet updateSheet(Sheet sheet) {
		return null;
	}

	/**
	 * Get a sheet version.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/version
	 * 
	 * Parameters: - id : the ID
	 * 
	 * Returns: the sheet version (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.getResource("sheet/" + id + "/version", Sheet.class).getVersion();
	 * 
	 * @param id
	 * @return
	 */
	public int getSheetVersion(long id) {
		return 0;
	}

	/**
	 * Send a sheet as a PDF attachment via email to the designated recipients.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{sheetId}/emails
	 * 
	 * Parameters: - id : the ID of the sheet - email : the SheetEmail
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: this.createResource("sheet/" + id + "/emails", SheetEmail.class, email);
	 * 
	 * @param id
	 * @param email
	 */
	public void sendSheet(long id, SheetEmail email) {
	}

	/**
	 * Return the ShareResources object that provides access to Share resources associated with Sheet resources.
	 * 
	 * Returns: the ShareResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.shares;
	 * 
	 * @return
	 */
	public ShareResources shares() {
		return null;
	}

	/**
	 * Return the SheetRowResources object that provides access to Row resources associated with Sheet resources.
	 * 
	 * Returns: the SheetRowResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.rows;
	 * 
	 * @return
	 */
	public SheetRowResources rows() {
		return null;
	}

	/**
	 * Return the SheetColumnResources object that provides access to Column resources associated with Sheet resources.
	 * 
	 * Returns: the SheetColumnResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.columns;
	 * 
	 * @return
	 */
	public SheetColumnResources columns() {
		return null;
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Sheet resources.
	 * 
	 * Returns: the AssociatedAttachmentResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.attachments;
	 * 
	 * @return
	 */
	public AssociatedAttachmentResources attachments() {
		return null;
	}

	/**
	 * Return the AssociatedDiscussionResources object that provides access to discussion resources associated with
	 * Sheet resources.
	 * 
	 * Returns: the AssociatedDiscussionResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.discussions;
	 * 
	 * @return
	 */
	public AssociatedDiscussionResources discussions() {
		return null;
	}

	/**
	 * Get the status of the Publish settings of the sheet, including the URLs of any enabled publishings.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{sheetId}/publish
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
	 * Implementation: return this.getResource("sheet/" + id + "/publish", SheetPublish.class);
	 * 
	 * @param id
	 * @return
	 */
	public SheetPublish getPublishStatus(long id) {
		return null;
	}

	/**
	 * Sets the publish status of a sheet and returns the new status, including the URLs of any enabled publishings.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /sheet/{sheetId}/publish
	 * 
	 * Parameters: - id : the ID - publish : the SheetPublish object limited to the following attributes *
	 * readOnlyLiteEnabled * readOnlyFullEnabled * readWriteEnabled * icalEnabled
	 * 
	 * Returns: the updated SheetPublish (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.updateResource("sheet/" + id + "/publish", SheetPublish.class, publish);
	 * 
	 * @param id
	 * @param publish
	 * @return
	 */
	public SheetPublish updatePublishStatus(long id, SheetPublish publish) {
		return null;
	}

	/**
	 * Get a sheet as a file.
	 * 
	 * Parameters: - id : the ID - paperSize : the optional paper size - outputStream : the OutputStream to which the
	 * Excel file will be written - contentType : the content type
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
	 * Implementation: String path = "sheet/" + id; if (paperSize != null) { path += "?paperSize=" + paperSize; }
	 * 
	 * HttpRequest request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(path), HttpMethod.GET);
	 * request.getHeaders().put("Accept", contentType);
	 * 
	 * HttpResponse response = this.getSmartsheet().getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: BufferedInputStream bis = new
	 * BufferedInputStream(response.getEntity().getContent()); BufferedOutputStream bos = new
	 * BufferedOutputStream(outputStream); // Read from the BufferedInputStream and write to BufferedOutputStream ...
	 * default: handleError(response); }
	 * 
	 * @param id
	 * @param outputStream
	 * @param paperSize
	 * @param contentType
	 */
	private void getSheetAsFile(long id, PaperSize paperSize, OutputStream outputStream, String contentType) {
	}
}
