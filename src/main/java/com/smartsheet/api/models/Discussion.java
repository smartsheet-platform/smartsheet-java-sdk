package com.smartsheet.api.models;

import java.util.Date;
import java.util.List;

/**
 * Represents the Discussion object.
 */
public class Discussion {
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
}
