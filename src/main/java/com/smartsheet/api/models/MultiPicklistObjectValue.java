package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2018 Smartsheet
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

import com.smartsheet.api.models.enums.ObjectValueType;

import java.util.List;

public class MultiPicklistObjectValue implements ObjectValue {

    /**
     * The list of selected options
     */
    private List<String> values;

    public MultiPicklistObjectValue() {
        this(null);
    }

    public MultiPicklistObjectValue(List<String> values) {
        this.values = values;
    }

    /**
     * Gets the array of selected options (strings).
     *
     * @return values (selected options)
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * Sets the array of selected options (strings).
     *
     * @param values
     */
    public MultiPicklistObjectValue setValues(List<String> values) {
        this.values = values;
        return this;
    }

    @Override
    public ObjectValueType getObjectType() {
        return ObjectValueType.MULTI_PICKLIST;
    }
}
