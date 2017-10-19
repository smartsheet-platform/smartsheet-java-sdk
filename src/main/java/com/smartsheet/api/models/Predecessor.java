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

public class Predecessor {

    /**
     * The Id of the predecessor row
     */
    private Long rowId;

    /**
     * The row number of the predecessor row
     */
    private Integer rowNumber;

    /**
     * The type of the predecessor - one of FS, FF, SS, or SF
     */
    private String type;

    /**
     * The lag value of this predecessor. Omitted if there is no lag.
     */
    private Duration lag;

    /**
     * True if the row referenced by rowId is not a valid row in this sheet, or there is a circular reference
     */
    private Boolean invalid;

    /**
     * True if this predecessor is in the critical path
     */
    private Boolean inCriticalPath;

    /**
     * Get the Id of the predecessor row
     *
     * @return rowId
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * Set the Id of the predecessor row
     *
     * @param rowId
     */
    public Predecessor setRowId(Long rowId) {
        this.rowId = rowId;
        return this;
    }

    /**
     * Get the number of the predecessor row
     *
     * @return rowNumber
     */
    public Integer getRowNumber() {
        return rowNumber;
    }

    /**
     * Set the number of the predecessor row
     *
     * @param rowNumber
     */
    public Predecessor setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    /**
     * Get the type of the predecessor
     *
     * @return type (FS, FF, SS, or SF)
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the predecessor
     *
     * @param type
     */
    public Predecessor setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the lag value of this predecessor
     *
     * @return lag
     */
    public Duration getLag() {
        return lag;
    }

    /**
     * Set the lag value of this predecessor
     *
     * @param lag
     */
    public Predecessor setLag(Duration lag) {
        this.lag = lag;
        return this;
    }

    /**
     * Get flag indicating if the row referenced by rowId is not a valid row in this sheet,
     * or if there is a circular reference
     *
     * @return invalid
     */
    public Boolean isInvalid() {
        return invalid;
    }

    /**
     * Set flag indicating if the row referenced by rowId is not a valid row in this sheet,
     * or if there is a circular reference
     *
     * @param invalid
     */
    public Predecessor setInvalid(Boolean invalid) {
        this.invalid = invalid;
        return this;
    }

    /**
     * Get flag indicating if this predecessor is in the critical path
     *
     * @return inCriticalPath
     */
    public Boolean isInCriticalPath() {
        return inCriticalPath;
    }

    /**
     * Set flag indicating if this predecessor is in the critical path
     *
     * @param inCriticalPath
     */
    public Predecessor setInCriticalPath(Boolean inCriticalPath) {
        this.inCriticalPath = inCriticalPath;
        return this;
    }
}

