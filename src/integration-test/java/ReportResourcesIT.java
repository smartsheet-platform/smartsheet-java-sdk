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
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.PaperSize;
import com.smartsheet.api.models.enums.ReportInclusion;
import com.smartsheet.api.models.enums.SheetEmailFormat;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ReportResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    PagedResult<Report> reportsWrapper;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testReportMethods() throws SmartsheetException, IOException {
        testListReports();
        testGetReport();
        testGetReportAsExcel();
        testGetReportAsCsv();
        testSendSheet();
    }

    public void testGetReport() throws SmartsheetException, IOException {
        if (reportsWrapper.getData().size() > 0) {
            Report report = smartsheet.reportResources().getReport(reportsWrapper.getData().get(0).getId(), EnumSet.of(ReportInclusion.ATTACHMENTS, ReportInclusion.DISCUSSIONS), 1, 1);
            smartsheet.reportResources().getReport(reportsWrapper.getData().get(0).getId(), null, null, null);
            assertNotNull(report);
        }
    }

    public void testListReports() throws  SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        reportsWrapper = smartsheet.reportResources().listReports(null);
        reportsWrapper = smartsheet.reportResources().listReports(parameters);
    }

    public void testGetReportAsExcel() throws SmartsheetException, IOException{
        if (reportsWrapper.getData().size() > 0) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        smartsheet.reportResources().getReportAsExcel(reportsWrapper.getData().get(0).getId(), output);
       assertNotNull(output);}
    }

    public void testGetReportAsCsv() throws SmartsheetException, IOException{
        if (reportsWrapper.getData().size() > 0) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        smartsheet.reportResources().getReportAsCsv(reportsWrapper.getData().get(0).getId(), output);
        assertNotNull(output);}
    }

    public void testSendSheet() throws SmartsheetException, IOException {
        List<Recipient> recipients = new ArrayList<Recipient>();
        RecipientEmail recipientEmail = new RecipientEmail.AddRecipientEmailBuilder().setEmail("aditi.nioding@smartsheet.com").build();
        //RecipientGroup recipientGroup = new RecipientGroup.AddRecipientGroupBuilder().setGroupId(1234L).build();

        //List<Recipient> recipients = Arrays.asList(recipientEmail, recipientGroup);
        recipients.add(recipientEmail);
        //recipients.add(recipientGroup);

        FormatDetails formatDetails = new FormatDetails();
        formatDetails.setPaperSize(PaperSize.A0);

        SheetEmail email = new SheetEmail.AddSheetEmailBuilder().setFormat(SheetEmailFormat.PDF).setFormatDetails(formatDetails).setSubject("Check this report out!").setMessage("something").setCcMe(false).setSendTo(recipients).setFormatDetails(formatDetails).build();

        if (reportsWrapper.getData().size() > 0) {
            smartsheet.reportResources().sendReport(reportsWrapper.getData().get(0).getId(), email);
            //smartsheet.reportResources().sendReport(8623082916079492L, email);
        }
    }

}
