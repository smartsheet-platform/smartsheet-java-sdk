package com.smartsheet.api;

import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate invalid request error returned from Smartsheet REST API.
 * 
 * Basically this exception will be thrown when the Smartsheet REST API responds with "400 BAD REQUEST".
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class InvalidRequestException extends SmartsheetRestException {
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
	public InvalidRequestException(Error error) {
		super(error);
	}
}
