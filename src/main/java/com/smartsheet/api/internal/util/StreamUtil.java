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
    public static final int ONE_MB = 1 << 20;
    public static final int ONE_KB = 1 << 10;
    public static final int TEN_KB = 10 * ONE_KB;

    /**
     * read all bytes from an InputStream; doesn't close input-stream
     * @param source the input stream to consume
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream source) throws IOException {
        return readBytesFromStream(source, ONE_MB);
    }

    /**
     * read all bytes from an InputStream with the specified buffer size; doesn't close input-stream
     * @param source     the input stream to consume
     * @param bufferSize the buffer size to use when reading the stream
     * @return the bytes read from 'is'
     * @throws IOException if anything goes wrong reading from 'is'
     */
    public static byte[] readBytesFromStream(InputStream source, int bufferSize) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        copyContentIntoOutputStream(source, buffer, bufferSize, true);
        return buffer.toByteArray();
    }

    /**
     * the real work-horse behind most of these methods
     * @param source     the source InputStream from which to read the data (not closed when done)
     * @param target     the target OutputStream to which to write the data (not closed when done)
     * @param bufferSize the size of the transfer buffer to use
     * @param readToEOF  if we should read to end-of-file of the source (true) or just 1 buffer's worth (false)
     * @throws IOException
     */
    public static long copyContentIntoOutputStream(InputStream source, OutputStream target, int bufferSize,
                                                   boolean readToEOF) throws IOException {
        byte[] tempBuf = new byte[Math.max(ONE_KB, bufferSize)];  // at least a 1k buffer
        long bytesWritten = 0;
        while (true) {
            int bytesRead = source.read(tempBuf);
            if (bytesRead < 0) {
                break;
            }
            target.write(tempBuf, 0, bytesRead);
            bytesWritten += bytesRead;
            if (!readToEOF) {
                // prevents us from reading more than 1 buffer worth
                break;
            }

        }
        return bytesWritten;
    }

    /**
     * used when you want to clone a InputStream's content and still have it appear "rewound" to the stream beginning
     * @param source       the stream around the contents we want to clone
     * @param readbackSize the farthest we should read a resetable stream before giving up
     * @param target       an output stream into which we place a copy of the content read from source
     * @return the source if it was resetable; a new stream rewound around the source data otherwise
     * @throws IOException if any issues occur with the reading of bytes from the source stream
     */
    public static InputStream cloneContent(InputStream source, int readbackSize, ByteArrayOutputStream target) throws IOException {
        if (source == null) {
            return null;
        }
        // if the source supports mark/reset then we read and then reset up to the read-back size
        if (source.markSupported()) {
            readbackSize = Math.max(TEN_KB, readbackSize);  // at least 10 KB (minimal waste, handles those -1 ContentLength cases)
            source.mark(readbackSize);
            copyContentIntoOutputStream(source, target, readbackSize, false);
            source.reset();
            return source;
        } else {
            copyContentIntoOutputStream(source, target, ONE_MB, true);
            byte[] fullContentBytes = target.toByteArray();
            // if we can't reset the source we need to create a replacement stream so others can read the content
            return new ByteArrayInputStream(fullContentBytes);
        }
    }

    /** a convenience method to reduce all the casting of HttpEntity.getContentLength() to int */
    public static InputStream cloneContent(InputStream source, long readbackSize, ByteArrayOutputStream target) throws IOException {
        return cloneContent(source, (int)Math.min((long)Integer.MAX_VALUE, readbackSize), target);
    }

    /**
     * generate a String of UTF-8 characters (or hex-digits if byteStream isn't UTF-8 chars) from byteStream,
     * truncating to maxLen (with "..." added if the result is truncated)
     * @param byteStream the source of bytes to be converted to a UTF-8 String
     * @param maxLen     the point at which to truncate the string (-1 means don't truncate) in which case "..." is appended
     * @return the String read from the stream
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
