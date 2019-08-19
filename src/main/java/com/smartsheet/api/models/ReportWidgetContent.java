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

public class ReportWidgetContent implements WidgetContent {

    /**
     * Report ID denoting container source
     */
    private Long reportId;

    /**
     * HTML snippet to render report
     */
    private String htmlContent;

    /**
     * The widget has when clicked attribute set to that hyperlink (if present and non-null).
     * Hyperlinks will have interactionType.
     */
    private WidgetHyperlink hyperlink;

    /**
     * Returns the type for this widget content object
     *
     * @return GRIDGANTT
     */
    @Override
    public WidgetType getWidgetType() {
        return WidgetType.GRIDGANTT;
    }

    /**
     * Gets the report ID denoting container source
     *
     * @return report ID
     */
    public Long getReportId() { return reportId; }

    /**
     * Sets the report ID denoting container source
     *
     * @param reportId
     */
    public ReportWidgetContent setReportId(Long reportId) {
        this.reportId = reportId;
        return this;
    }

    /**
     * Gets the HTML snippet used to render report
     *
     * @return HTML content
     */
    public String getHtmlContent() { return htmlContent; }

    /**
     * Sets the HTML snippet used to render report
     *
     * @param htmlContent
     */
    public ReportWidgetContent setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
        return this;
    }

    /**
     * Gets the hyperlink associated with this Widget
     *
     * @return the hyperlink
     */
    public WidgetHyperlink getHyperlink() { return hyperlink; }

    /**
     * Sets the hyperlink associated with this Widget
     *
     * @param hyperlink
     */
    public ReportWidgetContent setHyperlink(WidgetHyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
    }
}
