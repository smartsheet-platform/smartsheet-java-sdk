package com.smartsheet.api.models.enums;

/**
 * Represents specific objects that can be included in some responses.
 */
public enum SheetCopyInclusion {
    DATA    ("data"),
    ATTACHMENTS		("attachments"),
    DISCUSSIONS     ("discussions"),
    CELLLINKS       ("cellinks"),
    FORMS           ("forms"),
    ALL             ("all");

    String inclusion;

    SheetCopyInclusion(String inclusion) { this.inclusion = inclusion;}

    @Override
    public String toString() {
        return super.toString();
    }
}
