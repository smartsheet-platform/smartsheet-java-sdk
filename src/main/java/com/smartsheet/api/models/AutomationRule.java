package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.models.enums.AutomationRuleDisabledReason;
import java.util.Date;

public class AutomationRule extends NamedModel<Long> {

    /**
     * AutomationAction object containing information for this rule.
     */
    private AutomationAction action;

    /**
     * A timestamp of when the rule was originally active.
     */
    private Date createdAt;

    /**
     * A User object containing the name and email of the creator of this rule.
     */
    private User createdBy;

    /**
     * Machine-readable reason a rule is disabled.
     */
    private AutomationRuleDisabledReason disabledReason;

    /**
     * Descriptive reason rule is disabled.
     */
    private String disabledReasonText;

    /**
     * Indicates if the rule is active
     */
    private Boolean enabled;

    /**
     * A timestamp indicating when the last change was made to the rule.
     */
    private Date modifiedAt;

    /**
     * User object containing the name and email of the user who last modified this rule.
     */
    private User modifiedBy;

    /**
     * Indicates that the current user can modify this rule.
     */
    private Boolean userCanModify;

    /**
     * Gets the automation action for this rule.
     *
     * @return the automation action
     */
    public AutomationAction getAction() { return action; }

    /**
     * Sets the automation action for this rule.
     *
     * @param action the automation action
     */
    public AutomationRule setAction(AutomationAction action) {
        this.action = action;
        return this;
    }

    /**
     * Gets a timestamp of when the rule was created.
     *
     * @return the timestamp
     */
    public Date getCreatedAt() { return createdAt; }

    /**
     * Sets a timestamp for when the rule was created.
     *
     * @param createdAt the timestamp
     */
    public AutomationRule setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Gets the User object for the author of this rule.
     *
     * @return the User object
     */
    public User getCreatedBy() { return createdBy; }

    /**
     * Sets the User object for the author of this rule.
     *
     * @param createdBy the User object
     */
    public AutomationRule setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets a machine-readable reason a rule is disabled.
     *
     * @return the reason
     */
    public AutomationRuleDisabledReason getDisabledReason() { return disabledReason; }

    /**
     * Sets a machine-readable reason a rule is disabled.
     *
     * @param disabledReason the reason
     */
    public AutomationRule setDisabledReason(AutomationRuleDisabledReason disabledReason) {
        this.disabledReason = disabledReason;
        return this;
    }

    /**
     * Gets descriptive text for why this rule is disabled.
     *
     * @return the reason
     */
    public String getDisabledReasonText() { return disabledReasonText; }

    /**
     * Sets descriptive text for why this rule is disabled.
     *
     * @param disabledReasonText the reason
     */
    public AutomationRule setDisabledReasonText(String disabledReasonText) {
        this.disabledReasonText = disabledReasonText;
        return this;
    }

    /**
     * Gets flag indicating if rule is active
     *
     * @return the flag
     */
    public Boolean getEnabled() { return enabled; }

    /**
     * Sets flag indicating if rule is active
     *
     * @param enabled the flag
     */
    public AutomationRule setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Gets a timestamp for the last modification to this rule.
     *
     * @return the timestamp
     */
    public Date getModifiedAt() { return  modifiedAt; }

    /**
     * Sets a timestamp for the last modification to this rule
     *
     * @param modifiedAt the timestamp
     */
    public AutomationRule setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * Gets the User who last modified this rule.
     *
     * @return the User
     */
    public User getModifiedBy() { return modifiedBy; }

    /**
     * Sets the User who last modified this rule.
     *
     * @param modifiedBy
     */
    public AutomationRule setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    /**
     * Gets a flag indicating if the current user can modify this rule.
     *
     * @return the flag
     */
    public Boolean getUserCanModify() { return userCanModify; }

    /**
     * Sets a flag indicating if the current user can modify this rule.
     *
     * @param userCanModify the flag
     */
    public AutomationRule setUserCanModify(Boolean userCanModify) {
        this.userCanModify = userCanModify;
        return this;
    }
}
