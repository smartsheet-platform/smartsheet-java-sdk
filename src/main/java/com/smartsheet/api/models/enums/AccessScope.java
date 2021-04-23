package com.smartsheet.api.models.enums;

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
 * Represents the type of access that is granted on a given sheet.
 * @see <a href="http://www.smartsheet.com/developers/api-documentation#h.89hb3ivv7eum">Access Scopes Help</a>
 */
public enum AccessScope {
    ADMIN_SHEETS,
    ADMIN_SIGHTS,
    ADMIN_USERS,
    ADMIN_WEBHOOKS,
    ADMIN_WORKSPACES,
    CREATE_SHEETS,
    CREATE_SIGHTS,
    DELETE_SHEETS,
    DELETE_SIGHTS,
    READ_CONTACTS,
    READ_SHEETS,
    READ_SIGHTS,
    READ_USERS,
    SHARE_SHEETS,
    SHARE_SIGHTS,
    WRITE_SHEETS,
}
