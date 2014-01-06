package com.smartsheet.api.models;

import java.util.*;
import com.smartsheet.api.models.*;

/**
 * Represents the Row object.
 */
public class Row {
    /**
     * Represents the Sheet ID.
     */
    private Long sheetId;

    /**
     * Represents the row number.
     */
    private Integer rowNumber;

    /**
     * Represents the parent row number.
     */
    private Integer parentRowNumber;

    /**
     * Represents the cells.
     */
    private List<Cell> cells;

    /**
     * Represents the discussions.
     */
    private List<Discussion> discussions;

    /**
     * Represents the attachments.
     */
    private List<Attachment> attachments;

    /**
     * Represents the columns.
     */
    private List<Column> columns;

    /**
     * Represents the created at timestamp.
     */
    private Date createdAt;

    /**
     * Represents the modified at timestamp.
     */
    private Date modifiedAt;

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
}

