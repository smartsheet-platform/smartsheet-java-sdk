package com.smartsheet.api.oauth;

import java.lang.*;

/**
 * This is the exception throw by OAuthFlow to indicate "invalid_request" error occurred during obtaining OAuth tokens.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class InvalidTokenRequestException extends OAuthTokenException {
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
    public InvalidTokenRequestException(String message) {
    	super(message);
    }
}

