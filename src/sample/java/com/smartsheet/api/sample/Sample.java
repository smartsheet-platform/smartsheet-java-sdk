package com.smartsheet.api.sample;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Sheet;

/**
 *
 */
public class Sample {
    static {
        System.setProperty("Smartsheet.trace.parts", "Request,ResponseBodySummary");
        System.setProperty("Smartsheet.trace.pretty", "true");

    }
    public static void main(String[] args) {
        try {
            Smartsheet api = new SmartsheetBuilder().setAccessToken(System.getenv("SMARTSHEET_ACCESS_TOKEN")).build();
            PagedResult<Sheet> sheets = api.sheetResources().listSheets(null, null, null);
            System.out.format("hello world - num-sheets: %d\n", sheets.getTotalCount());
        } catch (SmartsheetException sx) {
            sx.printStackTrace();
        }
        System.out.println("done.");
    }
}
