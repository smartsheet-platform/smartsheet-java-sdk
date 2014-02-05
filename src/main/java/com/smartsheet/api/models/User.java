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
 */
public class User extends UserProfile {
	/**
	 * Represents the admin flag.
	 */
	private Boolean admin;

	/**
	 * Represents the licensed sheet creator flag.
	 */
	private Boolean licensedSheetCreator;

	/**
	 * Represents the user status.
	 */
	private UserStatus status;

	/**
	 * Represents the name.
	 */
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getLicensedSheetCreator() {
		return licensedSheetCreator;
	}

	public void setLicensedSheetCreator(Boolean licensedSheetCreator) {
		this.licensedSheetCreator = licensedSheetCreator;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
