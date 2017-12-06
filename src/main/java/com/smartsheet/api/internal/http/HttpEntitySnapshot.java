package com.smartsheet.api.internal.http;

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


import com.smartsheet.api.internal.util.StreamUtil;
import org.apache.http.entity.ContentType;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * this extension of HttpEntity is only for the purpose of creating a copy of its data stream so that the original can
 * be used as-received and this duplicate can be logged if needed
 */
public class HttpEntitySnapshot extends HttpEntity {
    private static final String JSON_MIME_TYPE = ContentType.APPLICATION_JSON.getMimeType();
    private static final int MAX_SNAPSHOT_SIZE = StreamUtil.TEN_KB;

    private byte[] contentArray;

    /**
     * this ctor creates a snapshot of the original entity (which requires its stream either support reset or it must be
     * entirely consumed and replaced with an exact copy)
     */
    public HttpEntitySnapshot(HttpEntity original) throws IOException {
        final String contentType = original.getContentType();
        final InputStream contentStream = original.getContent();
        final long contentLength = original.getContentLength();

        super.setContentLength(contentLength);
        super.setContentType(contentType);

        if (contentType != null && contentType.startsWith(JSON_MIME_TYPE)) {
            // we need to read and then reset (if possible) the original entity's content stream (or replace it with an exact copy)
            // if contentLength > Integer.MAX_VALUE we have MUCH bigger problems than long->int rollover
            boolean sourceSupportsMark = contentStream.markSupported();
            if (sourceSupportsMark) {
                // here we can read up to a limited contents
                contentArray = new byte[MAX_SNAPSHOT_SIZE];
                contentStream.mark(MAX_SNAPSHOT_SIZE + 1);
                int bytesRead = contentStream.read(contentArray, 0, MAX_SNAPSHOT_SIZE);
                contentStream.reset();

                // trim content array to actual size
                if (bytesRead < MAX_SNAPSHOT_SIZE) {
                    contentArray = Arrays.copyOf(contentArray, bytesRead);
                }
            } else {
                // here we must read everything and then repackage the byte[] into an input stream to replace the original
                byte[] fullContentArray;
                try {
                    fullContentArray = StreamUtil.readBytesFromStream(contentStream, StreamUtil.ONE_MB);
                } finally {
                    contentStream.close();
                }
                // having consumed the content into memory we must now replace the original stream (so it can be read by subsequent code)
                original.setContent(new ByteArrayInputStream(fullContentArray));
                // and we need a copy for potential logging purposes
                contentArray = Arrays.copyOf(fullContentArray, Math.min(MAX_SNAPSHOT_SIZE, fullContentArray.length));
                // we see a lot of Content-Length:-1 from certain responses - no point in logging those
                if (contentLength != -1 && fullContentArray.length != contentLength) {
                    LoggerFactory.getLogger(HttpEntitySnapshot.class).info("actual content-length {} doesn't match" +
                                    " declared content-length {}", fullContentArray.length, contentLength);
                }
            }
        } else {
            contentArray = String.format("**contentType '%s' not logged**", contentType).getBytes();
        }
    }

    @Override
    public InputStream getContent() {
        return new ByteArrayInputStream(contentArray);
    }

    @Override
    public void setContentLength(long contentLength) {
        throw new UnsupportedOperationException("this class doesn't support having its content length changed");
    }

    @Override
    public void setContentType(String contentType) {
        throw new UnsupportedOperationException("this class doesn't support having its content type changed");
    }

    @Override
    public void setContent(InputStream content) {
        throw new UnsupportedOperationException("this class doesn't support having its content stream changed");
    }

    /**
     * unfortunately there is no way to make this exposed array immutable without wrapping it or copying it, which would
     * defeat the purpose of having a method like this for performance reasons.
     */
    public byte[] getContentArray() {
        return contentArray;
    }
}
