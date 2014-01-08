package com.smartsheet.api.oauth;

import java.util.EnumSet;

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
	 */
	public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state);

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
	 */
	public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL);

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
	 */
	public Token obtainNewToken(AuthorizationResult authorizationResult);

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
	 */
	public Token refreshToken(Token token);
}
