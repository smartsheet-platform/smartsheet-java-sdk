package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

public class ContainerDestination extends NamedModel<Long> {

    /** Represents the destination type when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
    private DestinationType destinationType;

    /** Represents the destination id when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
    Long destinationId;

    /** Represents the new name when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
    String newName;

    /**
     * Gets the destination type when copying or moving a Sheet or a Folder.
     *
     * @return the destination type
     */
    public DestinationType getDestinationType() {
        return destinationType;
    }

    /**
     * Sets the type for the folder
     * @param destinationType the destination type
     */
    public ContainerDestination setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
        return this;
    }

    /**
     * Gets the destination id when copying or moving a Sheet or a Folder.
     *
     * @return the destination id
     */
    public Long getDestinationId() {
        return destinationId;
    }

    /**
     * Sets the id for the folder
     * @param destinationId the destination id
     */
    public ContainerDestination setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
        return this;
    }

    /**
     * Gets the destination name when copying or moving a Sheet or a Folder.
     *
     * @return the destination id
     */
    public String getNewName() {
        return newName;
    }

    /**
     * Sets the name for the destination
     * @param newName the new name
     */
    public ContainerDestination setNewName(String newName) {
        this.newName = newName;
        return this;
    }

    /**
     * A convenience class to generate a comment with the appropriate fields for adding it to a sheet.
     */
    public static class AddContainerDestinationBuilder {

        /** Represents the destination type when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
        private DestinationType destinationType;

        /** Represents the destination id when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
        Long destinationId;

        /** Represents the new name when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
        String newName;

        /**
         * Gets the destination type when copying or moving a Sheet or a Folder.
         *
         * @return the destination type
         */
        public DestinationType getDestinationType() {
            return destinationType;
        }

        /**
         * Sets the type for the folder
         * @param destinationType the destination type
         * @return the builder
         */
        public AddContainerDestinationBuilder setDestinationType(DestinationType destinationType) {
            this.destinationType = destinationType;
            return this;
        }

        /**
         * Gets the destination id when copying or moving a Sheet or a Folder.
         *
         * @return the destination id
         */
        public Long getDestinationId() {
            return destinationId;
        }

        /**
         * Sets the id for the folder
         * @param destinationId the destination id
         * @return the builder
         */
        public AddContainerDestinationBuilder setDestinationId(Long destinationId) {
            this.destinationId = destinationId;
            return this;
        }

        /**
         * Gets the destination name when copying or moving a Sheet or a Folder.
         *
         * @return the new name
         */
        public String getNewName() {
            return newName;
        }

        /**
         * Sets the name for the folder
         * @param newName the new name for destination
         * @return the builder
         */
        public AddContainerDestinationBuilder setNewName(String newName) {
            this.newName = newName;
            return this;
        }

        /**
         * Builds the comment.
         *
         * @return the comment
         */
        public ContainerDestination build() {

            ContainerDestination destination = new ContainerDestination();
            destination.destinationId = destinationId;
            destination.destinationType = destinationType;
            destination.newName = newName;

            return destination;
        }
    }
}
