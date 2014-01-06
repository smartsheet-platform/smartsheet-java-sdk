package com.smartsheet.api.models;

import com.smartsheet.api.models.*;
import java.lang.*;

/**
 * Represents the User object.
 */
public class User extends UserProfile {
    /**
     * Represents the admin flag.
     */
    private Boolean admin;

    /**
     * Represents the licensed sheet creator flag.
     */
    private Boolean licensedSheetCreator;

    /**
     * Represents the user status.
     */
    private UserStatus status;

    /**
     * Represents the name.
     */
    private String name;
}

