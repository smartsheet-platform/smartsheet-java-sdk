package com.smartsheet.api;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * A server for answering HTTP requests with test response data.
 */
@Ignore
public class HttpTestServer {
	private Server _server;
	private String _responseBody;
	private String _requestBody;
	private String _mockResponseData;
	private int port;
	private String contentType;

	public HttpTestServer() {
		this.port = 9090;
		this.contentType = "application/json";
	}

	public HttpTestServer(String mockData) {
		this();
		setMockResponseData(mockData);
	}

	public void start() throws Exception {
		_server = new Server(port);
		_server.setHandler(getMockHandler());
		_server.start();
	}

	/**
	 * Creates an {@link AbstractHandler handler} returning an arbitrary String as a response.
	 * 
	 * @return never <code>null</code>.
	 */
	public Handler getMockHandler() {
		Handler handler = new AbstractHandler() {

			@Override
			public void handle(String target, Request baseRequest, HttpServletRequest request,
					HttpServletResponse response) throws IOException, ServletException {
				
//				Request baseRequest = request instanceof Request ? (Request) request : HttpConnection
//						.getCurrentConnection().getRequest();
				setResponseBody(getMockResponseData());
				setRequestBody(IOUtils.toString(baseRequest.getInputStream()));
				response.setStatus(SC_OK);
				response.setContentType(getContentType());
				IOUtils.write(getResponseBody(), response.getOutputStream());
				baseRequest.setHandled(true);
			}
		};
		return handler;
	}

	public void stop() throws Exception {
		_server.stop();
	}

	public void setResponseBody(String responseBody) {
		_responseBody = responseBody;
	}

	public String getResponseBody() {
		return _responseBody;
	}

	public void setRequestBody(String requestBody) {
		_requestBody = requestBody;
	}

	public String getRequestBody() {
		return _requestBody;
	}

	public void setMockResponseData(String mockResponseData) {
		_mockResponseData = mockResponseData;
	}
	
	public void setMockResponseData(File file) throws IOException{
		InputStream is = new FileInputStream(file);
		_mockResponseData = IOUtils.toString(is);
	}

	public String getMockResponseData() {
		return _mockResponseData;
	}

	protected Server getServer() {
		return _server;
	}
	
	public int getPort(){
		return port;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	private String getRecordedResponse(String recordedResponseFile) throws IOException {
        InputStream testDataStream = getClass().getClassLoader().getResourceAsStream(recordedResponseFile);
        return IOUtils.toString(testDataStream);
    }
}
