package com.smartsheet.api.models;

import java.util.Date;

/**
 * Represents CellHistory object.
 */
public class CellHistory extends Cell {
	/**
	 * Represents the created at timestamp.
	 */
	private Date createdAt;

	/**
	 * Represents the modified by user.
	 */
	private User modifiedBy;
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
