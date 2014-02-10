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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Folder;

public class HomeFolderResourcesImplTest extends ResourcesImplBase {

	private HomeFolderResourcesImpl homeFolderResources;

	@Before
	public void setUp() throws Exception {
		homeFolderResources = new HomeFolderResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testHomeFolderResourcesImpl() {
	}

	@Test
	public void testListFolders() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/listFolders.json"));
		
		List<Folder> folders = homeFolderResources.listFolders();
		
		assertTrue(folders.size() == 2);
		assertEquals("Personal", folders.get(0).getName());
		assertEquals("Expenses", folders.get(1).getName());
		assertTrue(1138268709382020L == folders.get(0).getId());
	}

	@Test
	public void testCreateFolder() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/createFolders.json"));
		
		Folder folder = new Folder();
		folder.setName("Hello World");
		
		Folder newFolder = homeFolderResources.createFolder(folder);
		assertTrue(6821399500220292L == newFolder.getId());
		assertEquals("hello world", newFolder.getName());
	}
}
