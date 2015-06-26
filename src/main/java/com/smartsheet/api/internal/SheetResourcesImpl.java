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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.AssociatedDiscussionResources;
import com.smartsheet.api.ShareResources;
import com.smartsheet.api.SheetColumnResources;
import com.smartsheet.api.SheetResources;
import com.smartsheet.api.SheetRowResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.*;

/**
 * This is the implementation of the SheetResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetResourcesImpl extends AbstractResources implements SheetResources {
	
	/** The Constant BUFFER_SIZE. */
	private final static int BUFFER_SIZE = 4098;

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
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 *
	 * @param smartsheet the smartsheet
	 */
	public SheetResourcesImpl(SmartsheetImpl smartsheet) {
		super(smartsheet);
		this.shares = new ShareResourcesImpl(smartsheet, "sheet");
		this.rows = new SheetRowResourcesImpl(smartsheet);
		this.columns = new SheetColumnResourcesImpl(smartsheet);
		this.attachments = new AssociatedAttachmentResourcesImpl(smartsheet, "sheet");
		this.discussions = new AssociatedDiscussionResourcesImpl(smartsheet, "sheet");
	}

	/**
	 * List all sheets.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheets
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param includeAll include all items
	 * @param pageSize the page size
	 * @param page the page
	 * @return all sheets (note that empty list will be returned if there is none)
	 * @throws SmartsheetException the smartsheet exception
	 */
	public DataWrapper<Sheet> listSheets(boolean includeAll, Integer pageSize, Integer page) throws SmartsheetException {
		PaginationParameters parameters = new PaginationParameters(includeAll, pageSize, page);

		String path = "sheets";
		path += parameters.toQueryString();

		return this.listResourcesWithWrapper(path, Sheet.class);
	}

	/**
	 * List all sheets in the organization.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /users/sheets
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param includeAll include all items
	 * @param pageSize the page size
	 * @param page the page
	 * @return all sheets (note that empty list will be returned if there is none)
	 * @throws SmartsheetException the smartsheet exception
	 */
	public DataWrapper<Sheet> listOrganizationSheets(boolean includeAll, Integer pageSize, Integer page) throws SmartsheetException {
		PaginationParameters parameters = new PaginationParameters(includeAll, pageSize, page);

		String path = "users/sheets";
		path += parameters.toQueryString();

		return this.listResourcesWithWrapper(path, Sheet.class);
	}

	/**
	 * Get a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id} 
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param includes used to specify the optional objects to include, currently DISCUSSIONS and
	 * ATTACHMENTS are supported.
	 * @return the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet getSheet(long id, EnumSet<SheetInclusion> includes, EnumSet<ObjectExclusion> excludes, Set<Long> rowIds, Set<Integer> rowNumbers, Set<Long> columnIds, Integer pageSize, Integer page) throws SmartsheetException {
		String path = "sheets/" + id;

		// Add the parameters to a map and build the query string at the end
		HashMap<String, String>	parameters = new HashMap<String, String>();

		if (includes != null) {
			parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
		}
		if (excludes != null) {
			parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));
		}
		if (rowIds != null) {
			parameters.put("rowIds", QueryUtil.generateCommaSeparatedList(rowIds));
		}
		if (rowNumbers != null) {
			parameters.put("rowNumbers", QueryUtil.generateCommaSeparatedList(rowNumbers));
		}
		if (columnIds != null) {
			parameters.put("columnIds", QueryUtil.generateCommaSeparatedList(columnIds));
		}
		if (pageSize != null) {
			parameters.put("pageSize", pageSize.toString());
		}
		if (page != null) {
			parameters.put("page", page.toString());
		}

		// Iterate through the map of parameters and generate the query string
		path += QueryUtil.generateUrl(null, parameters);

		return this.getResource(path, Sheet.class);
	}

	/**
	 * Get a sheet as an Excel file.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id} with "application/vnd.ms-excel" Accept
	 * HTTP header 
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if outputStream is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param outputStream the OutputStream to which the Excel file will be written
	 * @return the sheet as excel
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void getSheetAsExcel(long id, OutputStream outputStream) throws SmartsheetException {
		getSheetAsFile(id, null, outputStream, "application/vnd.ms-excel");
	}

	/**
	 * Get a sheet as a PDF file.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id} with "application/pdf" Accept HTTP 
	 * header
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if outputStream is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param outputStream the output stream to which the PDF file will be written.
	 * @param paperSize the optional paper size
	 * @return the sheet as pdf
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void getSheetAsPDF(long id, OutputStream outputStream, PaperSize paperSize) throws SmartsheetException {
		getSheetAsFile(id, paperSize, outputStream, "application/pdf");
	}

	/**
	 * Create a sheet in default "Sheets" collection.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheets 
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheet the sheet to create, limited to the following required attributes: * name (string) *
	 * columns (array of Column objects, limited to the following attributes) - title - primary - type - symbol -
	 * options
	 * @return the created sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheet(Sheet sheet) throws SmartsheetException {
		return this.createResource("sheets", Sheet.class, sheet);
	}

	/**
	 * Create a sheet (from existing sheet or template) in default "Sheets" collection.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheets 
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request 
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param sheet the sheet to create, limited to the following required attributes: * name (string) * fromId
	 * (number): ID of the Sheet or Template from which to create the sheet.
	 * @param includes used to specify the optional objects to include, currently DATA, DISCUSSIONS and ATTACHMENTS are 
	 * supported.
	 * @return the sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheetFromExisting(Sheet sheet, EnumSet<ObjectInclusion> includes) throws SmartsheetException {
		String path = "sheets";
		if (includes != null) {
			path += "?include=";
			for (ObjectInclusion oi : includes) {
				path += oi.name().toLowerCase() + ",";
			}
		}
		return this.createResource(path, Sheet.class, sheet);
	}

	/**
	 * Create a sheet in given folder.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /folder/{folderId}/sheets
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param folderId the folder id
	 * @param sheet the sheet to create, limited to the following required
	 * attributes: * name (string) * columns (array of Column objects, limited to the following attributes) - title -
	 * primary - type - symbol - options
	 * @return the created sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheetInFolder(long folderId, Sheet sheet) throws SmartsheetException {
		
		return this.createResource("folders/" + folderId + "/sheets", Sheet.class, sheet);
	}

	/**
	 * Create a sheet in given folder.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /folder/{folderId}/sheets 
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param folderId the folder id
	 * @param sheet the sheet
	 * @param includes the includes
	 * @return the sheet to create, limited to the following required
	 * attributes: * name (string) * columns (array of Column objects, limited to the following attributes) - title -
	 * primary - type - symbol - options
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheetInFolderFromExisting(long folderId, Sheet sheet, EnumSet<ObjectInclusion> includes) throws SmartsheetException {
		String path = "folders/" + folderId + "/sheets";
		if (includes != null) {
			path += "?include=";
			for (ObjectInclusion oi : includes) {
				path += oi.name().toLowerCase() + ",";
			}
		}

		return this.createResource(path, Sheet.class, sheet);
	}

	/**
	 * Create a sheet in given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{workspaceId}/sheets
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null 
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param workspaceId the workspace id
	 * @param sheet the sheet to create, limited to the following required attributes: * name (string) * columns 
	 * (array of Column objects, limited to the following attributes) - title - primary - type - symbol - options
	 * @return the created sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheetInWorkspace(long workspaceId, Sheet sheet) throws SmartsheetException {
		return this.createResource("workspaces/" + workspaceId + "/sheets", Sheet.class, sheet);
	}

	/**
	 * Create a sheet (from existing sheet or template) in given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{workspaceId}/sheets 
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param workspaceId the workspace id
	 * @param sheet the sheet to create, limited to the following required
	 * attributes: * name (string) * fromId (number): ID of the Sheet or Template from which to create the sheet. -
	 * includes : used to specify the optional objects to include, currently DATA, DISCUSSIONS and ATTACHMENTS are
	 * supported.
	 * @param includes the includes
	 * @return the created sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet createSheetInWorkspaceFromExisting(long workspaceId, Sheet sheet, EnumSet<ObjectInclusion> includes)
			throws SmartsheetException {
		String path = "workspaces/" + workspaceId + "/sheets";
		if (includes != null) {
			path += "?include=";
			for (ObjectInclusion oi : includes) {
				path += oi.name().toLowerCase() + ",";
			}
		}

		return this.createResource(path, Sheet.class, sheet);
	}

	/**
	 * Delete a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /sheet{id}
	 * 
	 * Exceptions:
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the ID of the sheet
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void deleteSheet(long id) throws SmartsheetException {
		this.deleteResource("sheets/" + id, Sheet.class);
	}

	/**
	 * Update a sheet.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /sheet/{id}
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
	 * @param sheet the sheet to update limited to the following attribute: * name (string)
	 * @return the updated sheet (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public Sheet updateSheet(Sheet sheet) throws SmartsheetException {
		return this.updateResource("sheets/" + sheet.getId(), Sheet.class, sheet);
	}

	/**
	 * Get a sheet version.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/version
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
	 * @return the sheet version (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public int getSheetVersion(long id) throws SmartsheetException {
		return this.getResource("sheets/" + id + "/version", Sheet.class).getVersion();
	}

	/**
	 * Send a sheet as a PDF attachment via email to the designated recipients.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{sheetId}/emails
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param email the email
	 * @throws SmartsheetException the smartsheet exception
	 */
	public void sendSheet(long id, SheetEmail email) throws SmartsheetException {
		this.createResource("sheets/" + id + "/emails", SheetEmail.class, email);
	}

	/**
	 * Return the ShareResources object that provides access to Share resources associated with Sheet resources.
	 *
	 * @return the ShareResources object
	 */
	public ShareResources shares() {
		return this.shares;
	}

	/**
	 * Return the SheetRowResources object that provides access to Row resources associated with Sheet resources.
	 *
	 * @return the sheet row resources
	 */
	public SheetRowResources rows() {
		return this.rows;
	}

	/**
	 * Return the SheetColumnResources object that provides access to Column resources associated with Sheet resources.
	 *
	 * @return the sheet column resources
	 */
	public SheetColumnResources columns() {
		return this.columns;
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Sheet resources.
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments() {
		return this.attachments;
	}

	/**
	 * Return the AssociatedDiscussionResources object that provides access to discussion resources associated with
	 * Sheet resources.
	 *
	 * @return the associated discussion resources
	 */
	public AssociatedDiscussionResources discussions() {
		return this.discussions;
	}

	/**
	 * Get the status of the Publish settings of the sheet, including the URLs of any enabled publishings.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /sheet/{sheetId}/publish
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @return the publish status
	 * @throws SmartsheetException the smartsheet exception
	 */
	public SheetPublish getPublishStatus(long id) throws SmartsheetException {
		return this.getResource("sheets/" + id + "/publish", SheetPublish.class);
	}

	/**
	 * Sets the publish status of a sheet and returns the new status, including the URLs of any enabled publishings.
	 * 
	 * It mirrors to the following Smartsheet REST API method: PUT /sheet/{sheetId}/publish
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param id the id
	 * @param publish the SheetPublish object limited to the following attributes *
	 * readOnlyLiteEnabled * readOnlyFullEnabled * readWriteEnabled * icalEnabled
	 * @return the updated SheetPublish (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException rather than returning null).
	 * @throws SmartsheetException the smartsheet exception
	 */
	public SheetPublish updatePublishStatus(long id, SheetPublish publish) throws SmartsheetException{
		return this.updateResource("sheets/" + id + "/publish", SheetPublish.class, publish);
	}

	/**
	 * Get a sheet as a file.
	 * 
	 * Exceptions: 
	 *   - InvalidRequestException : if there is any problem with the REST API request 
	 *   - AuthorizationException : if there is any problem with the REST API authorization(access token) 
	 *   - ResourceNotFoundException : if the resource can not be found 
	 *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) 
	 *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation 
	 *   - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * @param id the id
	 * @param paperSize the paper size
	 * @param outputStream the OutputStream to which the Excel file will be written
	 * @param contentType the content type
	 * @return the sheet as file
	 * @throws SmartsheetException the smartsheet exception
	 */
	private void getSheetAsFile(long id, PaperSize paperSize, OutputStream outputStream, String contentType)
			throws SmartsheetException {
		Util.throwIfNull(outputStream, contentType);
		
		String path = "sheet/" + id;
		if (paperSize != null) {
			path += "?paperSize=" + paperSize;
		}

		HttpRequest request;
		request = createHttpRequest(this.getSmartsheet().getBaseURI().resolve(path), HttpMethod.GET);
		request.getHeaders().put("Accept", contentType);

		com.smartsheet.api.internal.http.HttpResponse response = getSmartsheet().getHttpClient().request(request);

		switch (response.getStatusCode()) {
		case 200:
			try {
				copyStream(response.getEntity().getContent(), outputStream);
			} catch (IOException e) {
				throw new SmartsheetException(e);
			}
			break;
		default:
			handleError(response);
		}
		
		getSmartsheet().getHttpClient().releaseConnection();
	}

	/*
	 * Copy an input stream to an output stream.
	 * 
	 * @param input The input stream to copy.
	 * 
	 * @param output the output stream to write to.
	 * 
	 * @throws IOException if there is trouble reading or writing to the streams.
	 */
	/**
	 * Copy stream.
	 *
	 * @param input the input
	 * @param output the output
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void copyStream(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int len;
		while ((len = input.read(buffer)) != -1) {
			output.write(buffer, 0, len);
		}
	}
}
