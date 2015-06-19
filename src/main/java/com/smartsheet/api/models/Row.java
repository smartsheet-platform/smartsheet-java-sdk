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

import com.smartsheet.api.models.format.Format;

/**
 * Represents the Row object.
 */
public class Row extends IdentifiableModel<Long> {
	/** Represents the Sheet ID. */
	private Long sheetId;

	/** Represents the row number. */
	private Integer rowNumber;

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

	/** Represents the {@link Format} for this cell.*/
	private Format format;

	/** Represents the parent row ID. */
	private Integer parentId;

	/** Represents the sibling row ID*/
	private Integer siblingId;

	/** Represents the URL to the row of the sheet*/
	private String permalink;

	/** Indicates if the row is filtered out by a column filter*/
	private Boolean filteredOut;

	/** Indicates if the sheet is a project sheet with dependencies enabled and this row is in the critical path*/
	private Boolean inCriticalPath;

	/** Represents the conditional {@link Format} for this row.*/
	private Format conditionalFormat;

	/** Indicates if the row should be put at the top of the sheet*/
	private Boolean toTop;

	/** Indicates if the row should be put at the bottom of the sheet*/
	private Boolean toBottom;

	/** Indicates if the row should be put above the specified sibling row*/
	private Boolean above;
	
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

	/**
	 * @return the {@link Format}
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * @param format the {@link Format} to set
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * Gets the row's parent ID.
	 *
	 * @return the ID of the parent
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent row id.
	 *
	 * @param parentId the parent row id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the row's sibling ID.
	 *
	 * @return the ID of the sibling
	 */
	public Integer getSiblingId() {
		return siblingId;
	}

	/**
	 * Sets the sibling row id.
	 *
	 * @param siblingId the sibling row id
	 */
	public void setSiblingId(Integer siblingId) {
		this.siblingId = siblingId;
	}

	/**
	 * Gets the row's permalink URL.
	 *
	 * @return the permalink URL of the row
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * Sets the parmalink URL.
	 *
	 * @param permalink the URL to the row
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * Check if the row is filtered out
	 *
	 * @return true, if it is filtered out
	 */
	public Boolean isFilteredOut() {
		return filteredOut;
	}

	/**
	 * Sets the row to be filtered out.
	 *
	 * @param filteredOut if the row is filtered out
	 */
	public void setFilteredOut(Boolean filteredOut) {
		this.filteredOut = filteredOut;
	}

	/**
	 * Check if the row is in critical path
	 *
	 * @return true, if it is in critical path
	 */
	public Boolean isInCriticalPath() {
		return inCriticalPath;
	}

	/**
	 * Sets the row to be in critical path.
	 *
	 * @param inCriticalPath if the row is in critical path
	 */
	public void setInCriticalPath(Boolean inCriticalPath) {
		this.inCriticalPath = inCriticalPath;
	}

	/**
	 * @return the conditional {@link Format}
	 */
	public Format getConditionalFormat() {
		return conditionalFormat;
	}

	/**
	 * Sets the conditional format of the row.
	 *
	 * @param conditionalFormat the conditional format
	 */
	public void setConditionalFormat(Format conditionalFormat) {
		this.conditionalFormat = conditionalFormat;
	}

	/**
	 * Gets the to top.
	 *
	 * @return true, if row should be at the top
	 */
	public Boolean getToTop() {
		return toTop;
	}

	/**
	 * Sets the to top
	 *
	 * @param toTop if the row is to the top
	 */
	public void setToTop(Boolean toTop) {
		this.toTop = toTop;
	}

	/**
	 * Gets the to bottom.
	 *
	 * @return true, if row should be at the bottom
	 */
	public Boolean getToBottom() {
		return toBottom;
	}

	/**
	 * Sets the to bottom
	 *
	 * @param toBottom if the row is to the bottom
	 */
	public void setToBottom(Boolean toBottom) {
		this.toBottom = toBottom;
	}

	/**
	 * Gets the above.
	 *
	 * @return true, if row should be above the specified sibling ID
	 */
	public Boolean getAbove() {
		return above;
	}

	/**
	 * Sets the above
	 *
	 * @param above if the row is above a specified row
	 */
	public void setAbove(Boolean above) {
		this.above = above;
	}
}
