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


import org.apache.commons.codec.binary.Hex;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * a collection of Stream-oriented utility methods
 */
public class StreamUtil {
    /**
     * read all bytes from an InputStream; doesn't close input-stream
     *
     * @param source the input stream to consume
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream source) throws IOException {
        return readBytesFromStream(source, 1024 * 1024);
    }

    /**
     * read all bytes from an InputStream with the specified buffer size; doesn't close input-stream
     *
     * @param source     the input stream to consume
     * @param bufferSize the buffer size to use when reading the stream
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream source, int bufferSize) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        copyContentIntoOutputStream(source, buffer, bufferSize);
        return buffer.toByteArray();
    }

    /**
     * the real work-horse behind all the above methods
     *
     * @param source
     * @param content
     * @param bufferSize
     * @throws IOException
     */
    public static long copyContentIntoOutputStream(InputStream source, OutputStream content, int bufferSize) throws IOException {
        byte[] tempBuf = new byte[Math.max(1024, bufferSize)];  // at least a 1k buffer
        long bytesWritten = 0;
        while (true) {
            int bytesRead = source.read(tempBuf);
            if (bytesRead < 0) {
                break;
            }
            content.write(tempBuf, 0, bytesRead);
            bytesWritten += bytesRead;
        }
        return bytesWritten;
    }

    /**
     * used when you want to clone a InputStream's content and still have it appear "rewound" to the stream beginning
     */
    public static InputStream cloneContent(InputStream source, ByteArrayOutputStream target) throws IOException {
        if (source == null) {
            return null;
        }
        final boolean markSupported = source.markSupported();
        final int maxReadBack = 1024 * 1024;
        if (markSupported) {
            source.mark(maxReadBack);
        }
        long bytesCopied = copyContentIntoOutputStream(source, target, maxReadBack);
        if (markSupported && bytesCopied < maxReadBack) {
            source.reset();
            return source;  // since we could reset the source we return it
        }
        // if we can't reset the source we need to create a replacement around what we read
        return new ByteArrayInputStream(target.toByteArray());
    }

    /**
     * generate a String of UTF-8 characters (or hex-digits if byteStream isn't UTF-8 chars) from byteStream,
     * truncating to maxLen (with "..." added if the result is truncated)
     *
     * @param byteStream
     * @param maxLen
     * @return
     */
    public static String toUtf8StringOrHex(ByteArrayOutputStream byteStream, int maxLen) {
        if (maxLen == -1) {
            maxLen = Integer.MAX_VALUE;
        }

        String result;
        try {
            result = byteStream.toString("UTF-8");
        } catch (Exception notUtf8) {
            result = Hex.encodeHexString(byteStream.toByteArray());
        }

        final int resultLen = result != null ? result.length() : 0;
        final String suffix = resultLen > maxLen ? "..." : "";
        return resultLen == 0 ? "" : result.substring(0, Math.min(resultLen, maxLen)) + suffix;
    }
}
