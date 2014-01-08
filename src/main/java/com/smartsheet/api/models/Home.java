package com.smartsheet.api.models;

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
