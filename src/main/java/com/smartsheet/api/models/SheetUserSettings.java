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
     * Identifies if the user has critical path enabled.
     */
    private Boolean criticalPathEnabled;

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
    public void setCriticalPathEnabled(Boolean criticalPathEnabled) {
        this.criticalPathEnabled = criticalPathEnabled;
    }
}
