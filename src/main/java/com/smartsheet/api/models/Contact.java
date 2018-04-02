package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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
 * Represents the Contact object.
 */
public class Contact extends NamedModel<String>{

    /** Represents the contact email. */
    private String email;

    /**
     * Gets the email for the contact.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the contact
     * @param email the email
     */
    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }
}
