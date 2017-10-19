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

import java.util.Date;
import java.util.List;

/**
 * Represents the Comment object.
 */
public class Comment extends IdentifiableModel<Long> {

    /** Represents the text for the comment. */
    private String text;

    /** Represents the user that created the comment. */
    private User createdBy;

    /** Represents the date the comment was modified. */
    private Date modifiedDate;

    /** Represents the attachments for the comment. */
    private List<Attachment> attachments;

    /** Represents the discussion ID. */
    private Long discussionId;

    /** The date the comment was created. */
    private Date createdAt;

    /** The date the comment was last modified. */
    private Date modifiedAt;

    /**
     * Gets the text for the comment.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text for the comment.
     *
     * @param text the new text
     */
    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Gets user that created the comment.
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user that created the comment.
     *
     * @param createdBy the new created by
     */
    public Comment setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the date the comment was last modified.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the date the comment was last modified.
     *
     * @param modifiedDate the new modified date
     */
    public Comment setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    /**
     * Gets the comment attachments.
     *
     * @return the attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Sets the comment attachments.
     *
     * @param attachments the new attachments
     */
    public Comment setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * Gets the discussion id.
     *
     * @return the discussion id
     */
    public Long getDiscussionId() {
        return discussionId;
    }

    /**
     * Sets the discussion id.
     *
     * @param discussionId the new discussion id
     */
    public Comment setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
        return this;
    }

    /**
     * Gets the date the comment was created.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the date the comment was created.
     *
     * @param createdAt the new created at
     */
    public Comment setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Gets the date the comment was modified.
     *
     * @return the modified at
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Sets the date the comment was modified.
     *
     * @param modifiedAt the new modified at
     */
    public Comment setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * A convenience class to generate a comment with the appropriate fields for adding it to a sheet.
     */
    public static class AddCommentBuilder {

        /** The text. */
        private String text;

        /**
         * The text for the comment.
         *
         * @param text the text
         * @return the adds the comment builder
         */
        public AddCommentBuilder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Gets the text for the comment.
         * @return the text
         */
        public String getText(){
            return text;
        }

        /**
         * Builds the comment.
         *
         * @return the comment
         */
        public Comment build() {
            if(text == null){
                throw new InstantiationError("The comment text is required.");
            }

            Comment comment = new Comment();
            comment.text = text;
            return comment;
        }
    }
}
