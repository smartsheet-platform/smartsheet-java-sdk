package com.smartsheet.api.internal.http;

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



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * This interface defines methods to make an HTTP request.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface HttpClient extends Closeable {
	/** logger for requests */
	static final Logger requestLogger = LoggerFactory.getLogger(HttpClient.class.getName() + ".request");
	/** logger for responses */
	static final Logger responseLogger = LoggerFactory.getLogger(HttpClient.class.getName() + ".response");
	/** logger for general errors, warnings, etc */
	static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

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
	 * @param request the request
	 * @return the http response
	 * @throws HttpClientException the http client exception
	 */
	public HttpResponse request(HttpRequest request) throws HttpClientException;
	
	/**
	 * Release connection.
	 */
	public void releaseConnection();
}
