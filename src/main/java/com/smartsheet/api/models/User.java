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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((licensedSheetCreator == null) ? 0 : licensedSheetCreator.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (licensedSheetCreator == null) {
			if (other.licensedSheetCreator != null)
				return false;
		} else if (!licensedSheetCreator.equals(other.licensedSheetCreator))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	

}
