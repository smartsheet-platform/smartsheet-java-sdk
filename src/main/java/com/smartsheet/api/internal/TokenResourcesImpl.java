package com.smartsheet.api.internal;

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

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.TokenResources;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.oauth.OAuthTokenException;
import com.smartsheet.api.oauth.Token;

import java.net.URISyntaxException;

public class TokenResourcesImpl extends AbstractResources implements TokenResources {

    public TokenResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Obtain a new token.
     *
     * @throws NoSuchMethodException method implemented in OAuthFlow
     */
    public void getAccessToken() throws NoSuchMethodException{
        throw new NoSuchMethodException("Not implemented in TokenResources. Refer OAuthFlow.");
    }

    /**
     * Revoke access token used to make this request.
     *
     * Exceptions:
     * @throws NoSuchMethodException method implemented in OAuthFlow
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    public void revokeAccessToken() throws OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException, InvalidRequestException, SmartsheetException{
     this.deleteResource("token", Token.class);
    }

    /**
     * Refresh token.
     *
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     * @throws IllegalArgumentException if any other error occurred during the operation
     */
    public void refreshAccessToken() throws NoSuchMethodException{
        throw new NoSuchMethodException("Not implemented in TokenResources. Refer OAuthFlow.");
    }
}
