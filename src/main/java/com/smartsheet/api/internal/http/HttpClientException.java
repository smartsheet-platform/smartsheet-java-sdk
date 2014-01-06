package com.smartsheet.api.internal.http;

import java.lang.*;

import com.smartsheet.api.SmartsheetException;

/**
 * This is the exception throw by HttpClient to indicate errors occurred during HTTP operation.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class HttpClientException extends SmartsheetException {
    /**
     * Constructor.
     * 
     * Parameters:
     * - message : the message
     * 
     * Implementation:
     * super(message);
     * @param message 
     */
    public HttpClientException(String message) {
    	super(message);
    }

    /**
     * Constructor.
     * 
     * Parameters:
     * - message : the message
     * - cause : the cause
     * 
     * Implementation:
     * super(message, cause);
     * @param message 
     * @param cause 
     */
    public HttpClientException(String message, Throwable cause) {
    	super(message,cause);
    }
}

