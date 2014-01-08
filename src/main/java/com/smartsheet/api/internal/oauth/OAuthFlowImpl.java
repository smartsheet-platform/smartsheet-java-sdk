package com.smartsheet.api.internal.oauth;

import java.util.EnumSet;

import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.oauth.AccessScope;
import com.smartsheet.api.oauth.AuthorizationResult;
import com.smartsheet.api.oauth.OAuthFlow;
import com.smartsheet.api.oauth.Token;

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
	 * Parameters: - clientId : the client ID - clientSecret: the client secret - redirectURL : the redirect URL -
	 * tokenURL : the token URL - httpClient : the HttpClient - jsonSerializer : the JsonSerializer
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or empty string.
	 * 
	 * Implementation: Set correponding fields.
	 * 
	 * @param jsonSerializer
	 * @param redirectURL
	 * @param tokenURL
	 * @param httpClient
	 * @param authorizationURL
	 * @param clientSecret
	 * @param clientId
	 */
	public OAuthFlowImpl(String clientId, String clientSecret, String redirectURL, String authorizationURL,
			String tokenURL, HttpClient httpClient, JsonSerializer jsonSerializer) {
	}

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
	 * Implementation: StringBuilder sb = new StringBuilder(authorizationURL);
	 * sb.append("?response_type=code&client_id=") .append(clientId) .append("&redirect_uri=")
	 * .append(URLEncoder.encode(redirectURL, "utf-8")) .append(state != null ? state : "") .append("&scope="); for
	 * (AccessScope scope : scopes) { sb.append(scope.name()).append(","); } return sb.substring(0, sb.length() - 1);
	 * 
	 * @param scopes
	 * @param state
	 * @return
	 */
	public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state) {
		return null;
	}

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
	 * the response type isn't supported (note that this won't really happen in current implementation) -
	 * InvalidScopeException : if some of the specified scopes are invalid - OAuthAuthorizationCodeException : if any
	 * other error occurred during the operation
	 * 
	 * Implementation: URI uri = new URI(authorizationResponseURL); Map<String, String> map = new HashMap<String,
	 * String>(); for (String param : uri.getQuery().split("&")) { int index = param.indexOf('=');
	 * map.put(param.substring(0, index), param.substring(index + 1)); } String error = map.get("error"); if (error !=
	 * null) { if ("access_denied".equals(error)) { throw new AccessDeniedException(...); } else if
	 * ("unsupported_response_type".equals(error)) { throw new UnsupportedResponseTypeException(...); } else if
	 * ("invalid_scope".equals(error)) { throw new InvalidScopeException(...); } }
	 * 
	 * AuthorizationResult authorizationResult = new AuthorizationResult();
	 * authorizationResult.setCode(map.get("code")); authorizationResult.setState(map.get("state"));
	 * authorizationResult.setExpiresInSeconds(map.get("expires_in"));
	 * 
	 * return authorizationResult;
	 * 
	 * @param authorizationResponseURL
	 * @return
	 */
	public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL) {
		return null;
	}

	/**
	 * Obtain a new token using AuthorizationResult.
	 * 
	 * Parameters: - authorizationResult : the authorization result
	 * 
	 * Returns: the token.
	 * 
	 * Exceptions: - IllegalArgumentException : if authorizationResult is null - InvalidTokenRequestException : if the
	 * token request is invalid (note that this won't really happen in current implementation) -
	 * InvalidOAuthClientException : if the client information is invalid - InvalidOAuthGrantException : if the
	 * authorization code or refresh token is invalid or expired, the redirect_uri does not match, or the hash value
	 * does not match the client secret and/or code - UnsupportedOAuthGrantTypeException : if the grant type is invalid
	 * (note that this won't really happen in current implementation) - OAuthTokenException : if any other error
	 * occurred during the operation
	 * 
	 * Implementation: // Prepare the hash String doHash = clientSecret + "|" + authorizationResult.getCode();
	 * MessageDigest md = MessageDigest.getInstance("SHA-256"); byte[] digest = md.digest(doHash .getBytes("UTF-8"));
	 * String hash = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
	 * 
	 * // Prepare the URI StringBuilder sb = new StringBuilder(tokenURL); sb.append("?grant_type=authorization_code")
	 * .append("&client_id=") .append(clientId) .append("&code=") .append(authorizationResult.getCode())
	 * .append("&redirect_uri=") .append(URLEncoder.encode(redirectURL, "utf-8")) .append("&hash=") .append(hash);
	 * 
	 * return requestToken(sb.toString());
	 * 
	 * @param authorizationResult
	 * @return
	 */
	public Token obtainNewToken(AuthorizationResult authorizationResult) {
		return null;
	}

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
	 * Implementation: // Prepare the hash String doHash = clientSecret + "|" + token.getRefreshToken(); MessageDigest
	 * md = MessageDigest.getInstance("SHA-256"); byte[] digest = md.digest(doHash .getBytes("UTF-8")); String hash =
	 * javax.xml.bind.DatatypeConverter.printHexBinary(digest);
	 * 
	 * // Prepare the URI StringBuilder sb = new StringBuilder(tokenURL); sb.append("?grant_type=refresh_token")
	 * .append("&client_id=") .append(clientId) .append("&refresh_token=") .append(token.getRefreshToken())
	 * .append("&redirect_uri=") .append(URLEncoder.encode(redirectURL, "utf-8")) .append("&hash=") .append(hash);
	 * 
	 * return requestToken(sb.toString());
	 * 
	 * @param token
	 * @return
	 */
	public Token refreshToken(Token token) {
		return null;
	}

	/**
	 * Request a token.
	 * 
	 * Parameters: - url : the URL (with request parameters) from which the token will be requested
	 * 
	 * Returns: the token.
	 * 
	 * Exceptions: - IllegalArgumentException : if url is null or empty - InvalidTokenRequestException : if the token
	 * request is invalid - InvalidOAuthClientException : if the client information is invalid -
	 * InvalidOAuthGrantException : if the authorization code or refresh token is invalid or expired, the redirect_uri
	 * does not match, or the hash value does not match the client secret and/or code -
	 * UnsupportedOAuthGrantTypeException : if the grant type is invalid - OAuthTokenException : if any other error
	 * occurred during the operation
	 * 
	 * Implementation: HttpRequest request = new HttpRequest(); request.setUri(new URI(url));
	 * request.setMethod(HttpMethod.POST); request.setHeaders(new HashMap<String, String>());
	 * request.getHeaders().put("Content-Type", "application/x-www-form-urlencoded");
	 * 
	 * HttpResponse response = httpRequest.request(request);
	 * 
	 * Map map = jsonSerializer.deserialize(Map.class, response.getEntity().getContent());
	 * 
	 * if (200 == response.getStatusCode()) { Token token = new Token(); token.setAccessToken(map.get("access_token"));
	 * token.setTokenType(map.get("token_type")); token.setRefreshToken(map.get("refresh_token"));
	 * token.setExpiresInSeconds(map.get("expires_in")); return token; } else { String errorType = map.get("error");
	 * String errorDescription = map.get("error_description"); if ("invalid_request".equals(errorType)) { throw new
	 * InvalidTokenRequestException(errorDescription); } else if ("invalid_client".equals(errorType)) { throw new
	 * InvalidOAuthClientException(errorDescription); } else if ("invalid_grant".equals(errorType)) { throw new
	 * InvalidOAuthGrantException(errorDescription); } else if ("unsupported_grant_type".equals(errorType)) { throw new
	 * UnsupportedOAuthGrantTypeException(errorDescription); } else { throw new OAuthTokenException(errorDescription); }
	 * }
	 * 
	 * @param url
	 * @return
	 */
	private Token requestToken(String url) {
		return null;
	}
}
