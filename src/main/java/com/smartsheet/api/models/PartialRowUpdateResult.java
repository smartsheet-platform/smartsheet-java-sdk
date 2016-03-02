package com.smartsheet.api.models;

import java.util.List;

/**
 * Created by kskeem on 3/1/16.
 */
public class PartialRowUpdateResult extends Result<List<Row>> {

    private BulkRowFailedItems[] failedItems;

    public BulkRowFailedItems[] getFailedItems() {
        return failedItems;
    }

    public void setFailedItems(BulkRowFailedItems[] failedItems) {
        this.failedItems = failedItems;
    }
}
