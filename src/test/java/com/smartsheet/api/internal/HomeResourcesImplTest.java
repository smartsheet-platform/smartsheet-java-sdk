package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.FolderResources;
import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.ObjectInclusion;

public class HomeResourcesImplTest extends ResourcesImplBase {

	private HomeResourcesImpl homeResources;

	@Before
	public void setUp() throws Exception {
		homeResources = new HomeResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testHomeResourcesImpl() {}

	@Test
	public void testGetHome() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getHome.json"));
		
		List<Home> homes = new ArrayList<Home>();
		homes.add(homeResources.getHome(EnumSet.of(ObjectInclusion.TEMPLATES)));
		homes.add(homeResources.getHome(null));
		for(Home home : homes){
			assertNotNull(home.getSheets());
			assertTrue(home.getSheets().size() == 7);
			assertNotNull(home.getFolders());
			assertTrue(home.getFolders().size() == 5);
			assertNotNull(home.getWorkspaces());
			assertTrue(home.getWorkspaces().size() == 7);
			assertNull(home.getTemplates());
		}
	}

	@Test
	public void testFolders() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/getHomeFolders.json"));
		
		HomeFolderResources folders = homeResources.folders();
		assertNotNull(folders.listFolders());
		assertTrue(folders.listFolders().size() == 5);
	}

}
