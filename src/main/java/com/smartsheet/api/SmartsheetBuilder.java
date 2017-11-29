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
import com.smartsheet.api.internal.http.DefaultCalcBackoff;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.retry.CalcBackoff;

/**
 * <p>A convenience class to help create a {@link Smartsheet} instance with the appropriate fields.</p>
 * 
 * <p>Thread Safety: This class is not thread safe since it's mutable, one builder instance is NOT expected to be used in
 * multiple threads.</p>
 */
public class SmartsheetBuilder {
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
     * <p>Represents the base URI.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String baseURI;

    /**
     * <p>Represents the access token.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String accessToken;

    /**
     * <p>Represents the API scenario.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String apiScenario;

    /**
     * <p>Represents the assumed user.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String assumedUser;

    /**
     * <p>User provided calc backoff routine.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private CalcBackoff calcBackoff = null;

    /**
     * <p>Represents the change agent.</p>
     *
     * <p>It can be set using corresponding setter.</p>
     */
    private String changeAgent;

    /**
     * <p>Represents the default base URI of the Smartsheet REST API.</p>
     *
     * <p>It is a constant with value "https://api.smartsheet.com/1.1".</p>
     */
    public static final String DEFAULT_BASE_URI = "https://api.smartsheet.com/2.0/";

    /**
     * Constructor.
     */
    public SmartsheetBuilder() {
    }

    /**
     * <p>Set the HttpClient.</p>
     *
     * @param httpClient the http client
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /**
     * <p>Set the JsonSerializer.</p>
     *
     * @param jsonSerializer the JsonSerializer
     * @return the SmartsheetBuilder
     */
    public SmartsheetBuilder setJsonSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
        return this;
    }

    /**
     * <p>Set the base URI.</p>
     *
     * @param baseURI the base uri
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setBaseURI(String baseURI) {
        this.baseURI = baseURI;
        return this;
    }

    /**
     * <p>Set the access token.</p>
     *
     * @param accessToken the access token
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    /**
     * <p>Set the API scenario.</p>
     *
     * @param apiScenario the API scenario
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setAPIScenario(String apiScenario) {
        this.apiScenario = apiScenario;
        return this;
    }

    /**
     * <p>Set the assumed user.</p>
     *
     * @param assumedUser the assumed user
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setAssumedUser(String assumedUser) {
        this.assumedUser = assumedUser;
        return this;
    }

    /**
     * <p>Store a user provided userCalcBackoff.</p>
     *
     * <p>This interface is only valid when the DefaultHttpClient is used.</p>
     *
     * @param maxRetryTimeMillis
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setMaxRetryTimeMillis(long maxRetryTimeMillis) {
        this.calcBackoff = new DefaultCalcBackoff(maxRetryTimeMillis);
        return this;
    }

    /**
     * <p>Create a defaultCalcBackoff with a max elapsed timeout specified by the user.</p>
     *
     * <p>This interface is only valid when the DefaultHttpClient is used.</p>
     *
     * @param calcBackoff
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setUserCalcBackoff(CalcBackoff calcBackoff) {
        this.calcBackoff = calcBackoff;
        return this;
    }

    /**
     * <p>Set the assumed user.</p>
     *
     * @param changeAgent the identifier to include in the webhooks that result from the changes
     *                    made using the API
     *
     * @return the smartsheet builder
     */
    public SmartsheetBuilder setChangeAgent(String changeAgent) {
        this.changeAgent = changeAgent;
        return this;
    }


    /**
     * <p>Gets the http client.</p>
     *
     * @return the http client
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * <p>Gets the json serializer.</p>
     *
     * @return the json serializer
     */
    public JsonSerializer getJsonSerializer() {
        return jsonSerializer;
    }

    /**
     * <p>Gets the base uri.</p>
     *
     * @return the base uri
     */
    public String getBaseURI() {
        return baseURI;
    }

    /**
     * <p>Gets the access token.</p>
     *
     * @return the access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * <p>Gets the API scenario.</p>
     *
     * @return the API scenario
     */
    public String getAPIScenario() {
        return apiScenario;
    }


    /**
     * <p>Gets the assumed user.</p>
     *
     * @return the assumed user
     */
    public String getAssumedUser() {
        return assumedUser;
    }

    /**
     * <p>Gets the default base uri.</p>
     *
     * @return the default base uri
     */
    public static String getDefaultBaseUri() {
        return DEFAULT_BASE_URI;
    }

    public String getChangeAgent() {
        return changeAgent;
    }
    /**
     * <p>Build the Smartsheet instance.</p>
     *
     * @return the Smartsheet instance
     * @throws IllegalStateException if accessToken isn't set yet.
     */
    public Smartsheet build() {
        if(baseURI == null){
            baseURI = DEFAULT_BASE_URI;
        }

        if(accessToken == null){
            accessToken = System.getenv("SMARTSHEET_ACCESS_TOKEN");
        }

        SmartsheetImpl smartsheet = new SmartsheetImpl(baseURI, accessToken, httpClient, jsonSerializer, changeAgent, apiScenario);

        if(calcBackoff != null) {
            smartsheet.setCalcBackoff(calcBackoff);
        }

        if (assumedUser != null) { smartsheet.setAssumedUser(assumedUser); }

        return smartsheet;
    }
}
