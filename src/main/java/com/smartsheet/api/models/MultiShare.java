package com.smartsheet.api.models;

import java.lang.*;
import java.util.List;

import com.smartsheet.api.models.*;

/**
 * Represents the MultiShare object.
 */
public class MultiShare {
    /**
     * Represents the users.
     */
    private List<User> users;

    /**
     * Represents the access level.
     */
    private AccessLevel accessLevel;

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

