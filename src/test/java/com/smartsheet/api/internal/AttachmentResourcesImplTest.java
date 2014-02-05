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

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Attachment;

public class AttachmentResourcesImplTest extends ResourcesImplBase {

	private AttachmentResourcesImpl attachmentResourcesImpl;

	@Before
	public void setUp() throws Exception {
		attachmentResourcesImpl = new AttachmentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer));
	}

	@Test
	public void testAttachmentResourcesImpl() {}

	@Test
	public void testGetAttachment() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getAttachment.json"));

		Attachment attachment = attachmentResourcesImpl.getAttachment(1234L);
		assertNotNull(attachment.getUrl());
		assertEquals("AbstractResources.mup",attachment.getName());
	}

	@Test
	public void testDeleteAttachment() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteAttachment.json"));
		
		attachmentResourcesImpl.deleteAttachment(1234L);
	}

}
