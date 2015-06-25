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
import junit.framework.TestCase;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        Report report = reportResources.getReport(4583173393803140L, EnumSet.of(ObjectInclusion.ATTACHMENTS, ObjectInclusion.DISCUSSIONS), true, 1, 1);
        assertEquals(report.getPermalink(), "https://app.smartsheet.com/b/home?lx=pWNSDH9itjBXxBzFmyf-5w");
        assertTrue(report.getColumns().get(0).getVirtualId() == 4583173393803140L);
    }

    @Test
    public void testSendSheet() throws Exception {
        server.setResponseBody(new File("src/test/resources/sendEmails.json"));

        String[] emailAddress = { "someemail@somewhere.com" };
        SheetEmail email = new SheetEmail();
        email.setFormat(SheetEmailFormat.PDF);
        FormatDetails format = new FormatDetails();
        format.setPaperSize(PaperSize.A0);
        email.setFormatDetails(format);
        email.setTo(Arrays.asList(emailAddress));
        reportResources.sendSheet(1234L, email);
    }

    @Test
    public void testListReports() throws  SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listReports.json"));

        DataWrapper<Report> reportsWrapper = reportResources.listReports(true,null,null);

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
        reportResources.getReportAsExcel(4583173393803140L, EnumSet.of(ObjectInclusion.ATTACHMENTS, ObjectInclusion.DISCUSSIONS),1,1, output);
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
        reportResources.getReportAsExcel(4583173393803140L, EnumSet.of(ObjectInclusion.ATTACHMENTS, ObjectInclusion.DISCUSSIONS),1,1, output);
        assertNotNull(output);

        assertTrue(output.toByteArray().length > 0);

        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        assertEquals(data.length, output.toByteArray().length);
    }
}