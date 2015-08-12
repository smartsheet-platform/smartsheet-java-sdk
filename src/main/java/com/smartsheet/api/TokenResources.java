package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.oauth.OAuthTokenException;

import java.net.URISyntaxException;

public interface TokenResources {

    /**
     * Obtain a new token.
     *
     * @throws NoSuchMethodException method implemented in OAuthFlow
     */
    public void getAccessToken() throws NoSuchMethodException;

    /**
     * Revoke access token.
     *
     * Exceptions:
     *   - IllegalArgumentException : if url is null or empty
     *   - InvalidTokenRequestException : if the token request is invalid
     *   - InvalidOAuthClientException : if the client information is invalid
     *   - InvalidOAuthGrantException : if the authorization code or refresh token is invalid or
     *   expired, the redirect_uri does not match, or the hash value does not match the client secret and/or code
     *   - UnsupportedOAuthGrantTypeException : if the grant type is invalid
     *   - OAuthTokenException : if any other error occurred during the operation
     *
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    public void revokeAccessToken() throws OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException, InvalidRequestException, SmartsheetException;

    /**
     * Refresh token.
     *
     * @throws NoSuchMethodException method implemented in OAuthFlow
     */
    public void refreshAccessToken() throws NoSuchMethodException;

}
