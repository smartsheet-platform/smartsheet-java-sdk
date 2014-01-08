package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents the Workspace object.
 */
public class Workspace {
	/**
	 * Represents access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the link.
	 */
	private String permalink;

	/**
	 * Represents the sheets.
	 */
	private List<Sheet> sheets;

	/**
	 * Represents the folders.
	 */
	private List<Folder> folders;

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

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
}
