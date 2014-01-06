package com.smartsheet.api.models;

import java.util.*;
import java.lang.*;

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
}

