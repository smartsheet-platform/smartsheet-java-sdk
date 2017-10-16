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




/**
 * <p>This is the base class for all exceptions thrown from the Smartsheet SDK.</p>
 * 
 * <p>Thread safety: Exceptions are not thread safe.</p>
 */
public class SmartsheetException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor.</p>
     *
     * @param message the message
     */
    public SmartsheetException(String message) {
        super(message);
    }

    /**
     * <p>Constructor.</p>
     *
     * @param message the message
     * @param cause the cause
     */
    public SmartsheetException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <p>Instantiates a new smartsheet exception.</p>
     *
     * @param e the exception
     */
    public SmartsheetException(Exception e) {
        super(e);
    }
}
