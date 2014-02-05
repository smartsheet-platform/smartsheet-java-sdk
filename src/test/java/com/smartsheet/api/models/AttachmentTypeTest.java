package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AttachmentTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(AttachmentType.valueOf("FILE"));
		assertNotNull(AttachmentType.valueOf("GOOGLE_DRIVE"));
		assertNotNull(AttachmentType.valueOf("LINK"));
		assertNotNull(AttachmentType.valueOf("BOX_COM"));
		
		assertEquals(4,AttachmentType.values().length);
	}

}
