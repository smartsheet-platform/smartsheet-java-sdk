package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttachmentSubTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(AttachmentSubType.valueOf("DOCUMENT"));
		assertNotNull(AttachmentSubType.valueOf("SPREADSHEET"));
		assertNotNull(AttachmentSubType.valueOf("PRESENTATION"));
		assertNotNull(AttachmentSubType.valueOf("PDF"));
		assertNotNull(AttachmentSubType.valueOf("DRAWING"));
		assertEquals(5,AttachmentSubType.values().length);
	}

}
