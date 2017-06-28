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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class StreamUtil {
    /**
     * read all bytes from an InputStream; doesn't close input-stream
     * @param is    the input stream to consume
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream is) throws IOException {
        return readBytesFromStream(is, 1024 * 1024);
    }

    /**
     * read all bytes from an InputStream with the specified buffer size; doesn't close input-stream
     * @param is            the input stream to consume
     * @param bufferSize    the buffer size to use when reading the stream
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream is, int bufferSize) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] tempBuf = new byte[bufferSize];
        while (true) {
            int bytesRead = is.read(tempBuf);
            if (bytesRead < 0) {
                break;
            }
            buffer.write(tempBuf, 0, bytesRead);
        }
        return buffer.toByteArray();
    }
}
