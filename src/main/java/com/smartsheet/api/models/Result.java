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
 * Result object to contain information about a PUT or POST request.
 *
 * @param <T> the generic type
 */
public class Result<T> {
    /** Represents the result code from the request. */
    private Integer resultCode;

    /** Represents the message from the request. */
    private String message;

    /** Represents the object that was created or updated. */
    private T result;

    /** Represents the new version of the sheet. It is only available on some operations. */
    private Integer version;

    /**
     * Gets the result code from the request.
     *
     * @return the result code
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     * Sets the result code.
     *
     * @param resultCode the new result code
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Gets the message from the request.
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
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the result from the request.
     *
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Gets the new version of the sheet. It is only available on some operations..
     *
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }


}
