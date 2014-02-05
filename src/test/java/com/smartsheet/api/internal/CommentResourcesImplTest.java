package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Comment;

public class CommentResourcesImplTest extends ResourcesImplBase  {

	private CommentResourcesImpl commentResources;

	@Before
	public void setUp() throws Exception  {
		commentResources = new CommentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testCommentResourcesImpl() {
	}

	@Test
	public void testGetComment() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getComment.json"));
		
		Comment comment = commentResources.getComment(1234L);
		assertEquals(3831661625403268L, comment.getId().longValue());
		assertEquals("This text is the body of the first comment", comment.getText());
		assertEquals("Brett Batie",comment.getCreatedBy().getName());
		assertEquals("email@email.com", comment.getCreatedBy().getEmail());
		
		// Test equals method
		Comment newComment = new Comment();
		newComment.setId(3831661625403268L);
		assertTrue(comment.hashCode() == newComment.hashCode());
	}

	@Test
	public void testDeleteComment() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteComment.json"));

		commentResources.deleteComment(1234L);
	}

	@Test
	public void testAttachments() {
		assertNotNull(commentResources.attachments());
	}

}
