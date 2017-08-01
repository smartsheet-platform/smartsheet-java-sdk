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
public class CopyOrMoveRowDestination {

    /** Represents the ID of the destination object. */
    private Long sheetId;

    /**
     * Gets the ID of the destination object.
     *
     * @return ID of the destination object
     */
    public Long getSheetId() {
        return sheetId;
    }

    /**
     * Sets the ID of the destination object.
     *
     * @param sheetId ID of the destination object
     */
    public CopyOrMoveRowDestination setSheetId(Long sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    /**
     * A convenience class for quickly creating a List of cells to update.
     */
    public static class InsertCopyOrMoveRowDestinationBuilder {

        /** Represents the ID of the destination object. */
        private Long sheetId;

        /**
         * Gets the ID of the destination object.
         *
         * @return ID of the destination object
         */
        public Long getSheetId() {
            return sheetId;
        }

        /**
         * Sets the ID of the destination object.
         *
         * @param sheetId ID of the destination object
         * @return the builder
         */
        public InsertCopyOrMoveRowDestinationBuilder setSheetId(Long sheetId) {
            this.sheetId = sheetId;
            return  this;
        }

        /**
         * Returns the CopyOrMoveRowDestination.
         *
         * @return the CopyOrMoveRowDestination
         */
        public CopyOrMoveRowDestination build() {
            CopyOrMoveRowDestination copyOrMoveRowDestination = new CopyOrMoveRowDestination();
            copyOrMoveRowDestination.sheetId = sheetId;
            return copyOrMoveRowDestination;
        }

    }
}
