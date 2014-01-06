package com.smartsheet.api.models;

import java.lang.*;

/**
 * Represents Result object.
 */
public class Result<T> {
    /**
     * Represents the result code.
     */
    private Integer resultCode;

    /**
     * Represents the message.
     */
    private String message;

    /**
     * Represents the result.
     */
    private T result;

    /**
     * Represents the version.
     */
    private Integer version;
}

