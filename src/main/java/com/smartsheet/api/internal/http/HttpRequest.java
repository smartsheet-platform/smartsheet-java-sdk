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



import java.net.URI;

/**
 * This class represents an HTTP request.
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public class HttpRequest extends HttpMessage {
    /**
     * Represents the URI.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private URI uri;

    /**
     * Represents the HTTP method.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private HttpMethod method;

    /**
     * Gets the uri.
     *
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the uri.
     *
     * @param uri the new uri
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Gets the method.
     *
     * @return the method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Sets the method.
     *
     * @param method the new method
     */
    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}
