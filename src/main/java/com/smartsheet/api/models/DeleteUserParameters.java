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

import com.smartsheet.api.internal.util.QueryUtil;

import java.util.HashMap;

public class DeleteUserParameters {
    /**
     * Represents the ID of the user to transfer ownership to
     */
    private Long transferToId;

    /**
     * Determines whether or not to transfer sheets
     */
    private Boolean transferSheets;

    /**
     * Determines to remove the user from sharing for all sheets/workspaces in the organization
     */
    private Boolean removeFromSharing;

    /**
     * Constructor for creating the DeleteUserParameters object
     */
    public DeleteUserParameters() {}

    /**
     * Constructor for creating the DeleteUserParameters object
     * @param transferToId the transferToId
     * @param transferSheets the transferSheets flag
     * @param removeFromSharing the removeFromSharing flag
     */
    public DeleteUserParameters(Long transferToId, Boolean transferSheets, Boolean removeFromSharing) {
        this.transferToId = transferToId;
        this.transferSheets = transferSheets;
        this.removeFromSharing = removeFromSharing;
    }

    /**
     * Gets the id of the user to transfer ownership to
     * @return the user id
     */
    public Long getTransferToId() {
        return transferToId;
    }

    /**
     * Sets the id of the user to transfer ownership to
     * @param transferToId the user id
     */
    public DeleteUserParameters setTransferToId(Long transferToId) {
        this.transferToId = transferToId;
        return this;
    }

    /**
     * Gets transfer sheets flag
     * @return the transfer sheets flag
     */
    public Boolean isTransferSheets() {
        return transferSheets;
    }

    /**
     * Sets the transfer sheets flag
     * @param transferSheets the transfer sheets flag
     */
    public DeleteUserParameters setTransferSheets(boolean transferSheets) {
        this.transferSheets = transferSheets;
        return this;
    }

    /**
     * Gets whether or not the user is removed from sharing
     * @return the remove sharing flag
     */
    public Boolean isRemoveFromSharing() {
        return removeFromSharing;
    }

    /**
     * Sets the remove from sharing flag
     * @param removeFromSharing the remove from sharing flag
     */
    public DeleteUserParameters setRemoveFromSharing(boolean removeFromSharing) {
        this.removeFromSharing = removeFromSharing;
        return this;
    }

    public String toQueryString() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("transferTo", transferToId);
        parameters.put("transferSheets", transferSheets);
        parameters.put("removeFromSharing", removeFromSharing);

        return QueryUtil.generateUrl(null, parameters);
    }
}
