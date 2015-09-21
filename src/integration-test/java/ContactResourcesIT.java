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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Contact;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContactResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    String contactId;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testContactMethods() throws SmartsheetException, IOException {
        testListContacts();
        testGetContact();
    }

    public void testGetContact() throws SmartsheetException, IOException {
        Contact contact = smartsheet.contactResources().getContact(contactId);
        assertNotNull(contact.getEmail());
    }

    public void testListContacts() throws SmartsheetException, IOException {
        PagedResult<Contact> contacts = smartsheet.contactResources().listContacts(new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build());
        //assertEquals(contacts.getData().get(0).getName(), "David Davidson");
        assertTrue(contacts.getTotalCount() > 0);
        contactId = contacts.getData().get(0).getId();
    }
}
