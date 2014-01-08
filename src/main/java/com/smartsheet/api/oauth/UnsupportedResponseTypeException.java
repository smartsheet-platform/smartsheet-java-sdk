package com.smartsheet.api.oauth;

/**
 * This is the exception throw by OAuthFlow to indicate "unsupported_response_type" error occurred during obtaining
 * authorization code.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class UnsupportedResponseTypeException extends OAuthAuthorizationCodeException {
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
	public UnsupportedResponseTypeException(String message) {
		super(message);
	}
}
