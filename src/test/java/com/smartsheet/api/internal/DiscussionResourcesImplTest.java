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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;
import org.omg.PortableInterceptor.DISCARDING;

public class DiscussionResourcesImplTest extends ResourcesImplBase {

	private DiscussionResourcesImpl discussionResources;

	@Before
	public void setUp() throws Exception {
		discussionResources = new DiscussionResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testDiscussionResourcesImpl() {
	}

	@Test
	public void testGetDiscussion() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getDiscussion.json"));
		
		Discussion discussion = discussionResources.getDiscussion(1234L);
		
		assertEquals("New Discussion", discussion.getTitle());
		assertNotNull(discussion.getComments());
		assertTrue(discussion.getComments().size() == 3);
		assertEquals("This text is the body of the first comment4", discussion.getComments().get(0).getText());
		assertNotNull(discussion.getComments().get(0).getCreatedBy());
		assertEquals("Brett Batie", discussion.getComments().get(0).getCreatedBy().getName());
		assertEquals("email@email.com", discussion.getComments().get(0).getCreatedBy().getEmail());
	}

	@Test
	public void testAddDiscussionComment() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/addDiscussionComment.json"));
		
		Comment comment = new Comment();
		comment.setText("Some new Text");
		
		Comment newComment = discussionResources.addDiscussionComment(1234L, comment);
		
		assertEquals("Some new text",newComment.getText());
		assertEquals("Brett Batie", newComment.getCreatedBy().getName());
	}

	@Test
	public void testAttachments() {
		assertNull(discussionResources.attachments());
	}

	@Test
	public void testCreateDiscussionOnRow() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/createDiscussionOnRow.json"));

		Discussion discussion = new Discussion();
		discussion.setTitle("new discussion");
		Discussion newDiscussion = discussionResources.createDiscussionOnRow(1234L, 5678L, discussion);
		assertEquals("This is a new discussion", newDiscussion.getTitle());
		assertTrue(newDiscussion.getId() == 4583173393803140L);
	}

	@Test
	public void testDeleteDiscussion() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteDiscussion.json"));

		discussionResources.deleteDiscussion(1234L, 2345L);
	}

	@Test
	public void testGetAllDiscussions() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getAllDiscussions.json"));
		PaginationParameters parameters = new PaginationParameters(false, 1, 1);

		DataWrapper<Discussion> newDiscussion = discussionResources.getAllDiscussions(123L,parameters);
		assertTrue(newDiscussion.getTotalPages() == 1);
		assertTrue(newDiscussion.getPageSize() == 100);
		assertTrue(newDiscussion.getTotalCount() == 1);
		assertTrue(newDiscussion.getData().size() == 1);
	}
}
