package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */



import java.util.List;

/**
 * Represents an Email object.
 */
public abstract class Email {
	/**
	 * Represents the email recipient(s).
	 */
	private List<String> to;

	/**
	 * Represents the group recipient(s) (group id).
	 */
	private List<Long> toGroups;

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

	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public List<String> getTo() {
		return to;
	}

	/**
	 * Sets the to email address.
	 *
	 * @param to the new to
	 */
	public void setTo(List<String> to) {
		this.to = to;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the carbon copy me flag.
	 *
	 * @return the cc me
	 */
	public Boolean getCcMe() {
		return ccMe;
	}

	/**
	 * Sets the carbon copy me flag.
	 *
	 * @param ccMe the new cc me
	 */
	public void setCcMe(Boolean ccMe) {
		this.ccMe = ccMe;
	}

	/**
	 * @return the toGroups
	 */
	public List<Long> getToGroups() {
		return toGroups;
	}

	/**
	 * @param toGroups the toGroups to set
	 */
	public void setToGroups(List<Long> toGroups) {
		this.toGroups = toGroups;
	}
	
	
}
