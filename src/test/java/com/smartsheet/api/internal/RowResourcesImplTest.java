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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.AccessLevel;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.CellHistory;
import com.smartsheet.api.models.LinkType;
import com.smartsheet.api.models.ObjectInclusion;
import com.smartsheet.api.models.Row;
import com.smartsheet.api.models.RowEmail;
import com.smartsheet.api.models.RowWrapper;
import com.smartsheet.api.models.User;

public class RowResourcesImplTest extends ResourcesImplBase {

	private RowResourcesImpl rowResourcesImpl;

	@Before
	public void setUp() throws Exception {
		rowResourcesImpl = new RowResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer));
	}

	@Test
	public void testRowResourcesImpl() {}

	@Test
	public void testGetRow() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/getRowByID.json"));
		
		Row row = rowResourcesImpl.getRow(1234L, EnumSet.allOf(ObjectInclusion.class));
		assertTrue(row.getCells().size() == 1);
		assertEquals("http://domain.com",row.getCells().get(0).getLink().getUrl());
		assertEquals(LinkType.URL,row.getCells().get(0).getLink().getType());
		assertNull(row.getCells().get(0).getLink().getSheetId());
		assertNull(row.getCells().get(0).getLink().getColumnId());
		assertNull(row.getCells().get(0).getLink().getRowId());
		assertTrue(row.getColumns().size() == 2);
		assertTrue(row.getAccessLevel() == AccessLevel.OWNER);
	}

	@Test
	public void testMoveRow() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/moveRow.json"));
		
		RowWrapper rowWrapper = new RowWrapper();
		rowWrapper.setToTop(true);
		rowWrapper.setParentId(1234L);
		rowWrapper.setSiblingId(1234L);
		List<Row> rows = rowResourcesImpl.moveRow(1234L, rowWrapper);
		
		assertTrue(rows.size() == 1);
		assertTrue(rows.get(0).getCells().size() == 1);
	}

	@Test
	public void testDeleteRow() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteRow.json"));
		
		rowResourcesImpl.deleteRow(12345L);
	}

	@Test
	public void testSendRow() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/sendRow.json"));
		
		RowEmail email = new RowEmail();
		List<String> to = new ArrayList<String>();
		to.add("email@email.com");
		email.setTo(to);
		email.setMessage("Test Message");
		email.setSubject("Test Subject");
		email.setIncludeAttachments(true);
		email.setIncludeDiscussions(true);
		email.setCcMe(true);
		rowResourcesImpl.sendRow(1234L, email);
	}

	@Test
	public void testUpdateCells() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/updateCell.json"));
		List<Cell> cells = new ArrayList<Cell>();
		Cell cell = new Cell();
		cell.setColumnId(8764071660021636L);
		cell.setValue("Some New Text");
		cells.add(cell);
		cell.setColumnId(2110316914993028L);
		cell.setValue("Started");
		cell.setStrict(false);
		cells.add(cell);
		
		List<Cell> cellResult = rowResourcesImpl.updateCells(1234L, cells);
		assertTrue(cellResult.size() == cells.size());
		assertEquals("test","test");
		assertTrue(8764071660021636L == cellResult.get(0).getColumnId());
	}

	@Test
	public void testGetCellHistory() throws IOException, SmartsheetException {
		server.setResponseBody(new File("src/test/resources/getCellHistory.json"));
		
		List<CellHistory> history = rowResourcesImpl.getCellHistory(1234L, 123124L);
		assertTrue(history.size() == 2);
		
		assertEquals(new Date(1391109701000L), history.get(0).getModifiedAt());
		User user = new User();
		User newUser = history.get(0).getModifiedBy();
		assertNotNull(newUser);
		user.setId(2L);
		
		// Not equal because user id's are not set
		assertNotEquals(user, history.get(0).getModifiedBy());
		
		// Make user id's the same so equals method works
		user.setId(newUser.getId());

		assertEquals(user, history.get(0).getModifiedBy());
		assertEquals("message", user, history.get(0).getModifiedBy());
		assertEquals("Some New Text", history.get(0).getDisplayValue());
	}

	@Test
	public void testAttachments() {
		assertNotNull(rowResourcesImpl.attachments());
	}

	@Test
	public void testDiscussions() {
		assertNotNull(rowResourcesImpl.discussions());
	}
}
