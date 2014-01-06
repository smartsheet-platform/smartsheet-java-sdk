package com.smartsheet.api;

import com.smartsheet.api.models.*;
import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate service unavailable error (possibly due to rate limiting) returned from Smartsheet REST API.
 * 
 * Basically this exception will be thrown when the Smartsheet REST API responds with "503 SERVICE UNAVAILABLE".
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class ServiceUnavailableException extends SmartsheetRestException {
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
    public ServiceUnavailableException(Error error) {
    	super(error);
    }
}

