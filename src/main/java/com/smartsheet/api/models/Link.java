package com.smartsheet.api.models;

/**
 * Represents the Link object.
 */
public class Link {
	/**
	 * Represents the type.
	 */
	private LinkType type;

	/**
	 * Represents the URL.
	 */
	private String url;

	/**
	 * Represents the sheet ID.
	 */
	private Long sheetId;

	/**
	 * Represents the column ID.
	 */
	private Long columnId;

	/**
	 * Represents the row ID.
	 */
	private Long rowId;

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	
	
}
