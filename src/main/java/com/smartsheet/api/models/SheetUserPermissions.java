package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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

public class SheetUserPermissions {

    /**
     * ADMIN: full control over fields
     * READ_DELETE: sheet is owned by an individual account that doesn't have summary capabilities. If a summary exists,
     *     the only possible operations are GET and DELETE fields.
     * READ_ONLY:
     * READ_WRITE: can edit values of existing fields, but not create or delete fields, nor modify field type.
     */
    private String summaryPermissions;

    /**
     * Gets the sheet summary permissions
     *
     * @return summaryPermissions
     */
    public String getSummaryPermissions() { return summaryPermissions; }

    /**
     * Sets the sheet summary permissions
     *
     * @param summaryPermissions
     */
    public SheetUserPermissions setSummaryPermissions(String summaryPermissions) {
        this.summaryPermissions = summaryPermissions;
        return this;
    }
}
