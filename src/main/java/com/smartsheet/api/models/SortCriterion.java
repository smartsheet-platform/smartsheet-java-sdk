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

import com.smartsheet.api.models.enums.SortDirection;

public class SortCriterion extends NamedModel<Long> {

    /**
     * The column ID to sort on
     */
    private Long columnId;

    /**
     * The direction of the sort
     */
    private SortDirection direction;

    /**
     * Get the column ID of the column to sort on
     *
     * @return the column ID
     */
    public Long getColumnId() {
        return columnId;
    }

    /**
     * Set the column ID of the column to sort on
     *
     * @param columnId the column ID
     */
    public SortCriterion setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    /**
     * Get the sort direction
     *
     * @return the sort direction
     */
    public SortDirection getDirection() { return direction; }

    /**
     * Set the sort direction
     *
     * @param direction the column ID
     */
    public SortCriterion setDirection(SortDirection direction) {
        this.direction = direction;
        return this;
    }
}
