package com.smartsheet.api.models;

import java.util.*;
import java.lang.*;
import com.smartsheet.api.models.*;

/**
 * Represents Sheet object in the Smartsheet REST API.
 */
public class Sheet {
    /**
     * Represents the columns.
     */
    private List<Column> columns;

    /**
     * Represents the rows.
     */
    private List<Row> rows;

    /**
     * Represents the access level.
     */
    private AccessLevel accessLevel;

    /**
     * Represents the discussions.
     */
    private List<Discussion> discussions;

    /**
     * Represents the attachments.
     */
    private List<Attachment> attachments;

    /**
     * Represents the read only flag.
     */
    private Boolean readOnly;

    /**
     * Represents the creation timestamp.
     */
    private Date createdAt;

    /**
     * Represents the modification timestamp.
     */
    private Date modifiedAt;

    /**
     * Represents the direct URL to the sheet.
     */
    private String permalink;

    /**
     * Represents the Gantt enabled flag.
     */
    private Boolean ganttEnabled;

    /**
     * Represents the version.
     */
    private Integer version;

    /**
     * Represents the ID of the sheet/template from which the sheet was created.
     */
    private Long fromId;

    /**
     * Get column by index.
     * 
     * Parameters:
     * - index : the column index
     * 
     * Returns:
     * the column
     * 
     * Implementation:
     * for (Column column : columns) {
     *     if (column.getIndex() == index) {
     *         return column;
     *     }
     * }
     * return null;
     * @param index 
     * @return 
     */
    public Column getColumnByIndex(int index) {
        return null;
    }

    /**
     * Get column by ID.
     * 
     * Parameters:
     * - columnId : the column ID
     * 
     * Returns:
     * the column
     * 
     * Implementation:
     * for (Column column : columns) {
     *     if (column.getId() == columnId) {
     *         return column;
     *     }
     * }
     * return null;
     * @param columnId 
     * @return 
     */
    public Column getColumnById(long columnId) {
        return null;
    }

    /**
     * Get row by row number.
     * 
     * Parameters:
     * - rowNumber : the row number
     * 
     * Returns:
     * the row
     * 
     * Implementation:
     * for (Row row : rows) {
     *     if (row.getRowNumber() == rowNumber) {
     *         return row;
     *     }
     * }
     * return null;
     * @param rowNumber 
     * @return 
     */
    public Row getRowByRowNumber(int rowNumber) {
        return null;
    }
}

