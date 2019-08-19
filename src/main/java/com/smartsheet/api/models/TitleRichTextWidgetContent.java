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

public class TitleRichTextWidgetContent implements WidgetContent {

    /**
     * The hex color, for instance #E6F5FE
     */
    private String backgroundColor;

    /**
     * HTML snippet to render title or rich text
     */
    private String htmlContent;

    /**
     * Returns the type for this widget content object
     *
     * @return TITLE
     */
    @Override
    public WidgetType getWidgetType() {
        return WidgetType.TITLE;
    }

    /**
     * Gets the hex color for the background
     *
     * @return the hex color
     */
    public String getBackgroundColor() { return backgroundColor; }

    /**
     * Sets the hex color for the background
     *
     * @param backgroundColor
     */
    public TitleRichTextWidgetContent setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    /**
     * Gets the HTML snippet to render title or rich text
     *
     * @return the HTML snippet
     */
    public String getHtmlContent() { return htmlContent; }

    /**
     * Sets the HTML snippet to render title or rich text
     *
     * @param htmlContent
     */
    public TitleRichTextWidgetContent setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
        return this;
    }
}
