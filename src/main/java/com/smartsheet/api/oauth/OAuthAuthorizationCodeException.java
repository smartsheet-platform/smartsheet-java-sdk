package com.smartsheet.api.oauth;

import com.smartsheet.api.SmartsheetException;

/**
 * This is the exception throw by OAuthFlow to indicate errors occurred during obtaining authorization code.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class OAuthAuthorizationCodeException extends SmartsheetException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * Parameters: - message : the message
	 * 
	 * Implementation: super(message);
	 * 
	 * @param message
	 */
	public OAuthAuthorizationCodeException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * Parameters: - message : the message - cause : the cause
	 * 
	 * Implementation: super(message, cause);
	 * 
	 * @param message
	 * @param cause
	 */
	public OAuthAuthorizationCodeException(String message, Throwable cause) {
		super(message, cause);
	}
}
