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

public class Image {

    /**
     * Image ID
     */
    private String id;

    /**
     * Original width (in pixels) of the uploaded image.
     */
    private Long width;

    /**
     * Original height (in pixels) of the uploaded image.
     */
    private Long height;

    /**
     * Alternate text for the image.
     */
    private String altText;

    /**
     * Gets the image id.
     *
     * @return the image id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the image id
     *
     * @param id
     */
    public Image setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * gets the width (in pixels) of the uploaded image
     */
    public Long getWidth() {
        return width;
    }

    /**
     * sets the width (in pixels)
     *
     * @param width
     */
    public Image setWidth(Long width) {
        this.width = width;
        return this;
    }

    /**
     * gets the height (in pixels) of the uploaded image
     *
     * @return the height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * sets the width (in pixels) of the uploaded image
     *
     * @param height
     */
    public Image setHeight(Long height) {
        this.height = height;
        return this;
    }

    /**
     * get the alternate text (altText) for the image.
     *
     * @return altText
     */
    public String getAltText() {
        return altText;
    }

    /**
     * set the alternate text (altText) for the image.
     *
     * @param altText
     */
    public Image setAltText(String altText) {
        this.altText = altText;
        return this;
    }
}
