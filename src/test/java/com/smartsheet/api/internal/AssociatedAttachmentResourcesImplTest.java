package com.smartsheet.api.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public void testListAttachments() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/listAssociatedAttachments.json"));
		
		List<Attachment> attachments = associatedAttachment.listAttachments(1234L);
		assertTrue(attachments.size() == 4);
		assertTrue(attachments.get(0).getSizeInKb() == 102400);
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

	@Test
	public void testAttachURL() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/attachLink.json"));
		
		Attachment attachment = new Attachment();
		attachment.setUrl("http://www.smartsheet.com/sites/all/themes/blue_sky/logo.png");
		attachment.setAttachmentType(AttachmentType.LINK);
		attachment.setUrlExpiresInMillis(1L);
		attachment.setAttachmentSubType(AttachmentSubType.PDF);
		
		Attachment newAttachment = associatedAttachment.attachURL(1234L, attachment);
		assertEquals("http://www.smartsheet.com/sites/all/themes/blue_sky/logo.png", newAttachment.getName());
		assertEquals(AttachmentType.LINK, newAttachment.getAttachmentType());
	}

}
