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
import com.smartsheet.api.models.Attachment;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DiscussionAttachmentResourcesImplTest extends ResourcesImplBase {

    private DiscussionAttachmentResourcesImpl discussionAttachmentResources;

    @Before
    public void setUp() throws Exception {
        discussionAttachmentResources = new DiscussionAttachmentResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testGetAttachments() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listAssociatedAttachments.json"));
        PaginationParameters parameters = new PaginationParameters(false, 1,1);

        PagedResult<Attachment> attachments = discussionAttachmentResources.getAttachments(1234L, 456L, parameters);
        assertTrue(attachments.getTotalCount() == 2);
        assertTrue(attachments.getData().get(0).getId() == 4583173393803140L);
    }
}
