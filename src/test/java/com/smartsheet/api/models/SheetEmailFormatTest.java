package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SheetEmailFormatTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(SheetEmailFormat.valueOf("PDF"));
		assertNotNull(SheetEmailFormat.valueOf("EXCEL"));
		assertEquals(2,SheetEmailFormat.values().length);
	}

}
