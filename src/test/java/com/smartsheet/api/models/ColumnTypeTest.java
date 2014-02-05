package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColumnTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		assertNotNull(ColumnType.valueOf("TEXT_NUMBER"));
		assertNotNull(ColumnType.valueOf("PICKLIST"));
		assertNotNull(ColumnType.valueOf("DATE"));;
		assertNotNull(ColumnType.valueOf("CONTACT_LIST"));
		assertNotNull(ColumnType.valueOf("CHECKBOX"));
		
		assertEquals(5,ColumnType.values().length);
	}

}
