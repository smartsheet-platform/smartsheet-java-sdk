package com.smartsheet.api.internal.util;

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

import com.smartsheet.api.models.enums.ObjectInclusion;
import org.junit.Test;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryUtilTest {

    @Test
    public void testGenerateCommaSeparatedList() {
        Set<Long> list = new HashSet<Long>();
        list.add(123456789L);
        list.add(987654321L);

        // List
        String commaSeparatedStringList = QueryUtil.generateCommaSeparatedList(list);
        assertEquals("123456789,987654321", commaSeparatedStringList);

        // EnumSet
        String commaSeparatedStringEnumSet = QueryUtil.generateCommaSeparatedList(EnumSet.of(ObjectInclusion.DISCUSSIONS, ObjectInclusion.ATTACHMENTS));
        assertEquals("discussions,attachments", commaSeparatedStringEnumSet);

        assertEquals("", QueryUtil.generateCommaSeparatedList(null));
    }

    @Test
    public void testGenerateUrl() {
        assertEquals("", QueryUtil.generateUrl(null, null));
        assertEquals("url", QueryUtil.generateUrl("url", null));

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("param1", "1");
        map.put("param2", "2");
        assertEquals("?param1=1&param2=2", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put("param3", null);
        assertEquals("", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put(null, null);
        assertEquals("", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put("param1", 1);
        map.put("param2", null);
        assertEquals("?param1=1", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put("param1", 1);
        map.put("param2", null);
        map.put("param3", 3);
        String[] matches1 = new String[] {"param3=3", "param1=1"};
        for (String s : matches1)
        {
            assertTrue(QueryUtil.generateUrl(null, map).contains(s));
        }
        map.clear();

        map.put("param1", 1);
        map.put("param2", "");
        assertEquals("?param1=1", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put("param1", "");
        map.put("param2", "");
        assertEquals("", QueryUtil.generateUrl(null, map));
        map.clear();

        map.put(null, "");
        map.put("param2", "");
        assertEquals("", QueryUtil.generateUrl(null, map));
        map.clear();
    }
}
