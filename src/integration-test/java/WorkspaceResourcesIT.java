/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.AccessLevel;
import com.smartsheet.api.models.enums.WorkspaceCopyInclusion;
import com.smartsheet.api.models.enums.WorkspaceRemapExclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class WorkspaceResourcesIT extends ITResourcesImpl{

    Smartsheet smartsheet;
    long workspaceId;
    Workspace workspace;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testWorkspaceMethods() throws IOException, SmartsheetException {
        testCreateWorkspace();
        testCopyWorkspace();
        testShareWorkspace();
        testGetWorkspace();
        testListWorkspaces();
        testUpdateWorkspace();
        testDeleteWorkspace();
    }

    public void testCreateWorkspace() throws IOException, SmartsheetException {
        workspace = new Workspace.UpdateWorkspaceBuilder().setName("New Test Workspace").build();
        Workspace newWorkspace = smartsheet.workspaceResources().createWorkspace(workspace);

        workspaceId = newWorkspace.getId();
        assertEquals("New Test Workspace", newWorkspace.getName());
        assertEquals(AccessLevel.OWNER, newWorkspace.getAccessLevel());
    }

    public void testCopyWorkspace() throws SmartsheetException, IOException {

        ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setNewName("New Copied workspace").build();

        Workspace workspace = smartsheet.workspaceResources().copyWorkspace(workspaceId, destination, EnumSet.of(WorkspaceCopyInclusion.ALL), EnumSet.of(WorkspaceRemapExclusion.CELLLINKS));
        assertEquals(workspace.getName(), "New Copied workspace");
        deleteWorkspace(workspace.getId());
    }

    public void testShareWorkspace() throws IOException, SmartsheetException {
        List<Share> shares = new ArrayList<Share>();
        shares.add(new Share.CreateUserShareBuilder().setEmailAddress("jane.doe@smartsheet.com").setAccessLevel(AccessLevel.ADMIN).build());
        shares.add(new Share.CreateUserShareBuilder().setEmailAddress("aditi.test@smartsheet.com").setAccessLevel(AccessLevel.ADMIN).build());

        shares = smartsheet.workspaceResources().shareResources().shareTo(workspaceId, shares, true);
        assertTrue(shares.size() == 2);
        assertEquals("aditi.test@smartsheet.com", shares.get(0).getEmail());
    }

    public void testGetWorkspace() throws IOException, SmartsheetException {
        Workspace workspace = smartsheet.workspaceResources().getWorkspace(workspaceId, null, null);
        assertNotNull(workspace.getId());
    }

    public void testListWorkspaces() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters(true, 10, 10);
        PagedResult<Workspace> workspace = smartsheet.workspaceResources().listWorkspaces(parameters);
        assertNotNull(workspace);
    }

    public void testUpdateWorkspace() throws IOException, SmartsheetException {
        Workspace workspace = new Workspace.UpdateWorkspaceBuilder().setName("Updated workspace").setId(workspaceId).build();
        Workspace newWorkspace = smartsheet.workspaceResources().updateWorkspace(workspace);
        assertEquals(workspaceId, newWorkspace.getId().longValue());
    }

    public void testDeleteWorkspace() throws IOException, SmartsheetException {
        smartsheet.workspaceResources().deleteWorkspace(workspaceId);
    }
}
