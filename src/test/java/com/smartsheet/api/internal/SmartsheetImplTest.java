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

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.internal.http.DefaultHttpClient;

public class SmartsheetImplTest extends ResourcesImplBase {

	private SmartsheetImpl smartsheet;

	@Before
	public void setUp() throws Exception {
		smartsheet = new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken", 
				new DefaultHttpClient(), serializer);
	}

	@Test
	public void testFinalize() {
		
	}

	@Test
	public void testSmartsheetImpl() {
		
	}

	@Test
	public void testGetHttpClient() {
		
	}

	@Test
	public void testGetJsonSerializer() {
		
	}

	@Test
	public void testGetBaseURI() {
		
	}

	@Test
	public void testGetAssumedUser() {
		
	}

	@Test
	public void testGetAccessToken() {
		
	}

	@Test
	public void testHome() {
		assertNotNull(smartsheet.home());
	}

	@Test
	public void testWorkspaces() {
		assertNotNull(smartsheet.workspaces());
	}

	@Test
	public void testFolders() {
		assertNotNull(smartsheet.folders());
	}

	@Test
	public void testTemplates() {
		assertNotNull(smartsheet.templates());
	}

	@Test
	public void testSheets() {
		assertNotNull(smartsheet.sheets());
	}

	@Test
	public void testAttachments() {
		assertNotNull(smartsheet.attachments());
	}

	@Test
	public void testDiscussions() {
		assertNotNull(smartsheet.discussions());
	}

	@Test
	public void testComments() {
		assertNotNull(smartsheet.comments());
	}

	@Test
	public void testUsers() {
		assertNotNull(smartsheet.users());
	}

	@Test
	 public void testSearch() {
		assertNotNull(smartsheet.search());
	}

	@Test
	public void testReports() {
		assertNotNull(smartsheet.reports());
	}
	@Test
	public void testSetAssumedUser() {
		smartsheet.setAssumedUser("user");
	}

	@Test
	public void testSetAccessToken() {
		smartsheet.setAccessToken("1234");
	}

}
