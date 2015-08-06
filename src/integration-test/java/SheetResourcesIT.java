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
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class SheetResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    Sheet sheetHome;
    Sheet newSheetHome;
    Sheet newSheetTemplate;
    Workspace workspace;

    Folder folder;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testSheetMethods() throws SmartsheetException, IOException {
        testCreateSheetHome();
        testCreateSheetHomeFromTemplate();
        testCreateSheetInFolder();
        testCreateSheetInFolderFromTemplate();
        testCreateSheetInWorkspace();
        testCreateSheetInWorkspaceFromTemplate();
        testGetSheet();
        testGetSheetVersion();
        testGetSheetAsExcel();
        testGetSheetAsPDF();
        testGetPublishStatus();
        testUpdateSheet();
        testUpdatePublishStatus();
        testListSheets();
        testListOrganizationSheets();
        testSendSheet();
        testDeleteSheet();;
    }

    public void testCreateSheetHome() throws SmartsheetException, IOException {

        // create sheet object
        sheetHome = createSheetObject();

        //create sheet
        newSheetHome = smartsheet.sheetResources().createSheet(sheetHome);
        if (newSheetHome.getColumns().size() != 3) {
            fail("Issue creating a sheet");
        }
    }

    public void testCreateSheetHomeFromTemplate() throws SmartsheetException, IOException {

        Sheet sheet = new Sheet.CreateFromTemplateOrSheetBuilder().setFromId(newSheetHome.getId()).setName("New test sheet from template").build();
        newSheetTemplate = smartsheet.sheetResources().createSheetFromTemplate(sheet, EnumSet.of(SheetTemplateInclusion.ATTACHMENTS, SheetTemplateInclusion.DATA, SheetTemplateInclusion.DISCUSSIONS));

        assertEquals(AccessLevel.OWNER, newSheetHome.getAccessLevel());
    }

    public void testCreateSheetInFolder() throws SmartsheetException, IOException {
        //create a new folder and get the id
        folder = createFolder();

        Sheet newSheetFolder = smartsheet.sheetResources().createSheetInFolder(folder.getId(), sheetHome);

        if (newSheetFolder.getColumns().size() != 3) {
            fail("Issue creating a sheet");
        }
    }

    public void testCreateSheetInFolderFromTemplate() throws SmartsheetException, IOException {

        Sheet sheet = new Sheet.CreateFromTemplateOrSheetBuilder().setFromId(newSheetHome.getId()).setName("New test sheet from template").build();
        Sheet newSheetFromTemplate= smartsheet.sheetResources().createSheetInFolderFromTemplate(folder.getId(), sheet, null);

        if (newSheetFromTemplate.getId().toString().isEmpty() || newSheetFromTemplate.getAccessLevel() != AccessLevel.OWNER
                || newSheetFromTemplate.getPermalink().toString().isEmpty()) {
            fail("Sheet not correctly copied");
        }
    }

    public void testCreateSheetInWorkspace() throws SmartsheetException, IOException {
        //create temporary workspace
        workspace = createWorkspace("New Test Workspace");

        Sheet newSheet = smartsheet.sheetResources().createSheetInWorkspace(workspace.getId(), sheetHome);
        assertEquals(3, newSheet.getColumns().size());

        //delete temporary workspace
        //testDeleteWorkspace(workspace.getId());
    }

    public void testCreateSheetInWorkspaceFromTemplate() throws SmartsheetException, IOException {
        Sheet sheet = new Sheet.CreateFromTemplateOrSheetBuilder().setFromId(newSheetHome.getId()).setName("New test sheet in workspace from template").build();
        Sheet newSheetFromTemplate = smartsheet.sheetResources().createSheetInWorkspaceFromTemplate(workspace.getId(), sheet, EnumSet.allOf(SheetTemplateInclusion.class));

        if (newSheetFromTemplate.getId().toString().isEmpty() || newSheetFromTemplate.getAccessLevel() != AccessLevel.OWNER
                || newSheetFromTemplate.getPermalink().toString().isEmpty()) {
            fail("Sheet not correctly copied");
        }
    }

    public void testGetSheet() throws SmartsheetException, IOException {
        Sheet sheet = smartsheet.sheetResources().getSheet(newSheetHome.getId(), null, null, null, null, null, null, null);

        assertEquals(sheet.getPermalink(), newSheetHome.getPermalink());
    }

    public void testGetSheetVersion() throws SmartsheetException, IOException {
        int version = smartsheet.sheetResources().getSheetVersion(newSheetHome.getId());
        if (version != 1) {
            fail("Issue getting sheet version");
        }
    }

    public void testGetSheetAsExcel() throws SmartsheetException, IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        smartsheet.sheetResources().getSheetAsExcel(newSheetHome.getId(), output);

        assertNotNull(output);
    }

    public void testGetSheetAsPDF() throws SmartsheetException, IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        smartsheet.sheetResources().getSheetAsPDF(newSheetHome.getId(), output, PaperSize.A1);
        smartsheet.sheetResources().getSheetAsCSV(newSheetHome.getId(), output);

        assertNotNull(output);
    }

    public void testUpdateSheet() throws SmartsheetException, IOException {
        Sheet sheet = new Sheet.UpdateSheetBuilder().setName("Updated Name by Aditi").build();
        Sheet newSheet = smartsheet.sheetResources().updateSheet(newSheetHome.getId(), sheet);

        assertEquals(sheet.getName(), newSheet.getName());
    }

    public void testUpdatePublishStatus() throws SmartsheetException, IOException {
        SheetPublish sheetPublish = new SheetPublish.PublishStatusBuilder().setIcalEnabled(true).setReadOnlyFullEnabled(true).setReadWriteEnabled(true).setReadOnlyLiteEnabled(true).build();
        SheetPublish newSheetPublish = smartsheet.sheetResources().updatePublishStatus(newSheetHome.getId(), sheetPublish);

        assertTrue(newSheetPublish.getReadOnlyFullEnabled());
    }

    public void testGetPublishStatus() throws SmartsheetException, IOException {
        SheetPublish publishStatus = smartsheet.sheetResources().getPublishStatus(newSheetHome.getId());
        assertNotNull(publishStatus);
    }

    public void testListSheets() throws SmartsheetException, IOException {
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(false).setPageSize(1).setPage(1).build();
        PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets(EnumSet.of(SourceInclusion.SOURCE), parameters);
        smartsheet.sheetResources().listSheets(null, null);

        assertTrue(sheets.getPageNumber() == 1);
    }

    public void testListOrganizationSheets() throws SmartsheetException, IOException {
        //PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(false).setPageSize(1).setPage(1).build();
        //PagedResult<Sheet> sheets = smartsheet.sheetResources().listOrganizationSheets(parameters);

        //assertTrue(sheets.getPageNumber() == 1);
    }

    public void testattachFile() throws SmartsheetException, IOException {

//        File file = new File("/Users/anioding/smartsheet-sdk-integrationTest/large_sheet.pdf");
//        Attachment attachment = smartsheet.sheetResources().attachmentResources().attachFile(1234L, 345L, file,
//                "application/pdf");
//        assertTrue(attachment.getId() == 7265404226692996L);
//        assertEquals("Testing.PDF", attachment.getName());
//        assertEquals(AttachmentType.FILE, attachment.getAttachmentType());
//        assertEquals("application/pdf", attachment.getMimeType());
//        assertTrue(1831L == attachment.getSizeInKb());
//        assertEquals(AttachmentParentType.SHEET, attachment.getParentType());
    }

    public void testDeleteSheet() throws SmartsheetException, IOException {
        smartsheet.sheetResources().deleteSheet(newSheetHome.getId());
        smartsheet.sheetResources().deleteSheet(newSheetTemplate.getId());
        //cleanup
        deleteWorkspace(workspace.getId());
        deleteFolder(folder.getId());
    }

    public void testSendSheet() throws SmartsheetException, IOException {
        List<Recipient> recipients = new ArrayList<Recipient>();
        RecipientEmail recipientEmail = new RecipientEmail.AddRecipientEmailBuilder().setEmail("test.user@smartsheet.com").build();

        recipients.add(recipientEmail);
        FormatDetails formatDetails = new FormatDetails();
        formatDetails.setPaperSize(PaperSize.A0);

        SheetEmail email = new SheetEmail.AddSheetEmailBuilder().setFormat(SheetEmailFormat.PDF).setFormatDetails(formatDetails).build();
        email.setSendTo(recipients);

        //smartsheet.reportResources().sendReport(reportsWrapper.getData().get(0).getId(), email);
        smartsheet.sheetResources().sendSheet(newSheetHome.getId(), email);
    }
}
