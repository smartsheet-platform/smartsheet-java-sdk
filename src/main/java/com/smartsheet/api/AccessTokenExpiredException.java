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
 * <p>This is the exception to indicate that an access token expired error returned from Smartsheet REST API. This 
 * exception will be thrown when the Smartsheet REST API generates a "1003 Your Access Token has expired" error.</p>
 * 
 * <p>Thread safety: Exceptions are not thread safe.</p>
 */
public class AccessTokenExpiredException extends AuthorizationException {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new access token expired exception.
     *
     * @param error the error
     */
    public AccessTokenExpiredException(Error error) {
        super(error);
    }
}
