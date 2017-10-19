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

public class CellDataItem {

    /**
     * Label for the data point. This will either be the column name or a user-provided string.
     */
    private String label;

    /**
     * Format descriptor for the label
     */
    private String labelFormat;

    /**
     * The type of data returned will depend on the cell type and the data in the cell
     */
    private Object objectValue;

    /**
     * The cell object
     */
    private Cell cell;

    /**
     * The format descriptor for the value
     */
    private String valueFormat;

    /**
     * The display order for the CellDataItem
     */
    private Integer order;

    /**
     * Column Id for the cell
     */
    private Long columnId;

    /**
     * Get the label for the data point.
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label for the data point.
     *
     * @param label
     */
    public CellDataItem setLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     * Get the format descriptor for the label
     *
     * @return labelFormat
     */
    public String getLabelFormat() {
        return labelFormat;
    }

    /**
     * Set the format descriptor for the label
     *
     * @param labelFormat
     */
    public CellDataItem setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
        return this;
    }

    /**
     * Get the object for this CellDataItem. The type of the data returned will depend on
     * the cell type and the data in the cell.
     *
     * @return objectValue
     */
    public Object getObjectValue() {
        return objectValue;
    }

    /**
     * Set the object for this CellDataItem. The type of the data returned will depend on
     * the cell type and the data in the cell.
     *
     * @param objectValue
     */
    public CellDataItem setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
        return this;
    }

    /**
     * Get the cell object
     *
     * @return cell
     */
    public Cell getCell() {
        return cell;
    }

    /**
     * Set the cell object
     *
     * @param cell
     */
    public CellDataItem setCell(Cell cell) {
        this.cell = cell;
        return this;
    }

    /**
     * Get the format descriptor for the cell value
     *
     * @return valueFormat
     */
    public String getValueFormat() {
        return valueFormat;
    }

    /**
     * Set the format descriptor for the cell value
     *
     * @param valueFormat
     */
    public CellDataItem setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat;
        return this;
    }

    /**
     * Get the display order for the CellDataItem
     *
     * @return order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * Set the display order for the CellDataItem
     *
     * @param order
     */
    public CellDataItem setOrder(Integer order) {
        this.order = order;
        return this;
    }

    /**
     * Get the column Id for the cell.
     *
     * @return columnId
     */
    public Long getColumnId() {
        return columnId;
    }

    /**
     * Set the column Id for the cell.
     *
     * @param columnId
     */
    public CellDataItem setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }
}
