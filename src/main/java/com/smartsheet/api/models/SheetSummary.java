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

import java.util.List;

public class SheetSummary {

    /**
     * Array of summary (or metadata) fields defined on the sheet.
     */
    private List<SummaryField> fields;

    /**
     * Get sheet summary fields
     *
     * @return fields
     */
    public List<SummaryField> getFields() { return fields; }

    /**
     * Set sheet summary fields
     *
     * @param fields
     */
    public SheetSummary setFields(List<SummaryField> fields) {
        this.fields = fields;
        return this;
    }
}
