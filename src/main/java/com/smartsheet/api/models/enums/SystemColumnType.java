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
 * Represents the system column types.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504619-column-types">Column Types Help</a>
 */
public enum SystemColumnType {
    /**
     * Represents the AUTO_NUMBER system column type.
     */
    AUTO_NUMBER,

    /**
     * Represents the MODIFIED_DATE system column type.
     */
    MODIFIED_DATE,

    /**
     * Represents the MODIFIED_BY system column type.
     */
    MODIFIED_BY,

    /**
     * Represents the CREATED_DATE system column type.
     */
    CREATED_DATE,

    /**
     * Represents the CREATED_BY system column type.
     */
    CREATED_BY;


}
