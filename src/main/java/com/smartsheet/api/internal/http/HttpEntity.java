package com.smartsheet.api.internal.http;

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


import java.io.InputStream;

/**
 * This class represents an HTTP Entity (http://www.w3.org/Protocols/rfc2616/rfc2616-sec7.html).
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public class HttpEntity {
    /**
     * Represents the content type.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private String contentType;

    /**
     * Represents the content length.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private long contentLength;

    /**
     * Represents the content as an InputStream.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private InputStream content;

    /**
     * default ctor (needed because we're adding a copy-ctor)
     */
    public HttpEntity() {}

    /**
     * Gets the content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType the new content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets the content length.
     *
     * @return the content length
     */
    public long getContentLength() {
        return contentLength;
    }

    /**
     * Sets the content length.
     *
     * @param contentLength the new content length
     */
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public InputStream getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(InputStream content) {
        this.content = content;
    }
}
