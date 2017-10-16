package com.smartsheet.api.internal.json;

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



import com.smartsheet.api.SmartsheetException;

/**
 * This is the exception throw by JSONSerializer to indicate errors occurred during JSON serialization/de-serialization.
 * 
 * Thread safety: Exceptions are not thread safe.
 */
public class JSONSerializerException extends SmartsheetException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message the message
     */
    public JSONSerializerException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message the message
     * @param cause the cause
     */
    public JSONSerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new JSON serializer exception.
     *
     * @param e the e
     */
    public JSONSerializerException(Exception e) {
        super(e);
    }
}
