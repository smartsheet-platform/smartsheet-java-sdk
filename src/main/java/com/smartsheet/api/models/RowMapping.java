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

public class RowMapping {
    /** Represents the Row ID in the source sheet. */
    private Long from;

    /** Represents the Row ID in the destination sheet. */
    private Long to;

    /**
     * Gets the Row ID in the source sheet.
     *
     * @return Row ID in the source sheet
     */
    public Long getFrom() {
        return from;
    }

    /**
     * Sets the Row ID in the source sheet.
     *
     * @param from the Row ID in the source sheet
     */
    public RowMapping setFrom(Long from) {
        this.from = from;
        return this;
    }

    /**
     * Gets the Row ID in the destination sheet.
     *
     * @return the Row ID in the destination sheet
     */
    public Long getTo() {
        return to;
    }

    /**
     * Sets the Row ID in the destination sheet.
     *
     * @param to Row ID in the destination sheet
     */
    public RowMapping setTo(Long to) {
        this.to = to;
        return this;
    }
}
