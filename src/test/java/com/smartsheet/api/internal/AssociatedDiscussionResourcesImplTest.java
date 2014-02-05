package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
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
		server.setResponseBody(new File("src/test/resources/createDiscussion.json"));//FIXME: create this file tomorrow
		
		// Test success
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment();
		comment.setText("This is a test.");
		comments.add(comment);
		Discussion discussion = new Discussion();
		discussion.setTitle("New Discussion");
		discussion.setComments(comments);
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
