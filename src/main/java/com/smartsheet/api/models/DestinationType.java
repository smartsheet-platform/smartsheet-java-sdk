package com.smartsheet.api.models;
/**
 * Represents the type of the destination container when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied..
 */
public enum DestinationType {
    /** Represents the home destination container. */
    HOME,

    /** Represents the workspace destination container. */
    WORKSPACE,

    /** Represents the folder destination container. */
    FOLDER,
}
