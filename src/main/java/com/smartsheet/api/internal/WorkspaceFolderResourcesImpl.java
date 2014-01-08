package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.WorkspaceFolderResources;
import com.smartsheet.api.models.Folder;

/**
 * This is the implementation of the WorkspaceFolderResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class WorkspaceFolderResourcesImpl implements WorkspaceFolderResources {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is
	 * 
	 * Implementation: super(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public WorkspaceFolderResourcesImpl(SmartsheetImpl smartsheet) {
	}

	/**
	 * List folders of a given workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /workspace/{id}/folders
	 * 
	 * Parameters: - workspaceId : the ID of the workspace
	 * 
	 * Returns: the folders (note that empty list will be returned if there is none)
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.listResources("workspace/" + workspaceId + "/folders", Folder.class);
	 * 
	 * @param workspaceId
	 * @return
	 */
	public List<Folder> listFolders(long workspaceId) {
		return null;
	}

	/**
	 * Create a folder in the workspace.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /workspace/{id}/folders
	 * 
	 * Parameters: - workspaceId : the workspace ID - folder : the folder to create
	 * 
	 * Returns: the created folder
	 * 
	 * Exceptions: - IllegalArgumentException : if folder is null - InvalidRequestException : if there is any problem
	 * with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ServiceUnavailableException : if the REST API service is not available (possibly
	 * due to rate limiting) - SmartsheetRestException : if there is any other REST API related error occurred during
	 * the operation - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource("workspace/" + workspaceId + "/folders", Folder.class, folder);
	 * 
	 * @param folder
	 * @param workspaceId
	 * @return
	 */
	public Folder createFolder(long workspaceId, Folder folder) {
		return null;
	}
}
