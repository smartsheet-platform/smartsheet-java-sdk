package com.smartsheet.api.models;

import java.util.Date;
import java.util.List;

/**
 * Represents the Comment object.
 */
public class Comment {
	/**
	 * Represents the text.
	 */
	private String text;

	/**
	 * Represents the created by user.
	 */
	private User createdBy;

	/**
	 * Represents the modified date timestamp.
	 */
	private Date modifiedDate;

	/**
	 * Represents the attachments.
	 */
	private List<Attachment> attachments;

	/**
	 * Represents the discussion ID.
	 */
	private Long discussionId;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Long getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Long discussionId) {
		this.discussionId = discussionId;
	}
}
