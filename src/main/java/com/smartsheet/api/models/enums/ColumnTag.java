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
 * Represents the tags to indicate a special column.
 */
public enum ColumnTag {
    /** Represents CALENDAR_START_DATE tag. */
    CALENDAR_START_DATE,

    /** Represents CALENDAR_END_DATE tag. */
    CALENDAR_END_DATE,

    /** Represents GANTT_START_DATE tag. */
    GANTT_START_DATE,

    /** Represents GANTT_END_DATE tag. */
    GANTT_END_DATE,

    /** Represents GANTT_PERCENT_COMPLETE tag. */
    GANTT_PERCENT_COMPLETE,

    /** Represents GANTT_DISPLAY_LABEL tag. */
    GANTT_DISPLAY_LABEL,

    /** Represents GANTT_PREDECESSOR tag. */
    GANTT_PREDECESSOR,

    /** Represents GANTT_DURATION tag. */
    GANTT_DURATION,
    
    /** Represents GANTT_ASSIGNED_RESOURCE. */
    GANTT_ASSIGNED_RESOURCE,

    /** used for the allocation percentage for the resource management feature. */
    GANTT_ALLOCATION;
}
