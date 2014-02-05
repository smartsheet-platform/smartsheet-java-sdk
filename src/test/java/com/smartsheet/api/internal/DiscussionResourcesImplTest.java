package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;

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

}
