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
import com.smartsheet.api.models.AccessLevel;
import com.smartsheet.api.models.Workspace;

public class WorkspaceResourcesImplTest extends ResourcesImplBase {

	private WorkspaceResourcesImpl workspaceResources;

	@Before
	public void setUp() throws Exception {
		workspaceResources = new WorkspaceResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testWorkspaceResourcesImpl() {}

	@Test
	public void testListWorkspaces() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/listWorkspaces.json"));
		
		List<Workspace> workspace = workspaceResources.listWorkspaces();
		assertEquals(7, workspace.size());
		assertEquals(995897522841476L, workspace.get(0).getId().longValue());
		assertEquals("Bootcamp Company", workspace.get(0).getName());
		assertEquals(AccessLevel.OWNER, workspace.get(0).getAccessLevel());
		assertEquals("https://app.smartsheet.com/b/home?lx=asdsa", workspace.get(0).getPermalink());
	}

	@Test
	public void testGetWorkspace() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/getWorkspace.json"));
		
		Workspace workspace = workspaceResources.getWorkspace(1234L);
		assertEquals(995897522841476L, workspace.getId().longValue());
		assertEquals("Bootcamp Company", workspace.getName());
		assertEquals(0, workspace.getSheets().size());
		assertEquals(2, workspace.getFolders().size());
		assertEquals(AccessLevel.OWNER, workspace.getAccessLevel());
		assertEquals("https://app.smartsheet.com/b/home?asdf", workspace.getPermalink());
	}

	@Test
	public void testCreateWorkspace() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/createWorkspace.json"));
		
		Workspace workspace = new Workspace();
		workspace.setName("New Workspace");
		Workspace newWorkspace = workspaceResources.createWorkspace(workspace);
		assertEquals(2349499415848836L, newWorkspace.getId().longValue());
		assertEquals("New Workspace", newWorkspace.getName());
		assertEquals(AccessLevel.OWNER, newWorkspace.getAccessLevel());
		assertEquals("https://app.smartsheet.com/b/home?lx=Jasdfa", newWorkspace.getPermalink());
	}

	@Test
	public void testUpdateWorkspace() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/updateWorkspace.json"));
		
		Workspace workspace = new Workspace();
		workspace.setName("New Workspace");
		Workspace newWorkspace = workspaceResources.updateWorkspace(workspace);
		assertEquals(2349499415848836L, newWorkspace.getId().longValue());
		assertEquals("New Workspace1", newWorkspace.getName());
		assertEquals(AccessLevel.OWNER, newWorkspace.getAccessLevel());
		assertEquals("https://app.smartsheet.com/b/home?lx=asdf", newWorkspace.getPermalink());
	}

	@Test
	public void testDeleteWorkspace() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/deleteWorkspace.json"));
		workspaceResources.deleteWorkspace(1234L);
	}

	@Test
	public void testFolders() {
		assertNull(workspaceResources.folders());
	}

	@Test
	public void testShares() {
		assertNull(workspaceResources.shares());
	}

}
