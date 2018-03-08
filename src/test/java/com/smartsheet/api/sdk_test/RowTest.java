package com.smartsheet.api.sdk_test;
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


import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RowTest {

	
	@Test
	public void ListSheets_NoParams()
	{
		try {
			Smartsheet ss = HelperFunctions.SetupClient("List Sheets - No Params");
			PagedResult<Sheet> sheets = ss.sheetResources().listSheets(null, null, null);
			Assert.assertEquals("Copy of Sample Sheet", sheets.getData().get(0).getName());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_String()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - String");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue("Banana");
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Yellow Fruit");
			rowB.setCells(Arrays.asList(cell3, cell4));


			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1L, Arrays.asList(rowA,rowB));

			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_Location_Top()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Location - Top");

			Row rowA = new Row();
			rowA.setToTop(true);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Apple", addedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(1, addedRows.get(0).getRowNumber().intValue());
			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}

	}

  	@Test
	public void addRows_Location_Bottom()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Location - Bottom");

			Row rowA = new Row();
			rowA.setToBottom(true);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Apple", addedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(100, addedRows.get(0).getRowNumber().intValue());
			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_Int()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Int");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(100);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("One Hundred");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(2.1);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Two Point One");
			rowB.setCells(Arrays.asList(cell3, cell4));


			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA, rowB));

			Assert.assertEquals(100, addedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_Bool()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Bool");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(true);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("This is True");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(false);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("This is False");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA, rowB));

			Assert.assertEquals(10, addedRows.get(0).getId().intValue());
			Assert.assertEquals(true, addedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignFormulae()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Formulae");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("=SUM([Column2]3, [Column2]3, [Column2]4)", addedRows.get(0).getCells().get(1).getFormula());
			Assert.assertEquals(102L, addedRows.get(0).getCells().get(1).getColumnId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Google", addedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals("http://google.com", addedRows.get(0).getCells().get(0).getHyperlink().getUrl());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_HyperlinkSheetID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink SheetID");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Sheet2");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Sheet3");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setSheetId(3L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Sheet3", addedRows.get(0).getCells().get(1).getValue());
			Assert.assertEquals(102L, addedRows.get(0).getCells().get(1).getColumnId().longValue());
			Assert.assertEquals(3L, addedRows.get(0).getCells().get(1).getHyperlink().getSheetId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_AssignValues_HyperlinkReportID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Values - Hyperlink ReportID");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Report9");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setReportId(9L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Report8");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setReportId(8L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Report8", addedRows.get(0).getCells().get(1).getValue());
			Assert.assertEquals(102L, addedRows.get(0).getCells().get(1).getColumnId().longValue());
			Assert.assertEquals(8L, addedRows.get(0).getCells().get(1).getHyperlink().getReportId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_Invalid_AssignHyperlinkUrlandSheetId()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Invalid - Assign Hyperlink URL and SheetId");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			Assert.assertEquals("hyperlink.url must be null for sheet, report, or Sight hyperlinks.", ex.getMessage());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void addRows_Invalid_AssignValueAndFormulae()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Invalid - Assign Value and Formulae");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			cell1.setValue("20");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			Assert.assertEquals("If cell.formula is specified, then value, objectValue, image, hyperlink, and linkInFromCell must not be specified.", ex.getMessage());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}


	// TODO: Fix PredecessorList test. Java SDK (only) passes floats. This is legal - probably easiest to add another version of this test with fractional values. Else, fix SDK to not serialize trailing zeros
	// Different value found in node "[0].cells[0].objectValue.predecessors[0].lag.days". Expected 2, got 2.0.
	// Different value found in node "[0].cells[0].objectValue.predecessors[0].lag.hours". Expected 4, got 4.0.
	@Ignore("Fix test - APISDK-1502")
	@Test
	public void AddRows_AssignObjectValue_PredecessorList()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Add Rows - Assign Object Value - Predecessor List");

			Row rowA = new Row();
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			Duration duration = new Duration();
			duration.setDays(2.0);
			duration.setHours(4.0);
			Predecessor predecessor = new Predecessor();
			predecessor.setRowId(10L);
			predecessor.setType("FS");
			predecessor.setLag(duration);
			PredecessorList predecessorList = new PredecessorList();
			predecessorList.setPredecessors(Arrays.asList(predecessor));
			cell1.setObjectValue(predecessorList);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> addedRows = ss.sheetResources().rowResources().addRows(1, Arrays.asList(rowA));


			Assert.assertEquals(101L, addedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals("2FS +2d 4h", addedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_String()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - String");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Apple");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Red Fruit");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue("Banana");
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Yellow Fruit");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals("Apple", updatedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_Int()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Int");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(100);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("One Hundred");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(2.1);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("Two Point One");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(100, updatedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_Bool()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Bool");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue(true);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("This is True");
			rowA.setCells(Arrays.asList(cell1, cell2));

			Row rowB = new Row();
			rowB.setId(11L);
			Cell cell3 = new Cell();
			cell3.setColumnId(101L);
			cell3.setValue(false);
			Cell cell4 = new Cell();
			cell4.setColumnId(102L);
			cell4.setValue("This is False");
			rowB.setCells(Arrays.asList(cell3, cell4));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1L, Arrays.asList(rowA,rowB));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(true, updatedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignFormulae()
	{
		try{
			Smartsheet ss =  HelperFunctions.SetupClient("Update Rows - Assign Formulae");

			Row rowA = new Row();
			rowA.setId(11L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals("=SUM([Column2]3, [Column2]3, [Column2]4)", updatedRows.get(0).getCells().get(1).getFormula());
			Assert.assertEquals(102L, updatedRows.get(0).getCells().get(1).getColumnId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Google", updatedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals("http://google.com", updatedRows.get(0).getCells().get(0).getHyperlink().getUrl());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_HyperlinkSheetID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink SheetID");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Sheet2");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Sheet3");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setSheetId(3L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Sheet3", updatedRows.get(0).getCells().get(1).getValue());
			Assert.assertEquals(102L, updatedRows.get(0).getCells().get(1).getColumnId().longValue());
			Assert.assertEquals(3L, updatedRows.get(0).getCells().get(1).getHyperlink().getSheetId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_AssignValues_HyperlinkReportID()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Assign Values - Hyperlink ReportID");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Report9");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setReportId(9L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Report8");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setReportId(8L);
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals("Report8", updatedRows.get(0).getCells().get(1).getValue());
			Assert.assertEquals(102L, updatedRows.get(0).getCells().get(1).getColumnId().longValue());
			Assert.assertEquals(8L, updatedRows.get(0).getCells().get(1).getHyperlink().getReportId().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_Invalid_AssignHyperlinkUrlandSheetId()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Invalid - Assign Hyperlink URL and SheetId");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("Google");
			Hyperlink hyperlink1 = new Hyperlink();
			hyperlink1.setUrl("http://google.com");
			hyperlink1.setSheetId(2L);
			cell1.setHyperlink(hyperlink1);
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setValue("Bing");
			Hyperlink hyperlink2 = new Hyperlink();
			hyperlink2.setUrl("http://bing.com");
			cell2.setHyperlink(hyperlink2);
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			Assert.assertEquals("hyperlink.url must be null for sheet, report, or Sight hyperlinks.", ex.getMessage());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_Invalid_AssignValueAndFormulae()
	{
		try{
			Smartsheet ss =  HelperFunctions.SetupClient("Update Rows - Invalid - Assign Value and Formulae");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setFormula("=SUM([Column2]3, [Column2]4)*2");
			cell1.setValue("20");
			Cell cell2 = new Cell();
			cell2.setColumnId(102L);
			cell2.setFormula("=SUM([Column2]3, [Column2]3, [Column2]4)");
			rowA.setCells(Arrays.asList(cell1, cell2));

			// Update rows in sheet
			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			Assert.assertEquals("If cell.formula is specified, then value, objectValue, image, hyperlink, and linkInFromCell must not be specified.", ex.getMessage());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void UpdateRows_ClearValue_TextNumber()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Text Number");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(null, updatedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	public void UpdateRows_ClearValue_Checkbox()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Checkbox");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(false, updatedRows.get(0).getCells().get(0).getValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	// TODO: Fix failing test ClearValue_Hyperlink - ClearValue_Hyperlink
	// It's not possible to remove a hyperlink via SDK
	@Ignore("Failing test - APISDK-1510")
    @Test
	public void UpdateRows_ClearValue_Hyperlink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Hyperlink");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			cell1.setHyperlink(null);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(null, updatedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(null, updatedRows.get(0).getCells().get(0).getHyperlink());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	// TODO: Fix failing test ClearValue_CellLink
	// Expected [columnId, linkInFromCell, value], got [columnId, value]. Missing: "[0].cells[0].linkInFromCell"
	@Ignore("Failing test - APISDK-1527")
    @Test
	public void UpdateRows_ClearValue_CellLink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Clear Value - Cell Link");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			cell1.setLinkInFromCell(null);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(101L, updatedRows.get(0).getCells().get(0).getColumnId().longValue());
			Assert.assertEquals(null, updatedRows.get(0).getCells().get(0).getValue());
			Assert.assertEquals(null, updatedRows.get(0).getCells().get(0).getLinkInFromCell());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}


	// TODO: Investigate failing test Invalid_AssignHyperlinkAndCellLink
	// It appears the SDK is only serializing some of the properties. Could be ignoring customer intent. Needs investigation
    @Ignore("Failing test  - APISDK-1528")
	@Test
	public void UpdateRows_Invalid_AssignHyperlinkAndCellLink()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Invalid - Assign Hyperlink and Cell Link");

			Row rowA = new Row();
			rowA.setId(10L);
			Cell cell1 = new Cell();
			cell1.setColumnId(101L);
			cell1.setValue("");
			Hyperlink hyperlink = new Hyperlink();
			hyperlink.setUrl("www.google.com");
			cell1.setHyperlink(hyperlink);
			CellLink cellLink = new CellLink();
			cellLink.setRowId(20L);
			cellLink.setSheetId(2L);
			cellLink.setColumnId(201L);
			cell1.setLinkInFromCell(cellLink);
			rowA.setCells(Arrays.asList(cell1));

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

		}catch(SmartsheetException ex){
			Assert.assertEquals("Only one of cell.hyperlink or cell.linkInFromCell may be non-null.", ex.getMessage());
		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	public void UpdateRows_Location_Top()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Location - Top");

			Row rowA = new Row();
			rowA.setId(10L);
			rowA.setToTop(true);

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(10L, updatedRows.get(0).getId().longValue());
			Assert.assertEquals(1L, updatedRows.get(0).getRowNumber().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

    @Test
	public void UpdateRows_Location_Bottom()
	{
		try{
			Smartsheet ss = HelperFunctions.SetupClient("Update Rows - Location - Bottom");

			Row rowA = new Row();
			rowA.setId(10L);
			rowA.setToBottom(true);

			List<Row> updatedRows = ss.sheetResources().rowResources().updateRows(1, Arrays.asList(rowA));

			Assert.assertEquals(10L, updatedRows.get(0).getId().longValue());
			Assert.assertEquals(100L, updatedRows.get(0).getRowNumber().longValue());

		}catch(Exception ex){
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}
}
