package com.smartsheet.api;

import com.smartsheet.api.models.*;
import com.smartsheet.api.models.Error;

/**
 * This is the exception to indicate errors (Error objects of Smartsheet REST API) returned from Smartsheet REST API.
 * 
 * Several specific exceptions are defined to indicate the following HTTP responses:
 * 400 BAD REQUEST
 * 401 NOT AUTHORIZED
 * 403 FORBIDDEN
 * 404 NOT FOUND
 * 503 SERVICE UNAVAILABLE
 * 
 * Thread safety:
 * Exceptions are not thread safe.
 */
public class SmartsheetRestException extends SmartsheetException {
    /**
     * Represents the error code.
     * 
     * It will be initialized in constructor and will not change afterwards.
     */
    private final int errorCode;

    /**
     * Constructor.
     * 
     * Parameters:
     * - error : the Error object from Smartsheet REST API
     * 
     * Implementation:
     * super(error.getMessage());
     * this.errorCode = error.getErrorCode();
     * @param error 
     */
    public SmartsheetRestException(Error error) {
    	super(error.getMessage());
    	errorCode = error.getErrorCode();
    }

    /**
     * Returns the error code.
     * 
     * Returns:
     * the error code.
     * 
     * Implementation:
     * return this.errorCode;
     * @return 
     */
    public int getErrorCode() {
        return 0;
    }
}

