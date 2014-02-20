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

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.internal.http.DefaultHttpClient;

public class CommentAttachmentResourcesTest extends ResourcesImplBase {

	private CommentAttachmentResources commentAttachmentResource;

	@Before
	public void setUp() throws Exception {
		commentAttachmentResource = new CommentAttachmentResources(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer));
	}

	@Test
	public void testCommentAttachmentResources() {}

	@Test
	public void testListAttachments() {
		try{
			commentAttachmentResource.listAttachments(1234L);
			fail("Should have thrown an exception");
		}catch(UnsupportedOperationException ex){
			// Expected
		}
	}
}
