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
import com.smartsheet.api.models.Template;

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
		
		List<Template> templates = templateResources.listTemplates();
		assertNotNull(templates);
		assertEquals(11,templates.size());
		assertEquals(AccessLevel.ADMIN, templates.get(0).getAccessLevel());
		assertEquals(4705477956265860L, templates.get(0).getId().longValue());
		assertEquals("testing1234", templates.get(0).getDescription());
		assertEquals("<feature> -  Issues Template", templates.get(0).getName());
	}
}
