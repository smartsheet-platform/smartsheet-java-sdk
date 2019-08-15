package com.smartsheet.api.models;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2019 Smartsheet
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

public class Scope {
    /**
     * Array of Sheet objects (containing just the sheet ID) of any sheets that the
     * requester has access to that make up the report
     */
    private List<Sheet> sheets;

    /**
     * Array of Workspace objects (containing just the workspace ID) that the requester has access to
     * that make up the report
     */
    private List<Workspace> workspaces;

    /**
     * Gets the array of any sheets the requester has access to that make up the report
     *
     * @return the array of sheets
     */
    public List<Sheet> getSheets() { return sheets; }

    /**
     * Sets the array of sheets the requester has access to that make up the report
     *
     * @param sheets the array of sheets
     */
    public Scope setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
        return this;
    }

    /**
     * Gets the array of any workspaces the requester has access to that make up the report
     *
     * @return the array of workspaces
     */
    public List<Workspace> getWorkspaces() { return workspaces; }

    /**
     * Sets the array of any workspaces the requester has access to that make up the report
     *
     * @param workspaces the array of workspaces
     */
    public Scope setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
        return this;
    }
}
