package com.smartsheet.api.internal;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.enums.AttachmentParentType;
import com.smartsheet.api.models.enums.AttachmentSubType;
import com.smartsheet.api.models.enums.AttachmentType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class CommentAttachmentResourcesImplTest extends ResourcesImplBase {

    private  CommentAttachmentResourcesImpl commentAttachmentResources;

    @Before
    public void setUp() throws Exception {
        commentAttachmentResources = new CommentAttachmentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testAttachURL() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/attachLink.json"));

        Attachment attachment = new Attachment();
        attachment.setUrl("http://www.smartsheet.com/sites/all/themes/blue_sky/logo.png");
        attachment.setAttachmentType(AttachmentType.LINK);
        attachment.setUrlExpiresInMillis(1L);
        attachment.setAttachmentSubType(AttachmentSubType.PDF);

        Attachment newAttachment = commentAttachmentResources.attachUrl(1234L, 3456L, attachment);
        assertEquals("Search Engine", newAttachment.getName());
        assertEquals(AttachmentType.LINK, newAttachment.getAttachmentType());
    }

    @Test
    public void testattachFile() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/attachFile.json"));
        File file = new File("src/test/resources/large_sheet.pdf");
        Attachment attachment = commentAttachmentResources.attachFile(1234L, 345L, file,
                "application/pdf");
        assertTrue(attachment.getId() == 7265404226692996L);
        assertEquals("Testing.PDF", attachment.getName());
        assertEquals(AttachmentType.FILE, attachment.getAttachmentType());
        assertEquals("application/pdf", attachment.getMimeType());
        assertTrue(1831L == attachment.getSizeInKb());
        assertEquals(AttachmentParentType.SHEET, attachment.getParentType());
    }

    @Test
    public void testAttachFileAsInputStream() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/attachFile.json"));
        File file = new File("src/test/resources/large_sheet.pdf");
        InputStream inputStream = new FileInputStream(file);
        Attachment attachment = commentAttachmentResources.attachFile(1234L, 345L, inputStream,
                "application/pdf", file.length(), file.getName());
        assertTrue(attachment.getId() == 7265404226692996L);
        assertEquals("Testing.PDF", attachment.getName());
        assertEquals(AttachmentType.FILE, attachment.getAttachmentType());
        assertEquals("application/pdf", attachment.getMimeType());
        assertTrue(1831L == attachment.getSizeInKb());
        assertEquals(AttachmentParentType.SHEET, attachment.getParentType());
    }

//    @Test
//    public void testAttachFileWithMultipartUpload() throws SmartsheetException, IOException {
//        server.setResponseBody(new File("src/test/resources/attachFile.json"));
//        File file = new File("src/test/resources/large_sheet.pdf");
//        Comment comment = new Comment.AddCommentBuilder().setText("new comment").build();
//        comment.setId(345L);
//
//        Attachment attachment = commentAttachmentResources.attachFileWithMultipartUpload(123L, comment, file, "application/pdf");
//
//    }
}
