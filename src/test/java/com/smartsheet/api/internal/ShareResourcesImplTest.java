package com.smartsheet.api.internal;

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

import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Share;
import com.smartsheet.api.models.enums.AccessLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShareResourcesImplTest extends ResourcesImplBase {

    private ShareResourcesImpl shareResourcesImpl;

    @Before
    public void setUp() throws Exception {
        shareResourcesImpl = new ShareResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/", "accessToken",
                new DefaultHttpClient(), serializer), "sheets");
    }

    @Test
    public void testShareResourcesImpl() {
    }

    @Test
    public void testListShares() throws SmartsheetException, IOException {

        server.setResponseBody(new File("src/test/resources/listShares.json"));
        PaginationParameters parameters = new PaginationParameters(false, 1, 1);
        PagedResult<Share> shares = shareResourcesImpl.listShares(2906571706525572L, parameters, false);
        assertTrue("The number of shares returned is incorrect.", shares.getTotalCount() == 2);

        assertEquals("Email attribute of the share is incorrect.", "john.doe@smartsheet.com", shares.getData().get(0).getEmail());
        assertEquals("Email attribute of the share is incorrect.",null, shares.getData().get(1).getEmail());
    }

    @Test
    public void testGetShare() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getShare.json"));

        Share share = shareResourcesImpl.getShare(1234L, "fhqwhgads");

        assertEquals("Group 1", share.getName());
        assertEquals(AccessLevel.ADMIN, share.getAccessLevel());
        assertEquals("AQAISF82FOeE", share.getId());
    }

    @Test
    public void testUpdateShare() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateShare.json"));
        Share share = new Share();
        share.setAccessLevel(AccessLevel.ADMIN);
        Share newShare = shareResourcesImpl.updateShare(123L, share);
        assertEquals(share.getAccessLevel(), newShare.getAccessLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateShareWithNullShare() throws SmartsheetException {
        shareResourcesImpl.updateShare(123L, null);
    }

    @Test
    public void testDeleteShare() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteShare.json"));

        shareResourcesImpl.deleteShare(1234L, "fhqwhgads");
    }

    @Test
    public void testShareTo() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/shareTo.json"));

        List<Share> shares = new ArrayList<Share>();
        shares.add(new Share.CreateUserShareBuilder().setEmailAddress("john.doe@smartsheet.com").build());
        shares.add(new Share.CreateUserShareBuilder().setEmailAddress("jane.doe@smartsheet.com").build());
        shares.add(new Share.CreateGroupShareBuilder().setGroupId(34343l).build());

        shares = shareResourcesImpl.shareTo(1234L, shares, true);
        assertTrue(shares.size() == 1);
        assertEquals("jane.doe@smartsheet.com", shares.get(0).getEmail());
        assertEquals("Jane Doe", shares.get(0).getName());
    }
}
