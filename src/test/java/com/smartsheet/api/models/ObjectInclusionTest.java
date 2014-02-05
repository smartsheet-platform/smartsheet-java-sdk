package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObjectInclusionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testObjectInclusion() {
		assertNotNull(ObjectInclusion.valueOf("DISCUSSIONS"));
		assertNotNull(ObjectInclusion.valueOf("ATTACHMENTS"));
		assertNotNull(ObjectInclusion.valueOf("DATA"));
		assertNotNull(ObjectInclusion.valueOf("COLUMNS"));
		assertNotNull(ObjectInclusion.valueOf("TEMPLATES"));
		
		assertEquals(5,ObjectInclusion.values().length);
	}

}
