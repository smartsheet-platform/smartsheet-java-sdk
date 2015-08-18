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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Contact;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactResourcesImplTest extends ResourcesImplBase {
    ContactResourcesImpl contactResources;

    @Before
    public void setUp() throws Exception {
        contactResources = new ContactResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
                new DefaultHttpClient(), serializer));
    }

    @Test
    public void testGetContact() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getContact.json"));
        Contact contact = contactResources.getContact("xyz");
        assertEquals(contact.getName(), "David Davidson");
    }

    @Test
    public void testListContacts() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listContacts.json"));
        PagedResult<Contact> contacts = contactResources.listContacts(new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build());
        assertEquals(contacts.getData().get(0).getName(), "David Davidson");
        assertTrue(contacts.getTotalCount() == 2);
    }
}