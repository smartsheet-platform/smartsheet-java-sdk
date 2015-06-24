package com.smartsheet.api.internal;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.ObjectInclusion;
import com.smartsheet.api.models.Report;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

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

    }

    @Test
    public void testSendSheet() throws Exception {

    }
}