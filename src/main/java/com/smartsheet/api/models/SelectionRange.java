package com.smartsheet.api.models;

public class SelectionRange {

    /**
     * Defines beginning edge of range when specifying one or more columns.
     */
    private Long sourceColumnId1;

    /**
     * Defines ending edge of range when specifying one or more columns.
     */
    private Long sourceColumnId2;

    /**
     * Defines beginning edge of range when specifying one or more rows.
     */
    private Long sourceRowId1;

    /**
     * Defines ending edge of range when specifying one or more rows.
     */
    private Long sourceRowId2;

    /**
     * Gets the beginning of the range of columns
     *
     * @return sourceColumnId1
     */
    public Long getSourceColumnId1() { return sourceColumnId1; }

    /**
     * Sets the beginning of the range of columns
     *
     * @param sourceColumnId1
     */
    public SelectionRange setSourceColumnId1(Long sourceColumnId1) {
        this.sourceColumnId1 = sourceColumnId1;
        return this;
    }

    /**
     * Gets the ending of the range of columns
     *
     * @return sourceColumnId2
     */
    public Long getSourceColumnId2() { return sourceColumnId2; }

    /**
     * Sets the ending of the range of columns
     *
     * @param sourceColumnId2
     */
    public SelectionRange setSourceColumnId2(Long sourceColumnId2) {
        this.sourceColumnId2 = sourceColumnId2;
        return this;
    }

    /**
     * Gets the beginning of the range of rows
     *
     * @return sourceRowId1
     */
    public Long getSourceRowId1() { return sourceRowId1; }

    /**
     * Sets the beginning of the range of rows
     *
     * @param sourceRowId1
     */
    public SelectionRange setSourceRowId1(Long sourceRowId1) {
        this.sourceRowId1 = sourceRowId1;
        return this;
    }

    /**
     * Gets the ending of the range of rows
     *
     * @return sourceRowId2
     */
    public Long getSourceRowId2() { return sourceRowId2; }

    /**
     * Sets the ending of the range of rows
     *
     * @param sourceRowId2
     */
    public SelectionRange setSourceRowId2(Long sourceRowId2) {
        this.sourceRowId2 = sourceRowId2;
        return this;
    }
}
