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



import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.oauth.OAuthFlowImpl;
import com.smartsheet.api.internal.util.Util;

/**
 * <p>This is the builder that is used to build {@link OAuthFlow} instances.</p>
 * 
 * <p>Thread Safety: This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in
 * multiple threads.</p>
 */
public class OAuthFlowBuilder {
    /**
     * <p>Represents the default OAuth authorization URL</p>
     *
     * <p>It is a constant with value "https://www.smartsheet.com/b/authorize".</p>
     */
    public static final String DEFAULT_AUTHORIZATION_URL = "https://www.smartsheet.com/b/authorize";

    /**
     * <p>Represents the default token URL</p>
     *
     * <p>It is a constant with value "https://api.smartsheet.com/1.1/token".</p>
     */
    public static final String DEFAULT_TOKEN_URL = "https://api.smartsheet.com/2.0/token";

    /**
     * <p>Represents the HttpClient.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private HttpClient httpClient;

    /**
     * <p>Represents the JsonSerializer.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private JsonSerializer jsonSerializer;

    /**
     * <p>Represents the client ID.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String clientId;

    /**
     * <p>Represents the client secret.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String clientSecret;

    /**
     * <p>Represents the redirect URL.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String redirectURL;

    /**
     * <p>Represents the authorization URL.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String authorizationURL;

    /**
     * <p>Represents the token URL.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String tokenURL;

    /**
     * Constructor.
     */
    public OAuthFlowBuilder() {
    }

    /**
     * Set the HttpClient.
     *
     * @param httpClient the httpClient
     * @return the OAuthFlowBuilder
     */
    public OAuthFlowBuilder setHttpClient(HttpClient httpClient) {
        Util.throwIfNull(httpClient);

        this.httpClient = httpClient;
        return this;
    }

    /**
     * <p>Set the JsonSerializer.</p>
     *
     * @param jsonSerializer the JsonSerializer
     * @return the oAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
        Util.throwIfNull(jsonSerializer);

        this.jsonSerializer = jsonSerializer;
        return this;
    }

    /**
     * Set the client ID
     *
     * @param clientId the value to set
     * @return the OAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setClientId(String clientId) {
        Util.throwIfNull(clientId);

        this.clientId = clientId;
        return this;
    }

    /**
     * Set the client secret.
     *
     * @param clientSecret the client secret
     * @return the OAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setClientSecret(String clientSecret) {
        Util.throwIfNull(clientSecret);

        this.clientSecret = clientSecret;
        return this;
    }

    /**
     * Set the redirect URL
     *
     * @param redirectURL the redirect url
     * @return the OAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setRedirectURL(String redirectURL) {
        Util.throwIfNull(redirectURL);

        this.redirectURL = redirectURL;
        return this;
    }

    /**
     * Set the authorization URL.
     *
     * @param authorizationURL the authorization URL
     * @return the OAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setAuthorizationURL(String authorizationURL) {
        Util.throwIfNull(authorizationURL);

        this.authorizationURL = authorizationURL;
        return this;
    }

    /**
     * Set the token URL.
     *
     * @param tokenURL the token url
     * @return the OAuthFlowBuilder
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public OAuthFlowBuilder setTokenURL(String tokenURL) {
        Util.throwIfNull(tokenURL);

        this.tokenURL = tokenURL;
        return this;
    }

    /**
     * Gets the default authorization url.
     *
     * @return the default authorization url
     */
    public static String getDefaultAuthorizationUrl() {
        return DEFAULT_AUTHORIZATION_URL;
    }

    /**
     * Gets the default token url.
     *
     * @return the default token url
     */
    public static String getDefaultTokenUrl() {
        return DEFAULT_TOKEN_URL;
    }

    /**
     * Gets the http client.
     *
     * @return the http client
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Gets the json serializer.
     *
     * @return the json serializer
     */
    public JsonSerializer getJsonSerializer() {
        return jsonSerializer;
    }

    /**
     * Gets the client id.
     *
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Gets the client secret.
     *
     * @return the client secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Gets the redirect url.
     *
     * @return the redirect url
     */
    public String getRedirectURL() {
        return redirectURL;
    }

    /**
     * Gets the authorization url.
     *
     * @return the authorization url
     */
    public String getAuthorizationURL() {
        return authorizationURL;
    }

    /**
     * Gets the token url.
     *
     * @return the token url
     */
    public String getTokenURL() {
        return tokenURL;
    }

    /**
     * Build the OAuthFlow instance.
     *
     * @return the OAuthFlow instance
     * @throws IllegalArgumentException if clientId, clientSecret or redirectURL isn't set yet.
     */
    public OAuthFlow build() {
        if(httpClient == null){
            httpClient = new DefaultHttpClient();
        }

        if(tokenURL == null){
            tokenURL = DEFAULT_TOKEN_URL;
        }

        if(authorizationURL == null){
            authorizationURL = DEFAULT_AUTHORIZATION_URL;
        }

        if(jsonSerializer == null){
            jsonSerializer = new JacksonJsonSerializer();
        }

        if(clientId == null || clientSecret == null || redirectURL == null){
            throw new IllegalStateException();
        }

        return new OAuthFlowImpl(clientId, clientSecret, redirectURL, authorizationURL, tokenURL, httpClient,
                jsonSerializer);
    }
}
