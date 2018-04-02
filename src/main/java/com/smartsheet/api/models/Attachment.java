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

import com.smartsheet.api.models.enums.AttachmentParentType;
import com.smartsheet.api.models.enums.AttachmentSubType;
import com.smartsheet.api.models.enums.AttachmentType;

import java.util.Date;

/**
 * Represents the Attachment object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/518408-uploading-attachments">Help Uploading 
 * Attachments</a>
 */
public class Attachment extends NamedModel<Long> {

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
     * Represents the MIME type.
     */
    private String mimeType;

    /**
     * Represents the parent type.
     */
    private AttachmentParentType parentType;

    /**
     * Represents the parent ID.
     */
    private Long parentId;

    /**
     * Represents the attachment size.
     */
    private Long sizeInKb;

    /**
     * The user who created the attachment.
     */
    private User createdBy;

    /**
     * Represents the attachment description
     */
    private String description;

    /**
     * Provide an 'override' of setName (returns Attachment not NamedModel)
     *
     * @param name the new name
     */
    public Attachment setName(String name){
        super.setName(name);
        return this;
    }

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
     * @param url the new url
     */
    public Attachment setUrl(String url) {
        this.url = url;
        return this;
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
    public Attachment setUrlExpiresInMillis(Long urlExpiresInMillis) {
        this.urlExpiresInMillis = urlExpiresInMillis;
        return this;
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
    public Attachment setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
        return this;
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
    public Attachment setAttachmentSubType(AttachmentSubType attachmentSubType) {
        this.attachmentSubType = attachmentSubType;
        return this;
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
    public Attachment setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
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
    public Attachment setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
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
    public Attachment setParentType(AttachmentParentType parentType) {
        this.parentType = parentType;
        return this;
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
    public Attachment setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
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
    public Attachment setSizeInKb(Long sizeInKb) {
        this.sizeInKb = sizeInKb;
        return this;
    }

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public Attachment setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the attachment description.
     *
     * @return the attachment description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the attachment description.
     *
     * @param description the description
     *
     */
    public Attachment setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * A convenience class for quickly creating a List of cells to update.
     */
    public static class CreateAttachmentBuilder {

        /**
         * Represents the URL.
         */
        private String url;

        /**
         * Represents the attachment type.
         */
        private AttachmentType attachmentType;

        /**
         * Represents the attachment sub type.
         */
        private AttachmentSubType attachmentSubType;

        /**
         * Represents the attachment description
         */
        private String description;

        /**
         * Represents the attachment name
         */
        private String name;

        /**
         * Gets the name.
         *
         * @return The name.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name.
         *
         * @param name the new name
         * @return the builder object
         *
         */
        public CreateAttachmentBuilder setName(String name) {
            this.name = name;
            return this;
        }

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
         * @param url the new url
         * @return the builder object
         *
         */
        public CreateAttachmentBuilder setUrl(String url) {
            this.url = url;
            return this;
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
         * @return the builder object
         */
        public CreateAttachmentBuilder setAttachmentType(AttachmentType attachmentType) {
            this.attachmentType = attachmentType;
            return this;
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
         * @return the builder object
         */
        public CreateAttachmentBuilder setAttachmentSubType(AttachmentSubType attachmentSubType) {
            this.attachmentSubType = attachmentSubType;
            return this;
        }

        /**
         * Gets the attachment description.
         *
         * @return the attachment description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the attachment description.
         *
         * @param description the description
         * @return the builder object
         */
        public CreateAttachmentBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Returns the list of cells.
         *
         * @return the list
         */
        public Attachment build() {
            Attachment attachment = new Attachment();
            attachment.attachmentSubType= attachmentSubType;
            attachment.attachmentType = attachmentType;
            attachment.description=description;
            attachment.url = url;
            return attachment;
        }
    }
}
