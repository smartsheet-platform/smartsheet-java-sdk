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

import java.util.List;

/**
 * Represents RowEmail object.
 */
public class RowEmail extends Email {

    @Override
    public List<Recipient> getSendTo() {
        return super.getSendTo();
    }

    @Override
    public RowEmail setSendTo(List<Recipient> sendTo) {
        super.setSendTo(sendTo);
        return this;
    }

    @Override
    public String getSubject() {
        return super.getSubject();
    }

    @Override
    public RowEmail setSubject(String subject) {
        super.setSubject(subject);
        return this;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public RowEmail setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    @Override
    public Boolean getCcMe() {
        return super.getCcMe();
    }

    @Override
    public RowEmail setCcMe(Boolean ccMe) {
        super.setCcMe(ccMe);
        return this;
    }

    /**
     * Represents IDs of the columns to be included.
     */
    private List<Long> columnIds;

    /**
     * A flag to indicate if attachments should be included in the email.
     */
    private Boolean includeAttachments;

    /**
     * A flag to indicate if discussions should be included in the email.
     */
    private Boolean includeDiscussions;

    /**
     * One of the following values: HORIZONTAL, VERTICAL
     */
    private String layout;

    /**
     * Gets the IDs of the columns to be included.
     *
     * @return the colmn ids
     */
    public List<Long> getColumnIds() {
        return columnIds;
    }

    /**
     * Sets the IDs of the columns to be included.
     *
     * @param columnIds the column ids
     */
    public RowEmail setColumnIds(List<Long> columnIds) {
        this.columnIds = columnIds;
        return this;
    }

    /**
     * Gets the flag that indicates if attachments should be included in the email.
     *
     * @return the include attachments
     */
    public Boolean getIncludeAttachments() {
        return includeAttachments;
    }

    /**
     * Sets the flag that indicates if attachments should be included in the email.
     *
     * @param includeAttachments the new include attachments
     */
    public RowEmail setIncludeAttachments(Boolean includeAttachments) {
        this.includeAttachments = includeAttachments;
        return this;
    }

    /**
     * Gets the flag that indicates if discussions should be included in the email.
     *
     * @return the include discussions
     */
    public Boolean getIncludeDiscussions() {
        return includeDiscussions;
    }

    /**
     * Sets the flag that indicates if discussions should be included in the email.
     *
     * @param includeDiscussions the new include discussions
     */
    public RowEmail setIncludeDiscussions(Boolean includeDiscussions) {
        this.includeDiscussions = includeDiscussions;
        return this;
    }

    /**
     * Gets a string containing the layout. Defaults to HORIZONTAL if multiple rows are being setn,
     * and to VERTICAL when a single row is being sent.
     *
     * @return layout
     */
    public String getLayout() { return layout; }

    /**
     * Sets the layout string
     * @param layout string containing HORIZONTAL or VERTICAL strings
     *
     * @return
     */
    public RowEmail setLayout(String layout) {
        this.layout = layout;
        return this;
    }

    /**
     * A convenience class to help create a RowEmail object with the appropriate fields.
     */
    public static class AddRowEmailBuilder {
        /**
         * A flag to indicate if attachments should be included in the email.
         */
        private Boolean includeAttachments;

        /**
         * A flag to indicate if discussions should be included in the email.
         */
        private Boolean includeDiscussions;

        /**
         * One of the following values: HORIZONTAL, VERTICAL
         */
        private String layout;

        /**
         * Gets the flag that indicates if attachments should be included in the email.
         *
         * @return the include attachments
         */
        public Boolean getIncludeAttachments() {
            return includeAttachments;
        }

        /**
         * Sets the flag that indicates if attachments should be included in the email.
         *
         * @param includeAttachments the new include attachments
         * @return the builder
         */
        public AddRowEmailBuilder setIncludeAttachments(Boolean includeAttachments) {
            this.includeAttachments = includeAttachments;
            return this;
        }

        /**
         * Gets the flag that indicates if discussions should be included in the email.
         *
         * @return the include discussions
         */
        public Boolean getIncludeDiscussions() {
            return includeDiscussions;
        }

        /**
         * Sets the flag that indicates if discussions should be included in the email.
         *
         * @param includeDiscussions the new include discussions
         * @return the builder
         */
        public AddRowEmailBuilder setIncludeDiscussions(Boolean includeDiscussions) {
            this.includeDiscussions = includeDiscussions;
            return this;
        }

        /**
         * Gets a string containing the layout. Defaults to HORIZONTAL if multiple rows are being setn,
         * and to VERTICAL when a single row is being sent.
         *
         * @return layout
         */
        public String getLayout() { return layout; }

        /**
         * Sets the layout string
         *
         * @param layout string containing HORIZONTAL or VERTICAL strings
         * @return the builder
         */
        public AddRowEmailBuilder setLayout(String layout) {
            this.layout = layout;
            return this;
        }

        /**
         * Represents the list of recipients to send to
         */
        private List<Recipient> sendTo;

        /**
         * Represents the subject.
         */
        private String subject;

        /**
         * Represents the message.
         */
        private String message;

        /**
         * Represents the CC me flag.
         */
        private Boolean ccMe;

        /**
         * Gets the list of recipients to send to
         * @return the list of recipients
         */
        public List<Recipient> getSendTo() { return sendTo; }

        /**
         * Sets the list of recipients to send to
         * @param sendTo list of recipients
         * @return the builder
         */
        public AddRowEmailBuilder setSendTo(List<Recipient> sendTo) { this.sendTo = sendTo;
            return  this;}

        /**
         * Gets the subject.
         *
         * @return the subject
         */
        public String getSubject() {
            return subject;
        }

        /**
         * Sets the subject.
         *
         * @param subject the new subject
         * @return the associated builder
         */
        public AddRowEmailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Gets the message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the message.
         *
         * @param message the new message
         * @return the builder
         */
        public AddRowEmailBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Gets the carbon copy me flag.
         *
         * @return the cc me
         */
        public Boolean getCcMe() {
            return ccMe;
        }

        /**
         * Sets the carbon copy me flag.
         *
         * @param ccMe the new cc me
         * @return the builder
         */
        public AddRowEmailBuilder setCcMe(Boolean ccMe) {
            this.ccMe = ccMe;
            return this;
        }


        /**
         * Builds the row email.
         *
         * @return the rowemail
         */
        public RowEmail build() {
            RowEmail rowEmail = new RowEmail();
            rowEmail.includeAttachments = includeAttachments;
            rowEmail.includeDiscussions = includeDiscussions;
            rowEmail.layout = layout;
            rowEmail.setSendTo(sendTo);
            rowEmail.setSubject(subject);
            rowEmail.setMessage(message);
            rowEmail.setCcMe(ccMe);
            return rowEmail;
        }

    }
}
