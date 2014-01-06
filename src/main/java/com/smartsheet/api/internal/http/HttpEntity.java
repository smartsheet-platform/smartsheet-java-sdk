package com.smartsheet.api.internal.http;

import java.io.InputStream;
import java.lang.*;

/**
 * This class represents an HTTP Entity (http://www.w3.org/Protocols/rfc2616/rfc2616-sec7.html).
 * 
 * Thread Safety:
 * This class is not thread safe since it's mutable.
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
}

