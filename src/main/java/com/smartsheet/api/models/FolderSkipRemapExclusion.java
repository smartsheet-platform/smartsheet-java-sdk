package com.smartsheet.api.models;

/**
 * Represents specific objects should be excluded in some responses.
 */
public enum FolderSkipRemapExclusion {
    CELLLINKS   ("cellLinks"),
    REPORTS     ("reports"),
    ;

    String inclusion;

    FolderSkipRemapExclusion(String inclusion) { this.inclusion = inclusion;}

    @Override
    public String toString() {
        return super.toString();
    }
}
