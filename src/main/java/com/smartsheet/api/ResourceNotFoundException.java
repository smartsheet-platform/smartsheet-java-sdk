package com.smartsheet.api;

import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate a resource can not be found.
 * 
 * Basically this exception will be thrown when the Smartsheet REST API responds with "404 NOT FOUND".
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class ResourceNotFoundException extends SmartsheetRestException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * Parameters: - error : the Error object from Smartsheet REST API
	 * 
	 * Implementation: super(error);
	 * 
	 * @param error
	 */
	public ResourceNotFoundException(Error error) {
		super(error);
	}
}
