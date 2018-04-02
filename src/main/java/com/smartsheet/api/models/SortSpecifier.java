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

import java.util.List;

public class SortSpecifier {

    /**
     * An array of sort criterion
     */
    private List<SortCriterion> sortCriteria;

    /**
     * Get the sort criteria
     *
     * @return the array of sort criteria
     */
    public List<SortCriterion> getSortCriteria() {
        return sortCriteria;
    }

    /**
     * Set the sort criteria
     *
     * @param sortCriteria the array of sort criteria
     */
    public SortSpecifier setSortCriteria(List<SortCriterion> sortCriteria) {
        this.sortCriteria = sortCriteria;
        return this;
    }
}
