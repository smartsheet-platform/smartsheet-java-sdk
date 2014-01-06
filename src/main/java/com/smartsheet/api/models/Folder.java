package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents Folder object in the Smartsheet REST API.
 */
public class Folder {
    /**
     * Represents the sheets.
     */
    private List<Sheet> sheets;

    /**
     * Represents the child folders.
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

