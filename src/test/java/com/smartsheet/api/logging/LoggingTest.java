package com.smartsheet.api.logging;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetBuilder;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.Trace;
import com.smartsheet.api.models.Sheet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 */
public class LoggingTest {
    private static PrintStream originalSystemOut;
    private static ByteArrayOutputStream ourOutputStream = new ByteArrayOutputStream();
    private static PrintStream ourSystemOut = new PrintStream(ourOutputStream, true);

    @BeforeClass
    public static void rerouteSystemOut() {
        originalSystemOut = System.out;
        System.setOut(ourSystemOut);
    }

    @AfterClass
    public static void replaceSystemOut() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testConsoleLogging() throws Exception {
        Smartsheet client = new SmartsheetBuilder().build();
        client.setTraces(Trace.Request, Trace.Response);    // should log entire request and response to console
        try {
            Sheet sheet = client.sheetResources().getSheet(42, null, null, null, null, null, 1, 1);
            Assert.fail("expected SmartsheetException");
        } catch (SmartsheetException expected) {
            String output = ourOutputStream.toString();
            ourOutputStream.reset();
            // not super-robust but asserts the important parts
            Assert.assertTrue(output.contains("\"request\" : {"));
            Assert.assertTrue(output.contains("\"Authorization\" : \"Bearer null\","));
            Assert.assertTrue(output.contains("\"response\" : {"));
            Assert.assertTrue(output.contains("\"body\" : \"{\\n  \\\"errorCode\\\" : 1002,\\n  \\\"message\\\" : \\\"Your Access Token is invalid.\\\",\\n  \\\"refId\\\" :"));
            Assert.assertTrue(output.contains("\"status\" : \"HTTP/1.1 401 Unauthorized\""));
            originalSystemOut.println(output);
        }
    }
}
