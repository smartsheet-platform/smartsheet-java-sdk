package com.smartsheet.api.internal;

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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.UserStatus;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserResourcesImplTest extends ResourcesImplBase {

    private UserResourcesImpl userResources;

    @Before
    public void setUp() throws Exception {
        userResources = new UserResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testUserResourcesImpl() {}

    @Test
    public void testListUsers() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listUsers.json"));
        Set<String> email = new HashSet<String>();
        email.add("test@example.com");
        PaginationParameters pagination = new PaginationParameters();
        pagination.setIncludeAll(true);
        pagination.setPageSize(1);
        pagination.setPage(1);

        PagedResult<User> userWrapper1 = userResources.listUsers();
        assertTrue(userWrapper1.getData().size() == 2);

        PagedResult<User> userWrapper = userResources.listUsers(email, pagination);
        assertTrue(userWrapper.getPageNumber() == 1);
        assertTrue(userWrapper.getPageSize() == 100);
        assertTrue(userWrapper.getTotalCount() == 418);
        assertTrue(userWrapper.getTotalPages() == 5);

        List<User> users = userWrapper.getData();
        assertEquals(2, users.size());
        assertEquals(242165701390534L, users.get(0).getId().longValue());
        assertEquals(false, users.get(0).getAdmin());
        assertEquals("test@smartsheet.com", users.get(0).getEmail());
        assertEquals("John Doe", users.get(0). getName());
        assertEquals(true, users.get(0).getLicensedSheetCreator());
        assertEquals(UserStatus.ACTIVE, users.get(0).getStatus());
    }

    @Test
    public void testAddUserUser() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/addUser.json"));

        User user = new User();
        user.setAdmin(true);
        user.setEmail("test@test.com");
        user.setFirstName("test425");
        user.setLastName("test425");
        user.setLicensedSheetCreator(true);
        User newUser = userResources.addUser(user);

        assertEquals("test@test.com", newUser.getEmail());
        assertEquals("test425 test425", newUser.getName());
        assertEquals(false, newUser.getAdmin());
        assertEquals(true, newUser.getLicensedSheetCreator());
        assertEquals(3210982882338692L, newUser.getId().longValue());
    }

    @Test
    public void testAddUserUserBoolean() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/addUser.json"));

        User user = new User();
        user.setAdmin(true);
        user.setEmail("test@test.com");
        user.setFirstName("test425");
        user.setLastName("test425");
        user.setLicensedSheetCreator(true);
        User newUser = userResources.addUser(user, false);

        assertEquals("test@test.com", newUser.getEmail());
        assertEquals("test425 test425", newUser.getName());
        assertEquals(false, newUser.getAdmin());
        assertEquals(true, newUser.getLicensedSheetCreator());
        assertEquals(3210982882338692L, newUser.getId().longValue());
    }

    @Test
    public void testGetUser() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getUser.json"));

        UserProfile user = userResources.getUser(12345L);
        assertEquals("john.doe@smartsheet.com",user.getEmail());
        assertEquals(48569348493401200L, user.getId().longValue());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("en_US", user.getLocale());
        assertEquals("US/Pacific", user.getTimeZone());
    }

    @Test
    public void testGetCurrentUser() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getCurrentUser.json"));

        UserProfile user = userResources.getCurrentUser();
        assertEquals("test@smartsheet.com",user.getEmail());
        assertEquals(2222222222L, user.getId().longValue());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("en_US", user.getLocale());
        assertEquals("US/Pacific", user.getTimeZone());

        Account account = user.getAccount();
        assertEquals("Smartsheet", account.getName());
        assertEquals(111111111111L, account.getId().longValue());
    }

    @Test
    public void testUpdateUser() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateUser.json"));

        User user = new User();
        user.setId(1234L);
        user.setAdmin(true);
        user.setLicensedSheetCreator(true);
        User updatedUser = userResources.updateUser(user);
        assertEquals("email@email.com", updatedUser.getEmail());
        assertEquals(false, updatedUser.getAdmin());
        assertEquals(true, updatedUser.getLicensedSheetCreator());
        assertEquals(8166691168380804L, updatedUser.getId().longValue());
        assertEquals(UserStatus.ACTIVE, updatedUser.getStatus());
    }

    @Test
    public void testDeleteUser() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/deleteUser.json"));
        DeleteUserParameters parameters = new DeleteUserParameters(12345L, true, true);
        userResources.deleteUser(1234L, parameters);
    }

    @Test
    public void testListOrgSheets() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listOrgSheets.json"));

        PaginationParameters pagination = new PaginationParameters();
        pagination.setIncludeAll(true);
        pagination.setPageSize(1);
        pagination.setPage(1);

        PagedResult<Sheet> sheets = userResources.listOrgSheets(pagination, null);
    }
}
