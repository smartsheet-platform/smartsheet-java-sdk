package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2018 Smartsheet
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


import com.smartsheet.api.internal.SmartsheetImpl;

public class SmartsheetFactory {
    /**
     * <p>Represents the default base URI of the Smartsheet REST API.</p>
     *
     * <p>It is a constant with value "https://api.smartsheet.com/2.0".</p>
     */
    public static final String DEFAULT_BASE_URI = "https://api.smartsheet.com/2.0/";

    /**
     * <p>Creates a Smartsheet client with default parameters. SMARTSHEET_ACCESS_TOKEN
     * must be set in the environment.</p>
     *
     * @return the Smartsheet client
     */
    public static Smartsheet createDefaultClient() {
        String accessToken = System.getenv("SMARTSHEET_ACCESS_TOKEN");
        SmartsheetImpl smartsheet = new SmartsheetImpl(DEFAULT_BASE_URI, accessToken);
        return smartsheet;
    }

    /**
     * <p>Creates a Smartsheet client with default parameters.</p>
     *
     * @param accessToken
     *
     * @return the Smartsheet client
     */
    public static Smartsheet createDefaultClient(String accessToken) {
        SmartsheetImpl smartsheet = new SmartsheetImpl(DEFAULT_BASE_URI, accessToken);
        return smartsheet;
    }

    /**
     * <p>Returns a builder to allow the caller to create a custom Smartsheet client.</p>
     *
     * @return the SmartsheetBuilder
     */
    public static SmartsheetBuilder custom() {
        return new SmartsheetBuilder();
    }
}
