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
 * An enumeration representing the available font sizes within Smartsheet.
 */
public enum FontSize {
    PT_8    (8),
    PT_9    (9),
    PT_10   (10),
    PT_12   (12),
    PT_14   (14),
    PT_16   (16),
    PT_18   (18),
    PT_20   (20),
    PT_24   (24),
    PT_28   (28),
    PT_32   (32),
    PT_36   (36),
    ;
    private final int pt;

    /**
     * The default setting when the {@link Format} for {@link FontSize} is null;
     */
    public static final FontSize DEFAULT = PT_10;

    FontSize (int pt) {
        this.pt = pt;
    }

    /**
     * @return the pt
     */
    public int getPt() {
        return pt;
    }
}