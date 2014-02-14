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
 * Represents the Cell object that holds data in a sheet.
 */
public class Cell {

	/**
	 * Represents the column type.
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
	 * Represents the column ID for this cell.
	 */
	private Long columnId;

	/**
	 * Represents the row ID for this cell.
	 */
	private Long rowId;

	/**
	 * Represents the optional link that a cell might have.
	 */
	private Link link;

	/**
	 * The formula for the cell.
	 */
	private String formula;

	/**
	 * Represents the strict flag.
	 */
	private Boolean strict;

	/**
	 * Gets the column type.
	 *
	 * @return the type
	 */
	public ColumnType getType() {
		return type;
	}

	/**
	 * Sets the column type.
	 *
	 * @param type the new type
	 */
	public void setType(ColumnType type) {
		this.type = type;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the display value used on special columns such as "Contact List".
	 *
	 * @return the display value
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * Sets the display value used on columns special columns such as "Contact List".
	 *
	 * @param displayValue the new display value
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * Gets the column id for this cell.
	 *
	 * @return the column id
	 */
	public Long getColumnId() {
		return columnId;
	}

	/**
	 * Sets the column id for this cell.
	 *
	 * @param columnId the new column id
	 */
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	/**
	 * Gets the row id for this cell.
	 *
	 * @return the row id
	 */
	public Long getRowId() {
		return rowId;
	}

	/**
	 * Sets the row id for this cell.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	/**
	 * Gets the link for this cell.
	 *
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * Sets the optional link for this cell.
	 *
	 * @param link the new link
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * Gets the formula for this cell.
	 *
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * Sets the formula for this cell.
	 *
	 * @param formula the new formula
	 * @see <a href="http://help.smartsheet.com/customer/portal/articles/775363-using-formulas">Using Formulas</a>
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * Gets the strict value for this cell.
	 *
	 * @see <a href="http://www.smartsheet.com/developers/api-documentation#h.lay2yj3x1pp8">Column Types</a>
	 * @return the strict
	 */
	public Boolean getStrict() {
		return strict;
	}

	/**
	 * Sets the strict formatting rule for this cell.
	 *
	 * @see <a href="http://www.smartsheet.com/developers/api-documentation#h.lay2yj3x1pp8">Column Types</a>
	 * @param strict the new strict
	 */
	public void setStrict(Boolean strict) {
		this.strict = strict;
	}

	/**
	 * A convenience class for quickly creating a List of cells to update.
	 */
	// TODO: check if default values can be used for any of the builders.
	public static class UpdateRowCellsBuilder {
		
		/** The cells. */
		List<Cell> cells = new ArrayList<Cell>();
		
		/**
		 * Adds the cell.
		 *
		 * @param columnId the column id
		 * @param value the value
		 * @param strict the strict
		 * @return the update row cells builder
		 */
		public UpdateRowCellsBuilder addCell(Long columnId, String value, Boolean strict) {
			Cell cell = new Cell();
			cell.setColumnId(columnId);
			cell.setValue(value);
			cell.setStrict(strict);
			cells.add(cell);
			return this;
		}
		
		public List<Cell> getCells(){
			return cells;
		}
		
		/**
		 * Adds the cell.
		 *
		 * @param columnId the column id
		 * @param value the value
		 * @return the update row cells builder
		 */
		public UpdateRowCellsBuilder addCell(Long columnId, String value) {
			addCell(columnId, value, true);
			return this;
		}
		
		/**
		 * Returns the list of cells.
		 *
		 * @return the list
		 */
		public List<Cell> build() {
			return cells;
		}
	}
}
