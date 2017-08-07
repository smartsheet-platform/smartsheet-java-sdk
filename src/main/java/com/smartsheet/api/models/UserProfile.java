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

import com.smartsheet.api.models.enums.UserStatus;

/**
 * A profile object that contains the basic fields that most profiles will contain.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/520100-user-types">User Types Help</a>
 */
public class UserProfile extends UserModel {
	/**
	 * Represents the user's time zone
	 */
	private String timeZone;

	/**
	 * Represents the user's locale
	 */
	private String locale;

	/**
	 * Represents the user's customer account
	 */
	private Account account;

	/**
	 * Gets the time zone
	 * @return the time zone
	 */
	public String getTimeZone() {
		return timeZone;
	}

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
	public UserProfile setAdmin(Boolean admin) {
		this.admin = admin;
		return this;
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
	public UserProfile setLicensedSheetCreator(Boolean licensedSheetCreator) {
		this.licensedSheetCreator = licensedSheetCreator;
		return this;
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
	public UserProfile setStatus(UserStatus status) {
		this.status = status;
		return this;
	}

	/**
	 * @return the flag indicating if someone is a resource manager
	 */
	public Boolean getResourceViewer() {
		return resourceViewer;
	}

	/**
	 * Sets the resource manager flag.
	 * @param resourceViewer the flag
	 */
	public UserProfile setResourceViewer(Boolean resourceViewer) {
		this.resourceViewer = resourceViewer;
		return this;
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
	public UserProfile setGroupAdmin(Boolean groupAdmin) {
		this.groupAdmin = groupAdmin;
		return this;
	}
	/**
	 * Sets the time zone
	 * @param timeZone the time zone
	 */
	public UserProfile setTimeZone(String timeZone) {
		this.timeZone = timeZone;
		return this;
	}

	/**
	 * Gets the locale
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * Sets the locale
	 * @param locale the locale
	 */
	public UserProfile setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * Gets the account
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Sets the account
	 * @param account the account
	 */
	public UserProfile setAccount(Account account) {
		this.account = account;
		return this;
	}
}
