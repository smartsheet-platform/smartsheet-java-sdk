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

import com.smartsheet.api.models.enums.Symbol;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SymbolTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        assertNotNull(Symbol.valueOf("FLAG"));
        assertNotNull(Symbol.valueOf("STAR"));
        assertNotNull(Symbol.valueOf("HARVEY_BALLS"));
        assertNotNull(Symbol.valueOf("RYG"));
        assertNotNull(Symbol.valueOf("PRIORITY"));
        assertNotNull(Symbol.valueOf("PRIORITY_HML"));
        assertNotNull(Symbol.valueOf("DECISION_SYMBOLS"));
        assertNotNull(Symbol.valueOf("DECISION_SHAPES"));
        assertNotNull(Symbol.valueOf("VCR"));
        assertNotNull(Symbol.valueOf("RYGB"));
        assertNotNull(Symbol.valueOf("RYGG"));
        assertNotNull(Symbol.valueOf("WEATHER"));
        assertNotNull(Symbol.valueOf("PROGRESS"));
        assertNotNull(Symbol.valueOf("ARROWS_3_WAY"));
        assertNotNull(Symbol.valueOf("ARROWS_4_WAY"));
        assertNotNull(Symbol.valueOf("ARROWS_5_WAY"));
        assertNotNull(Symbol.valueOf("DIRECTIONS_3_WAY"));
        assertNotNull(Symbol.valueOf("DIRECTIONS_4_WAY"));
        assertNotNull(Symbol.valueOf("SKI"));
        assertNotNull(Symbol.valueOf("SIGNAL"));
        assertNotNull(Symbol.valueOf("STAR_RATING"));
        assertNotNull(Symbol.valueOf("HEARTS"));
        assertNotNull(Symbol.valueOf("MONEY"));
        assertNotNull(Symbol.valueOf("EFFORT"));
        assertNotNull(Symbol.valueOf("PAIN"));

        assertEquals(25,Symbol.values().length);
    }

}
