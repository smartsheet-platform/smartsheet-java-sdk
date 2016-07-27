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
import com.smartsheet.api.models.enums.AttachmentType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class AttachmentResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    File file;
    long sheetId;
    long rowId;
    long commentId;
    long discussionId;
    long sheetAttachmentId;
    long attachmentWithVersionId;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testAttachmentMethods() throws SmartsheetException, IOException {
        //smartsheet.setAssumedUser("ericyan99@gmail.com");
        //UserProfile user= smartsheet.userResources().getCurrentUser();
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());
        sheetId = sheet.getId();

        file = new File("src/integration-test/resources/small-text.txt");

        testattachFileSheet();
        testattachFileRow();
        testattachFileComment();
        testattachUrl();
        testListAttachments();
        testAttachNewVersion();
        testListAllVersions();
        testDeleteAllVersions();
        testDeleteAttachment();
    }

    public void testattachFileSheet() throws SmartsheetException, IOException {

        //attach file to sheet
        Attachment attachment = smartsheet.sheetResources().attachmentResources().attachFile(sheetId, file,
                "text/plain");
        testGetAttachmentSheet(attachment.getId());
    }

    public void testGetAttachmentSheet(long attachmentId) throws SmartsheetException, IOException {
        Attachment attachment = smartsheet.sheetResources().attachmentResources().getAttachment(sheetId, attachmentId);
        assertNotNull(attachment);
        sheetAttachmentId = attachment.getId();
    }

    public void testattachFileRow() throws SmartsheetException, IOException {
        //add rows
        Row row = addRows(sheetId);
        rowId = row.getId();

        //attach file to row
        Attachment attachment = smartsheet.sheetResources().rowResources().attachmentResources().attachFile(sheetId, rowId,file,
                "text/plain");
        testGetAttachmentRow(attachment.getId());
    }

    public void testGetAttachmentRow(long attachmentId) throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Attachment> attachments = smartsheet.sheetResources().rowResources().attachmentResources().getAttachments(sheetId, rowId, parameters);
        assertNotNull(attachments);
    }

    //smartsheet.sheetResources().commentResources().attachmentResources().attachFile(sheetId, commentId, file,"text/plain");
    public void testattachFileComment() throws SmartsheetException, IOException {
        //create comment to add to discussion
        Comment comment = new Comment.AddCommentBuilder().setText("This is a test comment").build();

        Discussion discussion = new Discussion.CreateDiscussionBuilder().setTitle("New Discussion").setComment(comment).build();
        discussion = smartsheet.sheetResources().discussionResources().createDiscussion(sheetId, discussion);

        //comment = smartsheet.sheetResources().discussionResources().comments().addComment(sheetId,discussion.getId(), comment);
        comment = discussion.getComments().get(0);
        commentId = comment.getId();
        discussionId = discussion.getId();

        File file1 = new File("src/integration-test/resources/small-text.txt");
        //attach file to comment
        Attachment attachment = smartsheet.sheetResources().commentResources().attachmentResources().attachFile(sheetId, commentId, file1,
                "text/plain");
        testGetAttachmentComment(attachment.getId());
    }

    public void testGetAttachmentComment(long attachmentId) throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Attachment> attachments = smartsheet.sheetResources().discussionResources().attachmentResources().getAttachments(sheetId, discussionId, parameters);
        assertNotNull(attachments);
    }

    public void testattachUrl() throws SmartsheetException, IOException {

        Attachment attachment = new Attachment.CreateAttachmentBuilder().setUrl("https://www.smartsheet.com").setAttachmentType(AttachmentType.LINK).setName("New Name").build();

        //attach file to sheet
        Attachment attachedUrl = smartsheet.sheetResources().attachmentResources().attachUrl(sheetId, attachment);
        assertNotNull(attachedUrl);

        //attach file to row
        attachedUrl = smartsheet.sheetResources().rowResources().attachmentResources().attachUrl(sheetId, rowId, attachment);
        assertNotNull(attachedUrl);

        //attach file to comment
        attachedUrl = smartsheet.sheetResources().commentResources().attachmentResources().attachUrl(sheetId, commentId,attachment);
        assertNotNull(attachedUrl);

    }

    public void testListAttachments() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();

        PagedResult<Attachment> attachments = smartsheet.sheetResources().attachmentResources().listAttachments(sheetId, parameters);
        assertNotNull(attachments);
    }

    public void testDeleteAttachment() throws SmartsheetException, IOException {
        //smartsheet.sheetResources().attachmentResources().deleteAttachment(sheetId, sheetAttachmentId);
        deleteSheet(sheetId);
    }

    public void testAttachNewVersion() throws IOException, SmartsheetException  {
        Attachment attachment = smartsheet.sheetResources().attachmentResources().versioningResources().attachNewVersion(sheetId, sheetAttachmentId, file,
                "text/plain");
        assertNotNull(attachment);
        attachmentWithVersionId = attachment.getId();
    }

    public void testListAllVersions() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Attachment> attachments = smartsheet.sheetResources().attachmentResources().versioningResources().listAllVersions(sheetId, attachmentWithVersionId, parameters);
        PagedResult<Attachment> attachments1 = smartsheet.sheetResources().attachmentResources().versioningResources().listAllVersions(sheetId, attachmentWithVersionId, null);
        assertNotNull(attachments);
    }

    public void testDeleteAllVersions() throws SmartsheetException, IOException {
        smartsheet.sheetResources().attachmentResources().versioningResources().deleteAllVersions(sheetId, attachmentWithVersionId);
    }
}
