package com.smartsheet.api.oauth;

import java.lang.*;

import com.smartsheet.api.SmartsheetException;

/**
 * This is the exception throw by OAuthFlow to indicate errors occurred during obtaining OAuth tokens.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class OAuthTokenException extends SmartsheetException {
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
    public OAuthTokenException(String message) {
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
    public OAuthTokenException(String message, Throwable cause) {
    	super(message,cause);
    }
}

