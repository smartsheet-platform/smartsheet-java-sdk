package com.smartsheet.api.models;

import java.util.List;

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
public class CopyOrMoveRowDirective {

    /** Represents the IDs of the rows to move or copy from the source sheet. */
    private List<Long> RowIds;

    /** Represents the CopyOrMoveRowDestination object that identifies the destination sheet */
    private CopyOrMoveRowDestination to;


    /**
     * Gets the IDs of the rows to move or copy from the source sheet..
     *
     * @return the IDs of the rows to move or copy from the source sheet.
     */
    public List<Long> getRowIds() {
        return RowIds;
    }

    /**
     * Sets the IDs of the rows to move or copy from the source sheet.
     *
     * @param rowIds ID of the destination sheet
     */
    public CopyOrMoveRowDirective setRowIds(List<Long> rowIds) {
        RowIds = rowIds;
        return this;
    }

    /**
     * Gets the CopyOrMoveRowDestination object that identifies the destination sheet.
     *
     * @return the CopyOrMoveRowDestination object that identifies the destination sheet.
     */
    public CopyOrMoveRowDestination getTo() {
        return to;
    }

    /**
     * Sets the CopyOrMoveRowDestination object that identifies the destination sheet.
     *
     * @param to CopyOrMoveRowDestination object
     */
    public CopyOrMoveRowDirective setTo(CopyOrMoveRowDestination to) {
        this.to = to;
        return this;
    }

    /**
     * A convenience class to help create a CopyOrMoveRowDestination object with the appropriate fields for adding to a sheet.
     */
    public static class InsertCopyOrMoveRowDirectiveBuilder {
        /** Represents the IDs of the rows to move or copy from the source sheet. */
        private List<Long> rowIds;

        /** Represents the CopyOrMoveRowDestination object that identifies the destination sheet */
        private CopyOrMoveRowDestination to;

        /**
         * Gets the IDs of the rows to move or copy from the source sheet..
         *
         * @return the IDs of the rows to move or copy from the source sheet.
         */
        public List<Long> getRowIds() {
            return rowIds;
        }

        /**
         * Sets the IDs of the rows to move or copy from the source sheet.
         *
         * @param rowIds ID of the destination sheet
         * @return the builder
         */
        public InsertCopyOrMoveRowDirectiveBuilder setRowIds(List<Long> rowIds) {
            this.rowIds = rowIds;
            return this;
        }

        /**
         * Gets the CopyOrMoveRowDestination object that identifies the destination sheet.
         *
         * @return the CopyOrMoveRowDestination object that identifies the destination sheet.
         */
        public CopyOrMoveRowDestination getTo() {
            return to;
        }

        /**
         * Sets the CopyOrMoveRowDestination object that identifies the destination sheet.
         *
         * @param to CopyOrMoveRowDestination object
         * @return the builder
         */
        public InsertCopyOrMoveRowDirectiveBuilder setTo(CopyOrMoveRowDestination to) {
            this.to = to;
            return this;
        }

        /**
         * Builds the CopyOrMoveRowDirective.
         *
         * @return the CopyOrMoveRowDirective
         */
        public CopyOrMoveRowDirective build() {
            CopyOrMoveRowDirective copyOrMoveRowDirective = new CopyOrMoveRowDirective();
            copyOrMoveRowDirective.RowIds = rowIds;
            copyOrMoveRowDirective.to = to;
            return copyOrMoveRowDirective;
        }
    }
}
