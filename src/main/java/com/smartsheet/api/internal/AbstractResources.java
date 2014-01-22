package com.smartsheet.api.internal;

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



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.json.JSONSerializerException;

/**
 * This is the base class of the Smartsheet REST API resources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying SmartsheetImpl is thread safe.
 */
public abstract class AbstractResources {
	
	
	public enum ErrorCode {
		BAD_REQUEST(400, InvalidRequestException.class), 
		NOT_AUTHORIZED(401, AuthorizationException.class),
		FORBIDDEN(403, AuthorizationException.class),
		NOT_FOUND(404, ResourceNotFoundException.class),
		METHOD_NOT_SUPPORTED(405, InvalidRequestException.class),
		INTERNAL_SERVER_ERROR(500, InvalidRequestException.class),
		SERVICE_UNAVAILABLE(503,ServiceUnavailableException.class);
		
		int errorCode;
		Class<? extends SmartsheetRestException> clazz;

		ErrorCode(int errorCode, Class<? extends SmartsheetRestException> clazz) {
			this.errorCode = errorCode;
			this.clazz = clazz;
		}

		public static ErrorCode getErrorCode(int errorNumber) {
			for (ErrorCode code : ErrorCode.values()) {
				if (code.errorCode == errorNumber) {
					return code;
				}
			}

			return null;
		}

		public SmartsheetRestException getException() throws InstantiationException, IllegalAccessException {
			return clazz.newInstance();
		}
		
		public SmartsheetRestException getException(com.smartsheet.api.models.Error error) throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
			
			return clazz.getConstructor(com.smartsheet.api.models.Error.class).newInstance(error);
		}
	}

	/**
	 * Represents the SmartsheetImpl.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private SmartsheetImpl smartsheet;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: this.smartsheet = smartsheet;
	 * 
	 * @param smartsheet
	 */
	protected AbstractResources(SmartsheetImpl smartsheet) {
		if(smartsheet == null){
			throw new IllegalArgumentException();
		}
		
		this.smartsheet = smartsheet;
	}

	/**
	 * Get a resource from Smartsheet REST API.
	 * 
	 * Parameters: - path : the relative path of the resource - objectClass : the resource object class
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ResourceNotFoundException : if the resource can
	 * not be found - ServiceUnavailableException : if the REST API service is not available (possibly due to rate
	 * limiting) - SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 * - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return this.smartsheet.getJsonSerializer().deserialize(objectClass,
	 * response.getEntity().getContent()); default: handleError(response); }
	 * 
	 * @param path the relative path of the resource.
	 * @return
	 * @throws HttpClientException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 */
	protected <T> T getResource(String path, Class<T> objectClass) throws HttpClientException, JsonParseException,
		JsonMappingException, IOException, SmartsheetRestException, IllegalArgumentException, SecurityException,
		InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) {
			case 200: 
				return this.smartsheet.getJsonSerializer().deserialize(objectClass, response.getEntity().getContent());
			default: 
				handleError(response); 
		}
		
		return null;
	}

	/**
	 * Create a resource using Smartsheet REST API.
	 * 
	 * Parameters: - path : the relative path of the resource collections - objectClass : the resource object class -
	 * object : the object to create
	 * 
	 * Returns: the created resource
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); this.smartsheet.getJsonSerializer().serialize(object,
	 * baos); HttpEntity entity = new HttpEntity(); entity.setContentType("application/json"); entity.setContent(new
	 * ByteArrayInputStream(baos.toByteArray())); entity.setContentLength(baos.size()); request.setEntity(entity);
	 * 
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return
	 * this.smartsheet.getJsonSerializer().deserializeResult(objectClass,
	 * response.getEntity().getContent()).getResult(); default: handleError(response); }
	 * 
	 * @param path
	 * @param object
	 * @return
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	protected <T> T createResource(String path, Class<T> objectClass, T object) throws JSONSerializerException, 
		HttpClientException, JsonParseException, JsonMappingException, SmartsheetRestException, IllegalArgumentException, 
		SecurityException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, 
		NoSuchMethodException {
		
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(object, baos);
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json");
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size());
		request.setEntity(entity);

		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) { 
			case 200:
				return this.smartsheet.getJsonSerializer().deserializeResult(objectClass, 
						response.getEntity().getContent()).getResult();
			default:
				handleError(response);
		}
		
		return null;
	}

	/**
	 * Update a resource using Smartsheet REST API.
	 * 
	 * Parameters: - path : the relative path of the resource - objectClass : the resource object class - object : the
	 * object to create
	 * 
	 * Returns: the updated resource
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ResourceNotFoundException : if the resource can
	 * not be found - ServiceUnavailableException : if the REST API service is not available (possibly due to rate
	 * limiting) - SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 * - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); this.smartsheet.getJsonSerializer().serialize(object,
	 * baos); HttpEntity entity = new HttpEntity(); entity.setContentType("application/json"); entity.setContent(new
	 * ByteArrayInputStream(baos.toByteArray())); entity.setContentLength(baos.size()); request.setEntity(entity);
	 * 
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return
	 * this.smartsheet.getJsonSerializer().deserializeResult(objectClass,
	 * response.getEntity().getContent()).getResult(); default: handleError(response); }
	 * 
	 * @param path
	 * @param object
	 * @return
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	protected <T> T updateResource(String path, Class<T> objectClass, T object) throws JSONSerializerException,
		HttpClientException, JsonParseException, JsonMappingException, SmartsheetRestException,
		IllegalArgumentException, SecurityException, IOException, InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		this.smartsheet.getJsonSerializer().serialize(object, baos);
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json");
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) { 
			case 200: 
				return this.smartsheet.getJsonSerializer().deserializeResult(objectClass, 
						response.getEntity().getContent()).getResult(); 
			default: 
				handleError(response); 
		}
		
		return null;
	}

	/**
	 * List resources using Smartsheet REST API.
	 * 
	 * Parameters: - path : the relative path of the resource collections - objectClass : the resource object class
	 * 
	 * Returns: the resources
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
	 * 
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return
	 * this.smartsheet.getJsonSerializer().deserializeList(objectClass, response.getEntity().getContent()); default:
	 * handleError(response); }
	 * 
	 * @param path
	 * @return
	 * @throws HttpClientException 
	 * @throws JSONSerializerException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	protected <T> List<T> listResources(String path, Class<T> objectClass) throws HttpClientException, JSONSerializerException, JsonParseException, JsonMappingException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) { 
			case 200: 
				return this.smartsheet.getJsonSerializer().deserializeList(objectClass, 
						response.getEntity().getContent()); 
			default:
				handleError(response);
		}
		
		return null;
	}

	/**
	 * Delete a resource from Smartsheet REST API.
	 * 
	 * Parameters: - path : the relative path of the resource - objectClass : the resource object class
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ResourceNotFoundException : if the resource can
	 * not be found - ServiceUnavailableException : if the REST API service is not available (possibly due to rate
	 * limiting) - SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 * - SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path),
	 * HttpMethod.DELETE); HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: this.smartsheet.getJsonSerializer().deserializeResult(objectClass,
	 * response.getEntity().getContent()); break; default: handleError(response); }
	 * 
	 * @param path
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 */
	protected <T> void deleteResource(String path, Class<T> objectClass) throws JsonParseException, 
		JsonMappingException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException,
		InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
		JSONSerializerException, HttpClientException {
		
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.DELETE);
		HttpResponse response = this.smartsheet.getHttpClient().request(request);

		switch (response.getStatusCode()) {
			case 200:
				this.smartsheet.getJsonSerializer().deserializeResult(objectClass, response.getEntity().getContent());
			break;
			default: 
				handleError(response); 
		}
	}

	/**
	 * Post an object to Smartsheet REST API and receive a list of objects from response.
	 * 
	 * Parameters: - path : the relative path of the resource collections - objectToPost : the object to post -
	 * objectClassToReceive : the resource object class to receive
	 * 
	 * Returns: the object list
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * this.smartsheet.getJsonSerializer().serialize(objectToPost, baos); HttpEntity entity = new HttpEntity();
	 * entity.setContentType("application/json"); entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
	 * entity.setContentLength(baos.size()); request.setEntity(entity);
	 * 
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return
	 * this.smartsheet.getJsonSerializer().deserializeListResult(objectClassToReceive,
	 * response.getEntity().getContent()).getResult(); default: handleError(response); }
	 * 
	 * @param path
	 * @return
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	protected <T, S> List<S> postAndReceiveList(String path, T objectToPost, Class<S> objectClassToReceive) 
			throws JSONSerializerException, HttpClientException, JsonParseException, JsonMappingException, 
			SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(objectToPost, baos); 
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json"); 
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) { 
			case 200:
				return this.smartsheet.getJsonSerializer().deserializeListResult(objectClassToReceive, 
						response.getEntity().getContent()).getResult();
			default:
				handleError(response); 
		}
		
		return null;
	}

	/**
	 * Put an object to Smartsheet REST API and receive a list of objects from response.
	 * 
	 * Parameters: - path : the relative path of the resource collections - objectToPut : the object to put -
	 * objectClassToReceive : the resource object class to receive
	 * 
	 * Returns: the object list
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null, or path is empty string -
	 * InvalidRequestException : if there is any problem with the REST API request - AuthorizationException : if there
	 * is any problem with the REST API authorization(access token) - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * this.smartsheet.getJsonSerializer().serialize(objectToPut, baos); HttpEntity entity = new HttpEntity();
	 * entity.setContentType("application/json"); entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
	 * entity.setContentLength(baos.size()); request.setEntity(entity);
	 * 
	 * HttpResponse response = this.smartsheet.getHttpClient().request(request);
	 * 
	 * switch (response.getStatusCode()) { case 200: return
	 * this.smartsheet.getJsonSerializer().deserializeListResult(objectClassToReceive,
	 * response.getEntity().getContent()).getResult(); default: handleError(response); }
	 * 
	 * @param path
	 * @return
	 * @throws JSONSerializerException 
	 * @throws HttpClientException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	protected <T, S> List<S> putAndReceiveList(String path, T objectToPut, Class<S> objectClassToReceive) throws JSONSerializerException, HttpClientException, JsonParseException, JsonMappingException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HttpRequest request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(objectToPut, baos); HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json"); entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		switch (response.getStatusCode()) { 
			case 200: 
				return this.smartsheet.getJsonSerializer().deserializeListResult(
						objectClassToReceive, response.getEntity().getContent()).getResult(); 
			default:
				handleError(response); 
		}
		
		return null;
	}

	/**
	 * Create an HttpRequest.
	 * 
	 * Parameters: - uri : the URI - method : the HttpMethod
	 * 
	 * Returns: the HttpRequest
	 * 
	 * Exceptions: Any exception shall be propagated since it's a private method.
	 * 
	 * Implementation: HttpRequest request = new HttpRequest(); request.setUri(uri); request.setMethod(method);
	 * 
	 * // Set authorization header request.setHeaders(new HashMap<String, String>());
	 * request.getHeaders().put("Authorization", "Bearer " + smartsheet.getAccessToken());
	 * 
	 * // Set assumed user if (smartsheet.getAssumedUser() != null) { request.getHeaders().put("Assume-User",
	 * URLEncoder.encode(smartsheet.getAssumedUser(), "utf-8")); }
	 * 
	 * return request;
	 * 
	 * @param method
	 * @param uri
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	protected HttpRequest createHttpRequest(URI uri, HttpMethod method) throws UnsupportedEncodingException {
		HttpRequest request = new HttpRequest();
		request.setUri(uri);
		request.setMethod(method);
		
		// Set authorization header 
		request.setHeaders(new HashMap<String, String>());
		request.getHeaders().put("Authorization", "Bearer " + smartsheet.getAccessToken());
		
		// Set assumed user
		if (smartsheet.getAssumedUser() != null) { 
			request.getHeaders().put("Assume-User", URLEncoder.encode(smartsheet.getAssumedUser(), "utf-8"));
		}
		
		return request;
	}

	/**
	 * Handles an error HttpResponse (non-200) returned by Smartsheet REST API.
	 * 
	 * Parameters: - response : the HttpResponse
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - SmartsheetRestException : the exception corresponding to the error
	 * 
	 * Implementation: Error error = this.smartsheet.getJsonSerializer().deserialize(Error.class,
	 * response.getEntity().getContent()); Class<? extends SmartsheetRestException> exceptionClass =
	 * STATUS_CODE_TO_EXCEPTION_CLASS.get(response.getStatusCode()); if (exceptionClass == null) { exceptionClass =
	 * SmartsheetRestException.class; } throw (SmartsheetRestException)
	 * exceptionClass.getConstructor(Error.class).newInstance(error);
	 * 
	 * @param response
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws SmartsheetRestException 
	 */
	protected void handleError(HttpResponse response) throws JsonParseException, JsonMappingException, IOException,
		SmartsheetRestException, IllegalArgumentException, SecurityException, InstantiationException, 
		IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		com.smartsheet.api.models.Error error = this.smartsheet.getJsonSerializer().deserialize(
				com.smartsheet.api.models.Error.class, response.getEntity().getContent());
		
		ErrorCode code = ErrorCode.getErrorCode(response.getStatusCode());
		
		if (code == null) {
			throw new SmartsheetRestException(error);
		}
		
		throw code.getException(error);
	}
	

	public SmartsheetImpl getSmartsheet() {
		return smartsheet;
	}

	public void setSmartsheet(SmartsheetImpl smartsheet) {
		this.smartsheet = smartsheet;
	}
}
