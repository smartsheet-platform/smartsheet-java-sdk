package com.smartsheet.api.models;

/**
 * Represents the Share object.
 */
public class Share {
	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the email.
	 */
	private String email;

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
