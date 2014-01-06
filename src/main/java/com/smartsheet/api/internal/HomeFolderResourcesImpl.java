package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.Folder;

/**
 * This is the implementation of the HomeFolderResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class HomeFolderResourcesImpl implements HomeFolderResources {
    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * 
     * Implementation:
     * super(smartsheet);
     * @param smartsheet 
     */
    public HomeFolderResourcesImpl(SmartsheetImpl smartsheet) {
    }

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
     * 
     * Implementation:
     * return this.listResources("home/folders", Folder.class);
     * @return 
     */
    public List<Folder> listFolders() {
        return null;
    }

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
     * 
     * Implementation:
     * return this.createResource("home/folders", Folder.class, folder);
     * @param folder 
     * @return 
     */
    public Folder createFolder(Folder folder) {
        return null;
    }
}

