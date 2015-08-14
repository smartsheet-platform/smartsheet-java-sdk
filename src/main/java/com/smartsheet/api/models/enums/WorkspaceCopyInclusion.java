package com.smartsheet.api.models.enums;

/**
 * Represents specific objects that can be included in some responses.
 */
public enum WorkspaceCopyInclusion {
    DATA            ("data"),
    ATTACHMENTS		("attachments"),
    DISCUSSIONS     ("discussions"),
    CELLLINKS       ("cellinks"),
    FORMS           ("forms"),
    BRAND           ("brand"),
    SHARES          ("shares"),
    ALL             ("all");

    String inclusion;

    WorkspaceCopyInclusion(String inclusion) { this.inclusion = inclusion;}

    @Override
    public String toString() {
        return super.toString();
    }
}
