package com.smartsheet.api.models;

import java.util.Date;

/**
 * Represents the Attachment object.
 */
public class Attachment {

	/**
	 * Represents the URL.
	 */
	private String url;

	/**
	 * Represents the URL expiration time.
	 */
	private Long urlExpiresInMillis;

	/**
	 * Represents the attachment type.
	 */
	private AttachmentType attachmentType;

	/**
	 * Represents the attachment sub type.
	 */
	private AttachmentSubType attachmentSubType;

	/**
	 * Represents the creation timestamp.
	 */
	private Date createdAt;

	/**
	 * Represents the MIME type
	 */
	private String mimeType;

	/**
	 * Represents the parent type.
	 */
	private AttachmentParentType parentType;

	/**
	 * Represents the parent ID
	 */
	private Long parentId;

	/**
	 * Represents the attachment size.
	 */
	private Long sizeInKb;
	
	public Attachment(String url, Long urlExpiresInMillis, AttachmentType attachmentType, 
			AttachmentSubType attachmentSubType, Date createdAt, String mimeType, AttachmentParentType parentType,
			Long parentId, Long sizeInKb){
		this.url = url;
		this.urlExpiresInMillis = urlExpiresInMillis;
		this.attachmentType = attachmentType;
		this.attachmentSubType = attachmentSubType;
		this.createdAt = createdAt;
		this.mimeType = mimeType;
		this.parentId = parentId;
		this.sizeInKb = sizeInKb;
		this.parentType = parentType;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getUrlExpiresInMillis() {
		return urlExpiresInMillis;
	}

	public void setUrlExpiresInMillis(Long urlExpiresInMillis) {
		this.urlExpiresInMillis = urlExpiresInMillis;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public AttachmentSubType getAttachmentSubType() {
		return attachmentSubType;
	}

	public void setAttachmentSubType(AttachmentSubType attachmentSubType) {
		this.attachmentSubType = attachmentSubType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public AttachmentParentType getParentType() {
		return parentType;
	}

	public void setParentType(AttachmentParentType parentType) {
		this.parentType = parentType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSizeInKb() {
		return sizeInKb;
	}

	public void setSizeInKb(Long sizeInKb) {
		this.sizeInKb = sizeInKb;
	}
}
