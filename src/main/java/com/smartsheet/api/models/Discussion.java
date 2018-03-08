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

import com.smartsheet.api.models.enums.ParentType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents the Discussion object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504767-using-discussions">Help Using Discussions</a>
 */
public class Discussion extends IdentifiableModel<Long> {
    /** Represents the title for the discussion. */
    private String title;

    /** Represents the comments for the discussion. */
    private List<Comment> comments;

    /** Represents the comment for the discussion (outbound only - singular "comment") */
    private Comment comment;

    /** Represents the comment attachments. */
    private List<Attachment> commentAttachments;

    /** The number of comments in the discussion. */
    private Integer commentCount;

    /** Represents the date a comment was last added to a discussion. */
    private Date lastCommentedAt;

    /** Represents the last user that left a comment in the discussion. */
    private User lastCommentedUser;

    /** Users permission on the Discussion */
    private String accessLevel;

    /** Represents ID of the directly associated row or sheet. */
    private Long parentId;

    /** Represents the “SHEET” or “ROW”: present only when the direct association is not clear. */
    private ParentType parentType;

    /** Represents the User object containing name and email of the creator of the Discussion. */
    private User createdBy;

    /** Represents the status of the Discussion. */
    private Boolean readOnly;

    /**
     * Gets the read only status
     *
     * @return the read only status
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * Sets the read only status.
     *
     * @param readOnly the read only status
     */
    public Discussion setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * Gets the created by
     *
     * @return the created by
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the created by
     */
    public Discussion setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the title for the discussion.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the discussion.
     *
     * @param title the new title
     */
    public Discussion setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets the comments for the discussion.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the comments for the discussion.
     *
     * @param comments the new comments
     */
    public Discussion setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    /**
     * Gets the comment for the discussion.
     *
     * @return the comment
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * Sets the comment for the discussion (outbound only, i.e. POST - will otherwise be null).
     *
     * @param comment the new comment
     */
    public Discussion setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Gets the comment attachments.
     *
     * @return the comment attachments
     */
    public List<Attachment> getCommentAttachments() {
        return commentAttachments;
    }

    /**
     * Sets the comment attachments.
     *
     * @param commentAttachments the new comment attachments
     */
    public Discussion setCommentAttachments(List<Attachment> commentAttachments) {
        this.commentAttachments = commentAttachments;
        return this;
    }

    /**
     * Gets the discussion comment count
     *
     * @return the comment count
     */
    public Integer getCommentCount() { return commentCount; }

    /**
     * Sets the discussion comment count
     *
     * @param commentCount the new comment count
     * @return the Discussion
     */
    public Discussion setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    /**
     * Gets the date a comment was last added to a discussion..
     *
     * @return the last commented at
     */
    public Date getLastCommentedAt() {
        return lastCommentedAt;
    }

    /**
     * Sets the date a comment was last added to a discussion.
     *
     * @param lastCommentedAt the new last commented at
     */
    public Discussion setLastCommentedAt(Date lastCommentedAt) {
        this.lastCommentedAt = lastCommentedAt;
        return this;
    }

    /**
     * Gets the user that last commented in the discussion.
     *
     * @return the last commented user
     */
    public User getLastCommentedUser() {
        return lastCommentedUser;
    }

    /**
     * Sets the user that last commented in the discussion.
     *
     * @param lastCommentedUser the new last commented user
     */
    public Discussion setLastCommentedUser(User lastCommentedUser) {
        this.lastCommentedUser = lastCommentedUser;
        return this;
    }

    /**
     * Gets the access level.
     *
     * @return the access level
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets the access level.
     *
     * @param accessLevel the new access level
     */
    public Discussion setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    /**
     * Gets the ID of the directly associated row or sheet
     *
     * @return the parent ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the ID of the directly associated row or sheet
     *
     * @param parentId the new access level
     */
    public Discussion setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * Gets the type to row or sheet
     *
     * @return the parent type
     */
    public ParentType getParentType() {
        return parentType;
    }

    /**
     * Sets the type to row or sheet
     *
     * @param parentType the new access level
     */
    public Discussion setParentType(ParentType parentType) {
        this.parentType = parentType;
        return this;
    }

    /**
     * A convenience class to help generate discussion object with the appropriate fields for adding a discussion to
     * a sheet.
     */
    public static class CreateDiscussionBuilder {
        private String title;
        private Comment comment;

        /**
         * Sets the title for the discussion.
         *
         * @param title the title
         * @return the creates the discussion builder
         */
        public CreateDiscussionBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the comments for the discussion.
         *
         * @param comment the comments
         * @return the creates the discussion builder
         */
        public CreateDiscussionBuilder setComment(Comment comment) {
            this.comment = comment;
            return this;
        }

        /**
         * Gets the title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Gets the comments.
         *
         * @return the comments
         */
        public Comment getComment() {
            return comment;
        }

        /**
         * Builds the.
         *
         * @return the discussion
         */
        public Discussion build() {
            if(title == null || comment == null){
                throw new InstantiationError("A title and comment is required.");
            }

            Discussion discussion = new Discussion();
            discussion.title = title;
            discussion.comment = comment;
            return discussion;
        }
    }
}
