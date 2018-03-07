package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

public class SheetUserSettings {

    /**
     * The ID of the filter currently applied to the sheet
     */
    private Long appliedSheetFilterId;

    /**
     * Identifies if the user has critical path enabled.
     */
    private Boolean criticalPathEnabled;

    /**
     * Identifies if the user has display summary tasks enabled.
     */
    private Boolean displaySummaryTasks;

    /**
     * Get the applied sheet filter ID
     *
     * @return the sheet filter ID
     */
    public Long getAppliedSheetFilterId() {
        return appliedSheetFilterId;
    }

    /**
     * Sets the applied sheet filter ID.
     *
     * @param appliedSheetFilterId the sheet filter ID
     */
    public SheetUserSettings setAppliedSheetFilterId(Long appliedSheetFilterId) {
        this.appliedSheetFilterId = appliedSheetFilterId;
        return this;
    }

    /**
     * True if the user has critical path enabled.
     *
     * @return criticalPathEnabled
     */
    public Boolean isCriticalPathEnabled() {
        return criticalPathEnabled;
    }

    /**
     * Sets the value for critical path enabled.
     *
     * @param criticalPathEnabled if the user has critical path enabled
     */
    public SheetUserSettings setCriticalPathEnabled(Boolean criticalPathEnabled) {
        this.criticalPathEnabled = criticalPathEnabled;
        return this;
    }

    /**
     * True if the user has display summary tasks enabled
     * 
     * @return displaySummaryTasks
     */
    public Boolean isDisplaySummaryTasksEnabled() {
        return displaySummaryTasks;
    }
    
    /**
     * Sets the value for display summary tasks.
     *
     * @param displaySummaryTasks
     */
    public SheetUserSettings setDisplaySummaryTasks(Boolean displaySummaryTasks) {
        this.displaySummaryTasks = displaySummaryTasks;
        return this;
    }
    
    /**
     * The Class AddUserSettingsBuilder.
     */
    public static class AddUserSettingsBuilder {
        /**
         * Identifies if the user has critical path enabled.
         */
        private Boolean criticalPathEnabled;
        
        /**
         * Identifies if the user has display summary tasks enabled.
         */
        private Boolean displaySummaryTasks;

        /**
         * True if the user has critical path enabled.
         *
         * @return criticalPathEnabled
         */
        public Boolean isCriticalPathEnabled() {
            return criticalPathEnabled;
        }

        /**
         * True if the user has display summary tasks enabled
         * 
         * @return displaySummaryTasks;
         */
        public Boolean isDisplaySummaryTasksEnabled() {
            return displaySummaryTasks;
        }
        
        /**
         * Sets the value for critical path enabled.
         *
         * @param criticalPathEnabled if the user has critical path enabled
         * @return the builder
         */
        public AddUserSettingsBuilder setCriticalPathEnabled(Boolean criticalPathEnabled) {
            this.criticalPathEnabled = criticalPathEnabled;
            return this;
        }

        /**
         * Sets the value for display summary tasks.
         * 
         * @param displaySummaryTasks
         * @return the builder
         */
        public AddUserSettingsBuilder setDisplaySummaryTasks(Boolean displaySummaryTasks) {
            this.displaySummaryTasks = displaySummaryTasks;
            return this;
        }
        
        public SheetUserSettings build() {
            SheetUserSettings sheetUserSettings = new SheetUserSettings();
            sheetUserSettings.criticalPathEnabled = criticalPathEnabled;
            sheetUserSettings.displaySummaryTasks = displaySummaryTasks;
            return sheetUserSettings;
        }
    }
}
