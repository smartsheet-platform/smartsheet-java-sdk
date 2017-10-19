package com.smartsheet.api.models;

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

import com.smartsheet.api.models.format.Format;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RowTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetColumnByIndex() {
        Row row = new Row();
        Column col = new Column();
        col.setId(1234L);
        col.setIndex(2);
        List<Column> columns = new ArrayList<Column>();
        columns.add(col);
        row.setColumns(columns);
        //row.setParentRowNumber(1);
        row.setDiscussions(new ArrayList<Discussion>());
        row.setAttachments(new ArrayList<Attachment>());

        assertEquals(col, row.getColumnById(1234L));
        assertEquals(col, row.getColumnByIndex(2));
        assertNull(row.getColumnById(12345L));
        assertNull(row.getColumnByIndex(22));
        assertNull(new Row().getColumnById(213L));
        assertNull(new Row().getColumnByIndex(33));
        Row row1 = new Row();
        row1.setColumns(new ArrayList<Column>());
        assertNull(row1.getColumnById(1L));
        assertNull(row1.getColumnByIndex(1));
    }

    @Test
    public void testInsertRowBuilder() {
        Format format = new Format("new format");
        List<Cell> cells = new ArrayList<Cell>();
        Row row = new Row.AddRowBuilder().setToTop(true).setExpanded(false).setFormat(format).setCells(cells).build();

        assertTrue(row.getToTop());
        assertFalse(row.isExpanded());
        assertNotNull(row.getFormat());
        assertNotNull(row.getCells());
        assertNull(row.getToBottom());
        assertNull(row.getParentId());
        assertNull(row.getSiblingId());
        assertNull(row.getAbove());
    }

    @Test
    public void testUpdateRowBuilder() {
        Format format = new Format("new format");
        List<Cell> cells = new ArrayList<Cell>();
        Row row = new Row.UpdateRowBuilder().setToTop(true).setExpanded(false).setFormat(format).setCells(cells).setLocked(true).build();

        assertTrue(row.getToTop());
        assertTrue(row.isLocked());
        assertFalse(row.isExpanded());
        assertNotNull(row.getFormat());
        assertNotNull(row.getCells());
        assertNull(row.getToBottom());
        assertNull(row.getParentId());
        assertNull(row.getSiblingId());
        assertNull(row.getAbove());
    }

}
