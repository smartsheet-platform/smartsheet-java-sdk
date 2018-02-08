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

import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.models.format.Format;

import java.util.ArrayList;
import java.util.List;

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
     * is an object representation of the cell's value and is currently used for adding or updating predecessor cell values
     */
    private ObjectValue objectValue;

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
    private Hyperlink hyperlink;

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
     * the image that the cell contains. Only returned if the cell contains an image.
     */
    private Image image;

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
     * ((Admin only) Flag indicating whether the cell value can contain a value outside of the validation
     * limits (value = true). When using this parameter, you must also set strict to false to bypass
     * value type checking. This property is honored for POST or PUT actions that update rows.
     */
    private Boolean overrideValidation;

    /**
     * Default constructor
     */
    public Cell() {
    }

    /**
     * Construct a cell with specified column id
     *
     * @param id Column id
     */
    public Cell(Long id) {
        this.columnId = id;
    }

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
    public Cell setColumnType(ColumnType columnType) {
        this.type = columnType;
        return this;
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
    public Cell setType(ColumnType type) {
        this.type = type;
        return this;
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
    public Cell setValue(Object value) {
        this.value = value;
        return this;
    }

    /**
     * Get object representation of the cell's value
     *
     * @return objectValue
     */
    public ObjectValue getObjectValue() {
        return objectValue;
    }

    /**
     * Set object representation of the cell's value
     *
     * @param objectValue
     */
    public Cell setObjectValue(ObjectValue objectValue) {
        this.objectValue = objectValue;
        return this;
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
    public Cell setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
        return this;
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
    public Cell setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
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
    public Cell setRowId(Long rowId) {
        this.rowId = rowId;
        return this;
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
    public Cell setFormula(String formula) {
        this.formula = formula;
        return this;
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
    public Cell setStrict(Boolean strict) {
        this.strict = strict;
        return this;
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
    public Cell setFormat(Format format) {
        this.format = format;
        return this;
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
    public Cell setHyperlink(Hyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
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
    public Cell setLinkInFromCell(CellLink linkInFromCell) {
        this.linkInFromCell = linkInFromCell;
        return this;
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
    public Cell setLinksOutToCells(List<CellLink> linksOutToCells) {
        this.linksOutToCells = linksOutToCells;
        return this;
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
    public Cell setConditionalFormat(String conditionalFormat) {
        this.conditionalFormat = conditionalFormat;
        return this;
    }

    /**
     * Gets the image for this cell.
     *
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image for this cell.
     *
     * @param image
     */
    public Cell setImage(Image image) {
        this.image = image;
        return this;
    }

    /**
     * Gets the value of the overrideValidation flag.
     *
     * @return the overrideValidation flag
     */
    public Boolean getOverrideValidation() {return overrideValidation; }

    /**
     * Sets the value of the overrideValidation flag.
     *
     * @param overrideValidation the new overrideValidation
     * @return the Cell
     */
    public Cell setOverrideValidation(Boolean overrideValidation) {
        this.overrideValidation = overrideValidation;
        return this;
    }

    /**
     * A convenience class for quickly creating a List of cells to add.
     */
    public static class AddRowCellsBuilder {

        /** The cells. */
        List<Cell> cells = new ArrayList<Cell>();

        /**
         * Adds the cell.
         *
         * @param columnId the column id
         * @param value the value
         * @param strict the strict
         * @param hyperlink the hyperlink
         * @param linkInFromCell the link
         * @return the add row cells builder
         */
        public AddRowCellsBuilder addCell(Long columnId, Object value, Boolean strict, Hyperlink hyperlink,  CellLink linkInFromCell) {
            Cell cell = new Cell()
                .setColumnId(columnId)
                .setValue(value)
                .setStrict(strict)
                .setHyperlink(hyperlink)
                .setLinkInFromCell(linkInFromCell);
            cells.add(cell);
            return this;
        }

        /**
         * Adds the cell
         *
         * @param columnId the column id
         * @param value the value
         * @param strict the strict
         * @param hyperlink the hyperlink
         * @param linkInFromCell the link
         * @param overrideValidation the overrideValidation flag
         * @return the add row cells builder
         */
        public AddRowCellsBuilder addCell(Long columnId, Object value, Boolean strict, Hyperlink hyperlink, CellLink linkInFromCell, Boolean overrideValidation) {
            Cell cell = new Cell()
                    .setColumnId(columnId)
                    .setValue(value)
                    .setStrict(strict)
                    .setHyperlink(hyperlink)
                    .setLinkInFromCell(linkInFromCell)
                    .setOverrideValidation(overrideValidation);
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
         * @return the builder
         */
        public AddRowCellsBuilder addCell(Long columnId, Object value) {
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
         * @param hyperlink the hyperlink
         * @param linkInFromCell the link
         * @return the update row cells builder
         */
        public UpdateRowCellsBuilder addCell(Long columnId, Object value, Boolean strict, Hyperlink hyperlink,  CellLink linkInFromCell) {
            Cell cell = new Cell()
                    .setColumnId(columnId)
                    .setValue(value)
                    .setStrict(strict)
                    .setHyperlink(hyperlink)
                    .setLinkInFromCell(linkInFromCell);
            cells.add(cell);
            return this;
        }

        /**
         * Adds the cell.
         *
         * @param columnId the column id
         * @param value the value
         * @param strict the strict
         * @param hyperlink the hyperlink
         * @param linkInFromCell the link
         * @param overrideValidation the overrideValidation flag
         * @return the update row cells builder
         */
        public UpdateRowCellsBuilder addCell(Long columnId, Object value, Boolean strict, Hyperlink hyperlink, CellLink linkInFromCell, Boolean overrideValidation) {
            Cell cell = new Cell()
                    .setColumnId(columnId)
                    .setValue(value)
                    .setStrict(strict)
                    .setHyperlink(hyperlink)
                    .setLinkInFromCell(linkInFromCell)
                    .setOverrideValidation(overrideValidation);
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
}