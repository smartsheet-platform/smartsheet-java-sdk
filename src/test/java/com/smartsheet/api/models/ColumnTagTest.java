package com.smartsheet.api.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColumnTagTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(ColumnTag.valueOf("CALENDAR_START_DATE"));
		assertNotNull(ColumnTag.valueOf("CALENDAR_END_DATE"));
		assertNotNull(ColumnTag.valueOf("GANTT_START_DATE"));
		assertNotNull(ColumnTag.valueOf("GANTT_END_DATE"));
		assertNotNull(ColumnTag.valueOf("GANT_PERCENT_COMPLETE"));
		assertNotNull(ColumnTag.valueOf("GANTT_PERCENT_COMPLETE"));
		assertNotNull(ColumnTag.valueOf("GANT_DISPLAY_LEVEL"));
		assertNotNull(ColumnTag.valueOf("GANTT_DISPLAY_LABEL"));
		assertNotNull(ColumnTag.valueOf("GANTT_PREDECESSOR"));
		assertNotNull(ColumnTag.valueOf("GANTT_DURATION"));
		assertNotNull(ColumnTag.valueOf("GANTT_ASSIGNED_RESOURCE"));

		assertEquals(11,ColumnTag.values().length);
	}

}

