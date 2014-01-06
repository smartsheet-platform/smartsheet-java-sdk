package com.smartsheet.api.models;

import java.lang.*;
import java.util.List;

/**
 * Represents SearchResultItem object.
 */
public class SearchResultItem {
    /**
     * Represents the text.
     */
    private String text;

    /**
     * Represents the object ID.
     */
    private Long objectId;

    /**
     * Represents the object type.
     */
    private String objectType;

    /**
     * Represents the parent object ID.
     */
    private Long parentObjectId;

    /**
     * Represents the parent object type.
     */
    private String parentObjectType;

    /**
     * Represents the parent object name.
     */
    private String parentObjectName;

    /**
     * Represents the context data.
     */
    private List<String> contextData;
}

