package com.smartsheet.api;

import java.lang.*;

/**
 * This is the base class for all exceptions thrown from the Smartsheet SDK.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class SmartsheetException extends Exception {
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
    public SmartsheetException(String message) {
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
    public SmartsheetException(String message, Throwable cause) {
    }
}

