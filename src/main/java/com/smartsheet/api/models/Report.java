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


import java.util.List;

/**
 * A report object that is a filtered view of the data from one or more Sheets.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522214-creating-reports">Creating Reports Help</a>
 */
public class Report extends AbstractSheet<ReportRow, ReportColumn, ReportCell> {

    /**
     * Represents the sheets that rows in the report originated from.
     */
    private List<Sheet> sourceSheets;

    /**
     * Gets the sheet array.
     *
     * @return the sourceSheets
     */
    public List<Sheet> getSourceSheets() {
        return sourceSheets;
    }

    /**
     * Sets the sheet array.
     *
     * @param sourceSheets the new index
     */
    public Report setSourceSheets(List<Sheet> sourceSheets) {
        this.sourceSheets = sourceSheets;
        return this;
    }
}


