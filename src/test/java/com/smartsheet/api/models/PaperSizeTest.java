package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PaperSizeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		assertNotNull(PaperSize.valueOf("LETTER"));
		assertNotNull(PaperSize.valueOf("LEGAL"));
		assertNotNull(PaperSize.valueOf("WIDE"));
		assertNotNull(PaperSize.valueOf("ARCHD"));
		assertNotNull(PaperSize.valueOf("A4"));
		assertNotNull(PaperSize.valueOf("A3"));
		assertNotNull(PaperSize.valueOf("A2"));
		assertNotNull(PaperSize.valueOf("A1"));
		assertNotNull(PaperSize.valueOf("A0"));
		
		
		assertEquals(9,PaperSize.values().length);
	}

}
