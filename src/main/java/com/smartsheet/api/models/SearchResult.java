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

/**
 * Represents the results of a search.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522231-searching-in-smartsheet">Help Searching in 
 * Smartsheet</a>
 */
public class SearchResult {
    /**
     * Represents total count of results.
     */
    private Integer totalCount;

    /**
     * A list of items returned from the search results.
     */
    private List<SearchResultItem> results;

    /**
     * Gets the total count of results.
     *
     * @return the total count
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the total count of results.
     *
     * @param totalCount the new total count
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Gets the list of results from the search.
     *
     * @return the results
     */
    public List<SearchResultItem> getResults() {
        return results;
    }

    /**
     * Sets the results list of results.
     *
     * @param results the new results
     */
    public void setResults(List<SearchResultItem> results) {
        this.results = results;
    }
}
