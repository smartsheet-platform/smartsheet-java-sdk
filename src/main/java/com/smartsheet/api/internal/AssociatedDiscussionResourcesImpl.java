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



import com.smartsheet.api.AssociatedDiscussionResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Discussion;

/**
 * @deprecated As of release 2.0
 */
@Deprecated
public class AssociatedDiscussionResourcesImpl extends AbstractAssociatedResources 
    implements AssociatedDiscussionResources {

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public AssociatedDiscussionResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
        super(smartsheet,masterResourceType);
    }

    /**
     * @deprecated As of release 2.0
     */
    @Deprecated
    public Discussion createDiscussion(long objectId, Discussion discussion) throws SmartsheetException {
        Util.throwIfNull(objectId, discussion);
        return this.createResource(getMasterResourceType() + "/" + objectId + "/discussions",
                Discussion.class, discussion);
    }
}
