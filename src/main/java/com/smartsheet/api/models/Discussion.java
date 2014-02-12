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
 * Represents the Discussion object.
 */
public class Discussion extends IdentifiableModel {
	/** Represents the title for the discussion. */
	private String title;

	/** Represents the comments for the discussion. */
	private List<Comment> comments;

	/** Represents the comment attachments. */
	private List<Attachment> commentAttachments;

	/** Represents the date a comment was last added to a discussion. */
	private Date lastCommentedAt;

	/** Represents the last user that left a comment in the discussion. */
	private User lastCommentedUser;

	private String accessLevel;

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
	public void setTitle(String title) {
		this.title = title;
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
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	public void setCommentAttachments(List<Attachment> commentAttachments) {
		this.commentAttachments = commentAttachments;
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
	public void setLastCommentedAt(Date lastCommentedAt) {
		this.lastCommentedAt = lastCommentedAt;
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
	public void setLastCommentedUser(User lastCommentedUser) {
		this.lastCommentedUser = lastCommentedUser;
	}

	
	//QUESTION: does a discussion have an access level. Take a look at the unit test to see how these are used.
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
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * A convenience class to help generate discussion object with the appropriate fields for adding a discussion to 
	 * a sheet.
	 */
	public static class CreateDiscussionBuilder {
		private String title;
		private List<Comment> comments;

		/**
		 * Sets the title for the dicussion.
		 *
		 * @param title the title
		 * @return the creates the discussion builder
		 */
		public CreateDiscussionBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Sets the comments for the dicussion.
		 *
		 * @param comments the comments
		 * @return the creates the discussion builder
		 */
		public CreateDiscussionBuilder setComments(List<Comment> comments) {
			this.comments = comments;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the discussion
		 */
		public Discussion build() {
			if(title == null || comments == null){
				throw new InstantiationError("A title and comment is required.");
			}
			
			Discussion discussion = new Discussion();
			discussion.title = title;
			discussion.comments = comments;
			return discussion;
		}
	}
}
