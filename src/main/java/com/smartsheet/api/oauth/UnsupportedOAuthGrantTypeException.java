package com.smartsheet.api.oauth;

/**
 * This is the exception throw by OAuthFlow to indicate "unsupported_grant_type" error occurred during obtaining OAuth
 * tokens.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class UnsupportedOAuthGrantTypeException extends OAuthTokenException {
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
	public UnsupportedOAuthGrantTypeException(String message) {
		super(message);
	}
}
