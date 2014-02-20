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
 * Represents the User object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/795920-managing-users-team-enterprise-only-">Help
 * Managing Users</a>
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/520100-user-types">User Types Help</a>
 */
public class User extends UserProfile {
	/**
	 * Represents the admin flag which allows managing users and accounts.
	 */
	private Boolean admin;

	/**
	 * Represents the licensed sheet creator flag which allows creating and owning sheets.
	 */
	private Boolean licensedSheetCreator;

	/**
	 * Represents the user status (active, pending, declined).
	 */
	private UserStatus status;

	/**
	 * Gets the admin flag which allows managing users and accounts.
	 *
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	/**
	 * Sets the admin flag which allows managing users and accounts.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Gets the licensed sheet creator flag that allows creating and owning sheets.
	 *
	 * @return the licensed sheet creator
	 */
	public Boolean getLicensedSheetCreator() {
		return licensedSheetCreator;
	}

	/**
	 * Sets the licensed sheet creator flag that allows creating and owning sheets.
	 *
	 * @param licensedSheetCreator the new licensed sheet creator
	 */
	public void setLicensedSheetCreator(Boolean licensedSheetCreator) {
		this.licensedSheetCreator = licensedSheetCreator;
	}

	/**
	 * Gets the status of the user (active, pending, declined).
	 *
	 * @return the status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of the user.
	 *
	 * @param status the new status
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * A convenience class for making a {@link User} object with the appropriate fields for adding the user.
	 */
	public static class AddUserBuilder {
		private Boolean admin;
		private String emailAddress;
		private Boolean licensedSheetCreator;

		/**
		 * Sets the admin flag which allows managing users and accounts.
		 *
		 * @param admin the admin
		 * @return the adds the user builder
		 */
		public AddUserBuilder setAdmin(Boolean admin) {
			this.admin = admin;
			return this;
		}

		/**
		 * Sets the licensed sheet creator flag that allows creating and owning sheets.
		 *
		 * @param licensedSheetCreator the licensed sheet creator
		 * @return the adds the user builder
		 */
		public AddUserBuilder setLicensedSheetCreator(Boolean licensedSheetCreator) {
			this.licensedSheetCreator = licensedSheetCreator;
			return this;
		}

		/**
		 * Sets the email for the user.
		 *
		 * @param email the email
		 * @return the adds the user builder
		 */
		public AddUserBuilder setEmail(String email) {
			this.emailAddress = email;
			return this;
		}

		/**
		 * Gets the admin.
		 *
		 * @return the admin
		 */
		public Boolean getAdmin() {
			return admin;
		}

		/**
		 * Gets the email address.
		 *
		 * @return the email address
		 */
		public String getEmailAddress() {
			return emailAddress;
		}

		/**
		 * Gets the licensed sheet creator.
		 *
		 * @return the licensed sheet creator
		 */
		public Boolean getLicensedSheetCreator() {
			return licensedSheetCreator;
		}

		/**
		 * Builds the {@link User} object using the required fields.
		 *
		 * @return the user
		 */
		public User build() {
			if (admin == null || emailAddress == null || licensedSheetCreator == null) {
				throw new InstantiationError();
			}

			User user = new User();
			user.admin = admin;
			user.licensedSheetCreator = licensedSheetCreator;
			user.setEmail(emailAddress);
			return user;
		}
	}

	/**
	 * A convenience class for making a {@link User} object with the appropriate fields for updating a user.
	 */
	public static class UpdateUserBuilder {
		private Boolean admin;
		private Boolean licensedSheetCreator;
		private Long id;

		/**
		 * Get the id of the user
		 * @return
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Set the id of the user
		 * @param id
		 */
		public UpdateUserBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the admin flag which allows managing users and accounts.
		 *
		 * @param admin the admin
		 * @return the update user builder
		 */
		public UpdateUserBuilder setAdmin(Boolean admin) {
			this.admin = admin;
			return this;
		}

		/**
		 * Licensed sheet creator.
		 *
		 * @param licensedSheetCreator the licensed sheet creator
		 * @return the update user builder
		 */
		public UpdateUserBuilder setLicensedSheetCreator(Boolean licensedSheetCreator) {
			this.licensedSheetCreator = licensedSheetCreator;
			return this;
		}

		/**
		 * Gets the admin.
		 *
		 * @return the admin
		 */
		public Boolean getAdmin() {
			return admin;
		}

		/**
		 * Gets the licensed sheet creator.
		 *
		 * @return the licensed sheet creator
		 */
		public Boolean getLicensedSheetCreator() {
			return licensedSheetCreator;
		}

		/**
		 * Builds the.
		 *
		 * @return the user
		 */
		public User build() {
			if(admin == null || licensedSheetCreator == null){
				throw new InstantiationError("An admin and licensed sheet creator must be set");
			}
			
			User user = new User();
			user.setAdmin(admin);
			user.setId(id);
			user.setLicensedSheetCreator(licensedSheetCreator);
			return user;
		}
	}
}
