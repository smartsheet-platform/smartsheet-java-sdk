package com.smartsheet.api.oauth;

import java.lang.*;

/**
 * This is the exception throw by OAuthFlow to indicate "unsupported_response_type" error occurred during obtaining authorization code.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class UnsupportedResponseTypeException extends OAuthAuthorizationCodeException {
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
    public UnsupportedResponseTypeException(String message) {
    	super(message);
    }
}

