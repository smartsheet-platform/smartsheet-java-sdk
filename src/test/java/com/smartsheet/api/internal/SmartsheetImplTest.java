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

import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartsheetImplTest extends ResourcesImplBase {

    private SmartsheetImpl smartsheet;
    private HttpClient httpClient;
    private final String baseURI = "http://localhost:9090/1.1/";
    private final String accessToken = "accessToken";

    @Before
    public void setUp() throws Exception {
        httpClient = new DefaultHttpClient();
        smartsheet = new SmartsheetImpl(baseURI, accessToken, httpClient, serializer);
    }

    @Test
    public void testFinalize() {

    }

    @Test
    public void testSmartsheetImpl() {

    }

    @Test
    public void testGetHttpClient() {
        assertSame(httpClient, smartsheet.getHttpClient());
    }

    @Test
    public void testGetJsonSerializer() {
        assertNotNull(smartsheet.getJsonSerializer());
    }

    @Test
    public void testGetBaseURI() {
        assertEquals(baseURI, smartsheet.getBaseURI().toString());
    }

    @Test
    public void testGetAssumedUser() {
        assertNull(smartsheet.getAssumedUser());
    }

    @Test
    public void testGetAccessToken() {
        assertEquals(accessToken, smartsheet.getAccessToken());
    }

    @Test
    public void testHome() {
        assertNotNull(smartsheet.homeResources());
    }

    @Test
    public void testWorkspaces() {
        assertNotNull(smartsheet.workspaceResources());
    }

    @Test
    public void testFolders() {
        assertNotNull(smartsheet.folderResources());
    }

    @Test
    public void testTemplates() {
        assertNotNull(smartsheet.templateResources());
    }

    @Test
    public void testSheets() {
        assertNotNull(smartsheet.sheetResources());
    }

    @Test
    public void testfavorites() {
        assertNotNull(smartsheet.favoriteResources());
    }

    @Test
    public void testUsers() {
        assertNotNull(smartsheet.userResources());
    }

    @Test
     public void testSearch() {
        assertNotNull(smartsheet.searchResources());
    }

    @Test
    public void testReports() {
        assertNotNull(smartsheet.reportResources());
    }

    @Test
    public void testSetAssumedUser() { smartsheet.setAssumedUser("user"); }

    @Test
    public void testSetAccessToken() { smartsheet.setAccessToken("1234"); }

    @Test
    public void testSetAPIScenario() {
        smartsheet.setAPIScenario("Scenario Name");
    }

    @Test
    public void testSights() { assertNotNull(smartsheet.sightResources()); }
}
