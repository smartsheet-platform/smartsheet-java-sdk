package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2019 Smartsheet
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

import com.smartsheet.api.models.enums.WidgetType;

import java.util.List;

public class CellLinkWidgetContent implements WidgetContent {

    /**
     * The Id of the sheet from which the cell data originates
     */
    private Long sheetId;

    /**
     * Array of cell data items
     */
    private List<CellDataItem> cellData;

    /**
     * Array of column objects
     */
    private List<Column> columns;

    /**
     * The widget has when clicked attribute set to that hyperlink (if present and non-null)
     */
    private WidgetHyperlink hyperlink;

    /**
     * Returns the type for this widget content object
     *
     * @return METRIC
     */
    @Override
    public WidgetType getWidgetType() {
        return WidgetType.METRIC;
    }

    /**
     * Get the ID of the sheet from which the cell data originates
     *
     * @return the sheet ID
     */
    public Long getSheetId() { return sheetId; }

    /**
     * Set the ID of the sheet from which the cell data originates
     *
     * @param sheetId
     */
    public CellLinkWidgetContent setSheetId(Long sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    /**
     * Gets the array of cell data items
     *
     * @return cellData
     */
    public List<CellDataItem> getCellData() { return cellData; }

    /**
     * Sets the array of cell data items
     *
     * @param cellData
     */
    public CellLinkWidgetContent setCellData(List<CellDataItem> cellData) {
        this.cellData = cellData;
        return this;
    }

    /**
     * Gets the array of columns
     *
     * @return columns
     */
    public List<Column> getColumns() { return columns; }

    /**
     * Sets the array of columns
     *
     * @param columns
     */
    public CellLinkWidgetContent setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Gets the widget hyperlink
     *
     * @return hyperlink
     */
    public WidgetHyperlink getHyperlink() { return hyperlink; }

    /**
     * Sets the widget hyperlink
     *
     * @param hyperlink
     */
    public CellLinkWidgetContent setHyperlink(WidgetHyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
    }
}
