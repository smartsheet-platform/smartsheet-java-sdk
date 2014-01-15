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
