package com.smartsheet.api.models.format;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2019 Smartsheet
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

public enum DateFormat {
    LOCALE_BASED,
    MMMM_D_YYYY,
    MMM_D_YYYY,
    D_MMM_YYYY,
    YYYY_MM_DD_HYPHEN,
    YYYY_MM_DD_DOT,
    DWWWW_MMMM_D_YYYY,
    DWWW_DD_MMM_YYYY,
    DWWW_MM_DD_YYYY,
    MMMM_D,
    D_MMMM
    ;

    /**
     * The default setting when the {@link Format} for {@link DateFormat} is null;
     */
    public static final DateFormat DEFAULT = LOCALE_BASED;
}
