package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.Folder;

/**
 * This interface provides methods to access Folder resources under home.
 * 
 * Currently the following resources are supported, please refer to http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * GET /home/folders
 * POST /home/folders
 * 
 * Thread Safety:
 * Implementation of this interface must be thread safe.
 */
public interface HomeFolderResources {
    /**
     * List folders under home.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /home/folders
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * the folders (note that empty list will be returned if there is none)
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @return 
     */
    public List<Folder> listFolders();

    /**
     * Create a folder in home.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /home/folders
     * 
     * Parameters:
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
     * @return 
     */
    public Folder createFolder(Folder folder);
}

