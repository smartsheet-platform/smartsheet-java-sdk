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

import com.smartsheet.api.models.enums.AccessLevel;

/**
 * Represents the Workspace object which is an area in which sheets, reports, templates and sub-folders can be 
 * organized, similar to a folder.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/506687-creating-a-workspace">Help Creating a 
 * Workspace</a>
 */
public class Workspace extends Folder {
    /** Represents the user's permissions on a workspace. */
    private AccessLevel accessLevel;

    /** Represents the link . */
    private String permalink;

    /**
     *  Represents if the workspace is marked as favorite
     */
    private Boolean favorite;

    /**
     * Gets the user's permissions on a workspace.
     *
     * @return the access level
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the user's permissions on a workspace.
     *
     * @param accessLevel the new access level
     */
    public Workspace setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    /**
     * Gets the permalink to the workspace.
     *
     * @return the permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Sets the permalink to the workspace.
     *
     * @param permalink the new permalink
     */
    public Folder setPermalink(String permalink) {
        this.permalink = permalink;
        return null;
    }

    /**
     * Gets the favorite
     * @return the favorite
     */
    public Boolean getFavorite() {
        return favorite;
    }

    /**
     * Sets the favorite
     * @param favorite the favorite
     */
    public Folder setFavorite(Boolean favorite) {
        this.favorite = favorite;
        return null;
    }

    /**
     * A convenience class for creating a {@link Workspace} object with the appropriate fields for updating a workspace.
     */
    public static class UpdateWorkspaceBuilder {
        private String workspaceName;
        private Long id;

        /**
         * Get the workspace id
         * @return the workspace id
         */
        public Long getId() {
            return id;
        }

        /**
         * Set the workspace id
         * @param id the workspace id
         * @return the builder
         */
        public UpdateWorkspaceBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * The name of the workspace.
         *
         * @param name the name
         * @return the update workspace builder
         */
        public UpdateWorkspaceBuilder setName(String name) {
            this.workspaceName = name;
            return this;
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName() {
            return workspaceName;
        }

        /**
         * Builds the {@link Workspace}.
         *
         * @return the workspace
         */
        public Workspace build() {
            if(workspaceName == null){
                throw new InstantiationError("A workspace name is required.");
            }

            Workspace workspace = new Workspace();
            workspace.setName(workspaceName);
            workspace.setId(id);
            return workspace;
        }
    }
}
