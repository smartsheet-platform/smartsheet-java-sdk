package com.smartsheet.api.models;

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
public class CopyOrMoveRowResult {

    /** Represents the ID of the destination sheet. */
    private String destinationSheetId;

    /** Represents Array of RowMapping objects. */
    private List<RowMapping> rowMappings;

    /**
     * Gets the ID of the destination sheet.
     *
     * @return ID of the destination sheet
     */
    public String getDestinationSheetId() {
        return destinationSheetId;
    }

    /**
     * Sets the ID of the destination sheet.
     *
     * @param destinationSheetId ID of the destination sheet
     */
    public CopyOrMoveRowResult setDestinationSheetId(String destinationSheetId) {
        this.destinationSheetId = destinationSheetId;
        return this;
    }

    /**
     * Gets the Array of RowMapping objects.
     *
     * @return Array of RowMapping objects
     */
    public List<RowMapping> getRowMappings() {
        return rowMappings;
    }

    /**
     * Sets the Array of RowMapping objects.
     *
     * @param rowMappings the Array of RowMapping objects
     */
    public CopyOrMoveRowResult setRowMappings(List<RowMapping> rowMappings) {
        this.rowMappings = rowMappings;
        return this;
    }
}
