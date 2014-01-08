package com.smartsheet.api.internal;

import java.util.EnumSet;

import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.HomeResources;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.ObjectInclusion;

/**
 * This is the implementation of the HomeResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class HomeResourcesImpl implements HomeResources {
	/**
	 * Represents the HomeFolderResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private HomeFolderResources folders;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet); this.folders = new HomeFolderResourcesImpl(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public HomeResourcesImpl(SmartsheetImpl smartsheet) {
	}

	/**
	 * Get a nested list of all Home objects, including sheets, workspaces and folders, and optionally reports and/or
	 * templates, as shown on the Home tab..
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /home
	 * 
	 * Parameters: - includes : used to specify the optional objects to include, currently TEMPLATES is supported.
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
	 * Implementation: String path = "home" + id; if (includes != null && !includes.isEmpty()) { path += "?include=";
	 * for (ObjectInclusion oi : includes) { path += oi.name().toLowerCase() + ","; } }
	 * 
	 * return this.getResource(path, Home.class);
	 * 
	 * @param includes
	 * @return
	 */
	public Home getHome(EnumSet<ObjectInclusion> includes) {
		return null;
	}

	/**
	 * Return the WorkspaceFolderResources object that provides access to Folder resources under home.
	 * 
	 * Returns: the WorkspaceFolderResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.folders;
	 * 
	 * @return
	 */
	public HomeFolderResources folders() {
		return null;
	}
}
