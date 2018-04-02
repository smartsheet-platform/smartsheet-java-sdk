package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.models.enums.SheetFilterType;

public class SheetFilter extends NamedModel<Long> {

    /**
     * Represents the filter type
     */
    private SheetFilterType filterType;

    /**
     * Represents the details that make up the Filter query
     */
    private SheetFilterDetails query;

    /**
     * the Filter version
     */
    private Integer version;

    /**
     * Gets the filter type
     *
     * @return the filter type
     */
    public SheetFilterType getFilterType() {
        return filterType;
    }

    /**
     * Sets the filter type
     *
     * @param filterType the filter type
     */
    public SheetFilter setFilterType(SheetFilterType filterType) {
        this.filterType = filterType;
        return this;
    }

    /**
     * Gets the details that make up the Filter query
     *
     * @return the SheetFilterDetails
     */
    public SheetFilterDetails getQuery() { return query; }

    /**
     * Sets the details that make up the Filter query
     *
     * @param query the SheetFilterDetails
     */
    public SheetFilter setQuery(SheetFilterDetails query) {
        this.query = query;
        return this;
    }

    /**
     * Gets the sheet filter version
     *
     * @return the SheetFilter version
     */
    public Integer getVersion() { return version; }

    /**
     * Sets the sheet filter version
     *
     * @param version the SheetFilter version
     */
    public SheetFilter setVersion(Integer version) {
        this.version = version;
        return this;
    }
}
