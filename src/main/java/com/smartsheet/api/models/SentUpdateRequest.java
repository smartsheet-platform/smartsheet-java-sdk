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
package com.smartsheet.api.models;

import com.smartsheet.api.models.enums.UpdateRequestStatus;

import java.util.Date;
import java.util.List;

public class SentUpdateRequest extends IdentifiableModel<Long> {

    /**
     * Id of the originating update request.
     */
    private Long updateRequestId;

    /**
     * The date and time for when the sent update request was sent to the recipient
     */
    private Date sentAt;

    /**
     * User object containing name and email of the sender
     */
    private User sentBy;

    /**
     * The status of the sent update request
     */
    private UpdateRequestStatus status;

    /**
     * Ids of rows for which update is requested.
     */
    private List<Long> rowIds;

    /**
     * Ids of columns included in the request.
     */
    private List<Long> columnIds;

    /**
     * A flag to indicate whether or not the attachments were include in the email.
     */
    private Boolean includeAttachments;

    /**
     * A flag to indicate whether or not the discussions were include in the email.
     */
    private Boolean includeDiscussions;

    /**
     * Recipient object
     */
    private Recipient sentTo;

    /**
     * The subject of the email
     */
    private String subject;

    /**
     * The message of the email
     */
    private String message;

    /**
     * Get the Id of the originating update request.
     *
     * @return updateRequestId
     */
    public Long getUpdateRequestId() {
        return updateRequestId;
    }

    /**
     * Set the Id of the originating update request.
     *
     * @param updateRequestId
     */
    public SentUpdateRequest setUpdateRequestId(Long updateRequestId) {
        this.updateRequestId = updateRequestId;
        return this;
    }

    /**
     * Get the date and time for when the update request was sent
     *
     * @return sentAt
     */
    public Date getSentAt() {
        return sentAt;
    }

    /**
     * Set the date and time for when the update request was sent
     * @param sentAt
     */
    public SentUpdateRequest setSentAt(Date sentAt) {
        this.sentAt = sentAt;
        return this;
    }

    /**
     * Get the User object containing the name and email of the sender.
     *
     * @return sentBy
     */
    public User getSentBy() {
        return sentBy;
    }

    /**
     * Set the User object containing the name and email of the sender.
     *
     * @param sentBy
     */
    public SentUpdateRequest setSentBy(User sentBy) {
        this.sentBy = sentBy;
        return this;
    }

    /**
     * Get the status of the sent update request.
     *
     * @return status
     */
    public UpdateRequestStatus getStatus() {
        return status;
    }

    /**
     * Set the status of the sent update request.
     *
     * @param status
     */
    public SentUpdateRequest setStatus(UpdateRequestStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the Ids of the rows for which the update is requested.
     *
     * @return rowIds
     */
    public List<Long> getRowIds() {
        return rowIds;
    }

    /**
     * Set the Ids of the rows for which the update is request.
     *
     * @param rowIds
     */
    public SentUpdateRequest setRowIds(List<Long> rowIds) {
        this.rowIds = rowIds;
        return this;
    }

    /**
     * Get the Ids of the columns included in the request.
     *
     * @return columnIds
     */
    public List<Long> getColumnIds() {
        return columnIds;
    }

    /**
     * Set the Ids of the columns included in the request.
     *
     * @param columnIds
     */
    public SentUpdateRequest setColumnIds(List<Long> columnIds) {
        this.columnIds = columnIds;
        return this;
    }

    /**
     * Gets the flag that indicates if attachments should be included in the email.
     *
     * @return includeAttachments
     */
    public Boolean getIncludeAttachments() {
        return includeAttachments;
    }

    /**
     * Sets the flag that indicates if attachments should be included in the email.
     *
     * @param includeAttachments
     */
    public SentUpdateRequest setIncludeAttachments(Boolean includeAttachments) {
        this.includeAttachments = includeAttachments;
        return this;
    }

    /**
     * Gets the flag that indicates if discussions should be included in the email.
     *
     * @return includeDiscussions
     */
    public Boolean getIncludeDiscussions() {
        return includeDiscussions;
    }

    /**
     * Sets the flag that indicates if discussions should be included in the email.
     *
     * @param includeDiscussions
     */
    public SentUpdateRequest setIncludeDiscussions(Boolean includeDiscussions) {
        this.includeDiscussions = includeDiscussions;
        return this;
    }

    /**
     * Gets the recipient
     *
     * @return sentTo
     */
    public Recipient getSentTo() {
        return sentTo;
    }

    /**
     * Sets the recipient
     *
     * @param sentTo
     */
    public SentUpdateRequest setSentTo(Recipient sentTo) {
        this.sentTo = sentTo;
        return this;
    }

    /**
     * Gets the subject
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject
     *
     * @param subject
     */
    public SentUpdateRequest setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Gets the email message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the email message.
     *
     * @param message
     */
    public SentUpdateRequest setMessage(String message) {
        this.message = message;
        return this;
    }
}

