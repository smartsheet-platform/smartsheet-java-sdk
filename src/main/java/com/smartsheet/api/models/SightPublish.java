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

public class SightPublish {

    /**
     * If true, a rich version of the Sight is published with the ability to use shortcuts and widget interactions.
     */
    private Boolean readOnlyFullEnabled;

    /**
     * Flag to indicate who can access the 'Read-Only Full' view of the published Sight
     *     If "ALL", it is available to anyone who has the link.
     *     If "ORG", it is available only to members of the Sight owner's Smartsheet organization.
     */
    private String readOnlyFullAccessibleBy;

    /**
     * URL for 'Read-Only Full' view of the published Sight.
     */
    private String readOnlyFullUrl;

    /**
     * Returns the value of the readOnlyFullEnabled flag.
     *
     * @return readOnlyFullEnabled
     */
    public Boolean getReadOnlyFullEnabled() {return readOnlyFullEnabled; }

    /**
     * Set the value of the readOnlyFullEnabled flag.
     *
     * @param readOnlyFullEnabled
     * @return
     */
    public SightPublish setReadOnlyFullEnabled(Boolean readOnlyFullEnabled) {
        this.readOnlyFullEnabled = readOnlyFullEnabled;
        return this;
    }

    /**
     * Returns the readOnlyFullUrl string.
     *
     * @return readOnlyFullUrl
     */
    public String getReadOnlyFullUrl() {return readOnlyFullUrl; }

    /**
     * Sets the value of the readOnlyFullUrL string.
     *
     * @param readOnlyFullUrl
     * @return
     */
    public SightPublish setReadOnlyFullUrl(String readOnlyFullUrl) {
        this.readOnlyFullUrl = readOnlyFullUrl;
        return this;
    }

    /**
     * Returns the readOnlyFullAccessibleBy string indicating if this sight is viewable by ALL or ORG
     *
     * @return readOnlyFullAccessibleBy
     */
    public String getReadOnlyFullAccessibleBy() {return readOnlyFullAccessibleBy; }

    /**
     * Sets the value of the readOnlyFullAccessibleBy string
     *
     * @param readOnlyFullAccessibleBy
     * @return
     */
    public SightPublish setReadOnlyFullAccessibleBy(String readOnlyFullAccessibleBy) {
        this.readOnlyFullAccessibleBy = readOnlyFullAccessibleBy;
        return this;
    }
}
