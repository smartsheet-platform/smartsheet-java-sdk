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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.AttachmentParentType;
import com.smartsheet.api.models.AttachmentSubType;
import com.smartsheet.api.models.AttachmentType;

public class AssociatedAttachmentResourcesImplTest extends ResourcesImplBase {

	private AssociatedAttachmentResourcesImpl associatedAttachment;

	@Before
	public void setUp() throws Exception {
		associatedAttachment = new AssociatedAttachmentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer),"sheet");
	}

	@Test
	public void testAssociatedAttachmentResourcesImpl() {
	}

	@Test
	public void testAttachFile() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/attachFile.json"));
		File file = new File("src/test/resources/large_sheet.pdf");
		Attachment attachment = associatedAttachment.attachFile(1234L, file, 
				"application/pdf");
		assertTrue(attachment.getId() == 7265404226692996L);
		assertEquals("Testing.PDF", attachment.getName());
		assertEquals(AttachmentType.FILE, attachment.getAttachmentType());
		assertEquals("application/pdf", attachment.getMimeType());
		assertTrue(1831L == attachment.getSizeInKb());
		assertEquals(AttachmentParentType.SHEET, attachment.getParentType());
	}

}
