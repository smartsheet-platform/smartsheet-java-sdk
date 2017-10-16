package com.smartsheet.api;

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

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.Ignore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A server for answering HTTP requests with test response data.
 */
@Ignore
public class HttpTestServer {
    private Server _server;
    //private String _responseBody;
    private String _requestBody;
    private int port;
    private String contentType;
    private byte[] _responseBody;
    private int status;

    public HttpTestServer() {
        this.port = 9090;
        this.contentType = "application/json";
    }

    public HttpTestServer(String mockData) {
        this();
        setResponseBody(mockData);
    }

    public void start() throws Exception {
        _server = new Server(port);
        _server.setHandler(getMockHandler());
        _server.start();
        status = HttpServletResponse.SC_OK;
    }

    /**
     * Creates an {@link AbstractHandler handler} returning an arbitrary String as a response.
     *
     * @return never <code>null</code>.
     */
    public Handler getMockHandler() {
        Handler handler = new AbstractHandler() {

            //@Override
            public void handle(String target, Request baseRequest, HttpServletRequest request,
                    HttpServletResponse response) throws IOException, ServletException {

                setRequestBody(IOUtils.toString(baseRequest.getInputStream()));

                response.setStatus(getStatus());
                response.setContentType(getContentType());

                byte[] body = getResponseBody();

                response.setContentLength(body.length);
                IOUtils.write(body, response.getOutputStream());

                baseRequest.setHandled(true);
            }
        };
        return handler;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

    public void stop() throws Exception {
        _server.stop();
    }

    public void setResponseBody(byte[] responseBody) {
        _responseBody = responseBody;
    }

    public void setResponseBody(String responseBody) {
        setResponseBody(responseBody.getBytes());
    }

    public void setResponseBody(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        setResponseBody(IOUtils.toByteArray(is));
        is.close();
    }

    public byte[] getResponseBody() {
        return _responseBody;
    }

    public void setRequestBody(String requestBody) {
        _requestBody = requestBody;
    }

    public String getRequestBody() {
        return _requestBody;
    }

//    public void setMockResponseData(String mockResponseData) {
//        _mockResponseData = mockResponseData;
//    }
//
//    public void setMockResponseData(File file) throws IOException{
//        InputStream is = new FileInputStream(file);
//        _mockResponseData = IOUtils.toString(is);
//    }

//    public String getMockResponseData() {
//        return _mockResponseData;
//    }

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
}
