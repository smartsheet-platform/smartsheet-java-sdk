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
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Template;
import com.smartsheet.api.models.enums.AccessLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemplateResourcesImplTest extends ResourcesImplBase {

    private TemplateResourcesImpl templateResources;

    @Before
    public void setUp() throws Exception {
        templateResources = new TemplateResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testTemplateResourcesImpl() {}

    @Test
    public void testListTemplates() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/listTemplates.json"));

        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<Template> templates = templateResources.listUserCreatedTemplates(parameters);

        assertNotNull(templates);
        assertEquals("template 1", templates.getData().get(0).getName());
        assertEquals(AccessLevel.OWNER, templates.getData().get(0).getAccessLevel());
        assertEquals(3457273486960516L, templates.getData().get(0).getId().longValue());
        assertEquals("This is template 1", templates.getData().get(0).getDescription());
    }

    @Test
    public void testListPublicTemplates() throws IOException, SmartsheetException {
        server.setResponseBody(new File("src/test/resources/listTemplates.json"));

        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<Template> templates = templateResources.listPublicTemplates(parameters);

        assertNotNull(templates);
        assertEquals("template 1", templates.getData().get(0).getName());
        assertEquals(AccessLevel.OWNER, templates.getData().get(0).getAccessLevel());
        assertEquals(3457273486960516L, templates.getData().get(0).getId().longValue());
        assertEquals("This is template 1", templates.getData().get(0).getDescription());
    }
}
