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



import com.smartsheet.api.internal.SmartsheetImpl;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;

/**
 * This is the builder that is used to build Smartsheet instances.
 * 
 * Thread Safety: This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in
 * multiple threads.
 */
public class SmartsheetBuilder {
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
	 * Represents the base URI.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String baseURI;

	/**
	 * Represents the access token.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String accessToken;

	/**
	 * Represents the assumed user.
	 * 
	 * It can be set using corresponding setter.
	 */
	private String assumedUser;

	/**
	 * Represents the default base URI of the Smartsheet REST API.
	 * 
	 * It is a constant with value "https://api.smartsheet.com/1.1".
	 */
	public static final String DEFAULT_BASE_URI = "https://api.smartsheet.com/1.1";

	/**
	 * Constructor.
	 * 
	 * Implementation: Does nothing.
	 */
	public SmartsheetBuilder() {
	}

	/**
	 * Set the HttpClient.
	 * 
	 * Parameters: - httpClient : the HttpClient
	 * 
	 * Implementation: this.httpClient = httpClient return this;
	 * 
	 * @param httpClient
	 * @return
	 */
	public SmartsheetBuilder setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
		return this;
	}

	/**
	 * Set the JsonSerializer.
	 * 
	 * Parameters: - jsonSerializer : the JsonSerializer
	 * 
	 * Implementation: this.jsonSerializer = jsonSerializer; return this;
	 * 
	 * @param jsonSerializer
	 * @return
	 */
	public SmartsheetBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
		this.jsonSerializer = jsonSerializer;
		return this;
	}

	/**
	 * Set the base URI.
	 * 
	 * Parameters: - baseURI : the base URI
	 * 
	 * Implementation: this.baseURI = baseURI; return this;
	 * 
	 * @param baseURI
	 * @return
	 */
	public SmartsheetBuilder setBaseURI(String baseURI) {
		this.baseURI = baseURI; 
		return this;
	}

	/**
	 * Set the access token.
	 * 
	 * Parameters: - accessToken : the access token
	 * 
	 * Implementation: this.accessToken = accessToken; return this;
	 * 
	 * @param accessToken
	 * @return
	 */
	public SmartsheetBuilder setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	/**
	 * Set the assumed user
	 * 
	 * Parameters: - assumedUser : the assumed user
	 * 
	 * Implementation: this.assumedUser = assumedUser; return this;
	 * 
	 * @param assumedUser
	 * @return
	 */
	public SmartsheetBuilder setAssumedUser(String assumedUser) {
		this.assumedUser = assumedUser;
		return this;
	}

	/**
	 * Build the Smartsheet instance.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the Smartsheet instance
	 * 
	 * Exceptions: - IllegalStateException : if accessToken isn't set yet.
	 * 
	 * Implementation: SmartsheetImpl smartsheet = new SmartsheetImpl( baseURI == null ? DEFAULT_BASE_URI : baseURI,
	 * httpClient == null ? new DefaultHttpClient(), jsonSerializer == null ? new JacksonJsonSerializer());
	 * 
	 * smartsheet.setAccessToken(accessToken);
	 * 
	 * if (assumedUser != null) { smartsheet.setAssumedUser(assumedUser); }
	 * 
	 * return smartsheet;
	 * 
	 * @return
	 */
	public Smartsheet build() {
		if(httpClient == null){
			httpClient = new DefaultHttpClient();
		}
		
		if(jsonSerializer == null){
			jsonSerializer = new JacksonJsonSerializer();
		}
		
		if(baseURI == null){
			baseURI = DEFAULT_BASE_URI;
		}
		
		SmartsheetImpl smartsheet = new SmartsheetImpl(baseURI, accessToken, httpClient, jsonSerializer);
		
		if (assumedUser != null) { smartsheet.setAssumedUser(assumedUser); }
		
		return smartsheet;
	}
}
