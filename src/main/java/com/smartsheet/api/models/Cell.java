package com.smartsheet.api.models;

import java.util.ArrayList;
import java.util.List;

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

/**
 * Represents the Cell object.
 */
public class Cell {

	/**
	 * Represents the type.
	 */
	private ColumnType type;

	/**
	 * Represents the value.
	 */
	private String value;

	/**
	 * Represents the display value.
	 */
	private String displayValue;

	/**
	 * Represents the column ID.
	 */
	private Long columnId;

	/**
	 * Represents the row ID.
	 */
	private Long rowId;

	/**
	 * Represents the link.
	 */
	private Link link;

	/**
	 * Represents the formula.
	 */
	private String formula;

	/**
	 * Represents the strict flag.
	 */
	private Boolean strict;

	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
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

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Boolean getStrict() {
		return strict;
	}

	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	public static class UpdateRowCellsBuilder {
		List<Cell> cells = new ArrayList<Cell>();
		
		public UpdateRowCellsBuilder addCell(Long columnId, String value, Boolean strict) {
			Cell cell = new Cell();
			cell.setColumnId(columnId);
			cell.setValue(value);
			cell.setStrict(strict);
			cells.add(cell);
			return this;
		}
		
		public UpdateRowCellsBuilder addCell(Long columnId, String value) {
			addCell(columnId, value, true);
			return this;
		}
		
		public List<Cell> build() {
			return cells;
		}
	}
}
