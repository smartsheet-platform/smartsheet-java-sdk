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

import com.smartsheet.api.models.enums.UserStatus;

/**
 * A profile object that contains the basic fields that most profiles will contain.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/520100-user-types">User Types Help</a>
 */
public class UserProfile extends UserModel {
    /**
     * Represents the user's time zone
     */
    private String timeZone;

    /**
     * Represents the user's locale
     */
    private String locale;

    /**
     * Represents the user's customer account
     */
    private Account account;

    /**
     * Gets the time zone
     * @return the time zone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the time zone
     * @param timeZone the time zone
     */
    public UserProfile setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    /**
     * Gets the locale
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the locale
     * @param locale the locale
     */
    public UserProfile setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Gets the account
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account
     * @param account the account
     */
    public UserProfile setAccount(Account account) {
        this.account = account;
        return this;
    }
}
