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

/**
 * Represents a Share Object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/520104-sharing-sheets">Sharing Sheets</a>
 */
public class Share extends NamedModel {
	/**
	 * Represents the access level for this specific share.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the email for this specific share.
	 */
	private String email;

	/**
	 * Gets the access level for this specific share.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the access level for this specific share.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the email for this specific share.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email for this specific share.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * A convenience class for creating a {@link Share} with the necessary fields for sharing the sheet to one user.
	 */
	public static class shareToOneBuilder {
		private AccessLevel accessLevel;
		private String email;

		/**
		 * Access level for this specific share.
		 *
		 * @param accessLevel the access level
		 * @return the share to one builder
		 */
		public shareToOneBuilder setAccessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

		/**
		 * Email address for this specific share.
		 *
		 * @param email the email
		 * @return the share to one builder
		 */
		public shareToOneBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		/**
		 * Builds the {@link Share} object.
		 *
		 * @return the share
		 */
		public Share build() {
			if (email == null || accessLevel == null) {
				throw new InstantiationError("The email and accessLevel are required.");
			}

			Share share = new Share();
			share.accessLevel = accessLevel;
			share.email = email;

			return share;
		}
	}

	/**
	 * A convenience class for creating a {@link Share} with the necessary fields to update a specific share.
	 */
	public static class UpdateShareBuilder {
		private AccessLevel accessLevel;

		/**
		 * Access level for the share.
		 *
		 * @param accessLevel the access level
		 * @return the update share builder
		 */
		public UpdateShareBuilder setAccessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

		/**
		 * Builds the {@link Share} object.
		 *
		 * @return the share
		 */
		public Share build() {
			if(accessLevel == null){
				throw new InstantiationError("The access level must be specified.");
			}
			
			Share share = new Share();
			share.accessLevel = accessLevel;
			return share;
		}
	}
}
