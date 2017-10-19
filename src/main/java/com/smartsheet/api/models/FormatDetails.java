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


import com.smartsheet.api.models.enums.PaperSize;

/**
 * Represents the format details when generating a digital copy (PDF/EXCEL) of a sheet.
 */
public class FormatDetails {
    /**
     * Represents the paper size.
     */
    private PaperSize paperSize;

    /**
     * Gets the paper size.
     *
     * @return the paper size
     */
    public PaperSize getPaperSize() {
        return paperSize;
    }

    /**
     * Sets the paper size.
     *
     * @param paperSize the new paper size
     */
    public FormatDetails setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
        return this;
    }
}
