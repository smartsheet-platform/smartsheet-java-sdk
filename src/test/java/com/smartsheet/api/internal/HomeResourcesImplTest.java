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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.ObjectInclusion;
import com.smartsheet.api.models.Template;

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
			home.setTemplates(new ArrayList<Template>());
		}
	}

	@Test
	public void testFolders() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/getHomeFolders.json"));
		
		HomeFolderResources folders = homeResources.folders();
		PaginationParameters parameters = new PaginationParameters(true,1,1);
		assertNotNull(folders.listFolders(parameters));

		PaginationParameters parameters1 = new PaginationParameters(true,null,null);
		assertTrue(folders.listFolders(parameters1).getPageSize() == 100);

		PaginationParameters parameters2 = new PaginationParameters(false,1,1);
		assertTrue(folders.listFolders(parameters2).getPageSize() == 100);

		PaginationParameters parameters3 = new PaginationParameters(true,null,null);
		assertTrue(folders.listFolders(parameters3).getTotalCount() == 2);
	}

}
