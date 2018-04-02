package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */



/**
 * Represents Error object.
 */
public class Error {
    /**
     * Represents the error detail.
     */
    private Object detail;

    /**
     * Represents the error code.
     */
    private Integer errorCode;

    /**
     * Represents the message.
     */
    private String message;

    /**
     * Reference ID
     */
    private String refId;

    /**
     * Gets the error detail.
     *
     * @return the error detail
     */
    public Object getDetail() { return detail; }

    /**
     * Sets the error detail.
     *
     * @param detail the error detail
     */
    public Error setDetail(Object detail) {
        this.detail = detail;
        return this;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public Error setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Get the reference ID
     *
     * @return the refId
     */
    public String getRefId() { return refId; }

    /**
     * Set the reference ID
     *
     * @param refId the reference ID
     * @return the Error Object
     */
    public Error setRefId(String refId) {
        this.refId = refId;
        return this;
    }
}
