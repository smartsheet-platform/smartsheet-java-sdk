package com.smartsheet.api.oauth;

import java.lang.*;

/**
 * This is the exception throw by OAuthFlow to indicate "invalid_scope" error occurred during obtaining authorization code.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class InvalidScopeException extends OAuthAuthorizationCodeException {
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
    public InvalidScopeException(String message) {
    	super(message);
    }
}

