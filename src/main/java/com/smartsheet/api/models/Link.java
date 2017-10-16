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

import com.smartsheet.api.models.enums.LinkType;

/**
 * Represents the Link object.
 */
public class Link {
    /**
     * Represents the link type.
     */
    private LinkType type;

    /**
     * Represents the URL.
     */
    private String url;

    /**
     * Represents the sheet ID.
     */
    private Long sheetId;

    /**
     * Represents the sight ID.
     */
    private Long sightId;

    /**
     * Represents the column ID.
     */
    private Long columnId;

    /**
     * Represents the row ID.
     */
    private Long rowId;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public LinkType getType() {
        return type;
    }

    /**
     * Sets the link type.
     *
     * @param type the new type
     */
    public Link setType(LinkType type) {
        this.type = type;
        return this;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public Link setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Gets the sheet id.
     *
     * @return the sheet id
     */
    public Long getSheetId() {
        return sheetId;
    }

    /**
     * Sets the sheet id.
     *
     * @param sheetId the new sheet id
     */
    public Link setSheetId(Long sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    /**
     * Gets the sight id.
     *
     * @return sightId
     */
    public Long getSightId() {
        return sightId;
    }

    /**
     * Set the sight id.
     *
     * @param sightId
     */
    public Link setSightId(Long sightId) {
        this.sightId = sightId;
        return this;
    }

    /**
     * Gets the column id.
     *
     * @return the column id
     */
    public Long getColumnId() {
        return columnId;
    }

    /**
     * Sets the column id.
     *
     * @param columnId the new column id
     */
    public Link setColumnId(Long columnId) {
        this.columnId = columnId;
        return this;
    }

    /**
     * Gets the row id.
     *
     * @return the row id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * Sets the row id.
     *
     * @param rowId the new row id
     */
    public Link setRowId(Long rowId) {
        this.rowId = rowId;
        return this;
    }
}
