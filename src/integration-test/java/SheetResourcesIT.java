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
import com.smartsheet.api.models.enums.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        testCopySheet();
        testMoveSheet();
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
        testPublishSheetDefaults();
        testPublishSheet();
        testUpdatePublishStatus();
        testListSheets();
        testListOrganizationSheets();
        testSendSheet();
        testCreateUpdateRequest();
        testDeleteSheet();
    }

    public void testCreateSheetHome() throws SmartsheetException, IOException {

        // create sheet object
        sheetHome = createSheetObject();

        //create sheet
        newSheetHome = smartsheet.sheetResources().createSheet(sheetHome);
        if (newSheetHome.getColumns().size() != sheetHome.getColumns().size()) {
            fail("Issue creating a sheet");
        }
    }

    public void testCopySheet() throws SmartsheetException, IOException {
        Folder folder = createFolder();

        //ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setDestinationType(DestinationType.HOME).setDestinationId(null).setNewName("New Copied sheet").build();
        ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder()
                .setDestinationType(DestinationType.FOLDER)
                .setDestinationId(folder.getId())
                .setNewName("New Copied sheet")
                .build();

        Sheet sheet = smartsheet.sheetResources().copySheet(newSheetHome.getId(), destination, EnumSet.of(SheetCopyInclusion.ALL));
        assertEquals(sheet.getName(), "New Copied sheet");
        deleteFolder(folder.getId());
    }

    public void testMoveSheet() throws SmartsheetException, IOException {
        Folder folder = createFolder();
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setDestinationType(DestinationType.HOME).setDestinationId(null).setNewName("New Copied sheet").build();
        ContainerDestination destination = new ContainerDestination.AddContainerDestinationBuilder().setDestinationType(DestinationType.FOLDER).setDestinationId(folder.getId()).build();

        Sheet movedSheet = smartsheet.sheetResources().moveSheet(sheet.getId(), destination);
        assertNotNull(movedSheet);
        deleteSheet(movedSheet.getId());
        deleteFolder(folder.getId());
    }

    public void testCreateUpdateRequest() throws SmartsheetException, IOException {
        //
        //create sheet
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //get column
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Column> wrapper = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(), EnumSet.allOf(ColumnInclusion.class), parameters);

        Column addedColumn1 = wrapper.getData().get(0);
        Column addedColumn2 = wrapper.getData().get(1);

        // Specify cell values for first row.
        List<Cell> cellsA = new Cell.AddRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "New status").build();

        // Specify contents of first row.
        Row row = new Row.AddRowBuilder().setCells(cellsA).setToBottom(true).build();

        // Specify cell values for second row.
        List<Cell> cellsB = new Cell.AddRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "New status").build();

        // Specify contents of first row.
        Row rowA = new Row.AddRowBuilder().setCells(cellsB).setToBottom(true).build();

        List<Row> newRows = smartsheet.sheetResources().rowResources().addRows(sheet.getId(), Arrays.asList(row, rowA));

        List<Column> columns = wrapper.getData();
        Column addedColumn = columns.get(1);
        //

        RecipientEmail recipientEmail = new RecipientEmail.AddRecipientEmailBuilder()
                .setEmail("aditi.nioding@smartsheet.com")
                .setEmail("john.doe@smartsheet.com")
                .build();

        List<Recipient> recipients = new ArrayList<Recipient>();
        recipients.add(recipientEmail);

        MultiRowEmail multiRowEmail = new MultiRowEmail.AddMultiRowEmailBuilder()
                .setSendTo(recipients)
                .setSubject("some subject")
                .setMessage("some message")
                .setCcMe(false)
                .setRowIds(Arrays.asList(newRows.get(0).getId()))
                .setColumnIds(Arrays.asList(addedColumn.getId()))
                .setIncludeAttachments(false)
                .setIncludeDiscussions(false)
                .build();

        smartsheet.sheetResources().createUpdateRequest(sheet.getId(), multiRowEmail);

        deleteSheet(sheet.getId());
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

        if (newSheetFolder.getColumns().size() != sheetHome.getColumns().size()) {
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
        assertEquals(sheetHome.getColumns().size(), newSheet.getColumns().size());

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
        Sheet sheet = new Sheet.UpdateSheetBuilder().setSheetId(newSheetHome.getId()).setName("Updated Name by Aditi").build();
        Sheet newSheet = smartsheet.sheetResources().updateSheet(sheet);

        assertEquals(sheet.getName(), newSheet.getName());
    }

    public void testPublishSheetDefaults() throws SmartsheetException, IOException {
        SheetPublish sheetPublish = new SheetPublish.PublishStatusBuilder()
                .setIcalEnabled(false)
                .setReadOnlyFullEnabled(true)
                .setReadWriteEnabled(true)
                .setReadOnlyLiteEnabled(true)
                .build();
        SheetPublish newSheetPublish = smartsheet.sheetResources().updatePublishStatus(newSheetHome.getId(), sheetPublish);

        assertTrue("read write show toolbar should be enabled", newSheetPublish.getReadWriteShowToolbar());
        assertTrue("read only full show toolbar should be enabled", newSheetPublish.getReadOnlyFullShowToolbar());
    }

    public void testPublishSheet() throws SmartsheetException, IOException {
        SheetPublish sheetPublish = new SheetPublish.PublishStatusBuilder()
                .setIcalEnabled(false)
                .setReadOnlyFullEnabled(true)
                .setReadWriteEnabled(true)
                .setReadOnlyLiteEnabled(true)
                .setReadWriteShowToolbarEnabled(false)
                .setReadOnlyFullShowToolbarEnabled(false)
                .build();
        SheetPublish newSheetPublish = smartsheet.sheetResources().updatePublishStatus(newSheetHome.getId(), sheetPublish);

        assertFalse("read write show toolbar should not be enabled", newSheetPublish.getReadWriteShowToolbar());
        assertFalse("read only full show toolbar should not be enabled", newSheetPublish.getReadOnlyFullShowToolbar());
    }

    public void testUpdatePublishStatus() throws SmartsheetException, IOException {
        // In order to publish an icalendar, we have to have at least one row of data
        PagedResult<Column> columns = smartsheet.sheetResources().columnResources().listColumns(newSheetHome.getId(), null, null);
        Column dateColumn;
        for (Column column : columns.getData()) {
            if (column.getType() == ColumnType.DATE) {
                dateColumn = column;
                smartsheet.sheetResources().rowResources().addRows(newSheetHome.getId(), Collections.singletonList(
                        new Row.AddRowBuilder()
                                .setCells(new Cell.AddRowCellsBuilder()
                                        .addCell(dateColumn.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                                        .build())
                                .setToBottom(true)
                                .build()));
                break;
            }
        }

        SheetPublish sheetPublish = new SheetPublish.PublishStatusBuilder()
                .setIcalEnabled(true)
                .setReadOnlyFullEnabled(true)
                .setReadWriteEnabled(true)
                .setReadOnlyLiteEnabled(true)
                .build();
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

//        File file = new File("src/integration-test/resources/small-text.txt");
//        Attachment attachment = smartsheet.sheetResources().attachmentResources().attachFile(1234L, 345L, file,
//                "application/pdf");
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
