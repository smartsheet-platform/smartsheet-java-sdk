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

/**
 * Represents the publish status of a sheet.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522078-publishing-sheets">Help Publishing 
 * Sheets</a>
 */
public class SheetPublish {
    /**
     * Represents the read-only lite (static HTML UI) flag.
     */
    private Boolean readOnlyLiteEnabled;

    /**
     * Represents the read-only full (fancy UI) flag.
     */
    private Boolean readOnlyFullEnabled;

    /**
     * Represents the read-write enabled flag.
     */
    private Boolean readWriteEnabled;

    /**
     * Represents the iCal enabled flag.
     */
    private Boolean icalEnabled;

    /**
     * Flag to indicate who can access the 'Read-Only Full' view of the published sheet:
     *    If "ALL", it is available to anyone who has the link.
     *    If "ORG", it is available only to members of the sheet owner's Smartsheet organization.
     */
    private String readOnlyFullAccessibleBy;

    /**
     * Flag to indicate who can access the 'Edit by Anyone' view of the published sheet:
     *    If "ALL", it is available to anyone who has the link.
     *    If "ORG", it is available only to members of the sheet owner's Smartsheet organization.
     */
    private String readWriteAccessibleBy;

    /**
     * Represents the read-only lite (static HTML UI) URL.
     */
    private String readOnlyLiteUrl;

    /**
     * Represents the read-only lite URL with HTTPS.
     */
    private String readOnlyLiteSslUrl;

    /**
     * Represents the read-only full URL.
     */
    private String readOnlyFullUrl;

    /**
     * Represents the read-write URL.
     */
    private String readWriteUrl;

    /**
     * Represents the iCal URL.
     */
    private String icalUrl;

    /**
     * Flag to show or hide the left nav toolbar for the read only sheet.
     */
    private Boolean readOnlyFullShowToolbar = true;

    /**
     * Flag to show or hide the left nav toolbar for the read/write sheet.
     */
    private Boolean readWriteShowToolbar = true;

    /**
     * Default view for read only published sheet. (GRID, CARDS, CALENDAR)
     */
    private String readOnlyFullDefaultView;

    /**
     * Default view for read write published sheet. (GRID, CARDS, CALENDAR)
     */
    private String readWriteDefaultView;

    /**
     * Gets the read only lite enabled flag.
     *
     * @return the read only lite enabled flag
     */
    public Boolean getReadOnlyLiteEnabled() {
        return readOnlyLiteEnabled;
    }

    /**
     * Sets the read only lite (static html UI) enabled flag.
     *
     * @param readOnlyLiteEnabled the new read only lite enabled flag
     */
    public SheetPublish setReadOnlyLiteEnabled(Boolean readOnlyLiteEnabled) {
        this.readOnlyLiteEnabled = readOnlyLiteEnabled;
        return this;
    }

    /**
     * Gets the read only full (fancy UI) enabled flag.
     *
     * @return the read only full enabled flag
     */
    public Boolean getReadOnlyFullEnabled() {
        return readOnlyFullEnabled;
    }

    /**
     * Sets the read only full (fancy UI) enabled flag.
     *
     * @param readOnlyFullEnabled the new read only full enabled flag
     */
    public SheetPublish setReadOnlyFullEnabled(Boolean readOnlyFullEnabled) {
        this.readOnlyFullEnabled = readOnlyFullEnabled;
        return this;
    }

    /**
     * Gets the read write enabled flag.
     *
     * @return the read write enabled flag
     */
    public Boolean getReadWriteEnabled() {
        return readWriteEnabled;
    }

    /**
     * Sets the read write enabled flag.
     *
     * @param readWriteEnabled the new read write enabled flag
     */
    public SheetPublish setReadWriteEnabled(Boolean readWriteEnabled) {
        this.readWriteEnabled = readWriteEnabled;
        return this;
    }

    /**
     * Gets the ical enabled flag.
     *
     * @return the ical enabled flag
     */
    public Boolean getIcalEnabled() {
        return icalEnabled;
    }

    /**
     * Sets the ical enabled flag.
     *
     * @param icalEnabled the new ical enabled flag
     */
    public SheetPublish setIcalEnabled(Boolean icalEnabled) {
        this.icalEnabled = icalEnabled;
        return this;
    }

    /**
     * Get string indicating who can access the "Read-Only Full" view of the published sheet.
     *
     * @return readOnlyFullAccessibleBy
     */
    public String getReadOnlyFullAccessibleBy() {
        return readOnlyFullAccessibleBy;
    }

    /**
     * Set string indicating who can access the "Read-Only Full" view of the published sheet.
     *
     * @param readOnlyFullAccessibleBy
     */
    public SheetPublish setReadOnlyFullAccessibleBy(String readOnlyFullAccessibleBy) {
        this.readOnlyFullAccessibleBy = readOnlyFullAccessibleBy;
        return this;
    }

    /**
     * Get string indicating who can access the "Edit by Anyone" view of the published sheet.
     *
     * @return readWriteAccessibleBy
     */
    public String getReadWriteAccessibleBy() {
        return readWriteAccessibleBy;
    }

    /**
     * Set string indicating who can access the "Edit by Anyone" view of the published sheet.
     *
     * @param readWriteAccessibleBy
     */
    public SheetPublish setReadWriteAccessibleBy(String readWriteAccessibleBy) {
        this.readWriteAccessibleBy = readWriteAccessibleBy;
        return this;
    }

    /**
     * Gets the read only lite url.
     *
     * @return the read only lite url
     */
    public String getReadOnlyLiteUrl() {
        return readOnlyLiteUrl;
    }

    /**
     * Sets the read only lite (static html UI) url.
     *
     * @param readOnlyLiteUrl the new read only lite url
     */
    public SheetPublish setReadOnlyLiteUrl(String readOnlyLiteUrl) {
        this.readOnlyLiteUrl = readOnlyLiteUrl;
        return this;
    }

    /**
     * Get the read only lite ssl url
     *
     * @return the read only lite ssl url
     */
    public String getReadOnlyLiteSslUrl() {
        return readOnlyLiteSslUrl;
    }

    /**
     * Sets the read only lite ssl url
     *
     * @param readOnlyLiteSslUrl
     */
    public SheetPublish setReadOnlyLiteSslUrl(String readOnlyLiteSslUrl) {
        this.readOnlyLiteSslUrl = readOnlyLiteSslUrl;
        return this;
    }

    /**
     * Gets the read only full (fancy UI) url.
     *
     * @return the read only full url
     */
    public String getReadOnlyFullUrl() {
        return readOnlyFullUrl;
    }

    /**
     * Sets the read only full (fancy UI) url.
     *
     * @param readOnlyFullUrl the new read only full url
     */
    public SheetPublish setReadOnlyFullUrl(String readOnlyFullUrl) {
        this.readOnlyFullUrl = readOnlyFullUrl;
        return this;
    }

    /**
     * Gets the read write url.
     *
     * @return the read write url
     */
    public String getReadWriteUrl() {
        return readWriteUrl;
    }

    /**
     * Sets the read write url.
     *
     * @param readWriteUrl the new read write url
     */
    public SheetPublish setReadWriteUrl(String readWriteUrl) {
        this.readWriteUrl = readWriteUrl;
        return this;
    }

    /**
     * Gets the ical url.
     *
     * @return the ical url
     */
    public String getIcalUrl() {
        return icalUrl;
    }

    /**
     * Sets the ical url.
     *
     * @param icalUrl the new ical url
     */
    public SheetPublish setIcalUrl(String icalUrl) {
        this.icalUrl = icalUrl;
        return this;
    }

    /**
     * Get the read only full show toolbar flag
     *
     * @return readOnlyFullShowToolbar
     */
    public Boolean getReadOnlyFullShowToolbar() {
        return readOnlyFullShowToolbar;
    }

    /**
     * Set the read only full show toolbar flag
     *
     * @param readOnlyFullShowToolbar
     * @return
     */
    public SheetPublish setReadOnlyFullShowToolbar(Boolean readOnlyFullShowToolbar) {
        this.readOnlyFullShowToolbar = readOnlyFullShowToolbar;
        return this;
    }

    /**
     * Get the read/write show toolbar flag
     *
     * @return readWriteShowToolbar
     */
    public Boolean getReadWriteShowToolbar() {
        return readWriteShowToolbar;
    }

    /**
     * Set the read/write show toolbar flag
     *
     * @param readWriteShowToolbar
     */
    public SheetPublish setReadWriteShowToolbar(Boolean readWriteShowToolbar) {
        this.readWriteShowToolbar = readWriteShowToolbar;
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
     * @return the SheetPublish
     */
    public SheetPublish setReadOnlyFullDefaultView(String readOnlyFullDefaultView) {
        this.readOnlyFullDefaultView = readOnlyFullDefaultView;
        return this;
    }

    /**
     * Get the read write default view
     *
     * @return readWriteDefaultView. Valid options are "GRID", "CARDS", "CALENDAR"
     */
    public String getReadWriteDefaultView() { return readWriteDefaultView; }

    /**
     * Set the read write default view
     *
     * @param readWriteDefaultView Valid options are "GRID", "CARDS", "CALENDAR"
     * @return the SheetPublish
     */
    public SheetPublish setReadWriteDefaultView(String readWriteDefaultView) {
        this.readWriteDefaultView = readWriteDefaultView;
        return this;
    }

    /**
     * A convenience class for making a {@link SheetPublish} object with the necessary fields to publish a sheet.
     */
    public static class PublishStatusBuilder {
        private Boolean readOnlyLiteEnabled;
        private Boolean readOnlyFullEnabled;
        private Boolean readWriteEnabled;
        private Boolean icalEnabled;
        private Boolean readWriteShowToolbarEnabled;
        private Boolean readOnlyFullShowToolbarEnabled;

        /**
         * Show or hide toolbar on a read/write sheet
         *
         * @param readWriteShowToolbarEnabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setReadWriteShowToolbarEnabled(Boolean readWriteShowToolbarEnabled) {
           this.readWriteShowToolbarEnabled = readWriteShowToolbarEnabled;
           return this;
        }

        /**
         * Show or hide toolbar on a read only full sheet
         *
         * @param readOnlyFullShowToolbarEnabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setReadOnlyFullShowToolbarEnabled(Boolean readOnlyFullShowToolbarEnabled) {
            this.readOnlyFullShowToolbarEnabled = readOnlyFullShowToolbarEnabled;
            return this;
        }

        /**
         * Read only lite enabled.
         *
         * @param readOnlyLiteEnabled the read only lite (static html UI) enabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setReadOnlyLiteEnabled(Boolean readOnlyLiteEnabled) {
            this.readOnlyLiteEnabled = readOnlyLiteEnabled;
            return this;
        }

        /**
         * Read only full (fancy UI) enabled.
         *
         * @param readOnlyFullEnabled the read only full enabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setReadOnlyFullEnabled(Boolean readOnlyFullEnabled) {
            this.readOnlyFullEnabled = readOnlyFullEnabled;
            return this;
        }

        /**
         * Read write enabled.
         *
         * @param readWriteEnabled the read write enabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setReadWriteEnabled(Boolean readWriteEnabled) {
            this.readWriteEnabled = readWriteEnabled;
            return this;
        }

        /**
         * Ical enabled.
         *
         * @param icalEnabled the ical enabled
         * @return the publish status builder
         */
        public PublishStatusBuilder setIcalEnabled(Boolean icalEnabled) {
            this.icalEnabled = icalEnabled;
            return this;
        }

        /**
         * Gets the read only lite enabled.
         *
         * @return the read only lite enabled
         */
        public Boolean getReadOnlyLiteEnabled() {
            return readOnlyLiteEnabled;
        }

        /**
         * Gets the read only full enabled.
         *
         * @return the read only full enabled
         */
        public Boolean getReadOnlyFullEnabled() {
            return readOnlyFullEnabled;
        }

        /**
         * Gets the read write enabled.
         *
         * @return the read write enabled
         */
        public Boolean getReadWriteEnabled() {
            return readWriteEnabled;
        }

        /**
         * Gets the ical enabled.
         *
         * @return the ical enabled
         */
        public Boolean getIcalEnabled() {
            return icalEnabled;
        }

        /**
         * Builds the.
         *
         * @return the sheet publish
         */
        public SheetPublish build() {

            if(readOnlyLiteEnabled == null || readOnlyFullEnabled == null || readWriteEnabled == null){
                throw new InstantiationError("'Read only lite', 'read only full' and 'read write' must be set to "
                        + "update the publishing status.");
            }

            SheetPublish sheetPublish = new SheetPublish();
            sheetPublish.readOnlyLiteEnabled = readOnlyLiteEnabled;
            sheetPublish.readOnlyFullEnabled = readOnlyFullEnabled;
            sheetPublish.readWriteEnabled = readWriteEnabled;
            sheetPublish.icalEnabled = icalEnabled;

            if (readOnlyFullShowToolbarEnabled != null) {
                sheetPublish.readOnlyFullShowToolbar = readOnlyFullShowToolbarEnabled;
            }

            if (readWriteShowToolbarEnabled != null) {
                sheetPublish.readWriteShowToolbar = readWriteShowToolbarEnabled;
            }

            return sheetPublish;
        }
    }
}
