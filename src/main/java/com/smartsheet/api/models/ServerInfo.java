package com.smartsheet.api.models;

import java.util.List;

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
public class ServerInfo {

    /**
     * Represents all Smartsheet-supported locales.
     */
    private List<String> supportedLocales;

    /**
     * Represents format tables that are used in Column, Row, and Cell format property.
     */
    private FormatTables formats;

    /**
     * Represents feature info object.
     */
    private FeatureInfo featureInfo;

    /**
     * Gets the Smartsheet-supported locales.
     *
     * @return the supported locales
     */
    public List<String> getSupportedLocales() {
        return supportedLocales;
    }

    /**
     * Sets the Smartsheet-supported locales.
     *
     * @param supportedLocales the supported locales
     */
    public void setSupportedLocales(List<String> supportedLocales) {
        this.supportedLocales = supportedLocales;
    }

    /**
     * Gets the format tables.
     *
     * @return the format tables
     */
    public FormatTables getFormats() {
        return formats;
    }

    /**
     * Sets the format tables.
     *
     * @param formats the format tables
     */
    public void setFormats(FormatTables formats) {
        this.formats = formats;
    }

    /**
     * Gets the feature info.
     *
     * @return the feature info
     */
    public FeatureInfo getFeatureInfo() {
        return featureInfo;
    }

    /**
     * Sets the feature info.
     *
     * @param featureInfo the feature info
     */
    public void setFeatureInfo(FeatureInfo featureInfo) {
        this.featureInfo = featureInfo;
    }
}
