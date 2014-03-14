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



import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;

/**
 * <p>OAuthFlow interface provides methods to do the OAuth2 authorization.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface OAuthFlow {
	
	/**
	 * <p>Generate a new authorization URL.</p>
	 *
	 * @param scopes the requested scopes
	 * @param state an arbitrary string that will be returned to your app; intended to be used by you to ensure that 
	 * this redirect is indeed from an OAuth flow that you initiated.
	 * @return the authorization URL
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws IllegalArgumentException if scopes is null or empty
	 */
	public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state) throws UnsupportedEncodingException;

	/**
	 * Extract AuthorizationResult from the authorization response URL (i.e. the redirectURL with the response
	 * parameters from Smartsheet OAuth server).
	 *
	 * @param authorizationResponseURL the authorization response url
	 * @return the authorization result
	 * @throws URISyntaxException the URI syntax exception
	 * @throws AccessDeniedException the access denied exception
	 * @throws UnsupportedResponseTypeException the unsupported response type exception
	 * @throws InvalidScopeException the invalid scope exception
	 * @throws OAuthAuthorizationCodeException the o auth authorization code exception
	 * @throws IllegalArgumentException if any other error occurred during the operation
	 */
	public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL) throws 
		URISyntaxException, AccessDeniedException, UnsupportedResponseTypeException, InvalidScopeException, 
		OAuthAuthorizationCodeException;

	/**
	 * Obtain a new token using AuthorizationResult.
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
	 * @throws IllegalArgumentException if any other error occurred during the operation
	 */
	public Token obtainNewToken(AuthorizationResult authorizationResult) throws NoSuchAlgorithmException, UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException;

	/**
	 * Refresh token.
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
	 * @throws IllegalArgumentException if any other error occurred during the operation
	 */
	public Token refreshToken(Token token) throws NoSuchAlgorithmException, UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException;
}
