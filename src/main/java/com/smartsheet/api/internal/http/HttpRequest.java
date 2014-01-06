package com.smartsheet.api.internal.http;

import java.net.URI;

import com.smartsheet.api.internal.http.*;

/**
 * This class represents an HTTP request.
 * 
 * Thread Safety:
 * This class is not thread safe since it's mutable.
 */
public class HttpRequest extends HttpMessage {
    /**
     * Represents the URI.
     * 
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private URI uri;

    /**
     * Represents the HTTP method.
     * 
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private HttpMethod method;
}

