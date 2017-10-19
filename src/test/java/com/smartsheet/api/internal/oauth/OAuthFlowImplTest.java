package com.smartsheet.api.internal.oauth;

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

import com.smartsheet.api.HttpTestServer;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.oauth.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OAuthFlowImplTest {

    OAuthFlowImpl oauth;
    String clientId = "clientID";
    String clientSecret = "clientSecret";
    String redirectURL = "redirectURL";
    String authorizationURL = "authorizationURL";
    String tokenURL = "tokenURL";
    HttpClient httpClient = new DefaultHttpClient();
    JsonSerializer json = new JacksonJsonSerializer();

    HttpTestServer server;

    @After
    public void baseTearDown() throws Exception {
        server.stop();
    }

    @Before
    public void setUp() throws Exception {
        oauth = new OAuthFlowImpl(clientId,clientSecret,redirectURL,authorizationURL,tokenURL, httpClient, json);

        // Setup test server
        server = new HttpTestServer();
        server.setPort(9090);
        server.start();

        // Setup the serializer
        JacksonJsonSerializer serializer = new JacksonJsonSerializer();
        serializer.setFailOnUnknownProperties(true);
    }

    @Test
    public void testOAuthFlowImpl() {

        assertEquals(clientId,oauth.getClientId());
        assertEquals(clientSecret, oauth.getClientSecret());
        assertEquals(redirectURL, oauth.getRedirectURL());
        assertEquals(authorizationURL, oauth.getAuthorizationURL());
        assertEquals(tokenURL, oauth.getTokenURL());
        assertEquals(httpClient, oauth.getHttpClient());
        assertEquals(json, oauth.getJsonSerializer());
    }

    @Test
    public void testNewAuthorizationURL() throws UnsupportedEncodingException {
        try {
            oauth.newAuthorizationURL(null, null);
            fail("Should have thrown an exception.");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        oauth.newAuthorizationURL(EnumSet.of(AccessScope.READ_SHEETS), null);
        String authURL = oauth.newAuthorizationURL(EnumSet.of(AccessScope.READ_SHEETS), "state");

        assertEquals("authorizationURL?scope=READ_SHEETS&response_type=code&redirect_uri=redirectURL&state=state&"
                + "client_id=clientID", authURL);
    }

    @Test
    public void testExtractAuthorizationResult() throws URISyntaxException, OAuthAuthorizationCodeException {

        try{
            oauth.extractAuthorizationResult(null);
            fail("Should have thrown an exception.");
        }catch(IllegalArgumentException ex){
            // Expected
        }

        try{
            oauth.extractAuthorizationResult("");
            fail("Should have thrown an exception.");
        }catch(IllegalArgumentException ex){
            // Expected
        }

        // Null query
        try{
            oauth.extractAuthorizationResult("http://smartsheet.com");
            fail("Should have thrown an exception");
        }catch(OAuthAuthorizationCodeException ex){
            // Expected
        }

        try{
            oauth.extractAuthorizationResult("http://smartsheet.com?error=access_denied");
            fail("Should have thrown an exception");
        }catch(AccessDeniedException ex){
            // Expected
        }

        try{
            oauth.extractAuthorizationResult("http://smartsheet.com?error=unsupported_response_type");
            fail("Should have thrown an exception");
        }catch(UnsupportedResponseTypeException ex){
            // Expected
        }

        try{
            oauth.extractAuthorizationResult("http://smartsheet.com?error=invalid_scope");
            fail("Should have thrown an exception");
        }catch(InvalidScopeException ex){
            // Expected
        }

        try{
            oauth.extractAuthorizationResult("http://smartsheet.com?error=something_undefined");
            fail("Should have thrown an exception");
        }catch(OAuthAuthorizationCodeException ex){
            // Expected
        }

        // No valid parameters (empty result)
        oauth.extractAuthorizationResult("http://smartsheet.com?a=b");

        // Empty Error (empty result)
        oauth.extractAuthorizationResult("http://smartsheet.com?error=");

        // All parameters set (good response)
        oauth.extractAuthorizationResult("http://smartsheet.com?code=code&state=state&expires_in=10");
    }

    @Test
    public void testObtainNewToken() throws NoSuchAlgorithmException, OAuthTokenException,
        JSONSerializerException, HttpClientException, OAuthAuthorizationCodeException, URISyntaxException, InvalidRequestException, IOException {
        server.setStatus(403);
        server.setContentType("application/x-www-form-urlencoded");
        server.setResponseBody(new File("src/test/resources/OAuthException.json"));
        server.setResponseBody("{\"errorCode\": \"1004\", "
                + "\"message\": \"You are not authorized to perform this action.\"}");

        oauth.setTokenURL("http://localhost:9090/1.1/token");
        // 403 access forbidden
        try{
            oauth.obtainNewToken(oauth.extractAuthorizationResult("http://localhost?a=b"));
            fail("Exception should have been thrown.");
        }catch(OAuthTokenException ex){
            // Expected
        }
    }



    @Test
    public void testRefreshToken() throws InvalidRequestException, NoSuchAlgorithmException,
        UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException,
        URISyntaxException {
        oauth.setTokenURL("https://api.smartsheet.com/1.1/token");

        Token token = new Token();
        token.setAccessToken("AccessToken");
        token.setExpiresInSeconds(10L);
        token.setRefreshToken("refreshToken");
        token.setTokenType("tokenType");
        assertEquals("AccessToken", token.getAccessToken());
        assertEquals("refreshToken", token.getRefreshToken());
        assertEquals(10L, token.getExpiresInSeconds());
        assertEquals("tokenType", token.getTokenType());

        try{
            oauth.refreshToken(token);
            fail("An expection should have been thrown.");
        }catch(InvalidOAuthClientException ex){
            // Expected
        }
    }

    @Test
    public void testRevokeAccessToken() throws InvalidRequestException, NoSuchAlgorithmException,
            UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException {
        oauth.setTokenURL("https://api.smartsheet.com/1.1/token");

        Token token = new Token();
        token.setAccessToken("AccessToken");
        token.setExpiresInSeconds(10L);
        token.setRefreshToken("refreshToken");
        token.setTokenType("tokenType");
        try{
            oauth.revokeAccessToken(token);
            fail("An expection should have been thrown.");
        }catch(OAuthTokenException ex){
            // Expected
        }
    }
}
