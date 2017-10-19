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

import java.util.List;

/**
 * Represents the RowWrapper object that is used to specify the location for a {@link Row} or set of Rows.
 */
public class RowWrapper {
    /**
     * Represents to-top flag that puts the row at the top of the sheet.
     */
    private Boolean toTop;

    /**
     * Represents to-bottom flag that puts the row at the bottom of the sheet.
     */
    private Boolean toBottom;

    /**
     * Represents the parent ID that puts the row as the first child of the specified id.
     */
    private Long parentId;

    /**
     * Represents the sibling ID that puts the row as the next row at the same hierarchical level of this row.
     */
    private Long siblingId;

    /**
     * Represents the rows.
     */
    private List<Row> rows;

    /**
     * Gets the to top flag that puts the row at the top of the sheet.
     *
     * @return the to top
     */
    public Boolean getToTop() {
        return toTop;
    }

    /**
     * Sets the to top flag that puts the row at the top of the sheet.
     *
     * @param toTop the new to top
     */
    public RowWrapper setToTop(Boolean toTop) {
        this.toTop = toTop;
        return this;
    }

    /**
     * Gets the to bottom flag that puts the row at the bottom of the sheet.
     *
     * @return the to bottom
     */
    public Boolean getToBottom() {
        return toBottom;
    }

    /**
     * Sets the to bottom flag that puts the row at the bottom of the sheet.
     *
     * @param toBottom the new to bottom
     */
    public RowWrapper setToBottom(Boolean toBottom) {
        this.toBottom = toBottom;
        return this;
    }

    /**
     * Gets the parent id that puts the row as the first child of the specified id.
     *
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the parent id that puts the row as the first child of the specified id.
     *
     * @param parentId the new parent id
     */
    public RowWrapper setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * Gets the sibling id that puts the row as the next row at the same hierarchical level of this row.
     *
     * @return the sibling id
     */
    public Long getSiblingId() {
        return siblingId;
    }

    /**
     * Sets the sibling id that puts the row as the next row at the same hierarchical level of this row.
     *
     * @param siblingId the new sibling id
     */
    public RowWrapper setSiblingId(Long siblingId) {
        this.siblingId = siblingId;
        return this;
    }

    /**
     * Gets the rows.
     *
     * @return the rows
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Sets the rows.
     *
     * @param rows the new rows
     */
    public RowWrapper setRows(List<Row> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * A convenience class for creating a {@link RowWrapper} with the necessary fields for inserting a {@link Row} or
     * set of rows.
     */
    public static class InsertRowsBuilder {
        private Boolean toTop;
        private Boolean toBottom;
        private Long parentId;
        private Long siblingId;
        private List<Row> rows;

        /**
         * Sets the to top flag that puts the row at the top of the sheet.
         *
         * @param toTop the to top flag
         * @return the insert rows builder
         */
        public InsertRowsBuilder setToTop(Boolean toTop) {
            this.toTop = toTop;
            return this;
        }

        /**
         * Sets the to bottom flag that puts the row at the bottom of the sheet.
         *
         * @param toBottom the to bottom
         * @return the insert rows builder
         */
        public InsertRowsBuilder setToBottom(Boolean toBottom) {
            this.toBottom = toBottom;
            return this;
        }

        /**
         * Sets the parent id that puts the row as the first child of the specified id.
         *
         * @param parentId the parent id
         * @return the insert rows builder
         */
        public InsertRowsBuilder setParentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        /**
         * Sets the sibling id that puts the row as the next row at the same hierarchical level of this row.
         *
         * @param siblingId the sibling id
         * @return the insert rows builder
         */
        public InsertRowsBuilder setSiblingId(Long siblingId) {
            this.siblingId = siblingId;
            return this;
        }

        /**
         * Sets the rows.
         *
         * @param rows the rows
         * @return the insert rows builder
         */
        public InsertRowsBuilder setRows(List<Row> rows) {
            this.rows = rows;
            return this;
        }



        /**
         * Gets the to top.
         *
         * @return the to top
         */
        public Boolean getToTop() {
            return toTop;
        }

        /**
         * Gets the to bottom.
         *
         * @return the to bottom
         */
        public Boolean getToBottom() {
            return toBottom;
        }

        /**
         * Gets the parent id.
         *
         * @return the parent id
         */
        public Long getParentId() {
            return parentId;
        }

        /**
         * Gets the sibling id.
         *
         * @return the sibling id
         */
        public Long getSiblingId() {
            return siblingId;
        }

        /**
         * Gets the rows.
         *
         * @return the rows
         */
        public List<Row> getRows() {
            return rows;
        }

        /**
         * Builds the RowWrapper.
         *
         * @return the row wrapper
         */
        public RowWrapper build() {
            if (toTop == null && toBottom == null && parentId == null && siblingId == null) {
                new InstantiationException("One of the following fields must be set toTop, toBottom, parentId or"
                        + " sibling Id");
            }

            RowWrapper rowWrapper = new RowWrapper();
            rowWrapper.toTop = toTop;
            rowWrapper.toBottom = toBottom;
            rowWrapper.parentId = parentId;
            rowWrapper.siblingId = siblingId;
            rowWrapper.rows = rows;
            return rowWrapper;
        }
    }

    /**
     * A convenience class for creating a {@link RowWrapper} with the necessary fields for moving a {@link Row} or set
     * of rows.
     */
    public static class MoveRowBuilder {
        private Boolean toTop;
        private Boolean toBottom;
        private Long parentId;
        private Long siblingId;

        /**
         * Sets the to top flag that puts the row at the top of the sheet.
         *
         * @param toTop the to top
         * @return the move row builder
         */
        public MoveRowBuilder setToTop(Boolean toTop) {
            this.toTop = toTop;
            return this;
        }

        /**
         * Sets the to bottom flag that puts the row at the bottom of the sheet.
         *
         * @param toBottom the to bottom
         * @return the move row builder
         */
        public MoveRowBuilder setToBottom(Boolean toBottom) {
            this.toBottom = toBottom;
            return this;
        }

        /**
         * Sets the parent id that puts the row as the first child of the specified id.
         *
         * @param parentId the parent id
         * @return the move row builder
         */
        public MoveRowBuilder setParentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        /**
         * Sets the sibling id that puts the row as the next row at the same hierarchical level of this row.
         *
         * @param siblingId the sibling id
         * @return the move row builder
         */
        public MoveRowBuilder setSiblingId(Long siblingId) {
            this.siblingId = siblingId;
            return this;
        }

        /**
         * Gets the to top.
         *
         * @return the to top
         */
        public Boolean getToTop() {
            return toTop;
        }

        /**
         * Gets the to bottom.
         *
         * @return the to bottom
         */
        public Boolean getToBottom() {
            return toBottom;
        }

        /**
         * Gets the parent id.
         *
         * @return the parent id
         */
        public Long getParentId() {
            return parentId;
        }

        /**
         * Gets the sibling id.
         *
         * @return the sibling id
         */
        public Long getSiblingId() {
            return siblingId;
        }

        /**
         * Builds the RowWrapper.
         *
         * @return the row wrapper
         */
        public RowWrapper build() {
            if( toTop == null && toBottom == null && parentId == null && siblingId == null){
                throw new InstantiationError("One of the following must be defined to move a row: toTop, toBottom,"
                        + " parentId, siblingId.");
            }

            RowWrapper rowWrapper = new RowWrapper();
            rowWrapper.toTop = toTop;
            rowWrapper.toBottom = toBottom;
            rowWrapper.parentId = parentId;
            rowWrapper.siblingId = siblingId;
            return rowWrapper;
        }
    }
}
