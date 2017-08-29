package com.smartsheet.api.sample;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Sheet;

/**
 *
 */
public class Sample {
    public static void main(String[] args) throws Exception {
        Smartsheet api = new SmartsheetBuilder().setAccessToken(System.getenv("SMARTSHEET_ACCESS_TOKEN")).build();
        PagedResult<Sheet> sheets = api.sheetResources().listSheets(null, null, null);
        System.out.format("hello world - num-sheets: %d\n", sheets.getTotalCount());
    }
}
