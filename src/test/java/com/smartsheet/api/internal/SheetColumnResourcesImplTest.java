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
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.ColumnInclusion;
import com.smartsheet.api.models.enums.ColumnType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;

public class SheetColumnResourcesImplTest extends ResourcesImplBase {

    private SheetColumnResourcesImpl sheetColumnResourcesImpl;

    @Before
    public void setUp() throws Exception {
        sheetColumnResourcesImpl = new SheetColumnResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testSheetColumnResourcesImpl() {
    }

    @Test
    public void testListColumns() throws SmartsheetException, IOException {

        server.setResponseBody(new File("src/test/resources/listColumns.json"));
        PaginationParameters paginationParameters = new PaginationParameters(true, 1, 1);
        PagedResult<Column> wrapper = sheetColumnResourcesImpl.listColumns(1234L, EnumSet.allOf(ColumnInclusion.class), paginationParameters);
        List<Column> columns = wrapper.getData();
        assertEquals(3, columns.size());
        assertEquals("CHECKBOX", columns.get(0).getType().toString());
        assertEquals("STAR", columns.get(0).getSymbol().toString());
        assertTrue(columns.get(0).isLocked());
        assertFalse(columns.get(0).isLockedForUser());
        assertEquals("Status", columns.get(2).getTitle());
    }

    @Test
    public void testAddColumn() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/addColumn.json"));
        List<Column> columnsToCreate = new ArrayList<Column>();

        List<Column> addedColumns = sheetColumnResourcesImpl.addColumns(12345L, columnsToCreate);
        assertEquals(3, addedColumns.size());
        assertEquals("PICKLIST", addedColumns.get(0).getType().toString());
        assertEquals("DATE", addedColumns.get(1).getType().toString());
        assertEquals("PICKLIST", addedColumns.get(2).getType().toString());
    }

    @Test
    public void testUpdateColumn() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateColumn.json"));

        Column col = new Column();
        col.setId(5005385858869124L);
        col.setIndex(0);
        col.setTitle("First Column");
        col.setType(ColumnType.PICKLIST);


        Column updatedColumn = sheetColumnResourcesImpl.updateColumn(123456789L, col);

        assertNotNull(updatedColumn);
        assertEquals("First Column",updatedColumn.getTitle());

        try{
            sheetColumnResourcesImpl.updateColumn(123456789L, null);
            fail("Exception should have been thrown");
        }catch(IllegalArgumentException ex){
            // expected
        }
    }

    @Test
    public void testDeleteColumn() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteColumn.json"));
        sheetColumnResourcesImpl.deleteColumn(123456789L, 987654321L);
    }

    @Test
    public void testGetColumn() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getColumn.json"));
        Column col = new Column();
        col.setIndex(2);
        col.setTitle("Favorite");
        col.setType(ColumnType.CHECKBOX);

        Column newCol = sheetColumnResourcesImpl.getColumn(123L, 456L, EnumSet.of(ColumnInclusion.FILTERS));
        assertNotNull(newCol);
        assertEquals(col.getTitle(), newCol.getTitle());
    }
}
