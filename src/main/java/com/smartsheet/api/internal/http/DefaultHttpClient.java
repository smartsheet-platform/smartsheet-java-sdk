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

import com.smartsheet.api.internal.util.StreamUtil;
import com.smartsheet.api.internal.util.Util;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	private HttpRequestBase apacheHttpRequest;
	
	/** The apache http response. */
	private CloseableHttpResponse apacheHttpResponse;

	/** UserAgent string sent with each request */
	private final String userAgent;

	@Deprecated // never used (within SDK)
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
		this.userAgent = generateUserAgent(getClass());
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
		switch(smartsheetRequest.getMethod()) {
			case GET :
				apacheHttpRequest = new HttpGet(smartsheetRequest.getUri());
				break;
			case POST :
				apacheHttpRequest = new HttpPost(smartsheetRequest.getUri());
				break;
			case PUT:
				apacheHttpRequest = new HttpPut(smartsheetRequest.getUri());
				break;
			case DELETE:
				apacheHttpRequest = new HttpDelete(smartsheetRequest.getUri());
				break;
			default:
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
		apacheHttpRequest.setHeader(HttpHeaders.USER_AGENT, userAgent);

		// Set HTTP entity
		final HttpEntity entity = smartsheetRequest.getEntity();
		if (apacheHttpRequest instanceof HttpEntityEnclosingRequestBase && entity != null && entity.getContent() != null) {
			// we need access to the original stream so we can log it - once it's wrapped we can't touch it
			logRequest(apacheHttpRequest, entity);
			InputStreamEntity streamEntity = new InputStreamEntity(entity.getContent(), entity.getContentLength());
			streamEntity.setChunked(false);	// why?  not supported by library?
			((HttpEntityEnclosingRequestBase) apacheHttpRequest).setEntity(streamEntity);
		} else {
			logRequest(apacheHttpRequest, null);
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
				HttpEntity httpEntity = new HttpEntity();
				httpEntity.setContentType(apacheHttpResponse.getEntity().getContentType().getValue());
				httpEntity.setContentLength(apacheHttpResponse.getEntity().getContentLength());
				httpEntity.setContent(apacheHttpResponse.getEntity().getContent());
				smartsheetResponse.setEntity(httpEntity);
			}

			logResponse(apacheHttpRequest, smartsheetResponse);
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
				apacheHttpResponse = null;
			} catch (IOException e) {
				logger.error("error closing Apache HttpResponse - {}", e);
			}
		}
	}

	static String generateUserAgent(Class<?> clazz) {
		String thisVersion = "";
		String title = "";
		Package thisPackage = clazz.getPackage();
		if (thisPackage != null){
			thisVersion = thisPackage.getImplementationVersion();
			title = thisPackage.getImplementationTitle();
		}
		return title + "/" + thisVersion + " " + System.getProperty("os.name") + " "
				+ System.getProperty("java.vm.name") + " " + System.getProperty("java.vendor") + " "
				+ System.getProperty("java.version");
	}

	private static void logRequest(HttpRequestBase request, HttpEntity entity) {
		if (requestLogger.isTraceEnabled()) {
			StringBuilder buf = new StringBuilder();
			append(buf, request, entity, true);
			requestLogger.trace("Request ({}) - {}", System.identityHashCode(request), buf.toString());
		} else if(requestLogger.isInfoEnabled()) {
			StringBuilder buf = new StringBuilder();
			append(buf, request, null, false);
			requestLogger.info("Request ({}) - {}", System.identityHashCode(request), buf.toString());
		}
	}

	private static void logResponse(HttpRequestBase request, HttpResponse response) {
		if (response.getStatusCode() != 200) {
			// verbose logging of non-OK responses
			StringBuilder buf = new StringBuilder();
			append(buf, response, response.getEntity(), true);
			responseLogger.warn("Response ({}) - {}", System.identityHashCode(request), buf.toString());
		} else if (responseLogger.isTraceEnabled()) {
			StringBuilder buf = new StringBuilder();
			append(buf, response, response.getEntity(), true);
			responseLogger.trace("Response ({}) - {}", System.identityHashCode(request), buf.toString());
		} else if (responseLogger.isInfoEnabled()) {
			StringBuilder buf = new StringBuilder();
			append(buf, response, null, false);
			responseLogger.info("Response ({}) - {}", System.identityHashCode(request), buf.toString());
		}
	}

	private static void append(StringBuilder buf, HttpRequestBase request, HttpEntity entity, boolean includeHeaders) {
		buf.append("{ request:").append(request.getRequestLine());
		if (includeHeaders) {
			StringBuilder headerBuf = new StringBuilder();
			Header[] headers = request.getAllHeaders();
			for (Header header : headers) {
				headerBuf.append(',').append(header.getName()).append(':');
				if ("Authorization".equals(header.getName())) {
					headerBuf.append("Bearer ***");
				} else {
					headerBuf.append(Arrays.toString(header.getElements()));
				}
			}
			buf.append(", headers:[").append(headerBuf.substring(1)).append("]");
		}
		if (entity != null) {
			buf.append(", content:");
			append(buf, entity);
		}
		buf.append("}");
	}

	private static void append(StringBuilder buf, HttpResponse response, HttpEntity entity, boolean includeHeaders) {
		buf.append("{ status:").append(response.getStatusCode());
		if (includeHeaders) {
			buf.append(", headers:").append(response.getHeaders());
		}
		if (entity != null) {
			buf.append(", entity:");
			append(buf, entity);
		}
		buf.append(" }");
	}

	private static void append(StringBuilder buf, HttpEntity entity) {
		String contentAsText = null;
		try {
			InputStream inputStream = entity.getContent();
			ByteArrayOutputStream contentCopyStream = new ByteArrayOutputStream();
			InputStream resetStream = StreamUtil.cloneContent(inputStream, contentCopyStream);
			if (resetStream != inputStream) {
				entity.setContent(resetStream);
			}
			contentAsText = StreamUtil.toUtf8StringOrHex(contentCopyStream);
		} catch (IOException iox) {
			logger.error("failed to extract content from response - {}", iox);
		}

		buf.append("{ contentType:").append(entity.getContentType())
				.append(", contentLen:").append(entity.getContentLength())
				.append(", content:").append(contentAsText)
				.append(" }");
	}
}
