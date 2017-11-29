 package com.smartsheet.api.models;
 
 /*
  * #[license]
  * Smartsheet SDK for Java
  * %%
  * Copyright (C) 2016 Smartsheet
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

 /**
  * The ReportPublish object (see: http://smartsheet-platform.github.io/api-docs/?java#reportpublish-object)
  */
public class ReportPublish {

    /**
     * Represents the current publish status of the report.
     */
    private boolean readOnlyFullEnabled;

     /**
      * Indicates who can see the published report
      *     If "ALL", report is available to anyone who has the link.
      *     If "ORG", report is available only to members of the report owner's organization.
      */
     private String readOnlyFullAccessibleBy;

     /**
      * String containing the URL of the published report.
      */
     private String readOnlyFullUrl;

     /**
      * Flag to show or hide the left nav toolbar for the read only report.
      */
     private Boolean readOnlyFullShowToolbar = true;

     /**
      *  Default view for published report. (GRID, CARDS, CALENDAR)
      */
     private String readOnlyFullDefaultView;

    /**
     * Get the current publish status of a report
     *
     * @return read only publish status
     */
    public boolean getReadOnlyFullEnabled() {
        return readOnlyFullEnabled;
    }

    /**
     * If true, a rich version of the report is published
     *
     * @param readOnlyFullEnabled report publish status
     */
    public ReportPublish setReadOnlyFullEnabled(boolean readOnlyFullEnabled) {
        this.readOnlyFullEnabled = readOnlyFullEnabled;
        return this;
    }

    /**
     * Get who can see the published report. Only available when readOnlyFullEnabled = true
     *
     * @return String containing "ALL" - anyone, "ORG" - owner organization members
     */
    public String getReadOnlyFullAccessibleBy() {
        return readOnlyFullAccessibleBy;
    }

    /**
     * Set parameter indicating who can see the published report. Only valid when
     * readOnlyFullEnabled = true.
     *
     * @param readOnlyFullAccessibleBy - valid options are "ALL" and "ORG"
     */
    public ReportPublish setReadOnlyFullAccessibleBy(String readOnlyFullAccessibleBy) {
        this.readOnlyFullAccessibleBy = readOnlyFullAccessibleBy;
        return this;
    }

    /**
     * Get the URL of the published report. Valid only if readOnlyFullEnabled = true.
     *
     * @return String containing the URL of the published report.
     */
    public String getReadOnlyFullUrl() {
        return readOnlyFullUrl;
    }

    /**
     * Get the read only full show toolbar flag
     *
     * @return readOnlyFullShowToolbar
     */
    public Boolean getReadOnlyFullShowToolbar() { return readOnlyFullShowToolbar; }

    /**
     * Set the read only full show toolbar flag
     *
     * @param readOnlyFullShowToolbar
     * @return
     */
    public ReportPublish setReadOnlyFullShowToolbar(Boolean readOnlyFullShowToolbar) {
        this.readOnlyFullShowToolbar = readOnlyFullShowToolbar;
        return this;
    }

     /**
      * Get the read only full default view
      *
      * @return readOnlyFullDefaultView. Valid options are "GRID", "CARDS", "CALENDAR"
      */
    public String getReadOnlyFullDefaultView() { return readOnlyFullDefaultView; }

     /**
      * Set the read only full default view
      *
      * @param readOnlyFullDefaultView Valid options are "GRID", "CARDS", "CALENDAR"
      * @return the ReportPublish
      */
    public ReportPublish setReadOnlyFullDefaultView(String readOnlyFullDefaultView) {
        this.readOnlyFullDefaultView = readOnlyFullDefaultView;
        return this;
    }
}
