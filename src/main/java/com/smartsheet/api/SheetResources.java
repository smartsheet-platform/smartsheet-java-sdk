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



import java.io.OutputStream;
import java.util.EnumSet;
import java.util.Set;

import com.smartsheet.api.models.*;

/**
 * <p>This interface provides methods to access Sheet resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface SheetResources {

	/**
	 * <p>List all sheets.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sheets</p>
	 *
	 * @param parameters the object containing the pagination parameters
	 * @return A list of all sheets (note that an empty list will be returned if there are none).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public PagedResult<Sheet> listSheets(PaginationParameters parameters) throws SmartsheetException;

	/**
	 * <p>List all sheets in the organization.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /users/sheets</p>
	 *
	 * @param parameters the object containing the pagination parameters
	 * @return the list of all sheets (note that an empty list will be returned if there are none)
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public PagedResult<Sheet> listOrganizationSheets(PaginationParameters parameters) throws SmartsheetException;

	/**
	 * <p>Get a sheet.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sheet/{id}</p>
	 *
	 * @param id the id of the sheet
	 * @param includes used to specify the optional objects to include.
	 * @return the sheet resource (note that if there is no such resource, this method will throw 
	 * ResourceNotFoundException rather than returning null).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet getSheet(long id,
						  EnumSet<SheetInclusion> includes,
						  EnumSet<ObjectExclusion> excludes,
						  Set<Long> rowIds,
						  Set<Integer> rowNumbers,
						  Set<Long> columnIds,
						  Integer pageSize,
						  Integer page) throws SmartsheetException;

	/**
	 * <p>Get a sheet as an Excel file.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * GET /sheet/{id} with "application/vnd.ms-excel" Accept HTTP header</p>
	 *
	 * @param id the id of the sheet
	 * @param outputStream the output stream to which the Excel file will be written.
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void getSheetAsExcel(long id, OutputStream outputStream) throws SmartsheetException;

	/**
	 * <p>Get a sheet as a PDF file.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 * GET /sheet/{id} with "application/pdf" Accept HTTP header</p>
	 *
	 * @param id the id of the sheet
	 * @param outputStream the output stream to which the PDF file will be written.
	 * @param paperSize the paper size
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void getSheetAsPDF(long id, OutputStream outputStream, PaperSize paperSize) throws SmartsheetException;

	/**
	 * <p>Create a sheet in default "Sheets" collection.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method:<br />
	 *  POST /sheets</p>
	 *
	 * @param sheet the sheet to created
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheet(Sheet sheet) throws SmartsheetException;

	/**
	 * <p>Create a sheet (from existing sheet or template) in default "Sheets" collection.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sheets</p>
	 *
	 * @param sheet the sheet to create
	 * @param includes used to specify the optional objects to include.
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheetFromTemplate(Sheet sheet, EnumSet<SheetTemplateInclusion> includes) throws SmartsheetException;

	/**
	 * <p>Create a sheet in given folder.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/sheets</p>
	 *
	 * @param folderId the folder id
	 * @param sheet the sheet to create
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheetInFolder(long folderId, Sheet sheet) throws SmartsheetException;

	/**
	 * <p>Create a sheet (from existing sheet or template) in given folder.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /folders/{folderId}/sheets</p>
	 *
	 * @param folderID the folder id
	 * @param sheet the sheet to create
	 * @param used to specify the optional objects to include.
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheetInFolderFromExisting(long folderID, Sheet sheet, EnumSet<SheetTemplateInclusion> includes) throws SmartsheetException;

	/**
	 * <p>Create a sheet in given workspace.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /workspace/{workspaceId}/sheets</p>
	 *
	 * @param workspaceId the workspace id
	 * @param sheet the sheet to create
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheetInWorkspace(long workspaceId, Sheet sheet) throws SmartsheetException;

	/**
	 * <p>Create a sheet (from existing sheet or template) in given workspace.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /workspaces/{workspaceId}/sheets</p>
	 *
	 * @param workspaceId the workspace id
	 * @param sheet the sheet to create
	 * @param includes used to specify the optional objects to include
	 * @return the created sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet createSheetInWorkspaceFromExisting(long workspaceId, Sheet sheet, EnumSet<SheetTemplateInclusion> includes) throws SmartsheetException;

	/**
	 * <p>Delete a sheet.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheet{id}</p>
	 *
	 * Parameters: - id : the ID of the sheet
	 *
	 * Returns: None
	 *
	 *
	 * @param id the id
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void deleteSheet(long id) throws SmartsheetException;

	/**
	 * <p>Update a sheet.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: PUT /sheet/{id}</p>
	 *
	 * @param sheet the sheet to update
	 * @return the updated sheet
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public Sheet updateSheet(Sheet sheet) throws SmartsheetException;

	/**
	 * <p>Get a sheet version.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sheet/{id}/version</p>
	 *
	 * @param id the id
	 * @return the sheet version (note that if there is no such resource, this method will throw
	 * ResourceNotFoundException)
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public int getSheetVersion(long id) throws SmartsheetException;

	/**
	 * <p>Send a sheet as a PDF attachment via email to the designated recipients.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: POST /sheet/{sheetId}/emails</p>
	 *
	 * @param id the id
	 * @param email the email
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public void sendSheet(long id, SheetEmail email) throws SmartsheetException;

	/**
	 * <p>Return the ShareResources object that provides access to Share resources associated with Sheet resources.</p>
	 *
	 * @return the share resources object
	 */
	public ShareResources shares();

	/**
	 * <p>Return the SheetRowResources object that provides access to Row resources associated with Sheet resources.</p>
	 *
	 * @return the sheet row resources
	 */
	public SheetRowResources rows();

	/**
	 * <p>Return the SheetColumnResources object that provides access to Column resources associated with Sheet resources.</p>
	 *
	 * @return the sheet column resources
	 */
	public SheetColumnResources columns();

	/**
	 * <p>Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Sheet resources.</p>
	 *
	 * @return the associated attachment resources
	 */
	public AssociatedAttachmentResources attachments();

	/**
	 * <p>Return the AssociatedDiscussionResources object that provides access to discussion resources associated with
	 * Sheet resources.</p>
	 *
	 * @return the associated discussion resources
	 */
	public AssociatedDiscussionResources discussions();

	/**
	 * <p>Get the status of the Publish settings of the sheet, including the URLs of any enabled publishings.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: GET /sheet/{sheetId}/publish</p>
	 *
	 * @param id the id
	 * @return the publish status (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null)
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public SheetPublish getPublishStatus(long id) throws SmartsheetException;

	/**
	 * <p>Sets the publish status of a sheet and returns the new status, including the URLs of any enabled publishings.</p>
	 *
	 * <p>It mirrors to the following Smartsheet REST API method: PUT /sheet/{sheetId}/publish</p>
	 *
	 * @param id the id
	 * @param publish the SheetPublish object limited.
	 * @return the update SheetPublish object (note that if there is no such resource, this method will throw a 
	 * ResourceNotFoundException rather than returning null).
	 * @throws IllegalArgumentException if any argument is null or empty string
	 * @throws InvalidRequestException if there is any problem with the REST API request
	 * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
	 * @throws ResourceNotFoundException if the resource cannot be found
	 * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
	 * @throws SmartsheetException if there is any other error during the operation
	 */
	public SheetPublish updatePublishStatus(long id, SheetPublish publish) throws SmartsheetException;
}