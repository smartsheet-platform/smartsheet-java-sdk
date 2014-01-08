package com.smartsheet.api.models;

/**
 * Represents the User object.
 */
public class User extends UserProfile {
	/**
	 * Represents the admin flag.
	 */
	private Boolean admin;

	/**
	 * Represents the licensed sheet creator flag.
	 */
	private Boolean licensedSheetCreator;

	/**
	 * Represents the user status.
	 */
	private UserStatus status;

	/**
	 * Represents the name.
	 */
	private String name;

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getLicensedSheetCreator() {
		return licensedSheetCreator;
	}

	public void setLicensedSheetCreator(Boolean licensedSheetCreator) {
		this.licensedSheetCreator = licensedSheetCreator;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
