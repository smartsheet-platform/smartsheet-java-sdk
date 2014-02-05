package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SystemColumnTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(SystemColumnType.valueOf("AUTO_NUMBER"));
		assertNotNull(SystemColumnType.valueOf("MODIFIED_DATE"));
		assertNotNull(SystemColumnType.valueOf("MODIFIED_BY"));
		assertNotNull(SystemColumnType.valueOf("CREATED_DATE"));
		assertNotNull(SystemColumnType.valueOf("CREATED_BY"));
		
		assertEquals(5,SystemColumnType.values().length);
	}

}
