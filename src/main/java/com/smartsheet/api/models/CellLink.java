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

public class CellLink {

    /** One of the following values:
     OK: the link is in a good state
     BROKEN: the row or sheet linked to was deleted
     INACCESSIBLE: the sheet linked to cannot be viewed by this user
     Several other values indicating unusual error conditions: NOT_SHARED, BLOCKED, CIRCULAR, INVALID, and DISABLED . */
    private String status;

    /** The Sheet ID of the sheet that the linked cell belongs to. */
    private Long sheetId;

    /** The Row ID of the linked cell. */
    private Long rowId;

    /** The Column ID of the linked cell. */
    private Long columnId;

    /** The Sheet name of the linked cell. */
    private String sheetName;

    /**
     * Gets the status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status
     * @param status the status
     */
    public CellLink setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Gets the Sheet ID of the sheet that the linked cell belongs to.
     * @return sheet ID
     */
    public Long getSheetId() {
        return sheetId;
    }

    /**
     * Sets the Sheet ID of the sheet that the linked cell belongs to
     * @param sheetId the sheetId
     */
    public CellLink setSheetId(Long sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    /**
     * Gets Row ID of the linked cell.
     * @return rowI the row id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * Sets
     * @param rowId the row Id
     */
    public CellLink setRowId(Long rowId) {
        this.rowId = rowId;
        return this;
    }

    /**
     * Gets Column ID of the linked cell.
     * @return column ID
     */
    public Long getColumnId() {
        return columnId;
    }

    /**
     * Sets Column ID of the linked cell
     * @param columnId the column ID
     */
    public CellLink setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    /**
     * Gets Sheet name of the linked cell.
     * @return sheet name
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * Sets Sheet name of the linked cell
     * @param sheetName the sheet name
     */
    public CellLink setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }
}
