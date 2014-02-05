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



import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.models.ObjectInclusion;
import com.smartsheet.api.models.PaperSize;
import com.smartsheet.api.models.Sheet;
import com.smartsheet.api.models.SheetEmail;
import com.smartsheet.api.models.SheetPublish;

/**
 * This interface provides methods to access Sheet resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and
 * http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /sheets GET /sheet/{id} GET /sheet/{id}?pageSize={pageSize} GET /sheet/{id}/version POST /sheets PUT /sheet/{id}
 * DELETE /sheet{id} POST /sheet/{id}/emails GET /sheet/{id}/shares GET /sheet/{id}/share/{id} POST /sheet/{id}/shares
 * POST /sheet/{id}/multishare PUT /sheet/{id}/share/{userId} DELETE /sheet/{id}/share/{userId} GET /sheet/{id}/columns
 * POST /sheet/{id}/columns POST /sheet/{id}/rows GET /sheet/{id}/row/{number} GET /sheet/{id}/attachments POST
 * /sheet/{id}/attachments POST /sheet/{id}/attachments POST /sheet/{id}/discussions GET /sheet/{id}/publish PUT
 * /sheet/{id}/publish GET /users/sheets GET /search/sheet/{id}
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface SheetResources {
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
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Sheet> listSheets() throws SmartsheetException;

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
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public List<Sheet> listOrganizationSheets() throws SmartsheetException;

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
	 * @param id
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet getSheet(long id, EnumSet<ObjectInclusion> includes) throws SmartsheetException;

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
	 * @param id
	 * @param outputStream
	 * @param paperSize
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 */
	public void getSheetAsExcel(long id, OutputStream outputStream) throws SmartsheetException;

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
	 * @param id
	 * @param outputStream
	 * @param paperSize
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 */
	public void getSheetAsPDF(long id, OutputStream outputStream, PaperSize paperSize) throws SmartsheetException;

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
	 * @param sheet
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheet(Sheet sheet) throws SmartsheetException;

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
	 * @param sheet
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheetFromExisting(Sheet sheet, EnumSet<ObjectInclusion> includes) throws SmartsheetException;

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
	 * @param sheet
	 * @param folderId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheetInFolder(long folderId, Sheet sheet) throws SmartsheetException;

	/**
	 * Create a sheet (from existing sheet or template) in given folder.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /folder/{folderId}/sheets
	 * 
	 * Parameters: - folderId : the folder ID - sheet : the sheet to create, limited to the following required
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
	 * @param folderId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheetInFolderFromExisting(long folderID, Sheet sheet, EnumSet<ObjectInclusion> includes) throws SmartsheetException;

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
	 * @param workspaceId
	 * @param sheet
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheetInWorkspace(long workspaceId, Sheet sheet) throws SmartsheetException;

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
	 * @param workspaceId
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet createSheetInWorkspaceFromExisting(long workspaceId, Sheet sheet, EnumSet<ObjectInclusion> includes) throws SmartsheetException;

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
	 * @param id
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void deleteSheet(long id) throws SmartsheetException;

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
	 * @param sheet
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Sheet updateSheet(Sheet sheet) throws SmartsheetException;

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
	 * @param id
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public int getSheetVersion(long id) throws SmartsheetException;

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
	 * @param id
	 * @param email
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void sendSheet(long id, SheetEmail email) throws SmartsheetException;

	/**
	 * Return the ShareResources object that provides access to Share resources associated with Sheet resources.
	 * 
	 * Returns: the ShareResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public ShareResources shares();

	/**
	 * Return the SheetRowResources object that provides access to Row resources associated with Sheet resources.
	 * 
	 * Returns: the SheetRowResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public SheetRowResources rows();

	/**
	 * Return the SheetColumnResources object that provides access to Column resources associated with Sheet resources.
	 * 
	 * Returns: the SheetColumnResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public SheetColumnResources columns();

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Sheet resources.
	 * 
	 * Returns: the AssociatedAttachmentResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public AssociatedAttachmentResources attachments();

	/**
	 * Return the AssociatedDiscussionResources object that provides access to discussion resources associated with
	 * Sheet resources.
	 * 
	 * Returns: the AssociatedDiscussionResources object
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public AssociatedDiscussionResources discussions();

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
	 * @param id
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public SheetPublish getPublishStatus(long id) throws SmartsheetException;

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
	 * @param id
	 * @param publish
	 * @return
	 * @throws SmartsheetException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public SheetPublish updatePublishStatus(long id, SheetPublish publish) throws SmartsheetException;
}
