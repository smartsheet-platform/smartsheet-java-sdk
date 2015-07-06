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

public class Filter {

    /**
     * Represents the filter type
     */
    private FilterType type;

    /**
     * Represents the excluded selected flag
     */
    private Boolean excludeSelected;

    /**
     * Represents the list of values
     * Can contain string, numbers, or booleans
     */
    private List<Object> values;

    /**
     * Represents the list of criteria
     */
    private List<Criteria> criteria;

    /**
     * Gets the filter type
     * @return the filter type
     */
    public FilterType getType() {
        return type;
    }

    /**
     * Sets the filter type
     * @param type the filter type
     */
    public void setType(FilterType type) {
        this.type = type;
    }

    /**
     * Gets the exclude selected flag
     * @return the excludeSelected flag
     */
    public Boolean isExcludeSelected() {
        return excludeSelected;
    }

    /**
     * Sets the exclude selected flag
     * @param excludeSelected the excludeSelected flag
     */
    public void setExcludeSelected(Boolean excludeSelected) {
        this.excludeSelected = excludeSelected;
    }

    /**
     * Gets the list of values
     * @return list of values
     */
    public List<Object> getValues() {
        return values;
    }

    /**
     * Sets the list of values
     * @param values list of values
     */
    public void setValues(List<Object> values) {
        this.values = values;
    }

    /**
     * Gets the list of criteria
     * @return list of criteria
     */
    public List<Criteria> getCriteria() {
        return criteria;
    }

    /**
     * Sets the list of criteria
     * @param criteria list of criteria
     */
    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }
}
