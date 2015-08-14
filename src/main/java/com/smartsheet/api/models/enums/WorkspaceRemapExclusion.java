package com.smartsheet.api.models.enums;

/**
 * Represents specific objects should be excluded in some responses.
 */
public enum WorkspaceRemapExclusion {
    CELLLINKS   ("cellLinks"),
    REPORTS     ("reports"),
    ;

    String inclusion;

    WorkspaceRemapExclusion(String inclusion) { this.inclusion = inclusion;}

    @Override
    public String toString() {
        return super.toString();
    }
}
