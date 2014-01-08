package com.smartsheet.api.models;

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
}
