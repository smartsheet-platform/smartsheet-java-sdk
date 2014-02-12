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

import com.smartsheet.api.models.Workspace.UpdateWorkspaceBuilder;

/**
 * Represents a folder.
 */
public class Folder extends NamedModel {
	/**
	 * Represents the sheets contained in the folder.
	 */
	private List<Sheet> sheets;

	/**
	 * Represents the child folders contained in the folder.
	 */
	private List<Folder> folders;

	/**
	 * Represents the reports.
	 */
	//QUESTION: why was this commented out?
	// private List<Report> reports;

	/**
	 * Represents the templates contained in the folder.
	 */
	private List<Template> templates;
	
	/**
	 * Gets the sheets in the folder.
	 *
	 * @return the sheets
	 */
	public List<Sheet> getSheets() {
		return sheets;
	}

	/**
	 * Sets the sheets contained in the folder.
	 *
	 * @param sheets the new sheets
	 */
	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	/**
	 * Gets the folders contained in this folder.
	 *
	 * @return the folders
	 */
	public List<Folder> getFolders() {
		return folders;
	}

	/**
	 * Sets the folders contained in this folder.
	 *
	 * @param folders the new folders
	 */
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	/**
	 * Gets the templates contained in this folder.
	 *
	 * @return the templates
	 */
	public List<Template> getTemplates() {
		return templates;
	}

	/**
	 * Sets the templates contained in this folder.
	 *
	 * @param templates the new templates
	 */
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	/**
	 * A convenience class for setting up a folder with the appropriate fields for updating the folder.
	 */
	public static class UpdateFolderBuilder {
		private String folderName;

		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the update folder builder
		 */
		public UpdateFolderBuilder name(String name) {
			this.folderName = name;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the folder
		 */
		public Folder build() {
			Folder folder = new Folder();
			folder.setName(folderName);
			return folder;
		}
	}
	
}
