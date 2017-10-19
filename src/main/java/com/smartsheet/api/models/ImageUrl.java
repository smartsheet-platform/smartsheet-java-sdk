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
package com.smartsheet.api.models;

public class ImageUrl {

    /**
     * Image ID
     */
    private String imageId;

    /**
     * In the getImageUrls request, this (optional) attribute represents the requested width.
     */
    private Long width;

    /**
     * In the getImageUrls request, this (optional) attribute represents the requested height.
     */
    private Long height;

    /**
     * Temporary URL that can be used to retrieve the image. This attribute can be present
     * in a response but will never be specified in a request.
     */
    private String url;

    /**
     * Error object. Present in the getImageUrls response only if an error occurred.
     */
    private Error error;

    /**
     * Get the image id.
     *
     * @return imageId
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * Set the image id
     *
     * @param imageId
     */
    public ImageUrl setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    /**
     * Get the image width
     *
     * @return width
     */
    public Long getWidth() {
        return width;
    }

    /**
     * Set the image width
     *
     * @param width
     */
    public ImageUrl setWidth(Long width) {
        this.width = width;
        return this;
    }

    /**
     * Get the image height
     *
     * @return height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * Set the image height
     *
     * @param height
     */
    public ImageUrl setHeight(Long height) {
        this.height = height;
        return this;
    }

    /**
     * Get the temporary URL that can be used to retrieve the image.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the temporary URL that can be used to retrieve the image.
     *
     * @param url
     */
    public ImageUrl setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the error object
     *
     * @return error
     */
    public Error getError() {
        return error;
    }

    /**
     * Set the error object.
     *
     * @param error
     */
    public ImageUrl setError(Error error) {
        this.error = error;
        return this;
    }
}
