package com.smartsheet.api.models;

import java.lang.*;
import com.smartsheet.api.models.*;

/**
 * Represents the Cell object.
 */
public class Cell {
    /**
     * Represents the type.
     */
    private ColumnType type;

    /**
     * Represents the value.
     */
    private String value;

    /**
     * Represents the display value.
     */
    private String displayValue;

    /**
     * Represents the column ID.
     */
    private Long columnId;

    /**
     * Represents the row ID.
     */
    private Long rowId;

    /**
     * Represents the link.
     */
    private Link link;

    /**
     * Represents the formula.
     */
    private String formula;

    /**
     * Represents the strict flag.
     */
    private Boolean strict;
}

