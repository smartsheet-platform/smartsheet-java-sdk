package com.smartsheet.api.models;

import java.lang.*;
import java.util.List;

/**
 * Represents an Email object.
 */
public abstract class Email {
    /**
     * Represents the email recipient.
     */
    private List<String> to;

    /**
     * Represents the subject.
     */
    private String subject;

    /**
     * Represents the message.
     */
    private String message;

    /**
     * Represents the CC me flag.
     */
    private Boolean ccMe;
}

