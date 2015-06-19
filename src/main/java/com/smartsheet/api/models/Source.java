package com.smartsheet.api.models;

public class Source extends IdentifiableModel<Long> {
    /**
     * Represents the type.
     */
    private String type;

    /**
     * Gets the type.
     *
     * @return the name
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

}
