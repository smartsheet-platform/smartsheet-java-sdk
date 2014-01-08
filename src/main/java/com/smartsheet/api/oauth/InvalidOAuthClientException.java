package com.smartsheet.api.oauth;

/**
 * This is the exception throw by OAuthFlow to indicate "invalid_client" error occurred during obtaining OAuth tokens.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class InvalidOAuthClientException extends OAuthTokenException {
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
	public InvalidOAuthClientException(String message) {
		super(message);
	}
}
