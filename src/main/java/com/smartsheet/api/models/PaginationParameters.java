package com.smartsheet.api.models;

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
        HashMap<String, String> parameters = new HashMap<String, String>();

        parameters.put("includeAll", Boolean.toString(includeAll));

        if (includeAll) {
            return QueryUtil.generateUrl(null, parameters);
        } else {
            if (pageSize != null) {
                parameters.put("pageSize", pageSize.toString());
            }
            if (page != null) {
                parameters.put("page", page.toString());
            }
            return QueryUtil.generateUrl(null, parameters);
        }
    }
}
