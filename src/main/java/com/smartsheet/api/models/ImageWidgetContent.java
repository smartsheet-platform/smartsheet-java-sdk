package com.smartsheet.api.models;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2019 Smartsheet
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

import com.smartsheet.api.models.enums.WidgetType;
import com.smartsheet.api.models.format.Format;

public class ImageWidgetContent implements WidgetContent {

    /**
     * The image private Id
     */
    private String privateId;

    /**
     * Name of the image file
     */
    private String fileName;

    /**
     * Format Descriptor <seealso "href="https://smartsheet-platform.github.io/api-docs/?ruby#formatting">FormatDescriptor</seealso>
     */
    private Format format;

    /**
     * Original height of the image in pixels
     */
    private Integer height;

    /**
     * The widget has when clicked attribute set to that hyperlink (if present and non-null)
     */
    private WidgetHyperlink hyperlink;

    /**
     * Original width of the image in pixels
     */
    private Integer width;

    /**
     * Returns the type for this widget content object
     *
     * @return IMAGE
     */
    @Override
    public WidgetType getWidgetType() {
        return WidgetType.IMAGE;
    }

    /**
     * Gets the image private ID
     *
     * @return the private ID
     */
    public String getPrivateId() {
        return privateId;
    }

    /**
     * Sets the image private ID
     *
     * @param privateId
     */
    public ImageWidgetContent setPrivateId(String privateId) {
        this.privateId = privateId;
        return this;
    }

    /**
     * Gets the name of the image file
     *
     * @return the name of the image file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the name of the image file
     *
     * @param fileName
     */
    public ImageWidgetContent setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Gets the formatDescriptor for the image file
     *
     * @return the formatDescriptor
     */
    public Format getFormat() {
        return format;
    }

    /**
     * Sets the formatDescriptor for the image file
     *
     * @param format
     */
    public ImageWidgetContent setFormat(Format format) {
        this.format = format;
        return this;
    }

    /**
     * Gets the original height of the image in pixels
     *
     * @return the image height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the original height of the image in pixels
     *
     * @param height
     */
    public ImageWidgetContent setHeight(Integer height) {
        this.height = height;
        return this;
    }

    /**
     * Gets the hyperlink associated with this Widget
     *
     * @return the hyperlink
     */
    public WidgetHyperlink getHyperlink() {
        return hyperlink;
    }

    /**
     * Sets the hyperlink associated with this Widget
     *
     * @param hyperlink
     */
    public ImageWidgetContent setHyperlink(WidgetHyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
    }

    /**
     * Gets the original width of the image in pixels
     *
     * @return the image width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the original width of the image in pixels
     *
     * @param width
     */
    public ImageWidgetContent setWidth(Integer width) {
        this.width = width;
        return this;
    }
}
