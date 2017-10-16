package com.smartsheet.api;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;


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
