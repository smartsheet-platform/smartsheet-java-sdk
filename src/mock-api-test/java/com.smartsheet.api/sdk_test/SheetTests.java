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

import com.smartsheet.api.models.enums.SourceInclusion;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumSet;


public class SheetTests {

	
	@Test
	public void ListSheets_NoParams()
	{
		try {
			Smartsheet ss = HelperFunctions.SetupClient("List Sheets - No Params");
			PagedResult<Sheet> sheets = ss.sheetResources().listSheets(null, null, null);
			Assert.assertEquals(sheets.getData().get(0).getName(), "Copy of Sample Sheet");
		}catch(Exception ex){
			Assert.fail(String.format("Exception: %s Detail: %s", ex.getMessage(), ex.getCause()));
		}
	}

	@Test
	public void ListSheets_IncludeOwnerInfo()
	{
		try{
			Smartsheet ss =  HelperFunctions.SetupClient("List Sheets - Include Owner Info");
			PagedResult<Sheet> sheets = ss.sheetResources().listSheets(EnumSet.of(SourceInclusion.OWNERINFO), null, null);
			Assert.assertEquals(sheets.getData().get(0).getOwner(),"john.doe@smartsheet.com");
		}catch(Exception ex){
			Assert.fail(String.format("Exception: %s Detail: %s", ex.getMessage(), ex.getCause()));
		}
	}

	@Test
	public void CreateSheet__Invalid_NoColumns()
	{
		try {
			Smartsheet ss = HelperFunctions.SetupClient("Create Sheet - Invalid - No Columns");

			Sheet sheetA = new Sheet();
			sheetA.setName("New Sheet");
			sheetA.setColumns(new ArrayList<Column>());
			ss.sheetResources().createSheet(sheetA);

		}catch(SmartsheetException ex){
			Assert.assertEquals(ex.getMessage(), "The new sheet requires either a fromId or columns.");

		}catch(Exception ex){
			Assert.fail(String.format("Exception: %s Detail: %s", ex.getMessage(), ex.getCause()));
		}
	}
}
