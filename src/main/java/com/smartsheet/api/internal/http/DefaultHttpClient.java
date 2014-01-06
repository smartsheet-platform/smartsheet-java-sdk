package com.smartsheet.api.internal.http;

import com.smartsheet.api.internal.http.*;
import org.apache.http.impl.client.*;

/**
 * This is the Apache HttpClient (http://hc.apache.org/httpcomponents-client-ga/index.html) based HttpClient implementation.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and the underlying Apache CloseableHttpClient is thread safe.
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
     * Parameters:
     * None
     * 
     * Exceptions:
     * None
     * 
     * Implementation:
     * this(org.apache.http.impl.client.HttpClients.createDefault());
     */
    public DefaultHttpClient() {
    	httpClient = null; //FIXME: initialize httpClient correctly
    }

    /**
     * Constructor.
     * 
     * Parameters:
     * - httpClient : the Apache CloseableHttpClient to use
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * 
     * Implementation:
     * this.httpClient = httpClient;
     * @param httpClient 
     */
    public DefaultHttpClient(CloseableHttpClient httpClient) {
    	this.httpClient = httpClient;
    }

    /**
     * Make an HTTP request and return the response.
     * 
     * Parameters:
     * - request : the HTTP request
     * 
     * Returns:
     * the HTTP response
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * - HttpClientException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * org.apache.http.client.methods.HttpRequestBase httpRequest = null;
     * if (HttpMethod.GET == request.getMethod()) {
     *     httpRequest = new HttpGet(request.getUri());
     * } else if (HttpMethod.POST == request.getMethod()) {
     *     httpRequest = new HttpPost(request.getUri());
     * } else if (HttpMethod.PUT == request.getMethod()) {
     *     httpRequest = new HttpPut(request.getUri());
     * } else if (HttpMethod.DELETE == request.getMethod()) {
     *     httpRequest = new HttpDelete(request.getUri());
     * } else {
     *     throw new UnsupportedOperationException("Request method " + request.getMethod() + " is not supported!");
     * }
     * 
     * // Set HTTP headers
     * if (request.getHeaders() != null) {
     *     for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
     *         httpRequest.addHeader(header.getKey(), header.getValue());
     *     }
     * }
     * 
     * // Set HTTP entity
     * if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
     *     ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(new InputStreamEntity(request.getEntity().getContent()));
     * }
     * 
     * // Make the HTTP request
     * try {
     *     org.apache.http.HttpResponse httpResponse = this.httpClient.execute(httpRequest);
     *     HttpResponse response = new HttpResponse();
     *     response.setHeaders(new HashMap<String, String>());
     *     for (org.apache.http.Header header : httpResponse.getAllHeaders()) {
     *         response.getHeaders().put(header.getName(), header.getValue());
     *     }
     *     response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
     *     
     *     if (httpResponse.getEntity() != null) {
     *         HttpEntity entity = new HttpEntity();
     *         entity.setContentType(httpResponse.getEntity().getContentType().getValue());
     *         entity.setContentLength(httpResponse.getEntity().getContentLength());
     *         entity.setContent(httpResponse.getEntity().getContent());
     *         response.setEntity(entity);
     *     }
     *     return response;
     * } catch (ClientProtocolException e) {
     *     throw new HttpClientException("Error occurred.", e);
     * } catch (IOException e) {
     *     throw new HttpClientException("Error occurred.", e);
     * } finally {
     *     // Release connection
     *     httpRequest.releaseConnection();
     * }
     * @param request 
     * @return 
     */
    public HttpResponse request(HttpRequest request) {
        return null;
    }

    /**
     * Close the HttpClient.
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * None
     * 
     * Exceptions:
     * - IOException: if any error occurred during the operation.
     * 
     * Implementation:
     * this.httpClient.close();
     */
    public void close() {
    }
}

