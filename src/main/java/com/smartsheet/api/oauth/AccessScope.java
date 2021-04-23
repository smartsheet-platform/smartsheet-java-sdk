package com.smartsheet.api.oauth;

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



/**
 * Represents the access scope. These are the scopes that are required to access an end user's smartsheet data and 
 * specifies the type of operations that are permitted.
 */
public enum AccessScope {

    /** Modify sheet structure, including column definition, publish state, etc */
    ADMIN_SHEETS("ADMIN_SHEETS"),
    /** Modify dashboards structure. **/
    ADMIN_SIGHTS("ADMIN_SIGHTS"),
    /** Add and remove users from your smartsheet organization */
    ADMIN_USERS("ADMIN_USERS"),
    /** Create and manage webhooks */
    ADMIN_WEBHOOKS("ADMIN_WEBHOOKS"),
    /** Create and manage workspaces and folders, including sharing */
    ADMIN_WORKSPACES("ADMIN_WORKSPACES"),
    /** Create new sheets */
    CREATE_SHEETS("CREATE_SHEETS"),
    /** Create new Sights */
    CREATE_SIGHTS("CREATE_SIGHTS"),
    /** Delete Sheets */
    DELETE_SHEETS("DELETE_SHEETS"),
    /** Delete Sights */
    DELETE_SIGHTS("DELETE_SIGHTS"),
    /** Retrieve contacts. **/
    READ_CONTACTS("READ_CONTACTS"),
    /** Read all sheet data, including comments, attachments and cell data */
    READ_SHEETS("READ_SHEETS"),
    /** Read all Sight data */
    READ_SIGHTS("READ_SIGHTS"),
    /** Retrieve users and groups for your Smartsheet organization */
    READ_USERS("READ_USERS"),
    /** Share sheets, including sending sheets as attachments */
    SHARE_SHEETS("SHARE_SHEETS"),
    /** Share Sights */
    SHARE_SIGHTS("SHARE_SIGHTS"),
    /** Insert and modify sheet data, including comments, attachments and cell data */
    WRITE_SHEETS("WRITE_SHEETS");

    String scope;
    AccessScope(String scope) {
        this.scope = scope;
    }
}
