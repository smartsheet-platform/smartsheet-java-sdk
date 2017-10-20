package com.smartsheet.api.logging;

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
import com.smartsheet.api.Trace;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.models.Sheet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 *
 */
public class LoggingTest {
    @BeforeClass
    public static void dontFailOnUnrecongnizedFields() {
        // Setup the serializer
        JacksonJsonSerializer.setFailOnUnknownProperties(false);    // no idea why we enable this in ResourcesImplBase.baseSetup
    }

    @AfterClass
    public static void failOnUnrecongnizedFields() {
        // Setup the serializer
        JacksonJsonSerializer.setFailOnUnknownProperties(true);    // put it back the way we found it
    }

    public void testConsoleLogging() throws Exception {
        ByteArrayOutputStream traceStream = new ByteArrayOutputStream();
        DefaultHttpClient.setTraceStream(traceStream);
        Smartsheet client = new SmartsheetBuilder().build();
        client.setTraces(Trace.Request, Trace.Response);    // should log entire request and response
        try {
            Sheet sheet = client.sheetResources().getSheet(42, null, null, null, null, null, 1, 1);
            Assert.fail("expected SmartsheetException");
        } catch (SmartsheetException expected) {
            String output = traceStream.toString();
            // not super-robust but asserts some of the important parts
            Assert.assertTrue("request not found in - " + output,
                    output.contains("\"request\" : {"));
            Assert.assertTrue("Auth header not found in - " + output,
                    output.contains("\"Authorization\" : \"Bearer ****null")); // truncated Auth header
            Assert.assertTrue("response not found in - " + output,
                    output.contains("\"response\" : {"));
            Assert.assertTrue("response-body not found in - " + output,
                    output.contains("\"body\" : \"{\\n  \\\"errorCode\\\" : 1002,\\n  \\\"message\\\" : " +
                            "\\\"Your Access Token is invalid.\\\",\\n  \\\"refId\\\" :"));
            Assert.assertTrue("status not found in - " + output,
                    output.contains("\"status\" : \"HTTP/1.1 401 Unauthorized\""));
        }
    }

    @Test
    public void testCustomLogging() throws Exception {
        ByteArrayOutputStream traceStream = new ByteArrayOutputStream();
        DefaultHttpClient.setTraceStream(traceStream);
        Smartsheet client = new SmartsheetBuilder().setAccessToken("null").build();
        client.setTraces(Trace.Request, Trace.Response);    // should log entire request and response
        try {
            Sheet sheet = client.sheetResources().getSheet(42, null, null, null, null, null, 1, 1);
            Assert.fail("expected SmartsheetException");
        } catch (SmartsheetException expected) {
            String output = traceStream.toString();
            // not super-robust but asserts some of the important parts
            Assert.assertTrue("request not found in - " + output, output.contains("request:{"));
            Assert.assertTrue("Auth header not found in - " + output, output.contains("'Authorization':'Bearer ****null")); // truncated Auth header
            Assert.assertTrue("response not found in - " + output, output.contains("response:{"));
            Assert.assertTrue("response-body not found in - " + output,
                    output.contains("body:'{\n  \"errorCode\" : 1002,\n  \"message\" : \"Your Access Token is invalid.\",\n  \"refId\" :"));
            Assert.assertTrue("status not found in - " + output, output.contains("status:'HTTP/1.1 401 Unauthorized'"));
        }
    }
}
