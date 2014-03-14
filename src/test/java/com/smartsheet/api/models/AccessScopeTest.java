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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class AccessScopeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(AccessScope.valueOf("READ_SHEETS"));
		assertNotNull(AccessScope.valueOf("WRITE_SHEETS"));
		assertNotNull(AccessScope.valueOf("SHARE_SHEETS"));
		assertNotNull(AccessScope.valueOf("DELETE_SHEETS"));
		assertNotNull(AccessScope.valueOf("CREATE_SHEETS"));
		assertNotNull(AccessScope.valueOf("ADMIN_USERS"));
		assertNotNull(AccessScope.valueOf("ADMIN_SHEETS"));
		assertNotNull(AccessScope.valueOf("ADMIN_WORKSPACES"));
		assertEquals(8,AccessScope.values().length);
	}

}
