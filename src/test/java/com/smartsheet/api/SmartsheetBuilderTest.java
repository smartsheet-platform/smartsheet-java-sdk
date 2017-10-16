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

import com.smartsheet.api.internal.SmartsheetImpl;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SmartsheetBuilderTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSmartsheetBuilder() {}

    @Test
    public void testSetHttpClient() {

    }

    @Test
    public void testBuild() {
        Smartsheet smartsheet = new SmartsheetBuilder().build();
        assertTrue(smartsheet instanceof SmartsheetImpl);

        Smartsheet ss = (SmartsheetImpl)new SmartsheetBuilder().setBaseURI("a").setAccessToken("b").setHttpClient(
                new DefaultHttpClient()).setJsonSerializer(new JacksonJsonSerializer()).setAssumedUser("user").build();
        ss.getClass();
    }

}
