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

import com.smartsheet.api.models.enums.AttachmentType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AttachmentTypeTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        assertNotNull(AttachmentType.valueOf("FILE"));
        assertNotNull(AttachmentType.valueOf("GOOGLE_DRIVE"));
        assertNotNull(AttachmentType.valueOf("LINK"));
        assertNotNull(AttachmentType.valueOf("BOX_COM"));
        assertNotNull(AttachmentType.valueOf("DROPBOX"));
        assertNotNull(AttachmentType.valueOf("EVERNOTE"));
        assertNotNull(AttachmentType.valueOf("EGNYTE"));
        assertNotNull(AttachmentType.valueOf("ONEDRIVE"));
        assertNotNull(AttachmentType.valueOf("SMARTSHEET"));
        assertEquals(9,AttachmentType.values().length);
    }

}
