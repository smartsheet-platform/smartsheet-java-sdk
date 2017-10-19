package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */



import java.util.Date;

/**
 * Represents CellHistory object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/518314-viewing-cell-history">Cell History Documentation</a>
 */
public class CellHistory extends Cell {
    /**
     * Represents the user that modified the cell.
     */
    private User modifiedBy;

    /** The date the cell was modified. */
    private Date modifiedAt;

    /**
     * Gets the date the cell was modified.
     *
     * @return the modified at
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Sets the modified Date for the cell.
     *
     * @param modifiedAt the new modified at
     */
    public CellHistory setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * Gets the user that modified the cell.
     *
     * @return the modified by
     */
    public User getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the user that modified the cell.
     *
     * @param modifiedBy the new modified by
     */
    public CellHistory setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }
}
