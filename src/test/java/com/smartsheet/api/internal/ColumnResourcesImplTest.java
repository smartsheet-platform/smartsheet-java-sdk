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
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Column;
import com.smartsheet.api.models.Symbol;
import com.smartsheet.api.models.SystemColumnType;

public class ColumnResourcesImplTest extends ResourcesImplBase {
	ColumnResourcesImpl columnResource;
	
	@Before
	public void setUp() throws Exception {
		columnResource = new ColumnResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
				new DefaultHttpClient(), serializer));
	}

	@Test
	public void testColumnResourcesImpl() {}

	@Test
	public void testUpdateColumn() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/updateColumn.json"));
		
		Column col = new Column();
		
		col.setIndex(0);
		col.setTitle("something new");
		col.setSheetId(2906571706525572L);
		col.setHidden(false);
		col.setSymbol(Symbol.STAR);
		col.setSystemColumnType(SystemColumnType.AUTO_NUMBER);
		Column colNew = columnResource.updateColumn(col);
		
		assertNotNull(colNew);
		assertEquals("something new",colNew.getTitle());
		
		try{
			columnResource.updateColumn(null);
			fail("Exception should have been thrown");
		}catch(IllegalArgumentException ex){
			// expected
		}
	}

	@Test
	public void testDeleteColumn() throws SmartsheetException, IOException {
		server.setResponseBody(new File("src/test/resources/deleteColumn.json"));
		columnResource.deleteColumn(179084870346628L, 2906571706525572L);
	}

}
