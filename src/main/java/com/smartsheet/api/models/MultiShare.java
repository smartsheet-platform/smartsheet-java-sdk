package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents the MultiShare object.
 */
public class MultiShare {
	/**
	 * Represents the users.
	 */
	private List<User> users;

	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the subject.
	 */
	private String subject;

	/**
	 * Represents the message.
	 */
	private String message;

	/**
	 * Represents the CC me flag.
	 */
	private Boolean ccMe;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getCcMe() {
		return ccMe;
	}

	public void setCcMe(Boolean ccMe) {
		this.ccMe = ccMe;
	}
	
	
}
