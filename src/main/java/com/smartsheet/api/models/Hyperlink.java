package com.smartsheet.api.models;

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

public class Hyperlink {
    /**
     * When the hyperlink is a URL link, this property will contain the URL value.
     * When the hyperlink is a Sheet/Report link (i.e. sheetId or reportId is non-null),
     * this property will contain the permalink to the Sheet or Report.
     */
    private String url;

    /**
     * If non-null, this hyperlink is a link to the Sheet with this ID..
     */
    private Long sheetId;

    /**
     * If non-null, this hyperlink is a link to the Report with this ID.
     */
    private Long reportId;

    /**
     * If true, update will serialize a null to reset the hyperlink
     */
    private boolean isNull = true;
    
    /**
     * Gets the url
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url
     *
     * @param url the urk
     */
    public Hyperlink setUrl(String url) {
        this.isNull = false;
        this.url = url;
        return this;
    }

    /**
     * Gets the link to the Sheet with this ID.
     *
     * @return the sheet id
     */
    public Long getSheetId() {
        return sheetId;
    }

    /**
     * Sets the link to the Sheet with this ID.
     *
     * @param sheetId the sheet Id
     */
    public Hyperlink setSheetId(Long sheetId) {
        this.isNull = false;
        this.sheetId = sheetId;
        return this;
    }

    /**
     * Gets a link to the Report with this ID.
     *
     * @return the report Id
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * Sets a link to the Report with this ID.
     *
     * @param reportId the report Id
     */
    public Hyperlink setReportId(Long reportId) {
        this.isNull = false;
        this.reportId = reportId;
        return this;
    }
    
    /**
     * Get the value of the isNull flag
     * 
     * @return value of isNull flag
     */
    public boolean isNull() {
        return this.isNull;
    }
}
