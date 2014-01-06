package com.smartsheet.api;

import com.smartsheet.api.models.*;
import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate access token expired error returned from Smartsheet REST API.
 * 
 * Basically this exception will be thrown when the Smartsheet REST API with "1003 Your Access Token has expired" error.
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class AccessTokenExpiredException extends AuthorizationException {
    /**
     * @param error 
     */
    public AccessTokenExpiredException(Error error) {
    	super(error);
    	
    }
}

