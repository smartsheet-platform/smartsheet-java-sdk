package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccessLevelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(AccessLevel.valueOf("VIEWER"));
		assertNotNull(AccessLevel.valueOf("EDITOR"));
		assertNotNull(AccessLevel.valueOf("EDITOR_SHARE"));
		assertNotNull(AccessLevel.valueOf("ADMIN"));
		assertNotNull(AccessLevel.valueOf("OWNER"));
		assertEquals(5,AccessLevel.values().length);
	}
}
