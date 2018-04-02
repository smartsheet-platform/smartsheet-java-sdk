package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.models.enums.CrossSheetReferenceStatus;

public class CrossSheetReference extends NamedModel<Long> {

    /**
     * the final column in the reference block
     */
    private Long endColumnId;

    /**
     * The last row in the reference block
     */
    private Long endRowId;

    /**
     * The source sheet ID for the reference block
     */
    private Long sourceSheetId;

    /**
     * The first column of the reference block
     */
    private Long startColumnId;

    /**
     * The first row of the reference block
     */
    private Long startRowId;

    /**
     * The status of the cross sheet reference
     */
    private CrossSheetReferenceStatus status;

    /**
     * Provide an 'override' of setName (returns CrossSheetReference not NamedModel)
     *
     * @param name the new name
     */
    public CrossSheetReference setName(String name){
        super.setName(name);
        return this;
    }

    /**
     * Get the last column ID in the cross sheet reference block
     *
     * @return the last column ID
     */
    public Long getEndColumnId() { return endColumnId; }

    /**
     * Set the last column ID in the cross sheet reference block
     *
     * @param endColumnId the last column ID
     */
    public CrossSheetReference setEndColumnId(Long endColumnId) {
        this.endColumnId = endColumnId;
        return this;
    }

    /**
     * Get the last row ID in the cross sheet reference block
     *
     * @return the last row ID
     */
    public Long getEndRowId() { return endRowId; }

    /**
     * Set the last row ID in the cross sheet reference block
     *
     * @param endRowId the last row ID
     */
    public CrossSheetReference setEndRowId(Long endRowId) {
        this.endRowId = endRowId;
        return this;
    }

    /**
     *Get the source sheet ID for the cross sheet reference
     *
     * @return the source sheet ID
     */
    public Long getSourceSheetId() { return sourceSheetId; }

    /**
     * Set the source sheet ID for the cross sheet reference
     *
     * @param sourceSheetId the source sheet ID
     */
    public CrossSheetReference setSourceSheetId(Long sourceSheetId) {
        this.sourceSheetId = sourceSheetId;
        return this;
    }

    /**
     * Get the start column ID for the cross sheet reference block
     *
     * @return the start column ID
     */
    public Long getStartColumnId() { return startColumnId; }

    /**
     * Set the start column ID for the cross sheet reference block
     *
     * @param startColumnId the start column ID
     */
    public CrossSheetReference setStartColumnId(Long startColumnId) {
        this.startColumnId = startColumnId;
        return this;
    }

    /**
     * Get the start row ID for the cross sheet reference block
     *
     * @return the start row ID
     */
    public Long getStartRowId() { return startRowId; }

    /**
     * Set the start row ID for the cross sheet reference block
     *
     * @param startRowId the start row ID
     */
    public CrossSheetReference setStartRowId(Long startRowId) {
        this.startRowId = startRowId;
        return this;
    }

    /**
     * Get the status of the cross sheet reference
     *
     * @return CrossSheetReferenceStatus enum
     */
    public CrossSheetReferenceStatus getStatus() { return status; }

    /**
     * Set the status of the cross sheet reference
     *
     * @param status CrossSheetReferenceStatus enum
     */
    public CrossSheetReference setStatus(CrossSheetReferenceStatus status) {
        this.status = status;
        return this;
    }
}
