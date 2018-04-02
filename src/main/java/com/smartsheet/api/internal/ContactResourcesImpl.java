package com.smartsheet.api.internal;

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

import com.smartsheet.api.ContactResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Contact;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

/**
 * This is the implementation of the ContactResources.
 */
public class ContactResourcesImpl extends AbstractResources implements ContactResources{
    /**
     * Constructor
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public ContactResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Gets the specified Contact.
     *
     * It mirrors to the following Smartsheet REST API method: GET /contacts/{contactId}
     *
     * @param contactId the ID of the contact
     * @return the contact object
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Contact getContact(String contactId) throws SmartsheetException{
        return this.getResource("contacts/" + contactId, Contact.class);
    }


    /**
     * Gets a list of the user’s Smartsheet Contacts.
     *
     * It mirrors to the following Smartsheet REST API method: GET /contacts
     *
     * @param parameters the pagination parameters
     * @return the contacts as a paged list
     * @throws SmartsheetException
     */
    public PagedResult<Contact> listContacts(PaginationParameters parameters) throws SmartsheetException{
        String path = "contacts";

        if (parameters != null) {
            path += parameters.toQueryString();
        }
        return this.listResourcesWithWrapper(path, Contact.class);
    }
}
