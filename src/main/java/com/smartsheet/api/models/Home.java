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

/**
 * Represents the Home object.
 */
public class Home {
	/**
	 * Represents the sheets.
	 */
	private List<Sheet> sheets;

	/**
	 * Represents the folders.
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

	/**
	 * Represents the workspaces.
	 */
	private List<Workspace> workspaces;

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

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}
}
