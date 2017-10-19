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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.AccessLevel;
import com.smartsheet.api.models.enums.DestinationType;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<Workspace> workspace = workspaceResources.listWorkspaces(parameters);
        assertEquals(1, workspace.getPageNumber().longValue());
        assertEquals(100, workspace.getPageSize().longValue());
        assertEquals(1, workspace.getTotalPages().longValue());
        assertEquals(2, workspace.getTotalCount().longValue());

        assertEquals(2, workspace.getData().size());
        assertEquals(AccessLevel.OWNER, workspace.getData().get(0).getAccessLevel());
        assertEquals(3457273486960516L, workspace.getData().get(0).getId().longValue());
        assertEquals("workspace 1", workspace.getData().get(0).getName());
        assertEquals("https://app.smartsheet.com/b/home?lx=JNL0bgXtXc0pzni9tzAc4g", workspace.getData().get(0).getPermalink());
    }

    @Test
    public void testGetWorkspace() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/getWorkspace.json"));

        Workspace workspace = workspaceResources.getWorkspace(1234L, true, EnumSet.allOf(SourceInclusion.class));
        assertEquals(1, workspace.getSheets().size());

        Sheet sheet = workspace.getSheets().get(0);
        assertEquals("sheet 1", sheet.getName());

        Source source = sheet.getSource();
        assertNotNull(source.getId());
        assertNotNull(source.getType());

        assertEquals(7116448184199044L, workspace.getId().longValue());
        assertEquals("New workspace", workspace.getName());

        assertEquals(AccessLevel.OWNER, workspace.getAccessLevel());
        assertEquals("https://app.smartsheet.com/b/home?lx=8Z0XuFUEAkxmHCSsMw4Zgg", workspace.getPermalink());
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
    public void testCopyWorkspace() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/copyWorkspace.json"));
        ContainerDestination containerDestination = new ContainerDestination();
        containerDestination.setDestinationType(DestinationType.WORKSPACE);

        Folder folder = workspaceResources.copyWorkspace(123L, containerDestination, null, null);
        assertEquals(folder.getPermalink(), "https://{url}?lx=VL4YlIUnyYgASeX02grbLQ");
    }
}
