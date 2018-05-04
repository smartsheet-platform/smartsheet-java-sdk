package com.smartsheet.api.sdk_test;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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
import com.smartsheet.api.models.format.Format;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;

public class HeaderTest {
	@Test
	public void ChangeAgentHeader() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Change Agent Header - Can Be Passed");

			ss.setChangeAgent("MyChangeAgent");

			Column column = new Column();
			column.setTitle("Col1");
			column.setPrimary(true);
			column.setType(ColumnType.TEXT_NUMBER);

			Sheet sheet = new Sheet();
			sheet.setName("My new sheet");
			sheet.setColumns(Arrays.asList(column));

			// act
			Sheet result = ss.sheetResources().createSheet(sheet);
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}

	@Test
	public void AssumeUserHeader() {
		try {
			// arrange
			Smartsheet ss = HelperFunctions.SetupClient("Assume User - Can Be Set");

			ss.setAssumedUser("john.doe@smartsheet.com");

			// act
			Sheet result = ss.sheetResources().getSheet(123L, null, null, null, null, null, null, null);
		} catch (Exception ex) {
			HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
		}
	}
}
