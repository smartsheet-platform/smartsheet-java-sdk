package com.smartsheet.api.models;

/**
 * Represents Template object in the Smartsheet REST API.
 */
public class Template {
	/**
	 * Represents the description.
	 */
	private String description;

	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	
}
