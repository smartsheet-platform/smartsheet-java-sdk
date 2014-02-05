package com.smartsheet.api.models;

import static org.junit.Assert.*;

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
