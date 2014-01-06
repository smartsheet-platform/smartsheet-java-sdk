package com.smartsheet.api.models;

import java.lang.*;
import java.util.List;

/**
 * Represents the RowWrapper object.
 */
public class RowWrapper {
    /**
     * Represents to-top flag.
     */
    private Boolean toTop;

    /**
     * Represents to-bottom flag.
     */
    private Boolean toBottom;

    /**
     * Represents the parent ID.
     */
    private Long parentId;

    /**
     * Represents the sibling ID.
     */
    private Long siblingId;

    /**
     * Represents the rows.
     */
    private List<Row> rows;
}

