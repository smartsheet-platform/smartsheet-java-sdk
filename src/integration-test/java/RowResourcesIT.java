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
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class RowResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    Sheet sheet;
    List<Row> newRows;
    Row row;
    Column addedColumn;
    Sheet copyToSheet;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testRowMethods() throws SmartsheetException, IOException {
        testAddRows();
        testGetRow();
        testCopyRow();
        testSendRow();
        testUpdateRows();
        testMoveRow();
        testDeleteRow();
    }

    public void testAddRows() throws SmartsheetException, IOException {
        //create sheet
        sheet = smartsheet.sheetResources().createSheet(createSheetObject());

        //get column
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Column> wrapper = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(), EnumSet.allOf(ColumnInclusion.class), parameters);

        Column addedColumn1 = wrapper.getData().get(0);
        Column addedColumn2 = wrapper.getData().get(1);

        // Specify cell values for first row.
        List<Cell> cellsA = new Cell.AddRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "New status").build();

        // Specify contents of first row.
        row = new Row.AddRowBuilder().setCells(cellsA).setToBottom(true).build();

        // Specify cell values for second row.
        List<Cell> cellsB = new Cell.AddRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "New status").build();

        // Specify contents of first row.
        Row rowA = new Row.AddRowBuilder().setCells(cellsB).setToBottom(true).build();

        newRows = smartsheet.sheetResources().rowResources().addRows(sheet.getId(), Arrays.asList(row, rowA));

        List<Column> columns = wrapper.getData();
        addedColumn = columns.get(1);
    }

    public void testGetRow() throws SmartsheetException, IOException {
        smartsheet.sheetResources().rowResources().getRow(sheet.getId(), newRows.get(0).getId(), null, null);
        row = smartsheet.sheetResources().rowResources().getRow(sheet.getId(), newRows.get(0).getId(), EnumSet.of(RowInclusion.COLUMNS, RowInclusion.COLUMN_TYPE), EnumSet.of(ObjectExclusion.NONEXISTENT_CELLS));
       assertNotNull(row);
    }

    @Test
    public void testUpdateRows() throws SmartsheetException, IOException {
         //create sheet
        Sheet sheet = smartsheet.sheetResources().createSheet(createSheetObject());
        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        PagedResult<Column> wrapper = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(), EnumSet.allOf(ColumnInclusion.class), parameters);

        Column addedColumn1 = wrapper.getData().get(0);
        Column addedColumn2 = wrapper.getData().get(1);

        // Specify cell values for first row.
        List<Cell> cellsA = new Cell.AddRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "New status").build();

        // Specify contents of first row.
        Row row = new Row.AddRowBuilder().setCells(cellsA).setToBottom(true).build();
        List<Row> newRows = smartsheet.sheetResources().rowResources().addRows(sheet.getId(), Arrays.asList(row));

        //Updated cells //correct
        List<Cell> cellsB = new Cell.UpdateRowCellsBuilder().addCell(addedColumn1.getId(), true).addCell(addedColumn2.getId(), "Updtaed status").build();

        Row rowB = new Row.UpdateRowBuilder().setCells(cellsB).setRowId(newRows.get(0).getId()).build();

        List<Row> updatedRows = smartsheet.sheetResources().rowResources().updateRows(sheet.getId(), Arrays.asList(rowB));

        assertNotNull(updatedRows);
        deleteSheet(sheet.getId());
    }

    public void testCopyRow() throws SmartsheetException, IOException {
        //Create new sheet to copy to
        copyToSheet = smartsheet.sheetResources().createSheet(createSheetObject());

        CopyOrMoveRowDestination destination = new CopyOrMoveRowDestination.InsertCopyOrMoveRowDestinationBuilder().setSheetId(copyToSheet.getId()).build();
        CopyOrMoveRowDirective copyOrMoveRowDirective = new CopyOrMoveRowDirective.InsertCopyOrMoveRowDirectiveBuilder().setRowIds(Arrays.asList(newRows.get(0).getId())).setTo(destination).build();

        smartsheet.sheetResources().rowResources().copyRows(sheet.getId(), null, null, copyOrMoveRowDirective);
            smartsheet.sheetResources().rowResources().copyRows(sheet.getId(), EnumSet.of(RowCopyInclusion.CHILDREN), false, copyOrMoveRowDirective);

    }

    public void testMoveRow() throws SmartsheetException, IOException {
        List<Long> rowIds = new ArrayList<Long>();
        rowIds.add(newRows.get(0).getId());

        CopyOrMoveRowDestination destination = new CopyOrMoveRowDestination.InsertCopyOrMoveRowDestinationBuilder().setSheetId(copyToSheet.getId()).build();
        CopyOrMoveRowDirective directive = new CopyOrMoveRowDirective.InsertCopyOrMoveRowDirectiveBuilder().setRowIds(rowIds).setTo(destination).build();

        //smartsheet.sheetResources().rowResources().moveRows(sheet.getId(), null, null, directive);
        smartsheet.sheetResources().rowResources().moveRows(sheet.getId(), EnumSet.of(RowMoveInclusion.ATTACHMENTS, RowMoveInclusion.DISCUSSIONS), false, directive);
    }

    public void testSendRow() throws SmartsheetException, IOException {
        List<Recipient> recipients = new ArrayList<Recipient>();
        RecipientEmail recipientEmail = new RecipientEmail.AddRecipientEmailBuilder().setEmail("jane.doe@smartsheet.com").build();
        //RecipientGroup recipientGroup = new RecipientGroup.AddRecipientGroupBuilder().setGroupId(1234L).build();

        //List<Recipient> recipients = Arrays.asList(recipientEmail, recipientGroup);

        recipients.add(recipientEmail);
        FormatDetails formatDetails = new FormatDetails();
        formatDetails.setPaperSize(PaperSize.A0);

        RowEmail email = new RowEmail.AddRowEmailBuilder().setSendTo(recipients).setSubject("Check these rows out!").setMessage("Here are the rows I mentioned in our meeting").setCcMe(false).setIncludeAttachments(true).setIncludeDiscussions(true).build();

        //smartsheet.reportResources().sendReport(reportsWrapper.getData().get(0).getId(), email);
        smartsheet.sheetResources().rowResources().sendRow(sheet.getId(), newRows.get(0).getId(), email);
    }

    public void testDeleteRow() throws SmartsheetException, IOException {
        //testAddRows();
        //smartsheet.sheetResources().rowResources().deleteRow(sheet.getId(), newRows.get(0).getId());

        //clean up
        deleteSheet(sheet.getId());
        deleteSheet(copyToSheet.getId());
    }
}
