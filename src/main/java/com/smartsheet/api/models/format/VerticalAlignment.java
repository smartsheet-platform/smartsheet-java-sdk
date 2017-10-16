package com.smartsheet.api.models.format;
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
 * @author kskeem
 * An enumeration representing the available vertical alignments within Smartsheet.
 */
public enum VerticalAlignment {
    DEFAULT_ALIGNMENT   (""),
    TOP                 ("top"),
    MIDDLE              ("middle"),
    BOTTOM              ("bottom"),
    ;
    private final String position;
    private VerticalAlignment(String position) {
        this.position = position;
    }

    /**
     * The default setting when the {@link Format} for {@link VerticalAlignment} is null;
     */
    public static final VerticalAlignment DEFAULT = DEFAULT_ALIGNMENT;

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }
}