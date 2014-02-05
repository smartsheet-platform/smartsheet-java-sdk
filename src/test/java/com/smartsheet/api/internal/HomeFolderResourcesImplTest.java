package com.smartsheet.api.internal;

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
