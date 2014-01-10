package com.smartsheet.api.internal.http;

import java.net.URI;

/**
 * This class represents an HTTP request.
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public class HttpRequest extends HttpMessage {
	/**
	 * Represents the URI.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private URI uri;

	/**
	 * Represents the HTTP method.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private HttpMethod method;
	
	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}
}
