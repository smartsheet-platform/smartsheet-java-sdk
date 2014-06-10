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

import java.util.Date;
import java.util.List;

/**
 * Represents the Row object.
 */
public class Row extends IdentifiableModel {
	/** Represents the Sheet ID. */
	private Long sheetId;

	/** Represents the row number. */
	private Integer rowNumber;

	/** Represents the parent row number. */
	private Integer parentRowNumber;

	/** Represents the cells for this row. */
	private List<Cell> cells;

	/** Represents the discussions for this row. */
	private List<Discussion> discussions;

	/** Represents the attachments for this row. */
	private List<Attachment> attachments;

	/** Represents the columns for this row. */
	private List<Column> columns;

	/** Represents the date and time the row was created. */
	private Date createdAt;

	/** Represents the date and time the row was modified. */
	private Date modifiedAt;

	/** A read-only flag to indicate if the row is expanded or collapsed. */
	private Boolean expanded;

	/** The version number that is incremented every time a sheet is modified. */
	private Integer version;

	/** The user's permissions on the sheet. */
	private AccessLevel accessLevel;

	/** Indicates if the row is locked. Defaults to false **/
	private Boolean locked;
	
	/** Indicates if the row is locked for the current user. Defaults to false. **/
	private Boolean lockedForUser;
	
	
	/**
	 * Gets the user's permissions on the sheet.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the user's permissions on the sheet.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the version number that is incremented every time a sheet is modified.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the version number that is incremented every time a sheet is modified..
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Checks if the row is expanded.
	 *
	 * @return true, if is expanded
	 */
	public Boolean isExpanded() {
		return expanded;
	}

	/**
	 * Sets the row to be expanded.
	 *
	 * @param expanded the new expanded
	 */
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * Get a column by it's index.
	 *
	 * @param index the column index
	 * @return the column by index
	 */
	public Column getColumnByIndex(int index) {
		if (columns == null) {
			return null;
		}

		Column result = null;
		for (Column column : columns) {
			if (column.getIndex() == index) {
				result = column;
				break;
			}
		}

		return result;
	}

	/**
	 * Get a column by it's ID.
	 *
	 * @param columnId the column id
	 * @return the column by id
	 */
	public Column getColumnById(long columnId) {
		if (columns == null) {
			return null;
		}

		Column result = null;
		for (Column column : columns) {
			if (column.getId() == columnId) {
				result = column;
				break;
			}
		}

		return result;
	}

	/**
	 * Gets the id of the sheet.
	 *
	 * @return the sheet id
	 */
	public Long getSheetId() {
		return sheetId;
	}

	/**
	 * Sets the sheet id.
	 *
	 * @param sheetId the new sheet id
	 */
	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * Sets the row number.
	 *
	 * @param rowNumber the new row number
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * Gets the parent row number.
	 *
	 * @return the parent row number
	 */
	public Integer getParentRowNumber() {
		return parentRowNumber;
	}

	/**
	 * Sets the parent row number.
	 *
	 * @param parentRowNumber the new parent row number
	 */
	public void setParentRowNumber(Integer parentRowNumber) {
		this.parentRowNumber = parentRowNumber;
	}

	/**
	 * Gets the cells.
	 *
	 * @return the cells
	 */
	public List<Cell> getCells() {
		return cells;
	}

	/**
	 * Sets the cells.
	 *
	 * @param cells the new cells
	 */
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	/**
	 * Gets the discussions.
	 *
	 * @return the discussions
	 */
	public List<Discussion> getDiscussions() {
		return discussions;
	}

	/**
	 * Sets the discussions.
	 *
	 * @param discussions the new discussions
	 */
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * Sets the attachments.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * Gets the date and time a row was created.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the date and time a row was created.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the date and time a row was modified.
	 *
	 * @return the modified at
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * Sets the date and time a row was modified.
	 *
	 * @param modifiedAt the new modified at
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * Indicates whether a row is locked or not. 
	 * 
	 * @return the locked status
	 */
	public Boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * Indicates whether a row is locked for the user. Users cannot modify rows that are locked for them.
	 * @return the lock status for the user
	 */
	public Boolean isLockedForUser() {
		return lockedForUser;
	}

	public void setLockedForUser(Boolean lockedForUser) {
		this.lockedForUser = lockedForUser;
	}
}
