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
import com.smartsheet.api.models.enums.DestinationType;
import com.smartsheet.api.models.enums.FolderCopyInclusion;
import com.smartsheet.api.models.enums.FolderRemapExclusion;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FolderResourcesIT extends ITResourcesImpl {

    Smartsheet smartsheet;
    Folder newFolderHome;
    Folder newFolder;
    Folder newFolderWorkspace;
    Workspace workspace;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testFolderMethods() throws IOException, SmartsheetException {
        testCreateFolderInHome();
        testListFoldersInHome();
        testCreateFolderInFolder();
        testListFoldersInFolder();
        testCreateFolderInWorkspace();
        testListFoldersInWorkspace();
        testUpdateFolder();
        testGetFolder();
        testCopyFolder();
        testMoveFolder();
        testDeleteFolder();
    }

    public void testCreateFolderInHome() throws IOException, SmartsheetException {
        Folder folder = new Folder.CreateFolderBuilder().setName("New Folder in Home By Aditi").build();

        newFolderHome = smartsheet.homeResources().folderResources().createFolder(folder);
        assertEquals("New Folder in Home By Aditi", newFolderHome.getName());
    }

    public void testCreateFolderInFolder() throws SmartsheetException, IOException {
        Folder folder = new Folder.CreateFolderBuilder().setName("New Folder in Folder By Aditi").build();
        newFolder = smartsheet.folderResources().createFolder(newFolderHome.getId(), folder);

        Folder folder1 = new Folder.CreateFolderBuilder().setName("New Folder 2 in Folder By Aditi").build();
        smartsheet.folderResources().createFolder(newFolder.getId(), folder1);

        assertEquals(folder.getName(), newFolder.getName());
    }

    public void testCreateFolderInWorkspace() throws IOException, SmartsheetException {
        //calling helper method
        workspace = createWorkspace("New Workspace By Aditi");

        Folder folder = new Folder.CreateFolderBuilder().setName("New Folder in Workspace By Aditi").build();
        newFolderWorkspace = smartsheet.workspaceResources().folderResources().createFolder(workspace.getId(), folder);
        assertEquals("New Folder in Workspace By Aditi", folder.getName());
    }

    public void testUpdateFolder() throws SmartsheetException, IOException {
        Folder folder = new Folder.UpdateFolderBuilder().setName("Updated Name By Aditi").setId(newFolderHome.getId()).build();
        Folder resultFolder = smartsheet.folderResources().updateFolder(folder);

        assertEquals(resultFolder.getName(), folder.getName());
    }

    public void testListFoldersInFolder() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters(true,1,1);
        PagedResult<Folder> foldersWrapper = smartsheet.folderResources().listFolders(newFolder.getId(), parameters);

        assertTrue(foldersWrapper.getTotalCount() == 1);
    }

    public void testListFoldersInHome() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters(true,1,1);
        PagedResult<Folder> foldersWrapper = smartsheet.homeResources().folderResources().listFolders(parameters);

        assertTrue(foldersWrapper.getTotalCount() != null);
    }

    public void testListFoldersInWorkspace() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters(true,1,1);
        PagedResult<Folder> foldersWrapper = smartsheet.workspaceResources().folderResources().listFolders(workspace.getId(), parameters);

        assertTrue(foldersWrapper.getTotalCount() != null);
    }

    public void testGetFolder() throws SmartsheetException, IOException {
        Folder folder = smartsheet.folderResources().getFolder(newFolder.getId(), EnumSet.of(SourceInclusion.SOURCE));
        smartsheet.folderResources().getFolder(newFolder.getId(), null);

        // Verify results
        assertEquals("New Folder in Folder By Aditi", folder.getName());
    }

    public void testCopyFolder() throws SmartsheetException, IOException {
        ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setDestinationType(DestinationType.FOLDER).setDestinationId(newFolderWorkspace.getId()).setNewName("New Copied folder").build();
        Folder folder1 = smartsheet.folderResources().copyFolder(newFolderHome.getId(), destination, null, null);
        Folder folder2 = smartsheet.folderResources().copyFolder(newFolderHome.getId(), destination, EnumSet.of(FolderCopyInclusion.ALL), EnumSet.of(FolderRemapExclusion.CELLLINKS));
        smartsheet.folderResources().deleteFolder(folder1.getId());
        smartsheet.folderResources().deleteFolder(folder2.getId());
    }

    public void testMoveFolder() throws SmartsheetException, IOException {
        ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setDestinationType(DestinationType.FOLDER).setDestinationId(newFolderWorkspace.getId()).build();
        Folder folder1 = smartsheet.folderResources().moveFolder(newFolderHome.getId(), destination);
        smartsheet.folderResources().deleteFolder(folder1.getId());
    }

    public void testDeleteFolder() throws SmartsheetException, IOException {
        //smartsheet.folderResources().deleteFolder(newFolderHome.getId());
        deleteWorkspace(workspace.getId());
    }
}
