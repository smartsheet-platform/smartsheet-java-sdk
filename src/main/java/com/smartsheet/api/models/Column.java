package com.smartsheet.api.models;

import com.smartsheet.api.models.*;

import java.lang.*;
import java.util.List;

/**
 * Represents the Column object.
 */
public class Column {
    /**
     * Represents the index.
     */
    private Integer index;

    /**
     * Represents the title.
     */
    private String title;

    /**
     * Represents the primrary flag.
     */
    private Boolean primary;

    /**
     * Represents the type.
     */
    private ColumnType type;

    /**
     * Represents the options.
     */
    private List<String> options;

    /**
     * Represents the hiddenn flag.
     */
    private Boolean hidden;

    /**
     * Represents the symbol.
     */
    private Symbol symbol;

    /**
     * Represents the system column type.
     */
    private SystemColumnType systemColumnType;

    /**
     * Represents the auto number format.
     */
    private AutoNumberFormat autoNumberFormat;

    /**
     * Represents the tags.
     */
    private List<ColumnTag> tags;

    /**
     * Represents the sheet ID.
     */
    private Long sheetId;
}

