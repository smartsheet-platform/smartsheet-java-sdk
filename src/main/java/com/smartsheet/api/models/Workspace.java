package com.smartsheet.api.models;

import java.lang.*;
import java.util.List;

import com.smartsheet.api.models.*;

/**
 * Represents the Workspace object.
 */
public class Workspace {
    /**
     * Represents access level.
     */
    private AccessLevel accessLevel;

    /**
     * Represents the link.
     */
    private String permalink;

    /**
     * Represents the sheets.
     */
    private List<Sheet> sheets;

    /**
     * Represents the folders.
     */
    private List<Folder> folders;
}

