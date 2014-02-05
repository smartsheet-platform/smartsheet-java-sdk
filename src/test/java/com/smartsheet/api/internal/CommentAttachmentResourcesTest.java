package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
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
