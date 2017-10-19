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

import java.util.List;

/**
 * Represents the ImageUrl object.
 */
public class ImageUrlMap {

    /**
     * Milliseconds before the URLs within imageUrls will expire.
     */
    private Long urlExpiresInMillis;

    /**
     * Array of imageUrl objects
     */
    private List<ImageUrl> imageUrls;

    /**
     * Get the milliseconds before the URLs within imageUrls will expire.
     *
     * @return urlExpiresInMillis
     */
    public Long getUrlExpiresInMillis() {
        return urlExpiresInMillis;
    }

    /**
     * Set the milliseconds before the URLs within imageUruls will expire.
     *
     * @param urlExpiresInMillis
     */
    public ImageUrlMap setUrlExpiresInMillis(Long urlExpiresInMillis) {
        this.urlExpiresInMillis = urlExpiresInMillis;
        return this;
    }

    /**
     * Get the array of imageUrl objects
     *
     * @return imageUrls
     */
    public List<ImageUrl> getImageUrls() {
        return imageUrls;
    }

    /**
     * Set the array of imageUrl objects
     *
     * @param imageUrls
     */
    public ImageUrlMap setImageUrls(List<ImageUrl> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }
}
