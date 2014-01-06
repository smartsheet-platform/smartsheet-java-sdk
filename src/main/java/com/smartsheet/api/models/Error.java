package com.smartsheet.api.models;

import java.lang.*;

/**
 * Represents Error object.
 */
public class Error {
    /**
     * Represents the error code.
     */
    private Integer errorCode;

    /**
     * Represents the message.
     */
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}

