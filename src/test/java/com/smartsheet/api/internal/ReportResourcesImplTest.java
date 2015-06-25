package com.smartsheet.api.internal;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anioding on 6/24/15.
 */
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
}