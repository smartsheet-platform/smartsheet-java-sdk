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

/**
 * Represents the “virtual” Column object for Report.
 */
public class ReportColumn extends Column {

    /**
     * Represents the virtual ID of the report column.
     */
    private Long virtualId;

    /**
     * Represents the special “Sheet Name” report column (value = “true”).
     */
    private boolean sheetNameColumn;

    /**
     * Gets the sheet name for the column.
     *
     * @return the sheet name
     */
    public boolean getSheetNameColumn() {
        return sheetNameColumn;
    }

    /**
     * Sets the sheet name for the column.
     *
     * @param sheetNameColumn the sheetname for column
     */
    public ReportColumn setSheetNameColumn(boolean sheetNameColumn) {
        this.sheetNameColumn = sheetNameColumn;
        return this;
    }

    /**
     * Gets the virtual id for the column.
     *
     * @return the virtual id
     */
    public Long getVirtualId() {
        return virtualId;
    }

    /**
     * Sets the virtual id for the column.
     *
     * @param virtualId the virtual id
     */

    public ReportColumn setVirtualId(Long virtualId) {
        this.virtualId = virtualId;
        return this;
    }
}
