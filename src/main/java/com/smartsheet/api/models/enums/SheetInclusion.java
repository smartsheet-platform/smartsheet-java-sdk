package com.smartsheet.api.models.enums;

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
 * Represents specific objects that can be included in some responses.
 */
public enum SheetInclusion{
    ATTACHMENTS             ("attachments"),
    COLUMN_TYPE             ("columnType"),
    CONTACT_REFERENCES      ("contactReferences"),
    CROSS_SHEET_REFERENCES  ("crossSheetReferences"),
    DISCUSSIONS             ("discussions"),
    FILTERS                 ("filters"),
    FILTER_DEFINITIONS      ("filterDefinitions"),
    FORMAT                  ("format"),
    OBJECT_VALUE            ("objectValue"),
    OWNER_INFO              ("ownerInfo"),
    ROW_PERMALINK           ("rowPermalink"),
    ROW_WRITER_INFO         ("rowWriterInfo"), // deprecated, use writerInfo
    SOURCE                  ("source"),
    SUMMARY                 ("summary"),
    WRITER_INFO             ("writerInfo"),
    ;

    String inclusion;

    SheetInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    @Override
    public String toString() {
        return inclusion;
    }
}
