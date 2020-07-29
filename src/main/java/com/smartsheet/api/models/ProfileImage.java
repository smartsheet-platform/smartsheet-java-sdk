/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2020 Smartsheet
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

public class ProfileImage {

    /**
     * Image ID
     */
    private String id;

    /**
     * Original width (in pixels) of the image.
     */
    private Long width;

    /**
     * Original height (in pixels) of the image.
     */
    private Long height;

    /**
     * gets the image ID
     *
     * @return the id
     */
    public String getImageId() { return id; }

    /**
     * sets the image ID
     *
     * @param id the id
     */
    public ProfileImage setImageId(String id) {
        this.id = id;
        return this;
    }

    /**
     * gets the width (in pixels) of the image
     */
    public Long getWidth() {
        return width;
    }

    /**
     * sets the width (in pixels)
     *
     * @param width
     */
    public ProfileImage setWidth(Long width) {
        this.width = width;
        return this;
    }

    /**
     * gets the height (in pixels) of the image
     *
     * @return the height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * sets the width (in pixels) of the image
     *
     * @param height
     */
    public ProfileImage setHeight(Long height) {
        this.height = height;
        return this;
    }
}
