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
	 * 
	 * Implementation: Does nothing.
	 */
	public OAuthFlowBuilder() {
	}

	/**
	 * Set the HttpClient.
	 * 
	 * Parameters: - httpClient : the HttpClient
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: this.httpClient = httpClient return this;
	 * 
	 * @param httpClient
	 * @return
	 */
	public OAuthFlowBuilder setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient; 
		return this;
	}

	/**
	 * Set the JsonSerializer.
	 * 
	 * Parameters: - jsonSerializer : the JsonSerializer
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: this.jsonSerializer = jsonSerializer; return this;
	 * 
	 * @param jsonSerializer
	 * @return
	 */
	public OAuthFlowBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
		this.jsonSerializer = jsonSerializer;
		return this;
	}

	/**
	 * Set the client ID
	 * 
	 * Parameters: - clientId : the value to set
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * Implementation: this.clientId = clientId return this;
	 * 
	 * @param clientId
	 * @return
	 */
	public OAuthFlowBuilder setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * Set the client secret.
	 * 
	 * Parameters: - clientSecret : the value to set
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * Implementation: this.clientSecret = clientSecret; return this;
	 * 
	 * @param clientSecret
	 * @return
	 */
	public OAuthFlowBuilder setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

	/**
	 * Set the redirect URL
	 * 
	 * Parameters: - redirectURL : the value to set
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * Implementation: this.redirectURL = redirectURL; return this;
	 * 
	 * @param redirectURL
	 * @return
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
	 * Implementation: this.authorizationURL = authorizationURL; return this;
	 * 
	 * @param authorizationURL
	 * @return
	 */
	public OAuthFlowBuilder setAuthorizationURL(String authorizationURL) {
		this.authorizationURL = authorizationURL;
		return this;
	}

	/**
	 * Set the token URL.
	 * 
	 * Parameters: - tokenURL : the value to set
	 * 
	 * Exception: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * Implementation: this.tokenURL = tokenURL; return this;
	 * 
	 * @param tokenURL
	 * @return
	 */
	public OAuthFlowBuilder setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
		return this;
	}

	/**
	 * Build the OAuthFlow instance.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the OAuthFlow instance
	 * 
	 * Exceptions: - IllegalStateException: if clientId, clientSecret or redirectURL isn't set yet.
	 * 
	 * Implementation: OAuthFlowImpl flow = new OAuthFlow( clientId, clientSecret, redirectURL, authorizationURL == null
	 * ? DEFAULT_AUTHORIZATION_URI : authorizationURL, tokenURL == null ? DEFAULT_TOKEN_URL : tokenURL, httpClient ==
	 * null ? new DefaultHttpClient(), jsonSerializer == null ? new JacksonJsonSerializer()); return flow;
	 * 
	 * @return
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
