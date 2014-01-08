package com.smartsheet.api.models;

/**
 * Represents UserProfile object.
 */
public class UserProfile {
	/**
	 * Represents the email.
	 */
	private String email;

	/**
	 * Represents the first name.
	 */
	private String firstName;

	/**
	 * Represents the last name.
	 */
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
