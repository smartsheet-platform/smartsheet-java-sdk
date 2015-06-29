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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smartsheet.api.models.DataWrapper;
import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.User;
import com.smartsheet.api.models.UserProfile;
import com.smartsheet.api.models.UserStatus;

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
		List<String> email = new ArrayList<String>();
		DataWrapper<User> userWrapper = userResources.listUsers(email, true, 1, 1);
		assertTrue(userWrapper.getPageNumber() == 1);
		assertTrue(userWrapper.getPageSize() == 100);
		assertTrue(userWrapper.getTotalCount() == 418);
		assertTrue(userWrapper.getTotalPages() == 5);

		List<User> users = userWrapper.getData();
		assertEquals(2, users.size());
		assertEquals(94094820842L, users.get(0).getId().longValue());
		assertEquals(true, users.get(0).getAdmin());
		assertEquals("john.doe@smartsheet.com", users.get(0).getEmail());
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
	}

	@Test
	public void testGetCurrentUser() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getCurrentUser.json"));

		UserProfile user = userResources.getCurrentUser();
		assertEquals("email@email.com",user.getEmail());
		assertEquals(6199527427336068L, user.getId().longValue());
		assertEquals("Brett", user.getFirstName());
		assertEquals("Batie", user.getLastName());
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
		userResources.deleteUser(1234L, 56789L, true, true);
	}

}
