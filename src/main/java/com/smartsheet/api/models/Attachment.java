package com.smartsheet.api.models;

import java.util.*;
import com.smartsheet.api.models.*;
import java.lang.*;

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
}

