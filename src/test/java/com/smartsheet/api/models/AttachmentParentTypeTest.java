package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttachmentParentTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(AttachmentParentType.valueOf("SHEET"));
		assertNotNull(AttachmentParentType.valueOf("ROW"));
		assertNotNull(AttachmentParentType.valueOf("COMMENT"));
		assertEquals(3,AttachmentParentType.values().length);
	}

}
