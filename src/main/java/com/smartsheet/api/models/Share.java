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
 * Represents the Share object.
 */
public class Share extends NamedModel {
	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the email.
	 */
	private String email;

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static class shareToOneBuilder {
		private AccessLevel accessLevel;
		private String email;

		public shareToOneBuilder accessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

		public shareToOneBuilder email(String email) {
			this.email = email;
			return this;
		}

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

	public static class UpdateShareBuilder {
		private AccessLevel accessLevel;

		public UpdateShareBuilder accessLevel(AccessLevel accessLevel) {
			this.accessLevel = accessLevel;
			return this;
		}

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
