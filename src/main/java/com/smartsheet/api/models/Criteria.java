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

public class Criteria {

    /**
     * Represents the operator type
     */
    private Operator operator;

    /**
     * Represents the value1
     * Can be a string or number
     */
    private Object value1;

    /**
     * Represents teh value2
     * Can be a string or number
     */
    private Object value2;

    /**
     * Gets the operator
     * @return the operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Sets the operator
     * @param operator the operator
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * Gets the value1
     * @return the value1
     */
    public Object getValue1() {
        return value1;
    }

    /**
     * Sets the value1
     * @param value1 the value1
     */
    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    /**
     * Gets the value2
     * @return the value2
     */
    public Object getValue2() {
        return value2;
    }

    /**
     * Sets the value2
     * @param value2 the value2
     */
    public void setValue2(Object value2) {
        this.value2 = value2;
    }
}
