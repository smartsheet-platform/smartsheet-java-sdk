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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Comment;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SheetCommentResourcesImplTest extends ResourcesImplBase {
    private SheetCommentResourcesImpl sheetCommentResources;

    @Before
    public void setUp() throws Exception  {
        sheetCommentResources = new SheetCommentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }
    @Test
    public void testGetComment() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getComment.json"));

        Comment comment = sheetCommentResources.getComment(1234L, 1245L);
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

        sheetCommentResources.deleteComment(1234L, 2345L);
    }

}
