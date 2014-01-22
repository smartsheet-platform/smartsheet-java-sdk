package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.HttpTestServer;
import com.smartsheet.api.SmartsheetRestException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClientException;
import com.smartsheet.api.internal.json.JSONSerializerException;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.models.Folder;

public class FolderResourcesImplTest {
	FolderResourcesImpl folderResource;
	// TestAbstractResources resourcesRemote;
	HttpTestServer server;

	@Before
	public void setUp() throws Exception {
		// Setup test server
		server = new HttpTestServer();
		server.setPort(9090);
		server.start();

		// Create a folder resource
		folderResource = new FolderResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), new JacksonJsonSerializer()));
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testFolderResourcesImpl() {
	}

	@Test
	public void testGetFolder() throws IOException, HttpClientException, SmartsheetRestException,
			IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// Set a fake response
		server.setMockResponseData(new File("src/test/resources/getFolder.json"));

		// Send the request for a folder
		Folder folder = folderResource.getFolder(123L);

		// Verify results
		assertEquals("Personal", folder.getName());
		assertEquals(2, folder.getSheets().size());
		assertEquals(0, folder.getFolders().size());
	}

	@Test
	public void testUpdateFolder() throws IOException, JSONSerializerException, HttpClientException,
			SmartsheetRestException, IllegalArgumentException, SecurityException, InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		server.setMockResponseData(new File("src/test/resources/updateFolder.json"));

		Folder newFolder = new Folder();
		newFolder.setName("New Name");
		newFolder.setId(1138268709382020L);
		Folder resultFolder = folderResource.updateFolder(newFolder);
		assertEquals(resultFolder.getName(), newFolder.getName());
	}

	@Test
	public void testDeleteFolder() throws JsonParseException, JsonMappingException, SmartsheetRestException,
			IllegalArgumentException, SecurityException, JSONSerializerException, HttpClientException, IOException,
			InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		server.setMockResponseData(new File("src/test/resources/deleteFolder.json"));

		folderResource.deleteFolder(7752230582413188L);
	}

	@Test
	public void testListFolders() throws IOException, HttpClientException, JSONSerializerException,
			SmartsheetRestException, IllegalArgumentException, SecurityException, InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		server.setMockResponseData(new File("src/test/resources/listFolders.json"));

		List<Folder> folders = folderResource.listFolders(12345L);
		assertEquals(2, folders.size());
	}

	@Test
	public void testCreateFolder() throws JsonParseException, JsonMappingException, JSONSerializerException,
			HttpClientException, SmartsheetRestException, IllegalArgumentException, SecurityException, IOException,
			InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		server.setMockResponseData(new File("src/test/resources/createFolder.json"));

		Folder newFolder = new Folder();
		newFolder.setName("new folder by brett");
		Folder createdFolder = folderResource.createFolder(123L, newFolder);

		assertEquals(createdFolder.getName(), newFolder.getName());

	}

}
