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


import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.internal.http.*;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.oauth.*;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of OAuthFlow.
 *
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public class OAuthFlowImpl implements OAuthFlow {
    /**
     * Represents the HttpClient.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private HttpClient httpClient;

    /**
     * Represents the JsonSerializer.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private JsonSerializer jsonSerializer;

    /**
     * Represents the Client ID.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private String clientId;

    /**
     * Represents the Client Secret.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private String clientSecret;

    /**
     * Represents the redirect URL.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private String redirectURL;

    /**
     * Represents the authorization URL.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private String authorizationURL;

    /**
     * Represents the token URL.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private String tokenURL;

    /**
     * Constructor.
     *
     * Exceptions: -
     *
     * @param clientId the client id
     * @param clientSecret the client secret
     * @param redirectURL the redirect url
     * @param authorizationURL the authorization url
     * @param tokenURL the token url
     * @param httpClient the http client
     * @param jsonSerializer the json serializer
     * @throws IllegalArgumentException If any argument is null, or empty string.
     */
    public OAuthFlowImpl(String clientId, String clientSecret, String redirectURL, String authorizationURL,
                         String tokenURL, HttpClient httpClient, JsonSerializer jsonSerializer) {
        Util.throwIfNull(clientId, clientSecret, redirectURL, authorizationURL, tokenURL, httpClient, jsonSerializer);
        Util.throwIfEmpty(clientId, clientSecret, redirectURL, authorizationURL, tokenURL);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectURL = redirectURL;
        this.authorizationURL = authorizationURL;
        this.tokenURL = tokenURL;
        this.httpClient = httpClient;
        this.jsonSerializer = jsonSerializer;
    }

    /**
     * Generate a new authorization URL.
     *
     * Exceptions: - IllegalArgumentException : if scopes is null/empty
     *
     * @param scopes the scopes
     * @param state an arbitrary string that will be returned to your app; intended to be used by you to ensure that
     * this redirect is indeed from an OAuth flow that you initiated
     * @return the authorization URL
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state) {
        Util.throwIfNull(scopes);
        if(state == null){state = "";}

        // Build a map of parameters for the URL
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("response_type", "code");
        params.put("client_id", clientId);
        params.put("redirect_uri", redirectURL);
        params.put("state", state);

        StringBuilder scopeBuffer = new StringBuilder();
        for(AccessScope scope : scopes) {
            scopeBuffer.append(scope.name()+",");
        }
        params.put("scope",scopeBuffer.substring(0,scopeBuffer.length()-1));

        // Generate the URL with the parameters
        return QueryUtil.generateUrl(authorizationURL, params);
    }

    /**
     * Extract AuthorizationResult from the authorization response URL (i.e. the redirectURL with the response
     * parameters from Smartsheet OAuth server).
     *
     * Exceptions:
     *   - IllegalArgumentException : if authorizationResponseURL is null/empty, or a malformed URL
     *   - AccessDeniedException : if the user has denied the authorization request
     *   - UnsupportedResponseTypeException : if the response type isn't supported (note that this won't really happen in current implementation)
     *   - InvalidScopeException : if some of the specified scopes are invalid
     *   - OAuthAuthorizationCodeException : if any other error occurred during the operation
     *
     * @param authorizationResponseURL the authorization response URL
     * @return the authorization result
     * @throws URISyntaxException the URI syntax exception
     * @throws OAuthAuthorizationCodeException the o auth authorization code exception
     */
    public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL) throws
            URISyntaxException, OAuthAuthorizationCodeException {
        Util.throwIfNull(authorizationResponseURL);
        Util.throwIfEmpty(authorizationResponseURL);

        // Get all of the parms from the URL
        URI uri = new URI(authorizationResponseURL);
        String query = uri.getQuery();
        if(query == null){
            throw new OAuthAuthorizationCodeException("There must be a query string in the response URL");
        }

        Map<String, String> map = new HashMap<String,String>();
        for (String param : query.split("&")) {
            int index = param.indexOf('=');
            map.put(param.substring(0, index), param.substring(index + 1));
        }

        // Check for an error response in the URL and throw it.
        String error = map.get("error");
        if (error != null && !error.isEmpty()) {
            if ("access_denied".equals(error)) {
                throw new AccessDeniedException("Access denied.");
            } else if ("unsupported_response_type".equals(error)) {
                throw new UnsupportedResponseTypeException("response_type must be set to \"code\".");
            } else if ("invalid_scope".equals(error)) {
                throw new InvalidScopeException("One or more of the requested access scopes are invalid. "
                        + "Please check the list of access scopes");
            }else{
                throw new OAuthAuthorizationCodeException("An undefined error was returned of type: "+error);
            }
        }

        AuthorizationResult authorizationResult = new AuthorizationResult();
        authorizationResult.setCode(map.get("code"));
        authorizationResult.setState(map.get("state"));
        Long expiresIn;
        try{
            expiresIn = Long.parseLong(map.get("expires_in"));
        }catch(NumberFormatException ex){
            expiresIn = 0L;
        }
        authorizationResult.setExpiresInSeconds(expiresIn);

        return authorizationResult;
    }

    /**
     * Obtain a new token using AuthorizationResult.
     *
     * Exceptions:
     *   - IllegalArgumentException : if authorizationResult is null
     *   - InvalidTokenRequestException : if the token request is invalid (note that this won't really happen in current implementation)
     *   - InvalidOAuthClientException : if the client information is invalid
     *   - InvalidOAuthGrantException : if the authorization code or refresh token is invalid or expired, the
     *   redirect_uri does not match, or the hash value does not match the client secret and/or code
     *   - UnsupportedOAuthGrantTypeException : if the grant type is invalid (note that this won't really happen in
     *   current implementation)
     *   - OAuthTokenException : if any other error occurred during the operation
     *
     * @param authorizationResult the authorization result
     * @return the token
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    public Token obtainNewToken(AuthorizationResult authorizationResult) throws  OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException, InvalidRequestException {
        if(authorizationResult == null){
            throw new IllegalArgumentException();
        }

        // Prepare the hash

        String doHash = clientSecret + "|" + authorizationResult.getCode();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Your JVM does not support SHA-256, which is required for OAuth with Smartsheet.", e);
        }
        byte[] digest;
        try {
            digest = md.digest(doHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //String hash = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
        String hash = org.apache.commons.codec.binary.Hex.encodeHexString(digest);

        // create a Map of the parameters
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", clientId);
        params.put("code", authorizationResult.getCode());
        params.put("redirect_uri",redirectURL);
        params.put("hash", hash);

        // Generate the URL and then get the token
        return requestToken(QueryUtil.generateUrl(tokenURL, params));
    }

    /**
     * Refresh token.
     *
     * Exceptions:
     *   - IllegalArgumentException : if token is null.
     *   - InvalidTokenRequestException : if the token request is invalid
     *   - InvalidOAuthClientException : if the client information is invalid
     *   - InvalidOAuthGrantException : if the authorization code or refresh token is invalid or expired,
     *   the redirect_uri does not match, or the hash value does not match the client secret and/or code
     *   - UnsupportedOAuthGrantTypeException : if the grant type is invalid
     *   - OAuthTokenException : if any other error occurred during the operation
     *
     * @param token the token to refresh
     * @return the refreshed token
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    public Token refreshToken(Token token) throws OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException {
        // Prepare the hash
        String doHash = clientSecret + "|" + token.getRefreshToken();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Your JVM does not support SHA-256, which is required for OAuth with Smartsheet.", e);
        }
        byte[] digest;
        try {
            digest = md.digest(doHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //String hash = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
        String hash = org.apache.commons.codec.binary.Hex.encodeHexString(digest);


        // Create a map of the parameters
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("grant_type", "refresh_token");
        params.put("client_id",clientId);
        params.put("refresh_token",token.getRefreshToken());
        params.put("redirect_uri", redirectURL);
        params.put("hash",hash);

        // Generate the URL and get the token
        return requestToken(QueryUtil.generateUrl(tokenURL, params));
    }

    /**
     * Request a token.
     *
     * Exceptions:
     *   - IllegalArgumentException : if url is null or empty
     *   - InvalidTokenRequestException : if the token request is invalid
     *   - InvalidOAuthClientException : if the client information is invalid
     *   - InvalidOAuthGrantException : if the authorization code or refresh token is invalid or
     *   expired, the redirect_uri does not match, or the hash value does not match the client secret and/or code
     *   - UnsupportedOAuthGrantTypeException : if the grant type is invalid
     *   - OAuthTokenException : if any other error occurred during the operation
     *
     * @param url the URL (with request parameters) from which the token will be requested
     * @return the token
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    private Token requestToken(String url) throws OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException, InvalidRequestException {

        // Create the request and send it to get the response/token.
        HttpRequest request = new HttpRequest();
        request.setUri(new URI(url));
        request.setMethod(HttpMethod.POST);
        request.setHeaders(new HashMap<String, String>());
        request.getHeaders().put("Content-Type", "application/x-www-form-urlencoded");
        HttpResponse response = httpClient.request(request);

        // Create a map of the response
        InputStream inputStream = response.getEntity().getContent();
        Map<String, Object> map = jsonSerializer.deserializeMap(inputStream);
        httpClient.releaseConnection();

        // Check for a error response and throw it.
        if (response.getStatusCode() != 200 && map.get("error") != null) {
            String errorType = map.get("error").toString();
            String errorDescription = map.get("message")==null?"":(String)map.get("message");
            if ("invalid_request".equals(errorType)) {
                throw new InvalidTokenRequestException(errorDescription);
            } else if ("invalid_client".equals(errorType)) {
                throw new InvalidOAuthClientException(errorDescription);
            } else if ("invalid_grant".equals(errorType)) {
                throw new InvalidOAuthGrantException(errorDescription);
            } else if ("unsupported_grant_type".equals(errorType)) {
                throw new UnsupportedOAuthGrantTypeException(errorDescription);
            } else {
                throw new OAuthTokenException(errorDescription);
            }
        }

        // Another error by not getting a 200 result
        else if(response.getStatusCode() != 200){
            throw new OAuthTokenException("Token request failed with http error code: "+response.getStatusCode());
        }

        // Create a token based on the response
        Token token = new Token();
        Object tempObj = map.get("access_token");
        token.setAccessToken(tempObj == null ? "" : (String) tempObj);
        tempObj = map.get("token_type");
        token.setTokenType(tempObj==null?"":(String)tempObj);
        tempObj = map.get("refresh_token");
        token.setRefreshToken(tempObj==null?"":(String)tempObj);

        Long expiresIn;
        try{
            expiresIn = Long.parseLong(String.valueOf(map.get("expires_in")));
        }catch(NumberFormatException nfe){
            expiresIn = 0L;
        }
        token.setExpiresInSeconds(expiresIn);

        return token;
    }

    /**
     * Revoke access token.
     *
     * Exceptions:
     *   - IllegalArgumentException : if url is null or empty
     *   - InvalidTokenRequestException : if the token request is invalid
     *   - InvalidOAuthClientException : if the client information is invalid
     *   - InvalidOAuthGrantException : if the authorization code or refresh token is invalid or
     *   expired, the redirect_uri does not match, or the hash value does not match the client secret and/or code
     *   - UnsupportedOAuthGrantTypeException : if the grant type is invalid
     *   - OAuthTokenException : if any other error occurred during the operation
     *
     * @param token the access token to revoke access from
     * @throws OAuthTokenException the o auth token exception
     * @throws JSONSerializerException the JSON serializer exception
     * @throws HttpClientException the http client exception
     * @throws URISyntaxException the URI syntax exception
     * @throws InvalidRequestException the invalid request exception
     */
    public void revokeAccessToken(Token token) throws OAuthTokenException, JSONSerializerException, HttpClientException,
            URISyntaxException, InvalidRequestException{
        HttpRequest request = new HttpRequest();
        request.setUri(new URI(tokenURL));
        request.setMethod(HttpMethod.DELETE);

        request.setHeaders(new HashMap<String, String>());
        request.getHeaders().put("Authorization", "Bearer " + token.getAccessToken());
        HttpResponse response = httpClient.request(request);

        if(response.getStatusCode() != 200){
            throw new OAuthTokenException("Token request failed with http error code: "+response.getStatusCode());
        }

        httpClient.releaseConnection();
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
     * Sets the http client.
     *
     * @param httpClient the new http client
     */
    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
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
     * Sets the json serializer.
     *
     * @param jsonSerializer the new json serializer
     */
    public void setJsonSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
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
     * Sets the client id.
     *
     * @param clientId the new client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * Sets the client secret.
     *
     * @param clientSecret the new client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
     * Sets the redirect url.
     *
     * @param redirectURL the new redirect url
     */
    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
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
     * Sets the authorization url.
     *
     * @param authorizationURL the new authorization url
     */
    public void setAuthorizationURL(String authorizationURL) {
        this.authorizationURL = authorizationURL;
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
     * Sets the token url.
     *
     * @param tokenURL the new token url
     */
    public void setTokenURL(String tokenURL) {
        this.tokenURL = tokenURL;
    }




}