package com.smartsheet.api;

import com.smartsheet.api.internal.http.*;
import com.smartsheet.api.*;
import com.smartsheet.api.internal.json.*;
import java.lang.*;

/**
 * This is the builder that is used to build Smartsheet instances.
 * 
 * Thread Safety:
 * This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in multiple threads.
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
     * Implementation:
     * Does nothing.
     */
    public SmartsheetBuilder() {
    }

    /**
     * Set the HttpClient.
     * 
     * Parameters:
     * - httpClient : the HttpClient
     * 
     * Implementation:
     * this.httpClient = httpClient
     * return this;
     * @param httpClient 
     * @return 
     */
    public SmartsheetBuilder setHttpClient(HttpClient httpClient) {
        return null;
    }

    /**
     * Set the JsonSerializer.
     * 
     * Parameters:
     * - jsonSerializer : the JsonSerializer
     * 
     * Implementation:
     * this.jsonSerializer = jsonSerializer;
     * return this;
     * @param jsonSerializer 
     * @return 
     */
    public SmartsheetBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
        return null;
    }

    /**
     * Set the base URI.
     * 
     * Parameters:
     * - baseURI : the base URI
     * 
     * Implementation:
     * this.baseURI = baseURI;
     * return this;
     * @param baseURI 
     * @return 
     */
    public SmartsheetBuilder setBaseURI(String baseURI) {
        return null;
    }

    /**
     * Set the access token.
     * 
     * Parameters:
     * - accessToken : the access token
     * 
     * Implementation:
     * this.accessToken = accessToken;
     * return this;
     * @param accessToken 
     * @return 
     */
    public SmartsheetBuilder setAccessToken(String accessToken) {
        return null;
    }

    /**
     * Set the assumed user
     * 
     * Parameters:
     * - assumedUser : the assumed user
     * 
     * Implementation:
     * this.assumedUser = assumedUser;
     * return this;
     * @param assumedUser 
     * @return 
     */
    public SmartsheetBuilder setAssumedUser(String assumedUser) {
        return null;
    }

    /**
     * Build the Smartsheet instance.
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * the Smartsheet instance
     * 
     * Exceptions:
     * - IllegalStateException : if accessToken isn't set yet.
     * 
     * Implementation:
     * SmartsheetImpl smartsheet = new SmartsheetImpl(
     *     baseURI == null ? DEFAULT_BASE_URI : baseURI,
     *     httpClient == null ? new DefaultHttpClient(),
     *     jsonSerializer == null ? new JacksonJsonSerializer());
     * 
     * smartsheet.setAccessToken(accessToken);
     * 
     * if (assumedUser != null) {
     *     smartsheet.setAssumedUser(assumedUser);
     * }
     * 
     * return smartsheet;
     * @return 
     */
    public Smartsheet build() {
        return null;
    }
}

