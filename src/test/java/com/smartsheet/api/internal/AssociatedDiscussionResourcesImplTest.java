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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.User;

public class AssociatedDiscussionResourcesImplTest extends ResourcesImplBase {

	private AssociatedDiscussionResourcesImpl discussionResources;

	@Before
	public void setUp() throws Exception {
		discussionResources = new AssociatedDiscussionResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer),"sheet");
	}

	@Test
	public void testAssociatedDiscussionResourcesImpl() {
	}

	@Test
	public void testCreateDiscussion() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/createDiscussion.json"));

		// Test success
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment();
		comment.setText("This is a test.");
		comment.setAttachments(new ArrayList<Attachment>());
		comments.add(comment);
		Discussion discussion = new Discussion();
		discussion.setTitle("New Discussion");
		discussion.setComments(comments);
		discussion.setLastCommentedUser(new User());
		discussion.setLastCommentedAt(new Date());
		discussion.setCommentAttachments(new ArrayList<Attachment>());
		Discussion newDiscussion = discussionResources.createDiscussion(1234L, discussion);

		assertNotNull(newDiscussion.getComments());
		assertTrue(newDiscussion.getComments().size() == 1);
		assertEquals("Brett Batie", newDiscussion.getComments().get(0).getCreatedBy().getName());
		assertEquals("email@email.com", newDiscussion.getComments().get(0).getCreatedBy().getEmail());


		// Test failure - CreatedBy not allowed & only one comment can be added when creating a discussion.
		server.setStatus(400);
		server.setResponseBody(new File("src/test/resources/createDiscussion_1032.json"));
		comment = new Comment();
		User user = new User();
		user.setName("John Doe");
		user.setEmail("email@email.com");
		comment.setCreatedBy(user);
		comment.setText("This is a test.");
		comments.add(comment);
		discussion.setComments(comments);
		try{
			discussionResources.createDiscussion(1234L, discussion);
			fail("An exception should have been thrown");
		}catch(InvalidRequestException ex){
			// expected
		}
	}

}
