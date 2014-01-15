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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This is the Apache HttpClient (http://hc.apache.org/httpcomponents-client-ga/index.html) based HttpClient
 * implementation.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying Apache CloseableHttpClient is
 * thread safe.
 */
public class DefaultHttpClient implements HttpClient {
	/**
	 * Represents the underlying Apache CloseableHttpClient.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private final CloseableHttpClient httpClient;

	/**
	 * Constructor.
	 * 
	 * Parameters: None
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: this(org.apache.http.impl.client.HttpClients.createDefault());
	 */
	public DefaultHttpClient() {
		this(HttpClients.createDefault());
	}

	/**
	 * Constructor.
	 * 
	 * Parameters: - httpClient : the Apache CloseableHttpClient to use
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: this.httpClient = httpClient;
	 * 
	 * @param httpClient
	 */
	public DefaultHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

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
	 * Implementation: org.apache.http.client.methods.HttpRequestBase httpRequest = null; if (HttpMethod.GET ==
	 * request.getMethod()) { httpRequest = new HttpGet(request.getUri()); } else if (HttpMethod.POST ==
	 * request.getMethod()) { httpRequest = new HttpPost(request.getUri()); } else if (HttpMethod.PUT ==
	 * request.getMethod()) { httpRequest = new HttpPut(request.getUri()); } else if (HttpMethod.DELETE ==
	 * request.getMethod()) { httpRequest = new HttpDelete(request.getUri()); } else { throw new
	 * UnsupportedOperationException("Request method " + request.getMethod() + " is not supported!"); }
	 * 
	 * // Set HTTP headers if (request.getHeaders() != null) { for (Map.Entry<String, String> header :
	 * request.getHeaders().entrySet()) { httpRequest.addHeader(header.getKey(), header.getValue()); } }
	 * 
	 * // Set HTTP entity if (httpRequest instanceof HttpEntityEnclosingRequestBase) { ((HttpEntityEnclosingRequestBase)
	 * httpRequest).setEntity(new InputStreamEntity(request.getEntity().getContent())); }
	 * 
	 * // Make the HTTP request try { org.apache.http.HttpResponse httpResponse = this.httpClient.execute(httpRequest);
	 * HttpResponse response = new HttpResponse(); response.setHeaders(new HashMap<String, String>()); for
	 * (org.apache.http.Header header : httpResponse.getAllHeaders()) { response.getHeaders().put(header.getName(),
	 * header.getValue()); } response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
	 * 
	 * if (httpResponse.getEntity() != null) { HttpEntity entity = new HttpEntity();
	 * entity.setContentType(httpResponse.getEntity().getContentType().getValue());
	 * entity.setContentLength(httpResponse.getEntity().getContentLength());
	 * entity.setContent(httpResponse.getEntity().getContent()); response.setEntity(entity); } return response; } catch
	 * (ClientProtocolException e) { throw new HttpClientException("Error occurred.", e); } catch (IOException e) {
	 * throw new HttpClientException("Error occurred.", e); } finally { // Release connection
	 * httpRequest.releaseConnection(); }
	 * 
	 * @param smartsheetRequest
	 * @return
	 * @throws HttpClientException
	 */
	public HttpResponse request(HttpRequest smartsheetRequest) throws HttpClientException {
		if (smartsheetRequest == null) {
			throw new IllegalArgumentException("Request is null");
		}else if(smartsheetRequest.getUri() == null ){
			throw new IllegalArgumentException("A Request URI is required.");
		}
		
		HttpResponse smartsheetResponse = new HttpResponse();

		// Create Apache http request based on the smartsheetRequest request type
		org.apache.http.client.methods.HttpRequestBase apacheHttpRequest = null;
		if (HttpMethod.GET == smartsheetRequest.getMethod()) {
			apacheHttpRequest = new HttpGet(smartsheetRequest.getUri());
		} else if (HttpMethod.POST == smartsheetRequest.getMethod()) {
			apacheHttpRequest = new HttpPost(smartsheetRequest.getUri());
		} else if (HttpMethod.PUT == smartsheetRequest.getMethod()) {
			apacheHttpRequest = new HttpPut(smartsheetRequest.getUri());
		} else if (HttpMethod.DELETE == smartsheetRequest.getMethod()) {
			apacheHttpRequest = new HttpDelete(smartsheetRequest.getUri());
		} else {
			throw new UnsupportedOperationException("Request method " + smartsheetRequest.getMethod()
					+ " is not supported!");
		}

		// Set HTTP headers
		if (smartsheetRequest.getHeaders() != null) {
			for (Map.Entry<String, String> header : smartsheetRequest.getHeaders().entrySet()) {
				apacheHttpRequest.addHeader(header.getKey(), header.getValue());
			}
		}

		// Set HTTP entity
		if (apacheHttpRequest instanceof HttpEntityEnclosingRequestBase && smartsheetRequest.getEntity() != null && 
				smartsheetRequest.getEntity().getContent() != null) {
			((HttpEntityEnclosingRequestBase) apacheHttpRequest).setEntity(new InputStreamEntity(smartsheetRequest
					.getEntity().getContent()));
		}

		// Make the HTTP request
		try {
			org.apache.http.HttpResponse apacheHttpResponse = this.httpClient.execute(apacheHttpRequest);
			
			// Set returned headers
			smartsheetResponse.setHeaders(new HashMap<String, String>());
			for (org.apache.http.Header header : apacheHttpResponse.getAllHeaders()) {
				smartsheetResponse.getHeaders().put(header.getName(), header.getValue());
			}
			smartsheetResponse.setStatusCode(apacheHttpResponse.getStatusLine().getStatusCode());

			// Set returned entities
			if (apacheHttpResponse.getEntity() != null) {
				HttpEntity entity = new HttpEntity();
				entity.setContentType(apacheHttpResponse.getEntity().getContentType().getValue());
				entity.setContentLength(apacheHttpResponse.getEntity().getContentLength());
				entity.setContent(apacheHttpResponse.getEntity().getContent());
				smartsheetResponse.setEntity(entity);
			}
		} catch (ClientProtocolException e) {
			throw new HttpClientException("Error occurred.", e);
		} catch (IOException e) {
			throw new HttpClientException("Error occurred.", e);
		} finally {
			// Release connection
			apacheHttpRequest.releaseConnection();
		}
		
		return smartsheetResponse;
	}

	/**
	 * Close the HttpClient.
	 * 
	 * Parameters: None
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IOException: if any error occurred during the operation.
	 * 
	 * Implementation: this.httpClient.close();
	 */
	public void close() throws IOException {
		this.httpClient.close();
	}
}
