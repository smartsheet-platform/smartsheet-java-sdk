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
 * OAuthFlow interface provides methods to do the OAuth2 authorization.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface OAuthFlow {
	/**
	 * Generate a new authorization URL.
	 * 
	 * Parameters: - scopes : the requested scopes - state : an arbitrary string that will be returned to your app;
	 * intended to be used by you to ensure that this redirect is indeed from an OAuth flow that you initiated
	 * 
	 * Returns: the authorization URL
	 * 
	 * Exceptions: - IllegalArgumentException : if scopes is null/empty
	 * 
	 * @param scopes
	 * @param state
	 * @return
	 * @throws IllegalArgumentException 
	 * @throws UnsupportedEncodingException 
	 */
	public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state) throws UnsupportedEncodingException;

	/**
	 * Extract AuthorizationResult from the authorization response URL (i.e. the redirectURL with the response
	 * parameters from Smartsheet OAuth server).
	 * 
	 * Parameters: - authorizationResponseURL : the authorization response URL
	 * 
	 * Returns: the AuthorizationResult
	 * 
	 * Exceptions: - IllegalArgumentException : if authorizationResponseURL is null/empty, or a malformed URL -
	 * AccessDeniedException : if the user has denied the authorization request - UnsupportedResponseTypeException : if
	 * the response type isn't supported - InvalidScopeException : if some of the specified scopes are invalid -
	 * OAuthAuthorizationCodeException : if any other error occurred during the operation
	 * 
	 * @param authorizationResponseURL
	 * @return
	 * @throws URISyntaxException 
	 * @throws AccessDeniedException 
	 * @throws UnsupportedResponseTypeException 
	 * @throws InvalidScopeException 
	 * @throws OAuthAuthorizationCodeException 
	 */
	public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL) throws 
		URISyntaxException, AccessDeniedException, UnsupportedResponseTypeException, InvalidScopeException, 
		OAuthAuthorizationCodeException;

	/**
	 * Obtain a new token using AuthorizationResult.
	 * 
	 * Parameters: - authorizationResult : the authorization result
	 * 
	 * Returns: the token.
	 * 
	 * Exceptions: - IllegalArgumentException : if authorizationResult is null - InvalidTokenRequestException : if the
	 * token request is invalid - InvalidOAuthClientException : if the client information is invalid -
	 * InvalidOAuthGrantException : if the authorization code or refresh token is invalid or expired, the redirect_uri
	 * does not match, or the hash value does not match the client secret and/or code -
	 * UnsupportedOAuthGrantTypeException : if the grant type is invalid - OAuthTokenException : if any other error
	 * occurred during the operation
	 * 
	 * @param authorizationResult
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws URISyntaxException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws OAuthTokenException 
	 * @throws InvalidRequestException 
	 */
	public Token obtainNewToken(AuthorizationResult authorizationResult) throws NoSuchAlgorithmException, UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException;

	/**
	 * Refresh token.
	 * 
	 * Parameters: - token : the token to refresh
	 * 
	 * Returns: the refreshed token.
	 * 
	 * Exceptions: - IllegalArgumentException : if token is null. - InvalidTokenRequestException : if the token request
	 * is invalid - InvalidOAuthClientException : if the client information is invalid - InvalidOAuthGrantException : if
	 * the authorization code or refresh token is invalid or expired, the redirect_uri does not match, or the hash value
	 * does not match the client secret and/or code - UnsupportedOAuthGrantTypeException : if the grant type is invalid
	 * - OAuthTokenException : if any other error occurred during the operation
	 * 
	 * @param token
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws URISyntaxException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws OAuthTokenException 
	 * @throws InvalidRequestException 
	 */
	public Token refreshToken(Token token) throws NoSuchAlgorithmException, UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException;
}
