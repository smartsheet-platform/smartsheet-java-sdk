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
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SheetRowResourcesImplTest extends ResourcesImplBase {

    private SheetRowResourcesImpl sheetRowResource;

    @Before
    public void setUp() throws Exception {
        sheetRowResource = new SheetRowResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
                new DefaultHttpClient(), serializer));
    }

    @Test
    public void testSheetRowResourcesImpl() {}

    @Test
    public void testInsertRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/insertRows.json"));

        // Create a set of cells
        List<Cell> cells = new ArrayList<Cell>();
        Cell cell = new Cell();
        cell.setDisplayValue("Testing");
        cell.setColumnId(8764071660021636L);
        cell.setRowId(1234L);
        Link link = new Link();
        link.setUrl("http://google.com");
        link.setType(LinkType.URL);
        link.setSheetId(1234L);
        link.setColumnId(1234L);
        link.setRowId(1234L);
        //cell.setLink(link);
        cell.setFormula("=1+1");

        // Create a row and add the cells to it.
        List<Row> rows = new ArrayList<Row>();
        Row row = new Row();
        row.setCells(cells);
        rows.add(row);

        List<Row> newRows = sheetRowResource.addRows(1234L, rows);
        Row row1 = newRows.get(0);
        Row row2 = newRows.get(1);

        assertEquals(2, newRows.size());
        assertEquals(7670198317672324L, row1.getId().longValue());
        assertEquals(2, row1.getCells().size());
        assertEquals("CHECKBOX", row1.getCells().get(0).getColumnType().toString());
        assertEquals(2040698783459204L, row2.getId().longValue());
        assertEquals(2, row2.getCells().size());
        assertEquals("New status", row2.getCells().get(1).getValue());
    }

    @Test
    public void testGetRow() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getRow.json"));

        Row row = sheetRowResource.getRow(1234L, 5678L, EnumSet.of(RowInclusion.COLUMNS, RowInclusion.FORMAT), EnumSet.of(ObjectExclusion.NONEXISTENT_CELLS));

        assertNotNull(row);
        assertEquals(2361756178769796L, row.getId().longValue());
        assertEquals(4583173393803140L, row.getSheetId().longValue());
        assertEquals(2, row.getCells().size());
        assertEquals("Revision 1", row.getCells().get(0).getValue());
    }

    @Test
    public void testSendRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/sendRow.json"));

        RecipientEmail recipient = new RecipientEmail();
        recipient.setEmail("johndoe@smartsheet.com");

        MultiRowEmail email = new MultiRowEmail();

        List<Recipient> to = new ArrayList<Recipient>();
        to.add(recipient);

        email.setSendTo(to);
        email.setMessage("Test Message");
        email.setSubject("Test Subject");
        email.setIncludeAttachments(true);
        email.setIncludeDiscussions(true);
        email.setCcMe(true);

        sheetRowResource.sendRows(1234L, email);
    }

    @Test
    public void testUpdateRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateRows.json"));

        List<Row> rows = new ArrayList<Row>();

        List<Row> updatedRows = sheetRowResource.updateRows(1234L, rows);
        Row row1 = updatedRows.get(0);

        assertTrue(row1.getCells().size() == 6);
        assertEquals(3L, row1.getRowNumber().longValue());
        assertEquals(1231490655774596L, row1.getId().longValue());

        Cell cell = row1.getCells().get(0);
        assertEquals(7670639323572100L, cell.getColumnId().longValue());
    }

    @Test
    public void testMoveRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/moveRow.json"));
        CopyOrMoveRowDirective copyOrMoveRowDirective = new CopyOrMoveRowDirective();
        CopyOrMoveRowDestination copyOrMoveRowDestination = new CopyOrMoveRowDestination();
        copyOrMoveRowDestination.setSheetId(2258256056870788L);

        copyOrMoveRowDirective.setTo(copyOrMoveRowDestination);
        List<Long> rowIds = new ArrayList<Long>();
        rowIds.add(145417762563972L);
        copyOrMoveRowDirective.setRowIds(rowIds);
        sheetRowResource.moveRows(2258256056870788L, EnumSet.of(RowMoveInclusion.ATTACHMENTS), false, copyOrMoveRowDirective);
    }

    @Test
    public void testCopyRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/moveRow.json"));
        CopyOrMoveRowDirective copyOrMoveRowDirective = new CopyOrMoveRowDirective();
        CopyOrMoveRowDestination copyOrMoveRowDestination = new CopyOrMoveRowDestination();
        copyOrMoveRowDestination.setSheetId(2258256056870788L);

        copyOrMoveRowDirective.setTo(copyOrMoveRowDestination);
        List<Long> rowIds = new ArrayList<Long>();
        rowIds.add(145417762563972L);
        copyOrMoveRowDirective.setRowIds(rowIds);
        sheetRowResource.copyRows(2258256056870788L, EnumSet.of(RowCopyInclusion.ATTACHMENTS), false, copyOrMoveRowDirective);
    }

    @Test
    public void testDeleteRows() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteRow.json"));

        Set<Long> rowIds = new HashSet<Long>();
        rowIds.add(123456789L);
        rowIds.add(987654321L);

        List<Long> ids = sheetRowResource.deleteRows(123L, rowIds, true);
    }
}
