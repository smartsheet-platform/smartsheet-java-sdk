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
 * Represents column types.
 */
public enum ColumnType {
    /** Represents the TEXT_NUMBER column type. */
    TEXT_NUMBER,

    /** Represents the CONTACT_LIST column type. */
    CONTACT_LIST,

    /** Represents the DATE column type. */
    DATE,

    /** Represents the DATETIME column type. */
    DATETIME,

    /** Represents the PICKLIST column type. */
    PICKLIST,

    /** Represents the CHECKBOX column type. */
    CHECKBOX,

    /** Represents the DURATION column type. */
    DURATION,

    /** Represents the PREDECESSOR column type. */
    PREDECESSOR,

    /** Represents the ABSTRACT_DATETIME (auto number) column type */
    ABSTRACT_DATETIME,
}
