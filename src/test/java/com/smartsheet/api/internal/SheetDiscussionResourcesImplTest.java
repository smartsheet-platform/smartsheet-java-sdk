package com.smartsheet.api.internal;

import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.DiscussionInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

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
public class SheetDiscussionResourcesImplTest extends ResourcesImplBase {


    private SheetDiscussionResourcesImpl sheetDiscussionResources;

    @Before
    public void setUp() throws Exception {
        sheetDiscussionResources = new SheetDiscussionResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
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
        Discussion newDiscussion = sheetDiscussionResources.createDiscussion(1234L, discussion);

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
            sheetDiscussionResources.createDiscussion(1234L, discussion);
            fail("An exception should have been thrown");
        }catch(InvalidRequestException ex){
            // expected
        }
    }

    @Test
    public void testCreateDiscussionWithAttachment() throws Exception {
        server.setResponseBody(new File("src/test/resources/createDiscussion.json"));
        File file = new File("src/test/resources/large_sheet.pdf");
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
        Discussion newDiscussion = sheetDiscussionResources.createDiscussionWithAttachment(1234L, discussion, file, "application/pdf");

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
            sheetDiscussionResources.createDiscussion(1234L, discussion);
            fail("An exception should have been thrown");
        }catch(InvalidRequestException ex){
            // expected
        }
    }

    @Test
    public void testGetDiscussion() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getDiscussion.json"));

        Discussion discussion = sheetDiscussionResources.getDiscussion(1234L, 5678L);

        assertEquals("New Discussion", discussion.getTitle());
        assertNotNull(discussion.getComments());
        assertTrue(discussion.getComments().size() == 3);
        assertEquals("This text is the body of the first comment4", discussion.getComments().get(0).getText());
        assertNotNull(discussion.getComments().get(0).getCreatedBy());
        assertEquals("Brett Batie", discussion.getComments().get(0).getCreatedBy().getName());
        assertEquals("email@email.com", discussion.getComments().get(0).getCreatedBy().getEmail());
    }

    @Test
    public void testDeleteDiscussion() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteDiscussion.json"));

        sheetDiscussionResources.deleteDiscussion(1234L, 2345L);
    }

    @Test
    public void testGetAllDiscussions() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getAllDiscussions.json"));
        PaginationParameters parameters = new PaginationParameters(false, 1, 1);

        PagedResult<Discussion> newDiscussion = sheetDiscussionResources.listDiscussions(123L, parameters, EnumSet.of(DiscussionInclusion.COMMENTS));
        assertTrue(newDiscussion.getTotalPages() == 1);
        assertTrue(newDiscussion.getPageSize() == 100);
        assertTrue(newDiscussion.getTotalCount() == 1);
        assertTrue(newDiscussion.getData().size() == 1);
    }
}
