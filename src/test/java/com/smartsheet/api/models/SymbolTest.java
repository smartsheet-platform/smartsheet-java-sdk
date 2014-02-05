package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SymbolTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(Symbol.valueOf("FLAG"));
		assertNotNull(Symbol.valueOf("STAR"));
		assertNotNull(Symbol.valueOf("HARVEY_BALLS"));
		assertNotNull(Symbol.valueOf("RYG"));
		assertNotNull(Symbol.valueOf("PRIORITY"));
		
		assertEquals(5,Symbol.values().length);
	}

}
