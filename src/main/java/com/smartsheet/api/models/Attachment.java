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

/**
 * Represents the Attachment object.
 */
/**
 * @author brett
 * 
 */
public class Attachment extends NamedModel {

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

	/** Represents the MIME type. */
	private String mimeType;

	/**
	 * Represents the parent type.
	 */
	private AttachmentParentType parentType;

	/** Represents the parent ID. */
	private Long parentId;

	/**
	 * Represents the attachment size.
	 */
	private Long sizeInKb;

	/**
	 * Gets the URL.
	 * 
	 * @return The url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the URL.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the url expires in millis.
	 * 
	 * @return the url expires in millis
	 */
	public Long getUrlExpiresInMillis() {
		return urlExpiresInMillis;
	}

	/**
	 * Sets the url expires in millis.
	 * 
	 * @param urlExpiresInMillis
	 *            the new url expires in millis
	 */
	public void setUrlExpiresInMillis(Long urlExpiresInMillis) {
		this.urlExpiresInMillis = urlExpiresInMillis;
	}

	/**
	 * Gets the attachment type.
	 * 
	 * @return the attachment type
	 */
	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	/**
	 * Sets the attachment type.
	 * 
	 * @param attachmentType
	 *            the new attachment type
	 */
	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	/**
	 * Gets the attachment sub type.
	 * 
	 * @return the attachment sub type
	 */
	public AttachmentSubType getAttachmentSubType() {
		return attachmentSubType;
	}

	/**
	 * Sets the attachment sub type.
	 * 
	 * @param attachmentSubType
	 *            the new attachment sub type
	 */
	public void setAttachmentSubType(AttachmentSubType attachmentSubType) {
		this.attachmentSubType = attachmentSubType;
	}

	/**
	 * Gets the created at.
	 * 
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 * 
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the mime type.
	 * 
	 * @return the mime type
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Sets the mime type.
	 * 
	 * @param mimeType
	 *            the new mime type
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Gets the parent type.
	 * 
	 * @return the parent type
	 */
	public AttachmentParentType getParentType() {
		return parentType;
	}

	/**
	 * Sets the parent type.
	 * 
	 * @param parentType
	 *            the new parent type
	 */
	public void setParentType(AttachmentParentType parentType) {
		this.parentType = parentType;
	}

	/**
	 * Gets the parent id.
	 * 
	 * @return the parent id
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * Sets the parent id.
	 * 
	 * @param parentId
	 *            the new parent id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the size in kb.
	 * 
	 * @return the size in kb
	 */
	public Long getSizeInKb() {
		return sizeInKb;
	}

	/**
	 * Sets the size in kb.
	 * 
	 * @param sizeInKb
	 *            the new size in kb
	 */
	public void setSizeInKb(Long sizeInKb) {
		this.sizeInKb = sizeInKb;
	}
}
