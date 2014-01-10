package com.smartsheet.api.internal.http;

import java.io.InputStream;

/**
 * This class represents an HTTP Entity (http://www.w3.org/Protocols/rfc2616/rfc2616-sec7.html).
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public class HttpEntity {
	/**
	 * Represents the content type.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private String contentType;

	/**
	 * Represents the content length.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private long contentLength;

	/**
	 * Represents the content as an InputStream.
	 * 
	 * It has a pair of setter/getter (not shown on class diagram for brevity).
	 */
	private InputStream content;
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

}
