package com.smartsheet.api.internal;

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.CellHistory;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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
public class RowColumnResourcesImplTest extends ResourcesImplBase {
    private RowColumnResourcesImpl rowColumnResources;

    @Before
    public void setUp() throws Exception {
        rowColumnResources = new RowColumnResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
                new DefaultHttpClient(), serializer));
    }

    @Test
    public void testGetCellHistory() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getCellHistory.json"));
        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<CellHistory> cellHistory= rowColumnResources.getCellHistory(123L, 123L, 123L, parameters);

        assertTrue(cellHistory.getTotalPages() == 1);
        assertEquals(cellHistory.getData().get(1).getModifiedBy().getName(), "Joe Smart");
    }

}
