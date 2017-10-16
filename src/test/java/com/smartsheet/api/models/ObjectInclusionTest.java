package com.smartsheet.api.models;

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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertNotNull(ObjectInclusion.valueOf("FORMS"));
        assertNotNull(ObjectInclusion.valueOf("CELL_LINKS"));
        assertNotNull(ObjectInclusion.valueOf("FORMAT"));
        assertNotNull(ObjectInclusion.valueOf("SOURCE"));

        assertEquals(9,ObjectInclusion.values().length);
    }

}
