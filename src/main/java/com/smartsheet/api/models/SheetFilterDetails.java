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

import com.smartsheet.api.models.enums.SheetFilterOperator;

import java.util.List;

public class SheetFilterDetails {

    /**
     * Represents the list of criteria
     */
    private List<Criteria> criteria;

    /**
     * Include parent rows whose children are included in this filter
     */
    private Boolean includeParent;

    /**
     * How to combine criteria in this filter
     */
    private SheetFilterOperator operator;

    /**
     * Gets the list of criteria
     *
     * @return list of criteria
     */
    public List<Criteria> getCriteria() {
        return criteria;
    }

    /**
     * Sets the list of criteria
     *
     * @param criteria list of criteria
     */
    public SheetFilterDetails setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
        return this;
    }

    /**
     * Gets flag indicating whether to include parent rows whose children are included in this filter
     *
     * @return flag indicating whether to include parent
     */
    public Boolean getIncludeParent() { return includeParent; }

    /**
     * Sets flag indicating whether to include parent rows whose children are included in this filter
     *
     * @param includeParent flag indicating whether to include parent
     */
    public SheetFilterDetails setIncludeParent(Boolean includeParent) {
        this.includeParent = includeParent;
        return this;
    }

    /**
     * Gets how to combine criteria in this filter
     *
     * @return the operator
     */
    public SheetFilterOperator getOperator() { return operator; }

    /**
     * Sets how to combine criteria in this filter
     *
     * @param operator the operator
     */
    public SheetFilterDetails setOperator(SheetFilterOperator operator) {
        this.operator = operator;
        return this;
    }
}
