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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.Util;

/**
 * This is the base class of the Smartsheet REST API resources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying SmartsheetImpl is thread safe.
 */
public abstract class AbstractResources {
	
	
	/**
	 * The Enum ErrorCode.
	 */
	public enum ErrorCode {
		BAD_REQUEST(400, InvalidRequestException.class), 
		NOT_AUTHORIZED(401, AuthorizationException.class),
		FORBIDDEN(403, AuthorizationException.class),
		NOT_FOUND(404, ResourceNotFoundException.class),
		METHOD_NOT_SUPPORTED(405, InvalidRequestException.class),
		INTERNAL_SERVER_ERROR(500, InvalidRequestException.class),
		SERVICE_UNAVAILABLE(503,ServiceUnavailableException.class);
		
		/** The error code. */
		int errorCode;
		
		/** The Exception class. */
		Class<? extends SmartsheetRestException> exceptionClass;

		/**
		 * Instantiates a new error code.
		 *
		 * @param errorCode the error code
		 * @param exceptionClass the Exception class
		 */
		ErrorCode(int errorCode, Class<? extends SmartsheetRestException> exceptionClass) {
			this.errorCode = errorCode;
			this.exceptionClass = exceptionClass;
		}

		/**
		 * Gets the error code.
		 *
		 * @param errorNumber the error number
		 * @return the error code
		 */
		public static ErrorCode getErrorCode(int errorNumber) {
			for (ErrorCode code : ErrorCode.values()) {
				if (code.errorCode == errorNumber) {
					return code;
				}
			}

			return null;
		}

		/**
		 * Gets the exception.
		 *
		 * @return the exception
		 * @throws InstantiationException the instantiation exception
		 * @throws IllegalAccessException the illegal access exception
		 */
		public SmartsheetRestException getException() throws InstantiationException, IllegalAccessException {
			return exceptionClass.newInstance();
		}
		
		/**
		 * Gets the exception.
		 *
		 * @param error the error
		 * @return the exception
		 * @throws SmartsheetException the smartsheet exception
		 */
		public SmartsheetRestException getException(com.smartsheet.api.models.Error error) throws SmartsheetException  {
			
			try {
				return exceptionClass.getConstructor(com.smartsheet.api.models.Error.class).newInstance(error);
			} catch (IllegalArgumentException e) {
				throw new SmartsheetException(e);
			} catch (SecurityException e) {
				throw new SmartsheetException(e);
			} catch (InstantiationException e) {
				throw new SmartsheetException(e);
			} catch (IllegalAccessException e) {
				throw new SmartsheetException(e);
			} catch (InvocationTargetException e) {
				throw new SmartsheetException(e);
			} catch (NoSuchMethodException e) {
				throw new SmartsheetException(e);
			}
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
	 * @param smartsheet the smartsheet
	 */
	protected AbstractResources(SmartsheetImpl smartsheet) {
		Util.throwIfNull(smartsheet);
		
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
	 * Exceptions: -
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param path the relative path of the resource.
	 * @param objectClass the object class
	 * @return the resource
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T> T getResource(String path, Class<T> objectClass) throws SmartsheetException  {
		Util.throwIfNull(path, objectClass);
		
		if(path.isEmpty()) {
			com.smartsheet.api.models.Error error = new com.smartsheet.api.models.Error();
			error.setMessage("An empty path was provided.");
			throw new ResourceNotFoundException(error);
		}
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		T obj = null;
		switch (response.getStatusCode()) {
			case 200: 
				// Can't be here as the stream has not
				try {
					obj = this.smartsheet.getJsonSerializer().deserialize(objectClass, response.getEntity().getContent());
				} catch (JsonParseException e) {
					throw new SmartsheetException(e);
				} catch (JsonMappingException e) {
					throw new SmartsheetException(e);
				} catch (IOException e) {
					throw new SmartsheetException(e);
				}
			break;
			default: 
				handleError(response); 
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * Create a resource using Smartsheet REST API.
	 * 
	 * Exceptions: 
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param path the relative path of the resource collections
	 * @param objectClass the resource object class
	 * @param object the object to create
	 * @return the created resource
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T> T createResource(String path, Class<T> objectClass, T object) throws SmartsheetException {
		Util.throwIfNull(path, object);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(object, baos);
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json");
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size());
		request.setEntity(entity);

		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		T obj = null;
		switch (response.getStatusCode()) { 
			case 200:
				obj = this.smartsheet.getJsonSerializer().deserializeResult(objectClass, 
						response.getEntity().getContent()).getResult();
				break;
			default:
				handleError(response);
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * Update a resource using Smartsheet REST API.
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param path the relative path of the resource
	 * @param objectClass the resource object class
	 * @param object the object to create
	 * @return the updated resource
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T> T updateResource(String path, Class<T> objectClass, T object) throws SmartsheetException {
		Util.throwIfNull(path, object);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		this.smartsheet.getJsonSerializer().serialize(object, baos);
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json");
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		T obj = null;
		switch (response.getStatusCode()) { 
			case 200: 
				obj = this.smartsheet.getJsonSerializer().deserializeResult(objectClass, 
						response.getEntity().getContent()).getResult();
				break;
			default: 
				handleError(response); 
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * List resources using Smartsheet REST API.
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param path the relative path of the resource collections
	 * @param objectClass the resource object class
	 * @return the resources
	 * @throws SmartsheetException if an error occurred during the operation
	 */
	protected <T> List<T> listResources(String path, Class<T> objectClass) throws SmartsheetException {
		Util.throwIfNull(path, objectClass);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.GET);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		List<T> obj = null;
		switch (response.getStatusCode()) { 
			case 200: 
				obj = this.smartsheet.getJsonSerializer().deserializeList(objectClass, 
						response.getEntity().getContent());
				break;
			default:
				handleError(response);
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * Delete a resource from Smartsheet REST API.
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ResourceNotFoundException : if the resource can not be found
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param path the relative path of the resource
	 * @param objectClass the resource object class
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T> void deleteResource(String path, Class<T> objectClass) throws SmartsheetException {
		Util.throwIfNull(path, objectClass);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.DELETE);
		HttpResponse response = this.smartsheet.getHttpClient().request(request);

		switch (response.getStatusCode()) {
			case 200:
				this.smartsheet.getJsonSerializer().deserializeResult(objectClass, 
						response.getEntity().getContent());
				break;
			default: 
				handleError(response); 
		}
		
		smartsheet.getHttpClient().releaseConnection();
	}

	/**
	 * Post an object to Smartsheet REST API and receive a list of objects from response.
	 * 
	 * Parameters: - path : the relative path of the resource collections - objectToPost : the object to post -
	 * objectClassToReceive : the resource object class to receive
	 * 
	 * Returns: the object list
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param <S> the generic type
	 * @param path the path
	 * @param objectToPost the object to post
	 * @param objectClassToReceive the object class to receive
	 * @return the list
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T, S> List<S> postAndReceiveList(String path, T objectToPost, Class<S> objectClassToReceive) throws SmartsheetException {
		Util.throwIfNull(path, objectToPost, objectClassToReceive);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(objectToPost, baos); 
		HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json"); 
		entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		List<S> obj = null;
		switch (response.getStatusCode()) { 
			case 200:
				obj = this.smartsheet.getJsonSerializer().deserializeListResult(objectClassToReceive, 
						response.getEntity().getContent()).getResult();
				break;
			default:
				handleError(response); 
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * Put an object to Smartsheet REST API and receive a list of objects from response.
	 * 
	 * Exceptions:
	 *   IllegalArgumentException : if any argument is null, or path is empty string
	 *   InvalidRequestException : if there is any problem with the REST API request
	 *   AuthorizationException : if there is any problem with the REST API authorization(access token)
	 *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
	 *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
	 *   SmartsheetException : if there is any other error occurred during the operation
	 *
	 * @param <T> the generic type
	 * @param <S> the generic type
	 * @param path the relative path of the resource collections
	 * @param objectToPut the object to put
	 * @param objectClassToReceive the resource object class to receive
	 * @return the object list
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected <T, S> List<S> putAndReceiveList(String path, T objectToPut, Class<S> objectClassToReceive) 
			throws SmartsheetException {
		Util.throwIfNull(path, objectToPut, objectClassToReceive);
		Util.throwIfEmpty(path);
		
		HttpRequest request;
		request = createHttpRequest(smartsheet.getBaseURI().resolve(path), HttpMethod.PUT);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.smartsheet.getJsonSerializer().serialize(objectToPut, baos); HttpEntity entity = new HttpEntity();
		entity.setContentType("application/json"); entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
		entity.setContentLength(baos.size()); request.setEntity(entity);
		
		HttpResponse response = this.smartsheet.getHttpClient().request(request);
		
		List<S> obj = null;
		switch (response.getStatusCode()) { 
			case 200: 
				obj = this.smartsheet.getJsonSerializer().deserializeListResult(
						objectClassToReceive, response.getEntity().getContent()).getResult();
				break;
			default:
				handleError(response); 
		}
		
		smartsheet.getHttpClient().releaseConnection();
		
		return obj;
	}

	/**
	 * Create an HttpRequest.
	 * 
	 * Exceptions: Any exception shall be propagated since it's a private method.
	 *
	 * @param uri the URI
	 * @param method the HttpMethod
	 * @return the http request
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	protected HttpRequest createHttpRequest(URI uri, HttpMethod method) {
		HttpRequest request = new HttpRequest();
		request.setUri(uri);
		request.setMethod(method);
		
		// Set authorization header 
		request.setHeaders(new HashMap<String, String>());
		request.getHeaders().put("Authorization", "Bearer " + smartsheet.getAccessToken());
		
		// Set assumed user
		if (smartsheet.getAssumedUser() != null) { 
			try {
				request.getHeaders().put("Assume-User", URLEncoder.encode(smartsheet.getAssumedUser(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException ("Unsupported encode. You must support utf-8 for the Smartsheet Java SDK to work",e);
			}
		}
		
		return request;
	}

	/**
	 * Handles an error HttpResponse (non-200) returned by Smartsheet REST API.
	 * 
	 * Exceptions: 
	 *   SmartsheetRestException : the exception corresponding to the error
	 *
	 * @param response the HttpResponse
	 * @throws SmartsheetException the smartsheet exception
	 */
	protected void handleError(HttpResponse response) throws SmartsheetException {
		
		com.smartsheet.api.models.Error error;
		try {
			error = this.smartsheet.getJsonSerializer().deserialize(
					com.smartsheet.api.models.Error.class, response.getEntity().getContent());
		} catch (JsonParseException e) {
			throw new SmartsheetException(e);
		} catch (JsonMappingException e) {
			throw new SmartsheetException(e);
		} catch (IOException e) {
			throw new SmartsheetException(e);
		}
		
		ErrorCode code = ErrorCode.getErrorCode(response.getStatusCode());
		
		if (code == null) {
			throw new SmartsheetRestException(error);
		}
		
		try {
			throw code.getException(error);
		} catch (IllegalArgumentException e) {
			throw new SmartsheetException(e);
		} catch (SecurityException e) {
			throw new SmartsheetException(e);
		}
	}
	

	/**
	 * Gets the smartsheet.
	 *
	 * @return the smartsheet
	 */
	public SmartsheetImpl getSmartsheet() {
		return smartsheet;
	}

	/**
	 * Sets the smartsheet.
	 *
	 * @param smartsheet the new smartsheet
	 */
	public void setSmartsheet(SmartsheetImpl smartsheet) {
		this.smartsheet = smartsheet;
	}
}
