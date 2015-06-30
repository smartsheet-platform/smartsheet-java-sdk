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

import com.smartsheet.api.internal.util.QueryUtil;

import java.util.HashMap;

public class PaginationParameters {
    /**
     * Represents the includeAll option
     */
    private boolean includeAll;

    /**
     * Represents the page size
     */
    private Integer pageSize;

    /**
     * Represents the page
     */
    private Integer page;

    public PaginationParameters() {}

    public PaginationParameters(boolean includeAll, Integer pageSize, Integer page) {
        this.includeAll = includeAll;
        this.pageSize = pageSize;
        this.page = page;
    }

    /**
     * Gets includeAll
     * @return includeAll
     */
    public boolean isIncludeAll() {
        return includeAll;
    }

    /**
     * Sets includeAll
     * @param includeAll
     */
    public void setIncludeAll(boolean includeAll) {
        this.includeAll = includeAll;
    }

    /**
     * Gets the page size
     * @return page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets the page
     * @return page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets the page
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public String toQueryString() {
        HashMap<String, String> parameters = toHashMap();
        return QueryUtil.generateUrl(null, parameters);
    }

    public HashMap<String, String> toHashMap() {
        HashMap<String, String> parameters = new HashMap<String, String>();

        parameters.put("includeAll", Boolean.toString(includeAll));

        if (includeAll) {
            return parameters;
        } else {
            if (pageSize != null) {
                parameters.put("pageSize", pageSize.toString());
            }
            if (page != null) {
                parameters.put("page", page.toString());
            }
            return parameters;
        }
    }
}
