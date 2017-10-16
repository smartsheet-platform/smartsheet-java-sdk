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
 * Represents the Home object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522237-the-home-tab">Home Tab Help</a>
 */
public class Home {
    /**
     * Represents the sheets in the home location.
     */
    private List<Sheet> sheets;

    /**
     * Represents the folders in the home location.
     */
    private List<Folder> folders;

    /**
     * Represents the reports in the home location.
     */
    private List<Report> reports;

    /**
     * Represents the templates in the home location.
     */
    private List<Template> templates;

    /**
     * Represents the workspaces in the home location.
     */
    private List<Workspace> workspaces;

    /**
     * Represents the sights in the home location.
     */
    private List<Sight> sights;

    /**
     * Gets the sheets in the home location.
     *
     * @return the sheets
     */
    public List<Sheet> getSheets() {
        return sheets;
    }

    /**
     * Sets the sheets.
     *
     * @param sheets the new sheets
     */
    public Home setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
        return this;
    }

    /**
     * Gets the folders in the home location.
     *
     * @return the folders
     */
    public List<Folder> getFolders() {
        return folders;
    }

    /**
     * Sets the folders in the home location.
     *
     * @param folders the new folders
     */
    public Home setFolders(List<Folder> folders) {
        this.folders = folders;
        return this;
    }

    /**
     * Gets the reports.
     *
     * @return the reports
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * Sets the reports.
     *
     * @param reports the new reports
     */
    public Home setReports(List<Report> reports) {
        this.reports = reports;
        return this;
    }

    /**
     * Gets the templates in the home location.
     *
     * @return the templates
     */
    public List<Template> getTemplates() {
        return templates;
    }

    /**
     * Sets the templates in the home location.
     *
     * @param templates the new templates
     */
    public Home setTemplates(List<Template> templates) {
        this.templates = templates;
        return this;
    }

    /**
     * Gets the workspaces in the home location.
     *
     * @return the workspaces
     */
    public List<Workspace> getWorkspaces() {
        return workspaces;
    }

    /**
     * Sets the workspaces in the home location.
     *
     * @param workspaces the new workspaces
     */
    public Home setWorkspaces(List<Workspace> workspaces) {
        this.workspaces = workspaces;
        return this;
    }

    /**
     * Gets the sights in the home location.
     *
     * @return array of sights
     */
    public List<Sight> getSights() {
        return sights;
    }

    /**
     * Sets the sights in the home location.
     *
     * @param sights
     */
    public Home setSights(List<Sight> sights) {
        this.sights = sights;
        return this;
    }
}
