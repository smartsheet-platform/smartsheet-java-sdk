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
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        PaginationParameters parameters = new PaginationParameters(true, null, null);
        PagedResult<Folder> foldersWrapper = homeFolderResources.listFolders(parameters);

        assertTrue(foldersWrapper.getPageSize() == 100);
        assertEquals("Folder 1", foldersWrapper.getData().get(0).getName());
        assertEquals("Folder 2", foldersWrapper.getData().get(1).getName());
        assertTrue(7116448184199044L == foldersWrapper.getData().get(0).getId());
    }

    @Test
    public void testCreateFolder() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/createFolders.json"));

        Folder folder = new Folder.CreateFolderBuilder().setName("Hello World").build();

        Folder newFolder = homeFolderResources.createFolder(folder);
        assertTrue(6821399500220292L == newFolder.getId());
        assertEquals("hello world", newFolder.getName());
    }
}
