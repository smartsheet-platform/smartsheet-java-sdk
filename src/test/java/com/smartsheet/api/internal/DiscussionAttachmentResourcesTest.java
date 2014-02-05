package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Attachment;

public class DiscussionAttachmentResourcesTest extends ResourcesImplBase  {

	private DiscussionAttachmentResources discussionAttachmentResources;

	@Before
	public void setUp() throws Exception {
		discussionAttachmentResources = new DiscussionAttachmentResources(new SmartsheetImpl("http://localhost:9090/1.1/", 
				"accessToken", new DefaultHttpClient(), serializer));
	}

	@Test
	public void testAttachFileLongFileString() {
		try{
			discussionAttachmentResources.attachFile(1234L, new File("src/test/rescoures/getPDF.pdf"), "application/pdf");
			fail("Exception should have been thrown.");
		}catch(UnsupportedOperationException ex){
			// Expected
		}
	}
	
	@Test
	public void testAttachFileLongFileStringLong(){
		try{
			discussionAttachmentResources.attachFile(1234L, new File("src/test/resources/getPDF.pdf"), "application/pdf", 1234L);
			fail("Exception should have been thrown.");
		}catch(UnsupportedOperationException ex){
			// Expected
		}
	}

	@Test
	public void testAttachURL() {
		try{
			discussionAttachmentResources.attachURL(1234L, new Attachment());
			fail("Exception should have been thrown.");
		}catch(UnsupportedOperationException ex){
			// Expected
		}
	}

	@Test
	public void testDiscussionAttachmentResources() {}

}
