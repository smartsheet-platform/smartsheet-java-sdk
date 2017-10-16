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
 * Represents the AutoNumberFormat object. It describes how the the System Column type of "AUTO_NUMBER" is auto-generated
 * @see <a href="http://www.smartsheet.com/developers/api-documentation#h.xu85ymcuwnmq">Auto Number Format API Documentation</a>
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/1108408-auto-numbering">Auto Number Format Help</a>
 */
public class AutoNumberFormat {

    /** Represents the prefix. */
    private String prefix;

    /** Represents the suffix. */
    private String suffix;

    /** Represents the fill. */
    private String fill;

    /** Represents the starting number. */
    private Long startingNumber;

    /**
     * Gets the prefix.
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix. The prefix. Can include the date tokens {YY}, {YYYY}, {MM}, {DD}
     *
     * @param prefix the new prefix
     */
    public AutoNumberFormat setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Gets the suffix.
     *
     * @return the suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the suffix.
     *
     * @param suffix the new suffix
     */
    public AutoNumberFormat setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * Gets the fill.
     *
     * @return the fill
     */
    public String getFill() {
        return fill;
    }

    /**
     * Sets the fill. Must be 0 - 10 "0" characters. Indicates zero-padding
     *
     * @param fill the new fill
     */
    public AutoNumberFormat setFill(String fill) {
        this.fill = fill;
        return this;
    }

    /**
     * Gets the starting number.
     *
     * @return the starting number
     */
    public Long getStartingNumber() {
        return startingNumber;
    }

    /**
     * Sets the starting number for the auto-id.
     *
     * @param startingNumber the new starting number
     */
    public AutoNumberFormat setStartingNumber(Long startingNumber) {
        this.startingNumber = startingNumber;
        return this;
    }

}
