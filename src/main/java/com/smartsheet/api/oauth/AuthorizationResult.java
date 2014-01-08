package com.smartsheet.api.oauth;

/**
 * Represents OAuth authorization result.
 */
public class AuthorizationResult {
	/**
	 * Represents the code.
	 */
	private String code;

	/**
	 * Represents the expiration time in seconds.
	 */
	private long expiresInSeconds;

	/**
	 * Represents the state string.
	 */
	private String state;
}
