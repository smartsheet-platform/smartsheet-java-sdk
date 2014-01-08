package com.smartsheet.api;

import com.smartsheet.api.internal.SmartsheetImpl;
import com.smartsheet.api.internal.http.HttpClient;
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
		//FIXME: finish implementing
//		SmartsheetImpl smartsheet = new SmartsheetImpl(
//			baseURI == null ? DEFAULT_BASE_URI : baseURI,
//			//QUESTION: why the if? what other type of httpClient can there be?
//			httpClient == null ? new DefaultHttpClient() : httpClient,
//			jsonSerializer == null ? new JacksonJsonSerializer(): jsonSerializer
//		);
		
//		smartsheet.setAccessToken(accessToken);
//		
//		if (assumedUser != null) { smartsheet.setAssumedUser(assumedUser); }
	}
}
