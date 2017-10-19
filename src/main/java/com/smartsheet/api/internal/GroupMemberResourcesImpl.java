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

import com.smartsheet.api.GroupMemberResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.GroupMember;
import com.smartsheet.api.models.User;

import java.util.List;

/**
 * This is the implementation of the ShareResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class GroupMemberResourcesImpl extends AbstractAssociatedResources implements GroupMemberResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null or empty string
     *
     * @param smartsheet the smartsheet
     * @param masterResourceType the master resource type (e.g. "sheet", "workspace")
     */
    public GroupMemberResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
        super(smartsheet, masterResourceType);
    }

    @Override
    public List<GroupMember> addGroupMembers(long groupId, List<GroupMember> members) throws SmartsheetException {
        Util.throwIfNull(members);
        if (members.size() == 0) {
            return members;
        }
        return this.postAndReceiveList("groups/" + groupId + "/members", members, GroupMember.class);
    }

    @Override
    public void deleteGroupMember(long groupId, long userId) throws SmartsheetException {
        this.deleteResource("groups/" + groupId + "/members/" + userId, User.class);
    }

}
