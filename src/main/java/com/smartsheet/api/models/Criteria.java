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

import com.smartsheet.api.models.enums.CriteriaTarget;
import com.smartsheet.api.models.enums.Operator;

import java.util.List;

public class Criteria {

    /**
     * column ID
     */
    private Long columnId;

    /**
     * Represents the operator type
     */
    private Operator operator;

    /**
     * The target for the filter query (currently only ROW for row filters)
     */
    private CriteriaTarget target;

    /**
     * Present if a custom filter criteria's operator has one or more arguments
     */
    private List<Object> values;

    /**
     * Gets the column ID
     *
     * @return the column ID
     */
    public Long getColumnId() {
        return columnId;
    }

    /**
     * Sets the operator
     *
     * @param columnId the column ID
     */
    public Criteria setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    /**
     * Gets the operator
     *
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Sets the operator
     *
     * @param operator the operator
     */
    public Criteria setOperator(Operator operator) {
        this.operator = operator;
        return this;
    }

    /**
     * Gets the criteria target
     *
     * @return the criteria target
     */
    public CriteriaTarget getTarget() {
        return target;
    }

    /**
     * Sets the criteria target
     *
     * @param target the criteria target
     */
    public Criteria setTarget(CriteriaTarget target) {
        this.target = target;
        return this;
    }

    /**
     * Gets the values if this criteria's operator has arguments
     *
     * @return the values array
     */
    public List<Object> getValues() {
        return values;
    }

    /**
     * Sets the values if this criteria's operator has arguments
     *
     * @param values the criteria target
     */
    public Criteria setValues(List<Object> values) {
        this.values = values;
        return this;
    }

}
