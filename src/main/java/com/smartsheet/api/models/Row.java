package com.smartsheet.api.models;

import java.util.Date;
import java.util.List;

/**
 * Represents the Row object.
 */
public class Row {
	/**
	 * Represents the Sheet ID.
	 */
	private Long sheetId;

	/**
	 * Represents the row number.
	 */
	private Integer rowNumber;

	/**
	 * Represents the parent row number.
	 */
	private Integer parentRowNumber;

	/**
	 * Represents the cells.
	 */
	private List<Cell> cells;

	/**
	 * Represents the discussions.
	 */
	private List<Discussion> discussions;

	/**
	 * Represents the attachments.
	 */
	private List<Attachment> attachments;

	/**
	 * Represents the columns.
	 */
	private List<Column> columns;

	/**
	 * Represents the created at timestamp.
	 */
	private Date createdAt;

	/**
	 * Represents the modified at timestamp.
	 */
	private Date modifiedAt;

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

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getParentRowNumber() {
		return parentRowNumber;
	}

	public void setParentRowNumber(Integer parentRowNumber) {
		this.parentRowNumber = parentRowNumber;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
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

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
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
	
	
}
