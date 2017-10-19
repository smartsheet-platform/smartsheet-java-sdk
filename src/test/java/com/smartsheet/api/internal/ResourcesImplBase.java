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

import com.smartsheet.api.HttpTestServer;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import org.junit.After;
import org.junit.Before;

public class ResourcesImplBase {

    HttpTestServer server;
    FolderResourcesImpl folderResource;
    JacksonJsonSerializer serializer;

    @Before
    public void baseSetUp() throws Exception {
        // Setup test server
        server = new HttpTestServer();
        server.setPort(9090);
        server.start();

        // Setup the serializer
        JacksonJsonSerializer.setFailOnUnknownProperties(true);
    }

    @After
    public void baseTearDown() throws Exception {
        server.stop();
    }
}
