package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents the Home object.
 */
public class Home {
    /**
     * Represents the sheets.
     */
    private List<Sheet> sheets;

    /**
     * Represents the folders.
     */
    private List<Folder> folders;

    /**
     * Represents the reports.
     */
    //private List<Report> reports;

    /**
     * Represents the templates.
     */
    private List<Template> templates;

    /**
     * Represents the workspaces.
     */
    private List<Workspace> workspaces;
}

