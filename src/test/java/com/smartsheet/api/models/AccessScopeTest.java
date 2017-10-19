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

import com.smartsheet.api.models.enums.AccessScope;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AccessScopeTest {

    @Test
    public void test() {
        assertNotNull(AccessScope.valueOf("CREATE_SHEETS"));
        assertNotNull(AccessScope.valueOf("READ_SHEETS"));
        assertNotNull(AccessScope.valueOf("WRITE_SHEETS"));
        assertNotNull(AccessScope.valueOf("SHARE_SHEETS"));
        assertNotNull(AccessScope.valueOf("DELETE_SHEETS"));
        assertNotNull(AccessScope.valueOf("CREATE_SIGHTS"));
        assertNotNull(AccessScope.valueOf("DELETE_SIGHTS"));
        assertNotNull(AccessScope.valueOf("READ_SIGHTS"));
        assertNotNull(AccessScope.valueOf("SHARE_SIGHTS"));
        assertNotNull(AccessScope.valueOf("READ_USERS"));
        assertNotNull(AccessScope.valueOf("ADMIN_USERS"));
        assertNotNull(AccessScope.valueOf("ADMIN_SHEETS"));
        assertNotNull(AccessScope.valueOf("ADMIN_WORKSPACES"));
        assertNotNull(AccessScope.valueOf("ADMIN_WEBHOOKS"));
        assertEquals(14, AccessScope.values().length);
    }

    @Test
    public void bothVersionsOfAccessScopeAreIdentical() {
        Set<String> scopeNames = new HashSet<String>();
        for (AccessScope accessScope : AccessScope.values()) {
            scopeNames.add(accessScope.name());
        }

        for (com.smartsheet.api.oauth.AccessScope accessScope : com.smartsheet.api.oauth.AccessScope.values()) {
            assertTrue("Verify '" + accessScope.name() + "' exists", scopeNames.contains(accessScope.name()));
        }

        assertEquals("Verify lengths are the same", com.smartsheet.api.oauth.AccessScope.values().length, AccessScope.values().length);
    }
}
