package com.smartsheet.api.internal.util;

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


import org.apache.commons.io.input.CharSequenceInputStream;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 *
 */
public class StreamUtilTest {
    @Test
    public void testReadBytesFromStream() throws Exception {
        final String testString = "fuzzy wuzzy was a bear; fuzzy wuzzy had no hair...";
        final byte[] testBytes = testString.getBytes("UTF-8");
        final InputStream inputStream = new CharSequenceInputStream(testString, "UTF-8");
        final ByteArrayOutputStream copyStream = new ByteArrayOutputStream();

        // this takes what was in inputStream, copies it into copyStream, and either resets inputStream (if supported)
        // or returns a new stream around the bytes read
        final InputStream backupStream = StreamUtil.cloneContent(inputStream, StreamUtil.ONE_MB, copyStream);
        if (backupStream == inputStream) {
            System.out.println("same stream returned (reset)");
            // verify readBytesFromStream gets everything from the inputStream (it also verifies cloneContent resets the source)
            byte[] streamBytes = StreamUtil.readBytesFromStream(inputStream);
            Assert.assertArrayEquals(testBytes, streamBytes); // it's all US-ASCII so it should match UTF-8 bytes
        } else {
            System.out.println("new stream returned");
            byte[] backupBytes = StreamUtil.readBytesFromStream(backupStream);
            Assert.assertArrayEquals(testBytes, backupBytes);
        }

        Assert.assertArrayEquals(testBytes, copyStream.toByteArray());
    }
}
