package com.smartsheet.api;


import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Sheet;
import com.smartsheet.api.models.enums.ColumnInclusion;
import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;


@Ignore
public class CloseRequestTest {

    @Test
    public void columnIncludeFilters() throws SmartsheetException {
        String accessToken = "1szje047plr9r08tluy09twmir";
        // Get API access token from properties file or environment
        Smartsheet ss = new SmartsheetBuilder().setAccessToken(accessToken).build();
        Column column = ss.sheetResources()
            .columnResources()
            .getColumn(4305281023797124L, 6425819775035268L, EnumSet.of(ColumnInclusion.FILTERS));
        System.out.println(column);

    }

    @Test
    public void testConnectionRelease() {
        String accessToken = "1szje047plr9r08tluy09twmir";
        // Get API access token from properties file or environment
        Smartsheet ss = new SmartsheetBuilder().setAccessToken(accessToken).build();

        for(int i = 0; i < 20; i++) {
            try {
                getSheetInformation(ss);
            } catch(SmartsheetException e) {
                System.out.println(e.getMessage());
            }
            wait(10);
        }
    }

    private static void getSheetInformation(Smartsheet ss) throws SmartsheetException {

        Long sheetId = 3715518663288708L;
//        Long sheetId = 3715518663288709L;

        // Load the entire sheet
        Sheet sheet = ss.sheetResources().getSheet(sheetId, null, null, null, null, null, null, null);
        System.out.println("Loaded " + sheet.getRows().size() + " rows from sheet: " + sheet.getName());

    }

    private static void wait(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {

        }
    }
}
