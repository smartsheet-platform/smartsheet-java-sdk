package com.smartsheet.api.models;

import com.smartsheet.api.models.enums.DayOfWeek;

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

public class ProjectSettings {

    /**
     * Working days of a week for a project sheet.
     */
    private List<DayOfWeek> workingDays;

    /**
     * Non-working days for a project sheet. Must be an array of strings that are valid ISO-8601 dates ('YYYY-MM-DD’)
     */
    private List<String> nonWorkingDays;

    /**
     * Length of a workday in hours for a project sheet. Valid value must be above or equal to 1 hour, and less than or equal to 24 hours.
     */
    private Float lengthOfDay;


    /**
     * Get working days of a week for a project sheet.
     *
     * @return the list of working days
     */
    public List<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    /**
     * Set the working days of a week for a project sheet.
     *
     * @param workingDays the list of {@link DayOfWeek}s to set
     */
    public ProjectSettings setWorkingDays(List<DayOfWeek> workingDays) {
        this.workingDays = workingDays;
        return this;
    }

    /**
     * Get non-working days of a week for a project sheet.
     *
     * @return the list of non-working days
     */
    public List<String> getNonWorkingDays() {
        return nonWorkingDays;
    }

    /**
     * Set the non-working days of a week for a project sheet.
     *
     * @param nonWorkingDays the list of days to set. Must be an array of strings that are valid ISO-8601 dates ('YYYY-MM-DD’)
     */
    public ProjectSettings setNonWorkingDays(List<String> nonWorkingDays) {
        this.nonWorkingDays = nonWorkingDays;
        return this;
    }

     /**
     * Get length of workday for a project sheet, in hours.
     *
     * @return length of day
     */
    public Float getLengthOfDay() {
        return lengthOfDay;
    }

    /**
     * Set length of a workday for a project sheet in hours. Valid value must be above or equal to 1 hour, and less than or equal to 24 hours.
     *
     * @param lengthOfDay
     */
    public ProjectSettings setLengthOfDay(Float lengthOfDay) {
        this.lengthOfDay = lengthOfDay;
        return this;
    }

}
