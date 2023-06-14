package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.Sight;
import com.smartsheet.api.models.enums.SightExclusion;
import com.smartsheet.api.models.enums.SightInclusion;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import static org.junit.Assert.assertNotNull;


public class SightResourcesImplTest extends ResourcesImplBase {
    private SightResourcesImpl sightResourcesImpl;

    @Before
    public void before() {
        sightResourcesImpl = new SightResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
            new DefaultHttpClient(), serializer));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullSight() throws SmartsheetException {
        sightResourcesImpl.updateSight(null);
    }

    @Test
    public void testGetSight() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getSightWithoutWidgets.json"));

        Sight sight = sightResourcesImpl.getSight(1000018L, EnumSet.noneOf(SightInclusion.class),
                EnumSet.of(SightExclusion.WIDGET), 4);
        assertNotNull(sight);
    }
}
