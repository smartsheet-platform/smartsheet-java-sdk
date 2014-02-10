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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.AccessLevel;
import com.smartsheet.api.models.MultiShare;
import com.smartsheet.api.models.Share;
import com.smartsheet.api.models.User;

public class ShareResourcesImplTest extends ResourcesImplBase {

	private ShareResourcesImpl shareResourcesImpl;

	@Before
	public void setUp() throws Exception {
		shareResourcesImpl = new ShareResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer), "sheet");
	}

	@Test
	public void testShareResourcesImpl() {
	}

	@Test
	public void testListShares() throws SmartsheetException, IOException {

		server.setResponseBody(new File("src/test/resources/listShares.json"));

		List<Share> shares = shareResourcesImpl.listShares(2906571706525572L);
		assertTrue("The number of shares returned is incorrect.", shares.size() == 2);

		assertEquals("Email attribute of the share is incorrect.", "email@email.com", shares.get(0).getEmail());
		assertEquals("Email attribute of the share is incorrect.", "someone@somewhere.com", shares.get(1).getEmail());
	}

	@Test
	public void testGetShare() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getShare.json"));

		Share share = shareResourcesImpl.getShare(1234L, 12344L);

		assertEquals("email@email.com", share.getEmail());
		assertEquals(AccessLevel.ADMIN, share.getAccessLevel());
		assertEquals(8166691168380804L, share.getId().longValue());
	}

	@Test
	public void testShareToLongShare() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/shareToOne.json"));

		Share share = new Share();
		share.setEmail("email@email.com");
		share.setAccessLevel(AccessLevel.ADMIN);
		shareResourcesImpl.shareTo(1234L, share);

		assertEquals("email@email.com", share.getEmail());
		assertEquals(AccessLevel.ADMIN, share.getAccessLevel());
	}

	@Test
	public void testShareToLongShareBoolean() throws SmartsheetException, IOException {

		server.setResponseBody(new File("src/test/resources/shareToOne.json"));

		Share share = new Share();
		share.setEmail("email@email.com");
		share.setAccessLevel(AccessLevel.ADMIN);
		shareResourcesImpl.shareTo(1234L, share, true);

		assertEquals("email@email.com", share.getEmail());
		assertEquals(AccessLevel.ADMIN, share.getAccessLevel());
	}

	@Test
	public void testShareToLongMultiShare() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/shareToMany.json"));

		MultiShare share = new MultiShare();
		share.setAccessLevel(AccessLevel.ADMIN);
		share.setMessage("I have shared a Smartsheet with you. Please review it for the latest updates");
		share.setSubject("Testing");
		share.setCcMe(false);

		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setEmail("john.doe@smartsheet.com");
		users.add(user);
		user.setEmail("jane.doe@smartsheet.com");
		users.add(user);
		share.setUsers(users);

		List<Share> shares = shareResourcesImpl.shareTo(1234L, share);

		assertTrue(shares.size() == 2);
		assertEquals("john.doe@smartsheet.com", shares.get(0).getEmail());
		assertEquals("jane.doe@smartsheet.com", shares.get(1).getEmail());
	}

	@Test
	public void testShareToLongMultiShareBoolean() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/shareToMany.json"));

		MultiShare share = new MultiShare();
		share.setAccessLevel(AccessLevel.ADMIN);
		share.setMessage("I have shared a Smartsheet with you. Please review it for the latest updates");
		share.setCcMe(false);

		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setEmail("john.doe@smartsheet.com");
		users.add(user);
		user.setEmail("jane.doe@smartsheet.com");
		users.add(user);
		share.setUsers(users);

		List<Share> shares = shareResourcesImpl.shareTo(1234L, share, true);
		assertTrue(shares.size() == 2);
		assertEquals("john.doe@smartsheet.com", shares.get(0).getEmail());
		assertEquals("jane.doe@smartsheet.com", shares.get(1).getEmail());
	}

	@Test
	public void testUpdateShare() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/updateShare.json"));
		Share share = new Share();
		share.setAccessLevel(AccessLevel.ADMIN);
		Share newShare = shareResourcesImpl.updateShare(1234L, 123454L, share);
		assertEquals(share.getAccessLevel(), newShare.getAccessLevel());
	}

	@Test
	public void testDeleteShare() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteShare.json"));

		shareResourcesImpl.deleteShare(1234L, 142124L);
	}

}
