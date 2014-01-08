package com.smartsheet.api.internal;

import java.net.URI;
import java.util.List;
import java.util.Map;

import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;

/**
 * This is the base class of the Smartsheet REST API resources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying SmartsheetImpl is thread safe.
 */
public abstract class AbstractResources {

	/**
	 * Represents the mapping from HTTP status code to the class of corresponding exception to be thrown.
	 * 
	 * It will be initialized in a static initializer and will not change afterwards.
	 */
	private static final Map<Integer, Class<? extends SmartsheetRestException>> STATUS_CODE_TO_EXCEPTION_CLASS = null;// FIXME:
																														// what
																														// should
																														// this
																														// be?

	// FIXME: only use the ErrorCode enum instead of the hashmap
	public enum ErrorCode {
		INVALID_REQUEST(400, InvalidRequestException.class), AUTHORIZATION_EXCEPTION(401, AuthorizationException.class);

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
	}

	/**
	 * This is the static initializer of this class.
	 * 
	 * Implementation: STATUS_CODE_TO_EXCEPTION_CLASS = new HashMap<String, String>();
	 * STATUS_CODE_TO_EXCEPTION_CLASS.put(400, InvalidRequestException.class); STATUS_CODE_TO_EXCEPTION_CLASS.put(401,
	 * AuthorizationException.class); STATUS_CODE_TO_EXCEPTION_CLASS.put(403, AuthorizationException.class);
	 * STATUS_CODE_TO_EXCEPTION_CLASS.put(404, ResourceNotFoundException.class); STATUS_CODE_TO_EXCEPTION_CLASS.put(405,
	 * InvalidRequestException.class); STATUS_CODE_TO_EXCEPTION_CLASS.put(500, SmartsheetRestException.class);
	 * STATUS_CODE_TO_EXCEPTION_CLASS.put(503, ServiceUnavailableException.class);
	 */
	public static void init() {
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
	 * @param path
	 * @return
	 */
	protected <T> T getResource(String path, Class<T> objectClass) {
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
	 */
	protected <T> T createResource(String path, Class<T> objectClass, T object) {
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
	 */
	protected <T> T updateResource(String path, Class<T> objectClass, T object) {
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
	 */
	protected <T> List<T> listResources(String path, Class<T> objectClass) {
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
	 */
	protected <T> void deleteResource(String path, Class<T> objectClass) {
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
	 */
	// #postAndReceiveList<T,S>(path:String, objectToPost:T, objectClassToReceive:Class<S>):List<S>
	protected <T, S> List<T> postAndReceiveList(String path, T objectToPost, Class<S> objectClassToReceive) {
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
	 */
	protected <T, S> List<S> putAndReceiveList(String path, T objectToPut, Class<S> objectClassToReceive) {
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
	 */
	protected HttpRequest createHttpRequest(URI uri, HttpMethod method) {
		return null;
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
	 */
	private void handleError(HttpResponse response) {
	}
}
