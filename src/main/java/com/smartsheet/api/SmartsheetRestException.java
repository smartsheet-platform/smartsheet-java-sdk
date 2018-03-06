package com.smartsheet.api;

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



import com.smartsheet.api.models.Error;

/**
 * <p>This is the exception to indicate errors (Error objects of Smartsheet REST API) returned from Smartsheet REST API.</p>
 * 
 * <p>Thread safety: Exceptions are not thread safe.</p>
 */
public class SmartsheetRestException extends SmartsheetException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /**
     * <p>Represents the error code.</p>
     *
     * <p>It will be initialized in constructor and will not change afterwards.</p>
     */
    private final int errorCode;

    /**
     *     <p>Represents the reference ID.</p>
     *
     *     <p>It will be initialized in the constructor and will not change afterwards.</p>
     */
    private final String refId;

    /**
     *      <p>Represents any error detail provided by the API</p>
     *
     *     <p>It will be initialized in the constructor and will not change afterwards.</p>
     */
    private final Object detail;

    /**
     * <p>Constructor.</p>
     *
     * @param error the Error object from Smartsheet REST API
     */
    public SmartsheetRestException(Error error) {
        super(error.getMessage());
        errorCode = error.getErrorCode();
        refId = error.getRefId();
        detail = error.getDetail();
    }



    /**
     * <p>Returns the error code.</p>
     *
     * @return the error code
     */
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * <p>Retruns the refId.</p>
     *
     * @return the refId
     */
    public String getRefId() { return this.refId; }

    /**
     * <p>Returns the error detail</p>
     *
     * @return the error detail
     */
    public Object getDetail() { return this.detail; }
}
