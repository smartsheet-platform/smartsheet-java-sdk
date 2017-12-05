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

import com.smartsheet.api.models.format.Format;

import java.util.List;

/**
 * Represents the Row object.
 */
public class Row extends AbstractRow<Column, Cell> {

    /**
     * Default constructor
     */
    public Row() {
    }

    /**
     * Construct a Row with specified id
     *
     * @param id Row id
     */
    public Row(Long id) {
        this.setId(id);
    }

    /**
     * A convenience class for creating a {@link RowWrapper} with the necessary fields for inserting a {@link Row} or
     * set of rows.
     */
    public static class AddRowBuilder {
        private Boolean toTop;
        private Boolean toBottom;
        private Long parentId;
        private Long siblingId;
        private Boolean above;
        private Format format;
        private Boolean expanded;
        private List<Cell> cells;

        /**
         * Gets the to top.
         *
         * @return the to top
         */
        public Boolean getToTop() {
            return toTop;
        }

        /**
         * Sets the to top flag that puts the row at the top of the sheet.
         *
         * @param toTop the to top flag
         * @return the insert rows builder
         */
        public AddRowBuilder setToTop(Boolean toTop) {
            this.toTop = toTop;
            return this;
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
         * Sets the to bottom flag that puts the row at the bottom of the sheet.
         *
         * @param toBottom the to bottom
         * @return the insert rows builder
         */
        public AddRowBuilder setToBottom(Boolean toBottom) {
            this.toBottom = toBottom;
            return this;
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
         * Sets the parent id that puts the row as the first child of the specified id.
         *
         * @param parentId the parent id
         * @return the insert rows builder
         */
        public AddRowBuilder setParentId(Long parentId) {
            this.parentId = parentId;
            return this;
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
         * Sets the sibling id that puts the row as the next row at the same hierarchical level of this row.
         *
         * @param siblingId the sibling id
         * @return the insert rows builder
         */
        public AddRowBuilder setSiblingId(Long siblingId) {
            this.siblingId = siblingId;
            return this;
        }

        /**
         * Gets the above flag
         * @return the above flag
         */
        public Boolean getAbove() { return above; }

        /**
         * Sets the above flag
         * @param above the above flag
         * @return the insert rows builder
         */
        public AddRowBuilder setAbove(Boolean above) {
            this.above = above;
            return this;
        }

        /**
         * Gets the format
         * @return the format
         */
        public Format getFormat() { return format; }

        /**
         * Sets the format
         * @param format the format
         * @return the insert rows builder
         */
        public AddRowBuilder setFormat(Format format) {
            this.format = format;
            return this;
        }

        /**
         * Gets the expanded flag
         * @return the expanded flag
         */
        public Boolean getExpanded() { return expanded; }

        /**
         * Sets the expanded flag
         * @param expanded the expanded flag
         * @return the insert rows builder
         */
        public AddRowBuilder setExpanded(Boolean expanded) {
            this.expanded = expanded;
            return this;
        }

        /**
         * Gets the list of cells
         * @return the list of cells
         */
        public List<Cell> getCells() { return cells; }

        /**
         * Sets the list of cells
         * @param cells the list of cells
         * @return the insert rows builder
         */
        public AddRowBuilder setCells(List<Cell> cells) {
            this.cells = cells;
            return this;
        }

        /**
         * Builds the row object
         * @return the row object
         */
        public Row build() {
            Row row = new Row();
            row.setToTop(toTop);
            row.setToBottom(toBottom);
            row.setParentId(parentId);
            row.setSiblingId(siblingId);
            row.setAbove(above);
            row.setFormat(format);
            row.setExpanded(expanded);
            row.setCells(cells);
            return row;
        }
    }

    public static class UpdateRowBuilder {
        private Boolean toTop;
        private Boolean toBottom;
        private Long parentId;
        private Long siblingId;
        private Boolean above;
        private Integer indent;
        private Integer outdent;
        private Format format;
        private Boolean expanded;
        private List<Cell> cells;
        private Boolean locked;
        private Long id;

        public Long getRowId() {
            return id;
        }

        public UpdateRowBuilder setRowId(Long rowId) {
            this.id = rowId;
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
         * Sets the to top flag that puts the row at the top of the sheet.
         *
         * @param toTop the to top flag
         * @return the update rows builder
         */
        public UpdateRowBuilder setToTop(Boolean toTop) {
            this.toTop = toTop;
            return this;
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
         * Sets the to bottom flag that puts the row at the bottom of the sheet.
         *
         * @param toBottom the to bottom
         * @return the update rows builder
         */
        public UpdateRowBuilder setToBottom(Boolean toBottom) {
            this.toBottom = toBottom;
            return this;
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
         * Sets the parent id that puts the row as the first child of the specified id.
         *
         * @param parentId the parent id
         * @return the update rows builder
         */
        public UpdateRowBuilder setParentId(Long parentId) {
            this.parentId = parentId;
            return this;
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
         * Sets the sibling id that puts the row as the next row at the same hierarchical level of this row.
         *
         * @param siblingId the sibling id
         * @return the update rows builder
         */
        public UpdateRowBuilder setSiblingId(Long siblingId) {
            this.siblingId = siblingId;
            return this;
        }

        /**
         * Gets the above flag
         * @return the above flag
         */
        public Boolean getAbove() { return above; }

        /**
         * Sets the above flag
         * @param above the above flag
         * @return the insert rows builder
         */
        public UpdateRowBuilder setAbove(Boolean above) {
            this.above = above;
            return this;
        }

        /**
         * Gets the number of levels of indent
         * @return the number of levels of indent
         */
        public Integer getIndent() { return indent; }

        /**
         * Sets the number of levels of indent
         * @param indent number of levels of indent
         * @return the update rows builder
         */
        public UpdateRowBuilder setIndent(Integer indent) {
            this.indent = indent;
            return this;
        }

        /**
         * Gets the number of levels of outdent
         * @return the number of levels of outdent
         */
        public Integer getOutdent() { return outdent; }

        /**
         * Sets the number of levels of outdent
         * @param outdent number of levels of outdent
         * @return the update rows builder
         */
        public UpdateRowBuilder setOutdent(Integer outdent) {
            this.outdent = outdent;
            return this;
        }

        /**
         * Gets the format
         * @return the format
         */
        public Format getFormat() { return format; }

        /**
         * Sets the format
         * @param format the format
         * @return the update rows builder
         */
        public UpdateRowBuilder setFormat(Format format) {
            this.format = format;
            return this;
        }

        /**
         * Gets the expanded flag
         * @return the expanded flag
         */
        public Boolean getExpanded() { return expanded; }

        /**
         * Sets the expanded flag
         * @param expanded the expanded flag
         * @return the update rows builder
         */
        public UpdateRowBuilder setExpanded(Boolean expanded) {
            this.expanded = expanded;
            return this;
        }

        /**
         * Gets the list of cells
         * @return the list of cells
         */
        public List<Cell> getCells() { return cells; }

        /**
         * Sets the list of cells
         * @param cells the list of cells
         * @return the update rows builder
         */
        public UpdateRowBuilder setCells(List<Cell> cells) {
            this.cells = cells;
            return this;
        }

        /**
         * Gets the locked flag
         * @return the locked flag
         */
        public Boolean getLocked() {return locked; }

        /**
         * Sets the locked flag
         * @param locked the locked flag
         * @return the update rows builder
         */
        public UpdateRowBuilder setLocked(Boolean locked) {
            this.locked = locked;
            return this;
        }

        /**
         * Builds the row object
         * @return the row object
         */
        public Row build() {
            Row row = new Row();
            row.setToTop(toTop);
            row.setToBottom(toBottom);
            row.setParentId(parentId);
            row.setSiblingId(siblingId);
            row.setAbove(above);
            row.setIndent(indent);
            row.setOutdent(outdent);
            row.setFormat(format);
            row.setExpanded(expanded);
            row.setCells(cells);
            row.setLocked(locked);
            row.setId(getRowId());
            return row;
        }
    }

}
