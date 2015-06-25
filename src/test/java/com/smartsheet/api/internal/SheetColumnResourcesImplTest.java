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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.smartsheet.api.SheetColumnResources;
import com.smartsheet.api.models.*;
import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;

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
		
		List<Column> columns = sheetColumnResourcesImpl.listColumns(1234L);
		assertTrue(columns.size() == 1);
		assertEquals(columns.get(0).getTitle(),"something new");
	}

	@Test
	public void testAddColumn() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/addColumn.json"));
		Column col = new Column();
		col.setIndex(1);
		col.setTitle("Status");
		col.setType(ColumnType.PICKLIST);
		AutoNumberFormat format = new AutoNumberFormat();
		format.setPrefix("pre");
		format.setSuffix("suf");
		format.setStartingNumber(0L);
		format.setFill("000");
		col.setAutoNumberFormat(format);
		col.setOptions(Arrays.asList(new String[]{"Not Started", "Started", "Completed"}));

		Column newCol = sheetColumnResourcesImpl.addColumn(1234L, col);
		assertEquals("Status", newCol.getTitle());
		assertTrue(ColumnType.PICKLIST == col.getType());
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
}
