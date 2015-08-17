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

public class ContainerDestination extends NamedModel<Long>{

    /** Represents the destination type when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
    private DestinationType destinationType;

    /** Represents the destination id when a Sheet or Folder is moved, or when a Sheet, Folder, or Workspace is copied.. */
    long destinationId;

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
     * @param destinationType
     */
    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }

    /**
     * Gets the destination id when copying or moving a Sheet or a Folder.
     *
     * @return the destination id
     */
    public long getDestinationId() {
        return destinationId;
    }

    /**
     * Sets the id for the folder
     * @param destinationId
     */
    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
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
     * Sets the name for the folder
     * @param newName
     */
    public void setNewName(String newName) {
        this.newName = newName;
    }
}
