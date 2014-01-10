package com.smartsheet.api.internal.http;

/**
 * This class represents an HTTP response.
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public class HttpResponse extends HttpMessage {
	/**
	 * Represents the response status code.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
