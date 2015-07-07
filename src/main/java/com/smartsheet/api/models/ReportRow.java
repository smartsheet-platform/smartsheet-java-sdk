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


import java.util.LongSummaryStatistics;

/**
 * Represents the ReportRow object.
 */
public class ReportRow extends AbstractRow<ReportColumn, ReportCell>{

    /**
     * Represents the virtual ID of the cell’s column.
     */
    private Long sheetId;


    /**
     * Gets the ID of the Sheet from which the Row originates.
     *
     * @return the index
     */
    @Override
    public Long getSheetId() {
        return sheetId;
    }

    /**
     * Sets the ID of the Sheet from which the Row originates.
     *
     * @param sheetId the new title
     */
    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }
}
