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

	//FIXME: only generate documenation for non-internal stuff (seperate it by package).
	public static class ShareToManyBuilder {
		private List<User> users;
		private AccessLevel accessLevel;
		private String subject;
		private String message;
		private Boolean ccMe;

		public ShareToManyBuilder users(List<User> users) {
			this.users = users;
			return this;
		}

		public ShareToManyBuilder accessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

		public ShareToManyBuilder subject(String subject) {
			this.subject = subject;
			return this;
		}

		public ShareToManyBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ShareToManyBuilder ccMe(Boolean ccMe) {
			this.ccMe = ccMe;
			return this;
		}

		//FIXME: go back to all of the builders and determine if some of the items can be set to a default. For example
		//       accessLevel might be set to ? by default.
		public MultiShare build() {
			if(users == null || accessLevel == null ) {
				throw new InstantiationError("A user, access level and message are required.");
			}
			
			MultiShare multiShare = new MultiShare();
			multiShare.users = users;
			multiShare.accessLevel = accessLevel;
			multiShare.subject = subject;
			multiShare.message = message;
			multiShare.ccMe = ccMe;
			return multiShare;
		}
	}
}
