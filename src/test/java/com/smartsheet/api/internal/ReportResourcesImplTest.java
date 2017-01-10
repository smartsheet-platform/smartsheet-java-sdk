package com.smartsheet.api.internal;

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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.PaperSize;
import com.smartsheet.api.models.enums.ReportInclusion;
import com.smartsheet.api.models.enums.SheetEmailFormat;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class ReportResourcesImplTest extends ResourcesImplBase {

    private ReportResourcesImpl reportResources;

    @Before
    public void setUp() throws Exception {
        reportResources = new ReportResourcesImpl(new SmartsheetImpl("http://localhost:9090/2.0/",
                "accessToken", new DefaultHttpClient(), serializer));

    }

    @Test
    public void testGetReport() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getReport.json"));
        Report report = reportResources.getReport(4583173393803140L, EnumSet.of(ReportInclusion.ATTACHMENTS, ReportInclusion.DISCUSSIONS), 1,1);
        assertEquals(report.getPermalink(), "https://app.smartsheet.com/b/home?lx=pWNSDH9itjBXxBzFmyf-5w");
        assertTrue(report.getColumns().get(0).getVirtualId() == 4583173393803140L);
    }

    @Test
    public void testSendSheet() throws Exception {
        server.setResponseBody(new File("src/test/resources/sendEmails.json"));

        List<Recipient> recipients = new ArrayList<Recipient>();
        RecipientEmail recipientEmail = new RecipientEmail();
        recipientEmail.setEmail("johndoe@smartsheet.com");

        RecipientGroup recipientGroup = new RecipientGroup();
        recipientGroup.setGroupId(123456789L);

        recipients.add(recipientGroup);
        recipients.add(recipientEmail);

        SheetEmail email = new SheetEmail();
        email.setFormat(SheetEmailFormat.PDF);
        FormatDetails format = new FormatDetails();
        format.setPaperSize(PaperSize.A0);
        email.setFormatDetails(format);
        email.setSendTo(recipients);
        reportResources.sendReport(1234L, email);

    }

    @Test
    public void testListReports() throws  SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listReports.json"));
        PaginationParameters pagination = new PaginationParameters(true, null, null);
        PagedResult<Report> reportsWrapper = reportResources.listReports(pagination, null);

        assertTrue(reportsWrapper.getTotalPages() == 1);
        assertEquals("r1", reportsWrapper.getData().get(0).getName());
        assertEquals("r2", reportsWrapper.getData().get(1).getName());
        assertTrue(6761305928427396L == reportsWrapper.getData().get(0).getId());
    }

    @Test
    public void testGetReportAsExcel() throws SmartsheetException, IOException{
        File file = new File("src/test/resources/getExcel.xls");
        server.setResponseBody(file);
        server.setContentType("application/vnd.ms-excel");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        reportResources.getReportAsExcel(4583173393803140L, output);
        assertNotNull(output);

        assertTrue(output.toByteArray().length > 0);

        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        assertEquals(data.length, output.toByteArray().length);
    }

    @Test
    public void testGetReportAsCsv() throws SmartsheetException, IOException{
        File file = new File("src/test/resources/getExcel.xls");
        server.setResponseBody(file);
        server.setContentType("text/csv");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        reportResources.getReportAsExcel(4583173393803140L, output);
        assertNotNull(output);

        assertTrue(output.toByteArray().length > 0);

        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        assertEquals(data.length, output.toByteArray().length);
    }
}
