/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.Comment;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.Sheet;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class CommentResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    Sheet newSheet;
    Discussion newDiscussion;
    Comment newComment;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testCommentMethods() throws SmartsheetException, IOException {
        testAddComment();
        testGetComment();
        testAddCommentWithAttachment();
        testDeleteComment();
    }

    public void testAddComment() throws SmartsheetException, IOException {
        //create new sheet
        newSheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a new comment.").build();

        Discussion discussion = new Discussion.CreateDiscussionBuilder().setTitle("New Discussion").setComment(comment).build();
        newDiscussion = smartsheet.sheetResources().discussionResources().createDiscussion(newSheet.getId(), discussion);

        Comment newComment1 = new Comment.AddCommentBuilder().setText("This is a test comment").build();
        newComment = smartsheet.sheetResources().discussionResources().commentResources().addComment(newSheet.getId(),newDiscussion.getId(), newComment1);
    }

    public void testAddCommentWithAttachment() throws SmartsheetException, IOException {
        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a test comment").build();
        File file = new File("src/integration-test/resources/small-text.txt");

        Comment comment1=  smartsheet.sheetResources().discussionResources().commentResources().addCommentWithAttachment(newSheet.getId(), newDiscussion.getId(), comment, file, "application/pdf");
        assertNotNull(comment1);
        file = null;
    }

    public void testGetComment() throws SmartsheetException, IOException {
        Comment comment = smartsheet.sheetResources().commentResources().getComment(newSheet.getId(), newComment.getId());

        assertNotNull(comment);
    }

    public void testDeleteComment() throws SmartsheetException, IOException {
        smartsheet.sheetResources().commentResources().deleteComment(newSheet.getId(), newComment.getId());
        deleteSheet(newSheet.getId());
    }
}
