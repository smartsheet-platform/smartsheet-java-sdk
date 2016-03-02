package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2016 Smartsheet
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

import com.smartsheet.api.internal.http.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Map;

/**
 * Created by kskeem on 3/1/16.
 */
public class AbstractResourcesTest {

    @Test
    public void testHeaders() {
        String tokenValue = "somevalue";
        String changeAgent = "mychangeagent";
        AbstractResources resources = new AbstractResources(new SmartsheetImpl("doesnt/matter", tokenValue,  new DefaultHttpClient(), null, changeAgent)) {};

        Map<String, String> headers = resources.createHeaders();
        assertEquals(headers.get("Authorization"), "Bearer " + tokenValue);
        assertEquals(headers.get("Smartsheet-Change-Agent"), changeAgent);
    }
}
