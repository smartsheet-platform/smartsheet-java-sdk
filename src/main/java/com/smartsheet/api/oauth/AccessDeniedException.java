package com.smartsheet.api.oauth;

/**
 * This is the exception throw by OAuthFlow to indicate "access_denied" error occurred during obtaining authorization
 * code.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class AccessDeniedException extends OAuthAuthorizationCodeException {
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
	public AccessDeniedException(String message) {
		super(message);
	}
}
