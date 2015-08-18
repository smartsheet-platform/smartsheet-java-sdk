package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

import java.util.List;
/**
 * Represents the multi row email object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504773-sending-Sheets-Rows-via-Email">Sending Sheets & Rows via Email Help</a>
 */
public class MultiRowEmail extends RowEmail {

    /**
     * Represents IDs of rows to be included.
     */
    private List<Long> rowIds;

    /**
     * Gets the IDs of rows to be included
     * @return the row ids
     */
    public List<Long> getRowIds() {
        return rowIds;
    }

    /**
     * Sets the IDs of rows to be included
     * @param rowIds list of row ids
     */
    public void setRowIds(List<Long> rowIds) {
        this.rowIds = rowIds;
    }
}
