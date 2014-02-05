package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(LinkType.valueOf("URL"));
		assertNotNull(LinkType.valueOf("SHEETLINK"));
		assertNotNull(LinkType.valueOf("CELLLINK"));
		
		assertEquals(3,LinkType.values().length);
	}

}
