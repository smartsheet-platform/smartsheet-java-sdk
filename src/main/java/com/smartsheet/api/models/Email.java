package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents an Email object.
 */
public abstract class Email {
	/**
	 * Represents the email recipient.
	 */
	private List<String> to;

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

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
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
