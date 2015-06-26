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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PaginationParametersTest {

	@Test
	public void testPaginationParameters() {
		PaginationParameters parameters = new PaginationParameters(true, 1, 1);
		
		assertTrue(parameters.isIncludeAll());
		assertEquals(1, parameters.getPageSize().longValue());
		assertEquals(1, parameters.getPage().longValue());
	}

	@Test
	public void testToQueryString() {
		PaginationParameters parameters1 = new PaginationParameters(true, null, null);
		assertEquals("?includeAll=true", parameters1.toQueryString());

		PaginationParameters parameters2 = new PaginationParameters(true, 1, 1);
		assertEquals("?includeAll=true", parameters2.toQueryString());

		PaginationParameters parameters3 = new PaginationParameters(false, 1, 1);
		assertEquals("?page=1&pageSize=1&includeAll=false", parameters3.toQueryString());
	}
}
