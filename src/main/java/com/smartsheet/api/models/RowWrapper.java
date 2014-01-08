package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents the RowWrapper object.
 */
public class RowWrapper {
	/**
	 * Represents to-top flag.
	 */
	private Boolean toTop;

	/**
	 * Represents to-bottom flag.
	 */
	private Boolean toBottom;

	/**
	 * Represents the parent ID.
	 */
	private Long parentId;

	/**
	 * Represents the sibling ID.
	 */
	private Long siblingId;

	/**
	 * Represents the rows.
	 */
	private List<Row> rows;

	public Boolean getToTop() {
		return toTop;
	}

	public void setToTop(Boolean toTop) {
		this.toTop = toTop;
	}

	public Boolean getToBottom() {
		return toBottom;
	}

	public void setToBottom(Boolean toBottom) {
		this.toBottom = toBottom;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSiblingId() {
		return siblingId;
	}

	public void setSiblingId(Long siblingId) {
		this.siblingId = siblingId;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	
}
