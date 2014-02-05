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
	/**
	 * Represents the title.
	 */
	private String title;

	/**
	 * Represents the comments.
	 */
	private List<Comment> comments;

	/**
	 * Represents the comment attachments.
	 */
	private List<Attachment> commentAttachments;

	/**
	 * Represents the last commented timestamp.
	 */
	private Date lastCommentedAt;

	/**
	 * Represents the last commented user.
	 */
	private User lastCommentedUser;
	
	private String accessLevel;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Attachment> getCommentAttachments() {
		return commentAttachments;
	}

	public void setCommentAttachments(List<Attachment> commentAttachments) {
		this.commentAttachments = commentAttachments;
	}

	public Date getLastCommentedAt() {
		return lastCommentedAt;
	}

	public void setLastCommentedAt(Date lastCommentedAt) {
		this.lastCommentedAt = lastCommentedAt;
	}

	public User getLastCommentedUser() {
		return lastCommentedUser;
	}

	public void setLastCommentedUser(User lastCommentedUser) {
		this.lastCommentedUser = lastCommentedUser;
	}
	
	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
}
