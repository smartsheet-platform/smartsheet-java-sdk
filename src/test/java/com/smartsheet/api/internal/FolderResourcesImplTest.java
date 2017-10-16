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
import com.smartsheet.api.models.ContainerDestination;
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.DestinationType;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        Folder folder = folderResource.getFolder(123L, EnumSet.of(SourceInclusion.SOURCE));

        // Verify results
        assertEquals("Personal", folder.getName());
        assertEquals(2, folder.getSheets().size());
        assertEquals(0, folder.getFolders().size());
        assertEquals("https://app.smartsheet.com/b/home?lx=uWicCItTmkbxJwpCfQ5wiwW", folder.getSheets().get(0).getPermalink());
    }

    @Test
    public void testUpdateFolder() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateFolder.json"));

        Folder newFolder = new Folder.UpdateFolderBuilder().setName("New Name").build();
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
        PaginationParameters parameters = new PaginationParameters(true,1,1);
        PagedResult<Folder> foldersWrapper = folderResource.listFolders(12345L, parameters);

        assertTrue(foldersWrapper.getPageSize() == 100);
        assertEquals("Folder 1", foldersWrapper.getData().get(0).getName());
        assertEquals("Folder 2", foldersWrapper.getData().get(1).getName());
        assertTrue(7116448184199044L == foldersWrapper.getData().get(0).getId());
    }

    @Test
    public void testCreateFolder() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/createFolder.json"));

        Folder newFolder = new Folder.CreateFolderBuilder().setName("new folder by brett").build();
        Folder createdFolder = folderResource.createFolder(123L, newFolder);

        assertEquals(createdFolder.getName(), newFolder.getName());
    }

    @Test
    public void testCopyFolder() throws Exception {
        server.setResponseBody(new File("src/test/resources/copyFolder.json"));
        ContainerDestination containerDestination = new ContainerDestination();
        containerDestination.setDestinationType(DestinationType.FOLDER);

        Folder folder = folderResource.copyFolder(123L, containerDestination, null, null);
        assertEquals(folder.getPermalink(), "https://{base_url}?lx=lB0JaOh6AX1wGwqxsQIMaA");
    }

    @Test
    public void testMoveFolder() throws Exception {
        server.setResponseBody(new File("src/test/resources/moveFolder.json"));
        ContainerDestination containerDestination = new ContainerDestination();
        containerDestination.setDestinationType(DestinationType.FOLDER);

        Folder folder = folderResource.moveFolder(123L, containerDestination);
        assertTrue(folder.getId() == 4509918431602564L);
    }
}
