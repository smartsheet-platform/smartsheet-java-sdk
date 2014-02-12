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

/**
 * This is the builder that is used to build OAuthFlow instances.
 * 
 * Thread Safety: This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in
 * multiple threads.
 */
public class OAuthFlowBuilder {
	/**
	 * Represents the default OAuth authorization URL
	 * 
	 * It is a constant with value "https://www.smartsheet.com/b/authorize".
	 */
	public static final String DEFAULT_AUTHORIZATION_URL = "https://www.smartsheet.com/b/authorize";

	/**
	 * Represents the default token URL
	 * 
	 * It is a constant with value "https://api.smartsheet.com/1.1/token".
	 */
	public static final String DEFAULT_TOKEN_URL = "https://api.smartsheet.com/1.1/token";

	/**
	 * Represents the HttpClient.
	 * 
	 * It can be set using corresponding setter.
	 */
	private HttpClient httpClient;

	/**
	 * Represents the JsonSerializer.
	 * 
	 * It can be set using corresponding setter.
	 */
	private JsonSerializer jsonSerializer;

	/**
	 * Represents the client ID.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String clientId;

	/**
	 * Represents the client secret.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String clientSecret;

	/**
	 * Represents the redirect URL.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String redirectURL;

	/**
	 * Represents the authorization URL.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String authorizationURL;

	/**
	 * Represents the token URL.
	 * 
	 * It can be set using corresponding setter.
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
	 * Exception: - IllegalArgumentException : if any argument is null
	 *
	 * @param httpClient the httpClient
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient; 
		return this;
	}

	/**
	 * Set the JsonSerializer.
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null
	 *
	 * @param jsonSerializer the JsonSerializer
	 * @return the oAuthFlowBuilder
	 */
	public OAuthFlowBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
		this.jsonSerializer = jsonSerializer;
		return this;
	}

	/**
	 * Set the client ID
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 *
	 * @param clientId the value to set
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * Set the client secret.
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 *
	 * @param clientSecret the client secret
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

	/**
	 * Set the redirect URL
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 *
	 * @param redirectURL the redirect url
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
		return this;
	}

	/**
	 * Set the authorization URL.
	 * 
	 * Parameters: - authorizationURL : the value to set
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 *
	 * @param authorizationURL the authorization URL
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setAuthorizationURL(String authorizationURL) {
		this.authorizationURL = authorizationURL;
		return this;
	}

	/**
	 * Set the token URL.
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 *
	 * @param tokenURL the token url
	 * @return the OAuthFlowBuilder
	 */
	public OAuthFlowBuilder setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
		return this;
	}

	/**
	 * Build the OAuthFlow instance.
	 * 
	 * Exceptions: - IllegalStateException: if clientId, clientSecret or redirectURL isn't set yet.
	 *
	 * @return the OAuthFlow instance
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
		
		return new OAuthFlowImpl(clientId, clientSecret, redirectURL, authorizationURL, tokenURL, httpClient, 
				jsonSerializer);
	}
}
