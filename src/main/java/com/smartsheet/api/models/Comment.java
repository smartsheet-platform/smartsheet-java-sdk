package com.smartsheet.api.models;

import java.util.*;
import java.lang.*;

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
}

