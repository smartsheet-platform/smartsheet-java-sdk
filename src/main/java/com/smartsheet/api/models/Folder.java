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
 * Represents Folder object in the Smartsheet REST API.
 */
public class Folder extends NamedModel {
	/**
	 * Represents the sheets.
	 */
	private List<Sheet> sheets;

	/**
	 * Represents the child folders.
	 */
	private List<Folder> folders;

	/**
	 * Represents the reports.
	 */
	// private List<Report> reports;

	/**
	 * Represents the templates.
	 */
	private List<Template> templates;
	
	public List<Sheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public static class UpdateFolderBuilder {
		private String folderName;

		public UpdateFolderBuilder name(String name) {
			this.folderName = name;
			return this;
		}

		public Folder build() {
			Folder folder = new Folder();
			folder.setName(folderName);
			return folder;
		}
	}
	
}
