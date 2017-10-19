package com.smartsheet.api.models;

import com.smartsheet.api.models.enums.AccessLevel;

import java.util.Date;
import java.util.List;

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

public class Sight extends NamedModel<Long> {

    /**
     * Number of columns that the Sight contains
     */
    private Integer columnCount;

    /**
     * Array of Widget objects
     */
    private List<Widget> widgets;

    /**
     * Indicates whether the User has marked the Sight as a favorite
     */
    private Boolean favorite;

    /**
     * User's permissions on the Sight.
     */
    private AccessLevel accessLevel;

    /**
     * URL that represents a direct link to the Sight
     */
    private String permalink;

    /**
     * Time of creation
     */
    private Date createdAt;

    /**
     * Time last modified
     */
    private Date modifiedAt;

    /**
     * A workspace object, limited to only Id and Name
     */
    private Workspace workspace;

    /**
     * Get the number of columns that the Sight contains
     *
     * @return columnCount
     */
    public Integer getColumnCount() {
        return columnCount;
    }

    /**
     * Set the number of columns that the Sight contains
     *
     * @param columnCount
     */
    public Sight setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    /**
     * Get the array of Sight widgets
     *
     * @return array of widgets
     */
    public List<Widget> getWidgets() {
        return widgets;
    }

    /**
     * Set the array of Sight widgets
     *
     * @param widgets
     */
    public Sight setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
        return this;
    }

    /**
     * Get flag indicating whether the user has marked the Sight as a favorite
     *
     * @return favorite flag
     */
    public Boolean getFavorite() {
        return favorite;
    }

    /**
     * Set flag indicating whether the user has marked the Sight as a favorite
     *
     * @param favorite
     */
    public Sight setFavorite(Boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    /**
     * User's permissions on the Sight (OWNDER, ADMIN, VIEWER)
     *
     * @return accessLevel
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    /**
     * Set User's permissions on the Sight
     *
     * @param accessLevel
     */
    public Sight setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    /**
     * URL that represents a direct link to the Sight
     *
     * @return permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Set URL pointing to a direct link to the Sight
     *
     * @param permalink
     */
    public Sight setPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    /**
     * Get time of Sight creation
     *
     * @return createdAt (Date)
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set time of Sight creation
     *
     * @param createdAt
     */
    public Sight setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Get time of last modification
     *
     * @return modifiedAt (Date)
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Set time of last modification
     *
     * @param modifiedAt
     */
    public Sight setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * A workspace object limited to only id and name
     *
     * @return workspace
     */
    public Workspace getWorkspace() {
        return workspace;
    }

    /**
     * Set workspace object for this Sight (limited to only id and name)
     *
     * @param workspace
     */
    public Sight setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        return this;
    }
}
