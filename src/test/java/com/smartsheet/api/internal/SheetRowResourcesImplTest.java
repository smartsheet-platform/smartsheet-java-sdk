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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.Link;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.RowWrapper;

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
		cell.setLink(link);
		cell.setFormula("=1+1");
		
		// Create a row and add the cells to it.
		List<Row> rows = new ArrayList<Row>();
		Row row = new Row();
		row.setCells(cells);
		rows.add(row);
		
		// Create a rowWrapper to hold the rows for inserting.
		RowWrapper rowWrapper = new RowWrapper();
		rowWrapper.setToBottom(true);
		rowWrapper.setRows(rows);
		
		List<Row> newRows = sheetRowResource.insertRows(1234L, rowWrapper);
		
		assertNotNull(newRows);
		
		assertEquals("The number of rows created & inserted is not correct.", rows.size(), newRows.size());
	}

	@Test
	public void testGetRow() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/getRow.json"));
		
		Row row = sheetRowResource.getRow(1234L, 1);
		
		assertNotNull(row);
		assertTrue("Wrong row retrieved.", 1 == row.getRowNumber());
	}

}
