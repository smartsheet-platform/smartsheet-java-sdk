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
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;

import java.util.List;

/**
 *
 */
public class Sample {
    static {
        // Uncomment these lines to enable logging to console
        // System.setProperty("Smartsheet.trace.parts", "RequestBody,ResponseBodySummary");
        // System.setProperty("Smartsheet.trace.pretty", "true");

    }
    public static void main(String[] args) {
        try {
            // Create Smartsheet client
            // Expects access token in environment variable "SMARTSHEET_ACCESS_TOKEN" - else set here
            Smartsheet smartsheet = new SmartsheetBuilder().build();

            // List all sheets
            PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets(null, null, null );
            System.out.println("\nFound " + sheets.getTotalCount() + " sheets\n");

            Long sheetId =  sheets.getData().get(0).getId();            // Default to first sheet

            // TODO: Uncomment if you wish to read a specific sheet
            // sheetId = 239236234L;

            // Load entire sheet
            Sheet sheet = smartsheet.sheetResources().getSheet(sheetId, null, null, null, null, null, null, null);
            List<Row> rows = sheet.getRows();
            System.out.println("\nLoaded sheet id " + sheetId + " with " + rows.size() + " rows, title: " + sheet.getName());

            // Display the first 5 rows & columns
            for (int rowNumber = 0; rowNumber < rows.size() && rowNumber < 5; rowNumber++)
                DumpRow(rows.get(rowNumber), sheet.getColumns());
        } catch (SmartsheetException sx) {
            sx.printStackTrace();
        }
        System.out.println("done.");
    }

    static void DumpRow(Row row, List<Column> columns)
    {
        System.out.println("Row # " + row.getRowNumber() + ":");
        for (int columnNumber = 0; columnNumber < columns.size() && columnNumber < 5; columnNumber++) {
            System.out.println("    " + columns.get(columnNumber).getTitle() + ": " + row.getCells().get(columnNumber).getValue());
        }

    }
}
