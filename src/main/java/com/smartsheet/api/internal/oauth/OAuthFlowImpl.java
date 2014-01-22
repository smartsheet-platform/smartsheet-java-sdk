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



import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.oauth.AccessDeniedException;
import com.smartsheet.api.oauth.AccessScope;
import com.smartsheet.api.oauth.AuthorizationResult;
import com.smartsheet.api.oauth.InvalidOAuthClientException;
import com.smartsheet.api.oauth.InvalidOAuthGrantException;
import com.smartsheet.api.oauth.InvalidScopeException;
import com.smartsheet.api.oauth.InvalidTokenRequestException;
import com.smartsheet.api.oauth.OAuthAuthorizationCodeException;
import com.smartsheet.api.oauth.OAuthFlow;
import com.smartsheet.api.oauth.OAuthTokenException;
import com.smartsheet.api.oauth.Token;
import com.smartsheet.api.oauth.UnsupportedOAuthGrantTypeException;
import com.smartsheet.api.oauth.UnsupportedResponseTypeException;

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
	 * @throws UnsupportedEncodingException, IllegalArgumentException 
	 */
	public String newAuthorizationURL(EnumSet<AccessScope> scopes, String state) throws UnsupportedEncodingException {
		if(scopes == null){throw new IllegalArgumentException();}
		if(state == null){state = "";}
		
		// Build a map of parameters for the URL
		HashMap<String,String> params = new HashMap<String, String>();
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
		return generateURL(authorizationURL, params);
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
	 * @throws URISyntaxException 
	 * @throws AccessDeniedException 
	 * @throws UnsupportedResponseTypeException 
	 * @throws InvalidScopeException 
	 */
	public AuthorizationResult extractAuthorizationResult(String authorizationResponseURL) throws 
		URISyntaxException, OAuthAuthorizationCodeException {
		
		if(authorizationResponseURL == null || authorizationResponseURL.isEmpty()){
			throw new IllegalArgumentException();
		}
		
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
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws URISyntaxException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws OAuthTokenException 
	 * @throws InvalidRequestException 
	 */
	public Token obtainNewToken(AuthorizationResult authorizationResult) throws NoSuchAlgorithmException, 
		UnsupportedEncodingException, OAuthTokenException, JSONSerializerException, HttpClientException, 
		URISyntaxException, InvalidRequestException {
		
		 // Prepare the hash 
		String doHash = clientSecret + "|" + authorizationResult.getCode();
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		byte[] digest = md.digest(doHash.getBytes("UTF-8"));
		String hash = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
		
		// create a Map of the parameters
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", clientId);
		params.put("code", authorizationResult.getCode());
		params.put("redirect_uri",redirectURL);
		params.put("hash", hash);
		
		// Generate the URL and then get the token
		return requestToken(generateURL(tokenURL, params));
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
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws URISyntaxException 
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws OAuthTokenException 
	 * @throws InvalidRequestException 
	 */
	public Token refreshToken(Token token) throws NoSuchAlgorithmException, UnsupportedEncodingException,
		OAuthTokenException, JSONSerializerException, HttpClientException, URISyntaxException, InvalidRequestException {
		// Prepare the hash 
		String doHash = clientSecret + "|" + token.getRefreshToken(); 
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(doHash.getBytes("UTF-8"));
		String hash = javax.xml.bind.DatatypeConverter.printHexBinary(digest);
		
		// Create a map of the parameters
		Map<String,String> params = new HashMap<String,String>();
		params.put("grant_type", "refresh_token");
		params.put("client_id",clientId);
		params.put("refresh_token",token.getRefreshToken());
		params.put("redirect_uri", URLEncoder.encode(redirectURL, "utf-8"));
		params.put("hash",hash);
		
		// Generate the URL and get the token
		return requestToken(generateURL(tokenURL, params));
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
	 * @throws OAuthTokenException 
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws URISyntaxException 
	 * @throws InvalidRequestException 
	 */
	private Token requestToken(String url) throws OAuthTokenException, JSONSerializerException, HttpClientException, 
		URISyntaxException, InvalidRequestException {
		
		// Create the request and send it to get the response/token.
		HttpRequest request = new HttpRequest();
		request.setUri(new URI(url));
		request.setMethod(HttpMethod.GET); 
		request.setHeaders(new HashMap<String, String>());
		request.getHeaders().put("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse response = httpClient.request(request);
		
		// Create a map of the response
		InputStream inputStream = response.getEntity().getContent();
		Map<String, Object> map = jsonSerializer.deserializeMap(inputStream);
		
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
		token.setAccessToken(tempObj==null?"":(String)tempObj);
		tempObj = map.get("token_type");
		token.setTokenType(tempObj==null?"":(String)tempObj);
		tempObj = map.get("refresh_token");
		token.setRefreshToken(tempObj==null?"":(String)tempObj);
		
		Long expiresIn;
		try{
			expiresIn = (Long)map.get("expires_in");
		}catch(NumberFormatException nfe){
			expiresIn = 0L;
		}
		token.setExpiresInSeconds(expiresIn); 
		
		return token;
	}
	
	/**
	 * Helper function to generate a URL using the base URL and the given parameters. It will encode each of the 
	 * parameters as well.
	 * @param baseURL The base URL that the parameters will be appended to.
	 * @param parameters The parameters that will be appended to the base URL. Each parameter will be URL encoded.
	 * @return A string representing the full URL.
	 * @throws UnsupportedEncodingException
	 */
	protected String generateURL(String baseURL, Map<String,String> parameters) throws UnsupportedEncodingException {
		// Supports handling a relative URL
		if(baseURL == null){
			baseURL = "";
		}
		
		// Add a question mark to the URL if there isn't one already.
		if(!baseURL.contains("?")) {
			baseURL += "?";
		}
		
		// Test to see if a & should be the next character in the URL
		boolean needsAmpersand = true;
		if(baseURL.endsWith("?") || baseURL.endsWith("&")){
			needsAmpersand = false;
		}
		
		// Add the parameters to the URL
		StringBuilder sb = new StringBuilder(baseURL);
		if(parameters != null){
			for(Map.Entry<String, String> param : parameters.entrySet()) {
				// Don't add a & after a ?
				if(needsAmpersand){
					sb.append("&");
				}
				needsAmpersand = true; // this only matters for the first &;
				
				sb.append(URLEncoder.encode(param.getKey(),"utf-8"));
				sb.append("=");
				
				String key = param.getValue();
				if(key != null) {
					sb.append(URLEncoder.encode(param.getValue(),"utf-8"));
				}
			}
		}
		
		return sb.toString();
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public JsonSerializer getJsonSerializer() {
		return jsonSerializer;
	}

	public void setJsonSerializer(JsonSerializer jsonSerializer) {
		this.jsonSerializer = jsonSerializer;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getAuthorizationURL() {
		return authorizationURL;
	}

	public void setAuthorizationURL(String authorizationURL) {
		this.authorizationURL = authorizationURL;
	}

	public String getTokenURL() {
		return tokenURL;
	}

	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}
	
	
	
	
}
