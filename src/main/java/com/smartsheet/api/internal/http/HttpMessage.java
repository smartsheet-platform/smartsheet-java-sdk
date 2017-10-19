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



import java.util.Map;

/**
 * This is the base class of HTTP messages, it holds headers and an HttpEntity.
 * 
 * Thread Safety: This class is not thread safe since it's mutable.
 */
public abstract class HttpMessage {
    /**
     * Represents the HTTP headers.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private Map<String, String> headers;

    /**
     * Represents the HTTP entity.
     *
     * It has a pair of setter/getter (not shown on class diagram for brevity).
     */
    private HttpEntity entity;

    /**
     * Gets the headers.
     *
     * @return the headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the headers.
     *
     * @param headers the headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Gets the entity.
     *
     * @return the entity
     */
    public HttpEntity getEntity() {
        return entity;
    }

    /**
     * Sets the entity.
     *
     * @param entity the new entity
     */
    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }
}
