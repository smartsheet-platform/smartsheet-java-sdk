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
 * Represents the Workspace object which is an area in which sheets, reports, templates and sub-folders can be 
 * organized, similar to a folder.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/506687-creating-a-workspace">Creating a Workspace 
 * Documentation</a>
 */
public class Workspace extends Folder {
	/** Represents the user's permissions on a workspace. */
	private AccessLevel accessLevel;

	/** Represents the link . */
	private String permalink;

	/**
	 * Gets the user's permissions on a workspace.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the user's permissions on a workspace.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the permalink to the workspace.
	 *
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * Sets the permalink to the workspace.
	 *
	 * @param permalink the new permalink
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * A convenience class for creating a {@link Workspace} object with the appropriate fields for updating a workspace.
	 */
	public static class UpdateWorkspaceBuilder {
		private String workspaceName;

		/**
		 * The name of the workspace.
		 *
		 * @param name the name
		 * @return the update workspace builder
		 */
		public UpdateWorkspaceBuilder name(String name) {
			this.workspaceName = name;
			return this;
		}

		/**
		 * Builds the {@link Workspace}.
		 *
		 * @return the workspace
		 */
		public Workspace build() {
			Workspace workspace = new Workspace();
			workspace.setName(workspaceName);
			return workspace;
		}
	}
}
