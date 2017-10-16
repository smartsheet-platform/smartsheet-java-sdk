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

import com.smartsheet.api.HomeFolderResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Home;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Template;
import com.smartsheet.api.models.enums.SourceInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

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
        homes.add(homeResources.getHome(EnumSet.of(SourceInclusion.SOURCE)));
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

        HomeFolderResources folders = homeResources.folderResources();
        PaginationParameters parameters = new PaginationParameters(true, 1,1);
        assertNotNull(folders.listFolders(parameters));
        assertTrue(folders.listFolders(parameters).getTotalPages() == 1);
    }

}
