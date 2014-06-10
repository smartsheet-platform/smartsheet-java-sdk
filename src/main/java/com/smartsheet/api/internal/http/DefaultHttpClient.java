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

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.smartsheet.api.internal.util.Util;

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
	
	/** The apache http request. */
	private org.apache.http.client.methods.HttpRequestBase apacheHttpRequest;
	
	/** The apache http response. */
	private CloseableHttpResponse apacheHttpResponse;
	

	public static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";
	
	/**
	 * Constructor.
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
	 * @param httpClient the http client
	 */
	public DefaultHttpClient(CloseableHttpClient httpClient) {
		Util.throwIfNull(httpClient);
		
		this.httpClient = httpClient;
	}

	/**
	 * Make an HTTP request and return the response.
	 * 
	 * @param smartsheetRequest the smartsheet request
	 * @return the HTTP response
	 * @throws HttpClientException the HTTP client exception
	 */
	public HttpResponse request(HttpRequest smartsheetRequest) throws HttpClientException {
		Util.throwIfNull(smartsheetRequest);
		if(smartsheetRequest.getUri() == null ){
			throw new IllegalArgumentException("A Request URI is required.");
		}
		
		HttpResponse smartsheetResponse = new HttpResponse();

		// Create Apache HTTP request based on the smartsheetRequest request type
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
		
		RequestConfig.Builder builder = RequestConfig.custom();
		if (apacheHttpRequest.getConfig()!=null) {
			builder = RequestConfig.copy(apacheHttpRequest.getConfig());
		}
		builder.setRedirectsEnabled(true);
		RequestConfig config = builder.build();
		apacheHttpRequest.setConfig(config);

		// Set HTTP headers
		if (smartsheetRequest.getHeaders() != null) {
			for (Map.Entry<String, String> header : smartsheetRequest.getHeaders().entrySet()) {
				apacheHttpRequest.addHeader(header.getKey(), header.getValue());
			}
		}
		
		// Set User Agent
		String thisVersion = "";
		String title = "";
		Package thisPackage = getClass().getPackage();
		if(thisPackage != null){
			thisVersion = thisPackage.getImplementationVersion();
			title = thisPackage.getImplementationTitle();
		}
		
		apacheHttpRequest.setHeader(HttpHeaders.USER_AGENT, title+"/" + thisVersion +" " + 
				System.getProperty("os.name") + " "+System.getProperty("java.vm.name") + " " + 
				System.getProperty("java.vendor") + " " + System.getProperty("java.version"));

		// Set HTTP entity
		if (apacheHttpRequest instanceof HttpEntityEnclosingRequestBase && smartsheetRequest.getEntity() != null && 
				smartsheetRequest.getEntity().getContent() != null) {
			InputStreamEntity entity = new InputStreamEntity(smartsheetRequest.getEntity().getContent(), smartsheetRequest.getEntity().getContentLength());
			entity.setChunked(false);
			((HttpEntityEnclosingRequestBase) apacheHttpRequest).setEntity(entity);
		}
		
		// Make the HTTP request
		try {
			apacheHttpResponse = this.httpClient.execute(apacheHttpRequest);
			
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
			//apacheHttpRequest.releaseConnection();
		}
		
		return smartsheetResponse;
	}

	/**
	 * Close the HttpClient.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException {
		this.httpClient.close();
	}
	
	/* (non-Javadoc)
	 * @see com.smartsheet.api.internal.http.HttpClient#releaseConnection()
	 */
	public void releaseConnection() {
		if(apacheHttpResponse != null){
			try {
				apacheHttpResponse.close();
			} catch (IOException e) {
				// Ignore exception as there isn't anything else that can be done.
				//TODO: log this
			}
		}
	}
}
