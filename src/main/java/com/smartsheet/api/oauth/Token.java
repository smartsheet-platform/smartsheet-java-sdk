package com.smartsheet.api.oauth;

import java.lang.*;

/**
 * Represents OAuth token.
 */
public class Token {
    /**
     * Represents the access token.
     */
    private String accessToken;

    /**
     * Represents the token type.
     */
    private String tokenType;

    /**
     * Represents the refresh token.
     */
    private String refreshToken;

    /**
     * Represents the expiration time in seconds.
     */
    private long expiresInSeconds;
}

