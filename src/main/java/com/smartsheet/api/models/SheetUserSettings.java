package com.smartsheet.api.models;

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
