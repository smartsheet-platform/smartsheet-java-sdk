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
     * @param includeAll include all parameter
     */
    public PaginationParameters setIncludeAll(boolean includeAll) {
        this.includeAll = includeAll;
        return this;
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
     * @param pageSize the page size
     */
    public PaginationParameters setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Gets the page
     * @return page the page number
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets the page
     * @param page the page number
     */
    public PaginationParameters setPage(Integer page) {
        this.page = page;
        return this;
    }

    public String toQueryString() {
        HashMap<String, Object> parameters = toHashMap();
        return QueryUtil.generateUrl(null, parameters);
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("includeAll", Boolean.toString(includeAll));
        if (includeAll) {
            return parameters;
        } else {
            parameters.put("pageSize", pageSize);
            parameters.put("page", page);
            return parameters;
        }
    }

    /**
     * A convenience class for creating a PaginationParameters object
     */
    public static class PaginationParametersBuilder {
        private boolean includeAll;
        private Integer pageSize;
        private Integer page;

        /**
         * Gets the include all flag
         * @return the include all flag
         */
        public boolean isIncludeAll() {
            return includeAll;
        }

        /**
         * Sets the include All Flag
         * @param includeAll the include all flag
         * @return the builder
         */
        public PaginationParametersBuilder setIncludeAll(boolean includeAll) {
            this.includeAll = includeAll;
            return this;
        }

        /**
         * Gets the page
         * @return the page
         */
        public Integer getPage() {
            return page;
        }

        /**
         * Sets the page
         * @param page the page
         * @return the builder
         */
        public PaginationParametersBuilder setPage(Integer page) {
            this.page = page;
            return this;
        }

        /**
         * Gets the page size
         * @return the page size
         */
        public Integer getPageSize() {
            return pageSize;
        }

        /**
         * Sets the page size
         * @param pageSize the page size
         * @return the builder
         */
        public PaginationParametersBuilder setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * Builds the PaginationParameters object
         * @return pagination parameter object
         */
        public PaginationParameters build() {
            PaginationParameters pagination = new PaginationParameters();
            pagination.setIncludeAll(includeAll);
            pagination.setPageSize(pageSize);
            pagination.setPage(page);

            return pagination;
        }
    }
}
