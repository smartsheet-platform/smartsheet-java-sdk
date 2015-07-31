package com.smartsheet.api.models;

import java.util.ArrayList;
import java.util.List;

import com.smartsheet.api.models.format.Format;
import javafx.scene.control.Hyperlink;

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
	 * Represents the column columnType.
	 */
	private ColumnType columnType;

	/**
	 * Represents the column columnType.
	 */
	private ColumnType type;
	/**
	 * Represents the value.
	 */
	private Object value;

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
	 * Represents the hyperlink to a URL, sheet, or report.
	 */
	private com.smartsheet.api.models.Hyperlink hyperlink;

	/**
	 * Represents an inbound link from a cell in another sheet.
	 */
	private CellLink linkInFromCell;

	/**
	 * Represents an array of CellLink objects.
	 */
	private List<CellLink> linksOutToCells;

	/**
	 * Represents the format descriptor describing this cell’s conditional format.
	 */
	private String conditionalFormat;

	/**
	 * The formula for the cell.
	 */
	private String formula;

	/**
	 * Represents the strict flag.
	 */
	private Boolean strict;

	/**
	 * Represents the {@link Format} for this cell.
	 */
	private Format format;

	/**
	 * Gets the column columnType.
	 *
	 * @return the columnType
	 */
	public ColumnType getColumnType() {
		return type;
	}

	/**
	 * Sets the column columnType.
	 *
	 * @param columnType the new columnType
	 */
	public void setColumnType(ColumnType columnType) {
		this.type = columnType;
	}

	/**
	 * Gets the column columnType.
	 *
	 * @return the columnType
	 */
	public ColumnType getType() {
		return type;
	}

	/**
	 * Sets the column columnType.
	 *
	 * @param type the new columnType
	 */
	public void setType(ColumnType type) {
		this.type = type;
	}

	/**
	 * Gets the value. Can be one of columnType {@link String}, {@link Number}, or {@link Boolean}
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value. Can be one of columnType {@link String}, {@link Number}, or {@link Boolean}
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
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
		public UpdateRowCellsBuilder addCell(Long columnId, Object value, Boolean strict, com.smartsheet.api.models.Hyperlink hyperlink,  CellLink linkInFromCell) {
			Cell cell = new Cell();
			cell.setColumnId(columnId);
			cell.setValue(value);
			cell.setStrict(strict);
			cell.setHyperlink(hyperlink);
			cell.setLinkInFromCell(linkInFromCell);
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
		public UpdateRowCellsBuilder addCell(Long columnId, Object value) {
			addCell(columnId, value, true, null, null);
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
	 * @return hyperlink to a URL, sheet, or report
	 */
	public com.smartsheet.api.models.Hyperlink getHyperlink() {
		return hyperlink;
	}

	/**
	 * @param hyperlink hyperlink to a URL, sheet, or report to set
	 */
	public void setHyperlink(com.smartsheet.api.models.Hyperlink hyperlink) {
		this.hyperlink = hyperlink;
	}

	/**
	 * @return inbound link from a cell in another sheet
	 */
	public CellLink getLinkInFromCell() {
		return linkInFromCell;
	}

	/**
	 * @param linkInFromCell inbound link from a cell in another sheet to set
	 */
	public void setLinkInFromCell(CellLink linkInFromCell) {
		this.linkInFromCell = linkInFromCell;
	}

	/**
	 * @return array of CellLink objects
	 */
	public List<CellLink> getLinksOutToCells() {
		return linksOutToCells;
	}

	/**
	 * @param linksOutToCells array of CellLink objects
	 */
	public void setLinksOutToCells(List<CellLink> linksOutToCells) {
		this.linksOutToCells = linksOutToCells;
	}

	/**
	 * @return the format descriptor describing this cell’s conditional format
	 */
	public String getConditionalFormat() {
		return conditionalFormat;
	}

	/**
	 * @param conditionalFormat the format descriptor describing this cell’s conditional format to set
	 */
	public void setConditionalFormat(String conditionalFormat) {
		this.conditionalFormat = conditionalFormat;
	}


}