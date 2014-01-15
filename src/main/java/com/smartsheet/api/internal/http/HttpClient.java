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
