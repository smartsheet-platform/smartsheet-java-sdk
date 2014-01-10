package com.smartsheet.api.internal.http;

import java.io.Closeable;

/**
 * This interface defines methods to make an HTTP request.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface HttpClient extends Closeable {
	/**
	 * Make an HTTP request and return the response.
	 * 
	 * Parameters: - request : the HTTP request
	 * 
	 * Returns: the HTTP response
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - HttpClientException : if there is any other
	 * error occurred during the operation
	 * 
	 * @param request
	 * @return
	 * @throws HttpClientException 
	 */
	public HttpResponse request(HttpRequest request) throws HttpClientException;
}
