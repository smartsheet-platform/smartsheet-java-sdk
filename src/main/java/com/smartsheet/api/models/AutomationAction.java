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

import com.smartsheet.api.models.enums.AutomationActionFrequency;
import com.smartsheet.api.models.enums.AutomationActionType;
import java.util.List;

public class AutomationAction {

    /**
     * The frequency to apply this automation action
     */
    private AutomationActionFrequency frequency;

    /**
     * Include all columns in email contents
     */
    private Boolean includeAllColumns;

    /**
     * include attachments in email
     */
    private Boolean includeAttachments;

    /**
     * include discussions in email
     */
    private Boolean includeDiscussions;

    /**
     * specifies which columns to include in message
     */
    private List<Long> includedColumnIds;

    /**
     * Email body
     */
    private String message;

    /**
     * notifications are sent to all users shared to the sheet
     */
    private Boolean notifyAllSharedUsers;

    /**
     * List of column ids from which to collect email recipients
     */
    private List<Long> recipientColumnIds;

    /**
     * List of Recipients
     */
    private List<Recipient> recipients;

    /**
     * Email subject line
     */
    private String subject;

    /**
     * AutomationActionType
     */
    private AutomationActionType type;

    /**
     * Gets the automation action frequency
     *
     * @return the automation action frequency
     */
    public AutomationActionFrequency getFrequency() { return frequency; }

    /**
     * Sets the automation action frequency
     *
     * @param frequency the automation action frequency
     */
    public AutomationAction setFrequency(AutomationActionFrequency frequency) {
        this.frequency = frequency;
        return this;
    }

    /**
     * Gets the flag indicating if all columns in the sheet should be included with the email
     *
     * @return includeAllColumns flag
     */
    public Boolean getIncludeAllColumns() { return includeAllColumns; }

    /**
     * Sets the flag indicating if all columns in the sheet should be included with the email
     *
     * @param includeAllColumns includeAllColumns flag
     */
    public AutomationAction setIncludeAllColumns(Boolean includeAllColumns) {
        this.includeAllColumns = includeAllColumns;
        return this;
    }

    /**
     * Gets the flag indicating if attachments should be included with the email
     *
     * @return the includeAttachments flag
     */
    public Boolean getIncludeAttachments() { return includeAttachments; }

    /**
     * Sets the flag indicating if attachments should be included with the email
     *
     * @param includeAttachments the includeAttachments flag
     */
    public AutomationAction setIncludeAttachments(Boolean includeAttachments) {
        this.includeAttachments = includeAttachments;
        return this;
    }

    /**
     * Gets the flag indicating if discussions should be included with the email
     *
     * @return the includeDiscussions flag
     */
    public Boolean getIncludeDiscussions() { return includeDiscussions; }

    /**
     * Sets the flag indicating if discussions should be included with the email
     *
     * @param includeDiscussions the includeDiscussions flag
     */
    public AutomationAction setIncludeDiscussions(Boolean includeDiscussions) {
        this.includeDiscussions = includeDiscussions;
        return this;
    }

    /**
     * Gets the list of included columns
     *
     * @return the list of included columns
     */
    public List<Long> getIncludedColumnIds() { return includedColumnIds; }

    /**
     * Sets the list of included columns
     *
     * @param includedColumnIds the list of included columns
     */
    public AutomationAction setIncludedColumnIds(List<Long> includedColumnIds) {
        this.includedColumnIds = includedColumnIds;
        return this;
    }

    /**
     * Gets the email body
     *
     * @return the email body
     */
    public String getMessage() { return message; }

    /**
     * Sets the email body
     *
     * @param message the email body
     */
    public AutomationAction setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Gets the flag indicating if notification should be sent to all shared users
     *
     * @return the notifyAllSharedUsers flag
     */
    public Boolean getNotifyAllSharedUsers() { return notifyAllSharedUsers; }

    /**
     * Sets the flag indicating if notification should be sent to all shared users
     *
     * @param notifyAllSharedUsers the notifyAllSharedUsers flag
     */
    public AutomationAction setNotifyAllSharedUsers(Boolean notifyAllSharedUsers) {
        this.notifyAllSharedUsers = notifyAllSharedUsers;
        return this;
    }

    /**
     * Gets a list of columns from which to collect email recipients
     *
     * @return the list of column IDs
     */
    public List<Long> getRecipientColumnIds() { return recipientColumnIds; }

    /**
     * Sets a list of columns from which to collect email recipients
     *
     * @param recipientColumnIds the list of column IDs
     */
    public AutomationAction setRecipientColumnIds(List<Long> recipientColumnIds) {
        this.recipientColumnIds = recipientColumnIds;
        return this;
    }

    /**
     * Gets the list of Recipients
     *
     * @return the list of Recipients
     */
    public List<Recipient> getRecipients() { return recipients; }

    /**
     * Sets the list of Recipients
     *
     * @param recipients the list of Recipients
     */
    public AutomationAction setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
        return this;
    }

    /**
     * Gets the email subject line
     *
     * @return the email subject line
     */
    public String getSubject() { return subject; }

    /**
     * Sets the email subject line
     *
     * @param subject the email subject line
     */
    public AutomationAction setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Gets the automation action type
     *
     * @return the action type
     */
    public AutomationActionType getType() { return type;}

    /**
     * Sets the automation action type
     *
     * @param type the action type
     */
    public AutomationAction setType(AutomationActionType type) {
        this.type = type;
        return this;
    }
}
