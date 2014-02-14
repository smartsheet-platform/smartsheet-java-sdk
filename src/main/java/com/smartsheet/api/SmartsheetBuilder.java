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
 * A convenience class to help create a {@link Smartsheet} instance with the appropriate fields.
 * 
 * Thread Safety: This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in
 * multiple threads.
 */
/**
 * @author brett
 *
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
	public static final String DEFAULT_BASE_URI = "https://api.smartsheet.com/1.1/";

	/**
	 * Constructor.
	 */
	public SmartsheetBuilder() {
	}

	/**
	 * Set the HttpClient.
	 *
	 * @param httpClient the http client
	 * @return the smartsheet builder
	 */
	public SmartsheetBuilder setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
		return this;
	}

	/**
	 * Set the JsonSerializer.
	 *
	 * @param jsonSerializer the JsonSerializer
	 * @return the SmartsheetBuilder
	 */
	public SmartsheetBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
		this.jsonSerializer = jsonSerializer;
		return this;
	}

	/**
	 * Set the base URI.
	 *
	 * @param baseURI the base uri
	 * @return the smartsheet builder
	 */
	public SmartsheetBuilder setBaseURI(String baseURI) {
		this.baseURI = baseURI; 
		return this;
	}

	/**
	 * Set the access token.
	 *
	 * @param accessToken the access token
	 * @return the smartsheet builder
	 */
	public SmartsheetBuilder setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	/**
	 * Set the assumed user.
	 *
	 * @param assumedUser the assumed user
	 * @return the smartsheet builder
	 */
	public SmartsheetBuilder setAssumedUser(String assumedUser) {
		this.assumedUser = assumedUser;
		return this;
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
	 * Gets the base uri.
	 *
	 * @return the base uri
	 */
	public String getBaseURI() {
		return baseURI;
	}

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Gets the assumed user.
	 *
	 * @return the assumed user
	 */
	public String getAssumedUser() {
		return assumedUser;
	}

	/**
	 * Gets the default base uri.
	 *
	 * @return the default base uri
	 */
	public static String getDefaultBaseUri() {
		return DEFAULT_BASE_URI;
	}

	/**
	 * Build the Smartsheet instance.
	 * 
	 * Exceptions: - IllegalStateException : if accessToken isn't set yet.
	 *
	 * @return the Smartsheet instance
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
