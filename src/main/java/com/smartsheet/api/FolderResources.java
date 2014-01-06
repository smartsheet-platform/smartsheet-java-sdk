package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.*;

/**
 * This interface provides methods to access Folder resources.
 * 
 * Currently the following resources are supported, please refer to http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /folder/{id}
 * PUT /folder/{id}
 * DELETE /folder{id}
 * GET /folder/{id}/folders
 * POST /folder/{id}/folders
 * 
 * Thread Safety:
 * Implementation of this interface must be thread safe.
 */
public interface FolderResources {
    /**
     * Get a folder.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /folder/{id}
     * 
     * Parameters:
     * - folderId : the folder ID
     * 
     * Returns:
     * the folder (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param folderId 
     * @return 
     */
    public Folder getFolder(long folderId);

    /**
     * Update a folder.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * PUT /folder/{id}
     * 
     * Parameters:
     * - folder : the folder to update
     * 
     * Returns:
     * the updated folder (note that if there is no such folder, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - IllegalArgumentException : if folder is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param folder 
     * @return 
     */
    public Folder updateFolder(Folder folder);

    /**
     * Delete a folder.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * DELETE /folder{id}
     * 
     * Parameters:
     * - folderId : the folder ID
     * 
     * Returns:
     * None
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param folderId 
     */
    public void deleteFolder(long folderId);

    /**
     * List child folders of a given folder.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /folder/{id}/folders
     * 
     * Parameters:
     * - parentFolderId : the parent folder ID
     * 
     * Returns:
     * the child folders (note that empty list will be returned if no child folder found)
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param parentFolderId 
     * @return 
     */
    public List<Folder> listFolders(long parentFolderId);

    /**
     * Create a folder.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /folder/{id}/folders
     * 
     * Parameters:
     * - parentFolderId : the parent folder ID
     * - folder : the folder to create
     * 
     * Returns:
     * the created folder
     * 
     * Exceptions:
     * - IllegalArgumentException : if folder is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param folder 
     * @param parentFolderId 
     * @return 
     */
    public Folder createFolder(long parentFolderId, Folder folder);
}

