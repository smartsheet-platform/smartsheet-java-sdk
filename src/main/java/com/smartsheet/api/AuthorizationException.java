package com.smartsheet.api;

import com.smartsheet.api.models.*;
import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate authorization(access token) related errors returned from Smartsheet REST API.
 * 
 * Basically this exception will be thrown when the Smartsheet REST API responds with "401 NOT AUTHORIZED" / "403 FORBIDDEN"
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class AuthorizationException extends SmartsheetRestException {
    /**
     * Constructor.
     * 
     * Parameters:
     * - error : the Error object from Smartsheet REST API
     * 
     * Implementation:
     * super(error);
     * @param error 
     */
    public AuthorizationException(Error error) {
    	super(error);
    }
}

