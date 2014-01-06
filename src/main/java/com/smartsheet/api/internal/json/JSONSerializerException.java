package com.smartsheet.api.internal.json;

import java.lang.*;

import com.smartsheet.api.SmartsheetException;

/**
 * This is the exception throw by JSONSerializer to indicate errors occurred during JSON serialization/de-serialization.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class JSONSerializerException extends SmartsheetException {
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
    public JSONSerializerException(String message) {
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
    public JSONSerializerException(String message, Throwable cause) {
    	super(message,cause);
    }
}

