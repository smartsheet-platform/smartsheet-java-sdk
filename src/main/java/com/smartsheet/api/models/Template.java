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
 * A template object that is a default layout for future sheets.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522123-using-templates">Using Templates Help</a>
 */
public class Template extends NamedModel {
	/**
	 * Represents the description for the template.
	 */
	private String description;

	/**
	 * Represents the access level for the template.
	 */
	private AccessLevel accessLevel;


	/**
	 * Gets the description of the template.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the template.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the access level of the template.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the access level of the template.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
}
