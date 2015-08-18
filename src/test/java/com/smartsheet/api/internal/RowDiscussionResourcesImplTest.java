package com.smartsheet.api.internal;

import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.DiscussionInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.EnumSet;

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
public class RowDiscussionResourcesImplTest extends ResourcesImplBase {

    private RowDiscussionResourcesImpl discussionRowResources;
    @Before
    public void setUp() throws Exception {
        discussionRowResources = new RowDiscussionResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testCreateDiscussion() throws Exception {
        server.setResponseBody(new File("src/test/resources/createDiscussionOnRow.json"));

        Discussion discussion = new Discussion();
        discussion.setTitle("new discussion");
        Discussion newDiscussion = discussionRowResources.createDiscussion(1234L, 5678L, discussion);
        assertEquals("This is a new discussion", newDiscussion.getTitle());
        assertTrue(newDiscussion.getId() == 4583173393803140L);
    }

    @Test
    public void testCreateDiscussionWithAttachment() throws Exception {
        server.setResponseBody(new File("src/test/resources/createDiscussionOnRow.json"));
        File file = new File("src/test/resources/large_sheet.pdf");
        Comment comment = new Comment.AddCommentBuilder().setText("New comment").build();
        Discussion discussion = new Discussion.CreateDiscussionBuilder().setComment(comment).setTitle("Some title").build();

        Discussion newDiscussion = discussionRowResources.createDiscussionWithAttachment(123L, 456L, discussion, file, "application/pdf");
        assertEquals(newDiscussion.getTitle(), "This is a new discussion");
    }

    @Test
    public void testListDiscussions() throws Exception {
        server.setResponseBody(new File("src/test/resources/getRowDiscussions.json"));

        Discussion discussion = new Discussion();
        discussion.setTitle("new discussion");
        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<Discussion> newDiscussion = discussionRowResources.listDiscussions(1234L, 5678L, parameters, EnumSet.of(DiscussionInclusion.COMMENTS));
        assertEquals("Lincoln", newDiscussion.getData().get(0).getTitle());
        assertTrue(newDiscussion.getData().get(0).getId() == 3138415114905476L);
    }
}
