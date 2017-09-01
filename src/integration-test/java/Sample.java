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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.Sheet;

import java.util.List;

public class Sample {

    public static void main(String[] args) throws SmartsheetException
    {
        // Set trace levels before initializing smartsheet client
        System.setProperty("Smartsheet.trace.parts", "Request, ResponseBodySummary");

        // TODO: Set your access token here or in environment variable
        String token = System.getenv("SMARTSHEET_ACCESS_TOKEN");

        // Use the Smartsheet Builder to create a Smartsheet
        Smartsheet smartsheet = new SmartsheetBuilder().setAccessToken(token).build();

        PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets(null, null, null );
        System.out.println("Found " + sheets.getTotalCount() + " sheets");

        Long sheetId =  sheets.getData().get(0).getId();            // Default to first sheet

        // TODO: Uncomment if you wish to read a specific sheet
        // sheetId = 239236234L;

        Sheet sheet = smartsheet.sheetResources().getSheet(sheetId, null, null, null, null, null, null, null);
        List<Row> rows = sheet.getRows();
        System.out.println("Loaded sheet id " + sheetId + " with " + rows.size() + " rows, title: " + sheet.getName());

        // Display the first 5 rows & columns
        for (int rowNumber = 0; rowNumber < rows.size() && rowNumber < 5; rowNumber++)
            DumpRow(rows.get(rowNumber), sheet.getColumns());

    }

    static void DumpRow(Row row, List<Column> columns)
    {
        System.out.println("Row # " + row.getRowNumber() + ":");
        for (int columnNumber = 0; columnNumber < columns.size() && columnNumber < 5; columnNumber++) {
            System.out.println("    " + columns.get(columnNumber).getTitle() + ": " + row.getCells().get(columnNumber).getValue());
        }

    }


}
