package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2018 - 2015 Smartsheet
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

import com.smartsheet.api.models.enums.DestinationType;

public class ErrorDetail {

    /** User's alternate email address that was specified in the request. */
    private String alternateEmailAddress;

    /** When allowPartialSuccess = false, index of the row that caused the error. */
    private Integer index;

    /** The server-side limit on the number of sheets allowed in a single folder/workspace copy operation. */
    private Integer maxSheetCount;

    /** User's primary email address that must instead by specified for the operation. */
    private String primaryEmailAddress;

    /** When allowPartialSuccess = false, rowID of the row that caused the error. */
    private Long rowId;

    /** The ID of the top level folder or workspace that was partially copied. */
    private Long topContainerId;

    /** The destination type of the top level folder or workspace that was partially copied. */
    private DestinationType topContainerType;

    /**
     * Gets the alternate email address that was specified in the request.
     *
     * @return the alternate email address
     */
    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    /**
     * Sets the alternate email address that was specified in the request
     *
     * @param alternateEmailAddress the alternate email address
     */
    public ErrorDetail setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
        return this;
    }

    /**
     * Gets the index of the row that caused the error.
     *
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the index of the row that caused the error
     *
     * @param index the index
     */
    public ErrorDetail setIndex(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * Gets the server-side limit on the number of sheets allowed in a single copy operation.
     *
     * @return the maximum sheet count
     */
    public Integer getMaxSheetCount() {
        return maxSheetCount;
    }

    /**
     * Sets the maximum sheet count for the server-side limit
     *
     * @param maxSheetCount the maximum sheet count
     */
    public ErrorDetail setMaxSheetCount(Integer maxSheetCount) {
        this.maxSheetCount = maxSheetCount;
        return this;
    }

    /**
     * Gets the primary email address that should be specified in the request.
     *
     * @return the primary email address
     */
    public String getPrimaryEmailAddress() {
        return primaryEmailAddress;
    }

    /**
     * Sets the primary email address that should be specified in the request
     *
     * @param primaryEmailAddress the primary email address
     */
    public ErrorDetail setPrimaryEmailAddress(String primaryEmailAddress) {
        this.primaryEmailAddress = primaryEmailAddress;
        return this;
    }

    /**
     * Gets the row ID of the row that caused the error.
     *
     * @return the row ID
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * Sets the row ID of the row that caused the error
     *
     * @param rowId the row ID
     */
    public ErrorDetail setRowId(Long rowId) {
        this.rowId = rowId;
        return this;
    }

    /**
     * Gets the ID of the container that was partially copied
     *
     * @return the container ID
     */
    public Long getTopContainId() {
        return topContainerId;
    }

    /**
     * Sets the ID of the container that was partially copied
     *
     * @param topContainerId the container ID
     */
    public ErrorDetail setTopContainerId(Long topContainerId) {
        this.topContainerId = topContainerId;
        return this;
    }

    /**
     * Gets the type of the container that was partially copied
     *
     * @return the container type
     */
    public DestinationType getTopContainerType() {
        return topContainerType;
    }

    /**
     * Sets the type of the container that was partially copied
     *
     * @param topContainerType the container ID
     */
    public ErrorDetail setTopContainerType(DestinationType topContainerType) {
        this.topContainerType = topContainerType;
        return this;
    }
}
