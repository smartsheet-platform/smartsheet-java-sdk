package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2016 Smartsheet
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

/**
 * The AlternateEmail object (see: http://smartsheet-platform.github.io/api-docs/?shell#alternateemail-object)
 */
public class AlternateEmail {

    /**
     * The alternate email id
     */
    private Long id;

    /**
     * Get the alternate email id.
     *
     * @return the alternate email id
     */
    public Long getId() {
        return id;
    }

    /**
     * The user's alternate email address (user@example.com)
     */
    private String email;

    /**
     * Get the user's alternate email address.
     *
     * @return String containing the alternate email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the  email
     * Set the user's alternate email address.
     */
    public AlternateEmail setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Flag indicating whether the alternate email address has been confirmed
     */
    private Boolean confirmed;

    /**
     * Get flag indicating whether the alternate email address is confirmed.
     *
     * @return true if the alternate email address has been confirmed
     */
    public Boolean getConfirmed() {
        return confirmed;
    }

    /**
     * A convenience class for making a {@link AlternateEmail} object with the appropriate fields for adding to a {@link User}.
     */
    public static class AddAlternateEmailBuilder {
        private String email;

        /**
         * Get the alt email address
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * Set the alt email address
         * @param email the email
         * @return the builder
         */
        public AddAlternateEmailBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AlternateEmail build() {
            if(email == null){
                throw new InstantiationError("An email address must be set.");
            }

            AlternateEmail altEmail = new AlternateEmail();
            altEmail.setEmail(email);
            return altEmail;
        }
    }

}
