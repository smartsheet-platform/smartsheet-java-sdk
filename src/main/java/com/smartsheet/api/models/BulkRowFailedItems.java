package com.smartsheet.api.models;

/**
 * Created by kskeem on 3/1/16.
 */
public class BulkRowFailedItems {
    private int index;
    private Error error;
    private Long rowId;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }
}
