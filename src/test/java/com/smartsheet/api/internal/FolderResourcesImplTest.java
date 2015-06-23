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
import java.util.List;

import com.smartsheet.api.models.DataWrapper;
import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Folder;

public class FolderResourcesImplTest extends ResourcesImplBase {

	@Before
	public void setUp() {
		// Create a folder resource
		folderResource = new FolderResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer));
	}

	@Test
	public void testFolderResourcesImpl() {
	}

	@Test
	public void testGetFolder() throws SmartsheetException, IOException {

		// Set a fake response
		server.setResponseBody(new File("src/test/resources/getFolder.json"));

		//server.getClass().getClassLoader().getResourceAsStream(
		//		"com/smartsheet/api/internal/getFolder.json")
		
		// Send the request for a folder
 	
		//folderResource.getSmartsheet().getHttpClient().close();
		
		Folder folder = folderResource.getFolder(123L);
//		folder.setTemplates(new ArrayList<Template>());
//		folder.setWorkspaces(new ArrayList<Workspace>());
		folderResource.getFolder(123L);

		// Verify results
		assertEquals("Personal", folder.getName());
		assertEquals(2, folder.getSheets().size());
		assertEquals(0, folder.getFolders().size());
	}

	@Test
	public void testUpdateFolder() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/updateFolder.json"));

		Folder newFolder = new Folder();
		newFolder.setName("New Name");
		newFolder.setId(1138268709382020L);
		
		Folder resultFolder = folderResource.updateFolder(newFolder);
		
		assertEquals(resultFolder.getName(), newFolder.getName());
	}

	@Test
	public void testDeleteFolder() throws SmartsheetException, IOException {

		server.setResponseBody(new File("src/test/resources/deleteFolder.json"));

		folderResource.deleteFolder(7752230582413188L);
	}

	@Test
	public void testListFolders() throws SmartsheetException, IOException {
		
		server.setResponseBody(new File("src/test/resources/listFolders.json"));

		DataWrapper<Folder> foldersWrapper = folderResource.listFolders(12345L,true,1,1);

		assertTrue(foldersWrapper.getPageSize() == 100);
		assertEquals("Folder 1", foldersWrapper.getData().get(0).getName());
		assertEquals("Folder 2", foldersWrapper.getData().get(1).getName());
		assertTrue(7116448184199044L == foldersWrapper.getData().get(0).getId());
	}

	@Test
	public void testCreateFolder() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/createFolder.json"));

		Folder newFolder = new Folder();
		newFolder.setName("new folder by brett");
		Folder createdFolder = folderResource.createFolder(123L, newFolder);

		assertEquals(createdFolder.getName(), newFolder.getName());

	}

}
