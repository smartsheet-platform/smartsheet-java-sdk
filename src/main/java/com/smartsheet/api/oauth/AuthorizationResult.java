package com.smartsheet.api.oauth;

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
 * Represents an OAuth authorization result.
 */
public class AuthorizationResult {
    /** Represents the authorization code which is required to obtain an access token. */
    private String code;

    /** Represents the total number of seconds that the authorization token is valid. This is always 4 minutes. */
    private long expiresInSeconds;

    /** Represents the state string which is returned to the redirect URL for a registered application. */
    private String state;

    /**
     * Gets the authorization code which is required to obtain an access token.
     *
     * @return the authorization code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the authorization code which is required to obtain an access token.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the total number of seconds that the authorization token is valid. This is always 4 minutes.
     *
     * @return the expires in seconds
     */
    public long getExpiresInSeconds() {
        return expiresInSeconds;
    }

    /**
     * Sets the total number of seconds that the authorization token is valid. This is always 4 minutes.
     *
     * @param expiresInSeconds the new expires in seconds
     */
    public void setExpiresInSeconds(long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    /**
     * Gets the state string which is returned to the redirect URL for a registered application
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state string which is returned to the redirect URL for a registered application
     *
     * @param state the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder().append("AuthorizationResult [code=")
                .append(code)
                .append(", expiresInSeconds=")
                .append(expiresInSeconds)
                .append(", state=")
                .append(state)
                .append("]").toString();
    }
}
