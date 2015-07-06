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
public class User extends AbstractUser {
	/**
	 * Represents the name
	 */
	private String name;

	/**
	 * Represents the admin flag which allows managing users and accounts.
	 */
	private Boolean admin;

	/**
	 * Represents the licensed sheet creator flag which allows creating and owning sheets.
	 */
	private Boolean licensedSheetCreator;

	/**
	 * Represents the resource manager flag which allows the user access to the Resource Manager functionality.
	 */
	private Boolean resourceViewer;
	
	/**
	 * Represents the group admin flag which allows users to create and modify groups.
	 */
	private Boolean groupAdmin;
	
	/**
	 * Represents the user status (active, pending, declined).
	 */
	private UserStatus status;

	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

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
	 * @return the flag indicating if someone is a resource manager
	 */
	public Boolean getResourceViewer() {
		return resourceViewer;
	}

	/**
	 * Sets the resource manager flag.
	 * @param resourceViewer
	 */
	public void setResourceViewer(Boolean resourceViewer) {
		this.resourceViewer = resourceViewer;
	}

	/**
	 * 
	 * @return the flag indicating if the user is able to administer group.
	 */
	public Boolean getGroupAdmin() {
		return groupAdmin;
	}

	/**
	 * @param groupAdmin sets the flag that indicates if someone is a groupAdmin
	 */
	public void setGroupAdmin(Boolean groupAdmin) {
		this.groupAdmin = groupAdmin;
	}
	/**
	 * A convenience class for making a {@link User} object with the appropriate fields for adding the user.
	 */
	public static class AddUserBuilder {
		private Boolean admin;
		private String emailAddress;
		private Boolean licensedSheetCreator;
		private Boolean groupAdmin;
		private Boolean resourceViewer;
		private String firstName;
		private String lastName;

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
		 * Gets the licensed sheet creator.
		 *
		 * @return the licensed sheet creator
		 */
		public Boolean getLicensedSheetCreator() {
			return licensedSheetCreator;
		}

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public AddUserBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName the lastName to set
		 */
		public AddUserBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * @return the groupAdmin
		 */
		public Boolean getGroupAdmin() {
			return groupAdmin;
		}

		/**
		 * @param groupAdmin the groupAdmin to set
		 */
		public AddUserBuilder setGroupAdmin(Boolean groupAdmin) {
			this.groupAdmin = groupAdmin;
			return this;
		}

		/**
		 * @return the resourceViewer
		 */
		public Boolean getResourceViewer() {
			return resourceViewer;
		}

		/**
		 * @param resourceViewer the resourceViewer to set
		 */
		public AddUserBuilder setResourceViewer(Boolean resourceViewer) {
			this.resourceViewer = resourceViewer;
			return this;
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
			user.groupAdmin = groupAdmin;
			user.resourceViewer = resourceViewer;
			user.setFirstName(firstName);
			user.setLastName(lastName);
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
		private Boolean groupAdmin;
		private Boolean resourceViewer;
		private String firstName;
		private String lastName;

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
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}
		
		/**
		 * @param firstName the firstName to set
		 */
		public UpdateUserBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}
		
		/**
		 * @param lastName the lastName to set
		 */
		public UpdateUserBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		/**
		 * @return the groupAdmin
		 */
		public Boolean getGroupAdmin() {
			return groupAdmin;
		}

		/**
		 * @param groupAdmin the groupAdmin to set
		 */
		public UpdateUserBuilder setGroupAdmin(Boolean groupAdmin) {
			this.groupAdmin = groupAdmin;
			return this;
		}

		/**
		 * @return the resourceViewer
		 */
		public Boolean getResourceViewer() {
			return resourceViewer;
		}

		/**
		 * @param resourceViewer the resourceViewer to set
		 */
		public UpdateUserBuilder setResourceViewer(Boolean resourceViewer) {
			this.resourceViewer = resourceViewer;
			return this;
		}
		
		/**
		 * Builds the {@link User}.
		 *
		 * @return the user
		 */
		public User build() {
			if(admin == null || licensedSheetCreator == null){
				throw new InstantiationError("An admin and licensed sheet creator must be set");
			}
			
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.admin = admin;
			user.licensedSheetCreator = licensedSheetCreator;
			user.groupAdmin = groupAdmin;
			user.resourceViewer = resourceViewer;
			return user;
		}

		

	}
	/**
	 * A convenience class for making a {@link User} object with the appropriate fields for adding to a {@link Group}. See {@link CreateGroupBuilder}
	 */
	public static class NewGroupMemberBuilder {
		private String email;

		/**
		 * Get the email of the user
		 * @return
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * Set the id of the user
		 * @param id
		 */
		public NewGroupMemberBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public User build() {
			if(email == null){
				throw new InstantiationError("An email address must be set.");
			}
			
			User user = new User();
			user.setEmail(email);
			return user;
		}
	}
	
}
