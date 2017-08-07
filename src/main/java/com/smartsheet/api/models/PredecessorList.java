package com.smartsheet.api.models;

import com.smartsheet.api.models.enums.ObjectValueType;

import java.util.List;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2017 Smartsheet
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

public class PredecessorList implements ObjectValue {
    /**
     * The list of predecessors
     */
    private List<Predecessor> predecessors;

    public PredecessorList() {
        this(null);
    }

    public PredecessorList(List<Predecessor> predecessors) {
        this.predecessors = predecessors;
    }

    /**
     * Gets the array of Predecessor objects.
     *
     * @return predecessors
     */
    public List<Predecessor> getPredecessors() {
        return predecessors;
    }

    /**
     * Sets the array of Predecessor objects.
     *
     * @param predecessors
     */
    public PredecessorList setPredecessors(List<Predecessor> predecessors) {
        this.predecessors = predecessors;
        return this;
    }

    @Override
    public ObjectValueType getObjectType() {
        return ObjectValueType.PREDECESSOR_LIST;
    }
}
