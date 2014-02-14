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
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/520104-sharing-sheets">Help Sharing Sheets</a>
 */
public class MultiShare {
	/** The list of users that will be shared with. The email address must be defined for each user. */
	private List<User> users;

	/** Represents the access level for this share. */
	private AccessLevel accessLevel;

	/** The subject of the email that sent to notify the users. */
	private String subject;

	/** The message to be included in the body of the email that will be sent to the user. */
	private String message;

	/** A flag to indicate whether or not to carbon copy the user sharing the sheet. */
	private Boolean ccMe;

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users that will be shared with. The email address must be defined for each user.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Gets the access level.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the access level.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the subject of the email that sent to notify the users.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the email that sent to notify the users.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message to be included in the body of the email that will be sent to the use.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message to be included in the body of the email that will be sent to the use.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the flag to indicate whether or not to carbon copy the user sharing the sheet.
	 *
	 * @return the carbon copy me flag
	 */
	public Boolean getCcMe() {
		return ccMe;
	}

	/**
	 * Sets the flag to indicate whether or not to carbon copy the user sharing the sheet.
	 *
	 * @param ccMe the new cc me
	 */
	public void setCcMe(Boolean ccMe) {
		this.ccMe = ccMe;
	}

	/**
	 * A convenience class for creating a MultiShare object with the necessary fields for sharing a sheet with
	 * many users.
	 */
	public static class ShareToManyBuilder {
		private List<User> users;
		private AccessLevel accessLevel;
		private String subject;
		private String message;
		private Boolean ccMe;

		/**
		 * Sets the users that will be shared with. The email address must be defined for each user.
		 *
		 * @param users the users
		 * @return the share to many builder
		 */
		public ShareToManyBuilder setUsers(List<User> users) {
			this.users = users;
			return this;
		}

		/**
		 * Sets the access level.
		 *
		 * @param accessLevel the access level
		 * @return the share to many builder
		 */
		public ShareToManyBuilder setAccessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

		/**
		 * Sets the subject of the email that sent to notify the users.
		 *
		 * @param subject the subject
		 * @return the share to many builder
		 */
		public ShareToManyBuilder setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		/**
		 * Sets the message to be included in the body of the email that will be sent to the use.
		 *
		 * @param message the message
		 * @return the share to many builder
		 */
		public ShareToManyBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the carbon copy me flag.
		 *
		 * @param ccMe the carbon copy me flag.
		 * @return the share to many builder
		 */
		public ShareToManyBuilder setCCMe(Boolean ccMe) {
			this.ccMe = ccMe;
			return this;
		}

		/**
		 * Gets the users.
		 *
		 * @return the users
		 */
		public List<User> getUsers() {
			return users;
		}

		/**
		 * Gets the access level.
		 *
		 * @return the access level
		 */
		public AccessLevel getAccessLevel() {
			return accessLevel;
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
		 * Gets the message.
		 *
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * Gets the cc me.
		 *
		 * @return the cc me
		 */
		public Boolean getCcMe() {
			return ccMe;
		}

		/**
		 * Builds the Multishare object with the set fields.
		 *
		 * @return the multi share
		 */
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
