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
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.DiscussionInclusion;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.assertNotNull;

public class DiscussionResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    Sheet sheet;
    Discussion newDiscussionRow;
    Discussion newDiscussionSheet;
    Row row;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testDiscussionMethods() throws SmartsheetException, IOException {
        testCreateDiscussionOnSheet();
        testCreateDiscussionOnRow();
        testGetRowDiscussions();
        testGetAllDiscussions();
        testGetDiscussion();
        testDeleteDiscussion();
        testCreateDiscussionWithAttachmentOnRow();
    }

    public void testCreateDiscussionOnSheet() throws SmartsheetException, IOException {

        //create sheet
        sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a test comment").build();

        File file = new File("src/integration-test/resources/small-text.txt");

        Discussion discussion = new Discussion.CreateDiscussionBuilder().setTitle("New Discussion").setComment(comment).build();
        newDiscussionSheet = smartsheet.sheetResources().discussionResources().createDiscussion(sheet.getId(), discussion);

        //newDiscussionSheet = smartsheet.sheetResources().discussionResources().createDiscussionWithAttachment(sheet.getId(), discussion, file1, "text/plain");

        assertNotNull(newDiscussionSheet);
    }

    public void testCreateDiscussionOnRow() throws SmartsheetException, IOException {
        //add rows
        row = addRows(sheet.getId());

        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a test comment").build();

        Discussion discussion = new Discussion.CreateDiscussionBuilder().setTitle("New Discussion").setComment(comment).build();
        Discussion newDiscussionWithAttachment = smartsheet.sheetResources().rowResources().discussionResources().createDiscussion(sheet.getId(), row.getId(), discussion);

        assertNotNull(newDiscussionWithAttachment);
    }

    public void testCreateDiscussionWithAttachmentOnRow() throws SmartsheetException, IOException {

        //create sheet
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //add rows
        row = addRows(sheet.getId());

        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a test comment").build();

        Discussion discussion = new Discussion.CreateDiscussionBuilder().setTitle("New Discussion").setComment(comment).build();
        File file = new File("src/integration-test/resources/small-text.txt");
        newDiscussionRow = smartsheet.sheetResources().rowResources().discussionResources().createDiscussionWithAttachment(sheet.getId(), row.getId(), discussion, file, "text/plain");

        assertNotNull(newDiscussionRow);
    }

    public void testGetRowDiscussions() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();

        // Get all discussions (omit 'include' parameter and pagination parameters).
        smartsheet.sheetResources().rowResources().discussionResources().listDiscussions(sheet.getId(), row.getId(), null, null);

        // Get all discussions (specify 'include' parameter with values of 'comments' and 'attachments', and 'includeAll' parameter with value of "true").
        PagedResult<Discussion> newDiscussion = smartsheet.sheetResources().rowResources().discussionResources().listDiscussions(sheet.getId(), row.getId(), parameters, EnumSet.of(DiscussionInclusion.COMMENTS, DiscussionInclusion.ATTACHMENTS));
        assertNotNull(newDiscussion);
    }

    public void testGetAllDiscussions() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Discussion> newDiscussion = smartsheet.sheetResources().discussionResources().listDiscussions(sheet.getId(), null, null);
        newDiscussion = smartsheet.sheetResources().discussionResources().listDiscussions(sheet.getId(), parameters, EnumSet.of(DiscussionInclusion.COMMENTS, DiscussionInclusion.ATTACHMENTS));
        assertNotNull(newDiscussion);
    }

    public void testGetDiscussion() throws SmartsheetException, IOException {
        Discussion newDiscussion = smartsheet.sheetResources().discussionResources().getDiscussion(sheet.getId(), newDiscussionSheet.getId());

        assertNotNull(newDiscussion);
    }

    public void testDeleteDiscussion() throws SmartsheetException, IOException {
        smartsheet.sheetResources().discussionResources().deleteDiscussion(sheet.getId(), newDiscussionSheet.getId());
        //cleanup
        deleteSheet(sheet.getId());
    }
}
