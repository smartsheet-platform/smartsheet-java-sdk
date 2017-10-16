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

import com.smartsheet.api.models.DeleteUserParameters;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteUserParametersTest {

    @Test
    public void testDeleteUserParametersModel() {
        DeleteUserParameters parameters = new DeleteUserParameters();
        parameters.setTransferToId(12345L);
        parameters.setTransferSheets(true);
        parameters.setRemoveFromSharing(true);

        assertEquals(12345, parameters.getTransferToId().longValue());
        assertTrue(parameters.isTransferSheets());
        assertTrue(parameters.isRemoveFromSharing());

        DeleteUserParameters parameters2 = new DeleteUserParameters(6789L, false, false);
        assertEquals(6789, parameters2.getTransferToId().longValue());
        assertFalse(parameters2.isTransferSheets());
        assertFalse(parameters2.isRemoveFromSharing());
    }

    @Test
    public void testToQueryString() {
        DeleteUserParameters parameters = new DeleteUserParameters(12345L, true, true);
        String[] matches1 = new String[] {"transferSheets=true", "removeFromSharing=true", "transferTo=12345"};
        for (String s : matches1)
        {
            assertTrue(parameters.toQueryString().contains(s));
        }

        DeleteUserParameters parameters2 = new DeleteUserParameters(null, true, true);
        String[] matches2 = new String[] {"transferSheets=true", "removeFromSharing=true"};
        for (String s : matches2)
        {
            assertTrue(parameters2.toQueryString().contains(s));
        }

        DeleteUserParameters parameters3 = new DeleteUserParameters(null, null, true);
        assertEquals("?removeFromSharing=true", parameters3.toQueryString());

        DeleteUserParameters parameters4 = new DeleteUserParameters(null, null, null);
        assertEquals("", parameters4.toQueryString());
    }
}
