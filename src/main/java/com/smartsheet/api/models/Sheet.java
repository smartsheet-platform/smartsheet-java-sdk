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
 * Represents Sheet object in the Smartsheet REST API.
 */
public class Sheet {
	/**
	 * Represents the columns.
	 */
	private List<Column> columns;

	/**
	 * Represents the rows.
	 */
	private List<Row> rows;

	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the discussions.
	 */
	private List<Discussion> discussions;

	/**
	 * Represents the attachments.
	 */
	private List<Attachment> attachments;

	/**
	 * Represents the read only flag.
	 */
	private Boolean readOnly;

	/**
	 * Represents the creation timestamp.
	 */
	private Date createdAt;

	/**
	 * Represents the modification timestamp.
	 */
	private Date modifiedAt;

	/**
	 * Represents the direct URL to the sheet.
	 */
	private String permalink;

	/**
	 * Represents the Gantt enabled flag.
	 */
	private Boolean ganttEnabled;

	/**
	 * Represents the version.
	 */
	private Integer version;

	/**
	 * Represents the ID of the sheet/template from which the sheet was created.
	 */
	private Long fromId;

	/**
	 * Get column by index.
	 * 
	 * Parameters: - index : the column index
	 * 
	 * Returns: the column
	 * 
	 * Implementation: for (Column column : columns) { if (column.getIndex() == index) { return column; } } return null;
	 * 
	 * @param index
	 * @return
	 */
	public Column getColumnByIndex(int index) {
		return null;
	}

	/**
	 * Get column by ID.
	 * 
	 * Parameters: - columnId : the column ID
	 * 
	 * Returns: the column
	 * 
	 * Implementation: for (Column column : columns) { if (column.getId() == columnId) { return column; } } return null;
	 * 
	 * @param columnId
	 * @return
	 */
	public Column getColumnById(long columnId) {
		return null;
	}

	/**
	 * Get row by row number.
	 * 
	 * Parameters: - rowNumber : the row number
	 * 
	 * Returns: the row
	 * 
	 * Implementation: for (Row row : rows) { if (row.getRowNumber() == rowNumber) { return row; } } return null;
	 * 
	 * @param rowNumber
	 * @return
	 */
	public Row getRowByRowNumber(int rowNumber) {
		return null;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public Boolean getGanttEnabled() {
		return ganttEnabled;
	}

	public void setGanttEnabled(Boolean ganttEnabled) {
		this.ganttEnabled = ganttEnabled;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	
	
}
