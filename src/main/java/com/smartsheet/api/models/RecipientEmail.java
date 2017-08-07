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

public class RecipientEmail implements Recipient {
    /**
     * The email address of an individual recipient.
     */
    private String email;

    /**
     * Get the recipient's email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the recipient's email
     * @param email the email
     */
    public RecipientEmail setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * A convenience class to help create a RecipientEmail object with the appropriate fields.
     */
    public static class AddRecipientEmailBuilder {
        /**
         * The email address of an individual recipient.
         */
        private String email;

        /**
         * Get the recipient's email
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * Set the recipient's email
         * @param email the email
         * @return the builder
         */
        public AddRecipientEmailBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Builds the RecipientEmail.
         *
         * @return the RecipientEmail
         */
        public RecipientEmail build() {
            RecipientEmail recipientEmail = new RecipientEmail();
            recipientEmail.email = email;
            return recipientEmail;
        }

    }
}
