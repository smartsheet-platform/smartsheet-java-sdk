package com.smartsheet.api.models.enums;

/**
 * Represents specific objects should be excluded in some responses.
 */
public enum FolderRemapExclusion {
    CELLLINKS   ("cellLinks"),
    REPORTS     ("reports"),
    ;

    String inclusion;

    FolderRemapExclusion(String inclusion) { this.inclusion = inclusion;}

    @Override
    public String toString() {
        return super.toString();
    }
}
