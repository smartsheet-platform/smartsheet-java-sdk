package com.smartsheet.api.internal;

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
		assertEquals("<feature> -  Issues Template", templates.get(0).getName());
	}
}
