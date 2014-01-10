package com.smartsheet.api;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.omg.CosNaming.IstringHelper;


public class SmartsheetExceptionTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSmartsheetExceptionString() throws SmartsheetException {
		thrown.expect(SmartsheetException.class);
		thrown.expectMessage("My Exception");
		throw new SmartsheetException("My Exception");
	}

	@Test
	public void testSmartsheetExceptionStringThrowable() throws SmartsheetException {
		NullPointerException expected = new NullPointerException();
		thrown.expect(SmartsheetException.class);
		thrown.expectMessage("Throwable exception");
		thrown.expectCause(is(expected));
		throw new SmartsheetException("Throwable exception", expected);
	}

	@Test
	public void testSmartsheetExceptionException() throws SmartsheetException {
		NullPointerException expected = new NullPointerException();
		thrown.expect(SmartsheetException.class);
		thrown.expectCause(is(expected));
		throw new SmartsheetException(expected);
	}

}
