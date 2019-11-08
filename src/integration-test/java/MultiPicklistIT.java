/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class MultiPicklistIT extends ITResourcesImpl {
    Smartsheet smartsheet;
    Sheet sheet;
    List<Column> addCols;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();

        // create sheet object
        Sheet sheetHome = createSheetObject();

        //create sheet
        sheet = smartsheet.sheetResources().createSheet(sheetHome);
        if (sheet.getColumns().size() != sheetHome.getColumns().size()) {
            fail("Issue creating a sheet");
        }
    }

    @After
    public void Teardown() throws Exception {
        smartsheet.sheetResources().deleteSheet(sheet.getId());
    }

    @Test
    public void testMultiPicklistMethods() throws SmartsheetException, IOException {
        testAddMultiPicklistColumn();
        testListMultiPicklistColumn();
        testAddMultiPicklistRow();
        testGetMultiPicklistSheet();
    }

    public void testAddMultiPicklistColumn() throws SmartsheetException {
        Column mpl = new Column();
        mpl.setTitle("This is a multi-picklist column").setIndex(0).setType(ColumnType.MULTI_PICKLIST)
                .setOptions(Arrays.asList("Cat", "Rat", "Bat"));
        addCols = smartsheet.sheetResources().columnResources().addColumns(sheet.getId(), Arrays.asList(mpl));
        assertEquals(addCols.size(), 1);
    }

    public void testListMultiPicklistColumn() throws SmartsheetException {
        PagedResult<Column> cols = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(),
                null, null, null);
        // should be TEXT_NUMBER since level not specified
        assertEquals(cols.getData().get(0).getType(), ColumnType.TEXT_NUMBER);

        cols = smartsheet.sheetResources().columnResources().listColumns(sheet.getId(),
                null, null, 2);
        // should be MULTI_PICKLIST since level 2 specified
        assertEquals(cols.getData().get(0).getType(), ColumnType.MULTI_PICKLIST);
    }

    public void testAddMultiPicklistRow() throws SmartsheetException {
        List<Cell> insert_cells = Arrays.asList(new Cell().setColumnId(addCols.get(0).getId()).setObjectValue(
                new MultiPicklistObjectValue(Arrays.asList("Bat", "Cat"))));
        Row insert_row = new Row();
        insert_row.setToTop(true).setCells(insert_cells);
        List<Row> insert_rows = smartsheet.sheetResources().rowResources().addRows(sheet.getId(),
                Arrays.asList(insert_row));
        assertEquals(insert_rows.size(), 1);
    }

    public void testGetMultiPicklistSheet() throws SmartsheetException {
        Sheet mpl = smartsheet.sheetResources().getSheet(sheet.getId(), EnumSet.of(SheetInclusion.OBJECT_VALUE),
                null, null, null, new HashSet<Long>(Arrays.asList(addCols.get(0).getId())), null,
                null, null,null);
        // should be TEXT_NUMBER since level not specified
        assertTrue(mpl.getRows().get(0).getCells().get(0).getObjectValue() instanceof StringObjectValue);

        mpl = smartsheet.sheetResources().getSheet(sheet.getId(), EnumSet.of(SheetInclusion.OBJECT_VALUE),
                null, null, null, new HashSet<Long>(Arrays.asList(addCols.get(0).getId())), null,
                null, null,2);
        // should be MULTI_PICKLIST since level 2 specified
        assertTrue(mpl.getRows().get(0).getCells().get(0).getObjectValue() instanceof MultiPicklistObjectValue);
    }
}
