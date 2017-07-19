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

import java.util.List;
/**
 * Represents the multi row email object.
 */
public class MultiRowEmail extends RowEmail {

    /**
     * Represents IDs of rows to be included.
     */
    private List<Long> rowIds;

    /**
     * Gets the IDs of rows to be included
     * @return the row ids
     */
    public List<Long> getRowIds() {
        return rowIds;
    }

    /**
     * Sets the IDs of rows to be included
     * @param rowIds list of row ids
     */
    public MultiRowEmail setRowIds(List<Long> rowIds) {
        this.rowIds = rowIds;
        return this;
    }

    /**
     * A convenience class to help create a MultiRowEmail object with the appropriate fields.
     */
    public static class AddMultiRowEmailBuilder {

        private List<Recipient> sendTo;
        private String subject;
        private String message;
        private Boolean ccMe;
        private List<Long> rowIds;
        private Boolean includeAttachments;
        private Boolean includeDiscussions;
        private List<Long> columnIds;

        /**
         * Sets the list of recipients to send to
         * @param sendTo list of recipients
         * @return the builder
         */
        public AddMultiRowEmailBuilder setSendTo(List<Recipient> sendTo) { this.sendTo = sendTo;
        return this;
        }

        /**
         * Sets the subject.
         *
         * @param subject the new subject
         * @return the builder
         */
        public AddMultiRowEmailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * Sets the message.
         *
         * @param message the new message
         * @return the builder
         */
        public AddMultiRowEmailBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Sets the carbon copy me flag.
         *
         * @param ccMe the new cc me
         * @return the builder
         */
        public AddMultiRowEmailBuilder setCcMe(Boolean ccMe) {
            this.ccMe = ccMe;             return this;
        }

        /**
         * Sets the IDs of rows to be included
         * @param rowIds list of row ids
         * @return the builder
         */
        public AddMultiRowEmailBuilder setRowIds(List<Long> rowIds) {
            this.rowIds = rowIds;             return this;
        }

        /**
         * Sets the IDs of the columns to be included.
         *
         * @param columnIds the column ids
         * @return the builder
         */
        public AddMultiRowEmailBuilder setColumnIds(List<Long> columnIds) {
            this.columnIds = columnIds;             return this;
        }

        /**
         * Sets the flag that indicates if attachments should be included in the email.
         *
         * @param includeAttachments the new include attachments
         * @return the builder
         */
        public AddMultiRowEmailBuilder setIncludeAttachments(Boolean includeAttachments) {
            this.includeAttachments = includeAttachments;             return this;
        }

        /**
         * Sets the flag that indicates if discussions should be included in the email.
         *
         * @param includeDiscussions the new include discussions
         * @return the builder
         */
        public AddMultiRowEmailBuilder setIncludeDiscussions(Boolean includeDiscussions) {
            this.includeDiscussions = includeDiscussions;             return this;
        }

        /**
         * Builds the multi row email object
         * @return the multi row email object
         */
        public MultiRowEmail build() {
            MultiRowEmail multiRowEmail = new MultiRowEmail();
            multiRowEmail.setSendTo(sendTo);
            multiRowEmail.setSubject(subject);
            multiRowEmail.setMessage(message);
            multiRowEmail.setCcMe(ccMe);
            multiRowEmail.setRowIds(rowIds);
            multiRowEmail.setColumnIds(columnIds);
            multiRowEmail.setIncludeAttachments(includeAttachments);
            multiRowEmail.setIncludeDiscussions(includeDiscussions);
            return multiRowEmail;
        }

    }


}
