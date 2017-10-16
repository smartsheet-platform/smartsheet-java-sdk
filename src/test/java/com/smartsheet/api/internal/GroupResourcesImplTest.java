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
import com.smartsheet.api.models.Group;
import com.smartsheet.api.models.Group.CreateGroupBuilder;
import com.smartsheet.api.models.Group.UpdateGroupBuilder;
import com.smartsheet.api.models.GroupMember;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GroupResourcesImplTest extends ResourcesImplBase {

    private GroupResourcesImpl groupResources;

    @Before
    public void setUp() throws Exception {
        groupResources = new GroupResourcesImpl(new SmartsheetImpl("http://localhost:9090/1.1/",
                "accessToken", new DefaultHttpClient(), serializer));
    }

    @Test
    public void testHomeResourcesImpl() {}

    @Test
    public void testGetGroups() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/listGroups.json"));

        PaginationParameters parameters = new PaginationParameters(false,1,1);
        PagedResult<Group> groups =  groupResources.listGroups(parameters);

            assertNotNull(groups.getData().get(0).getId());
            assertNotNull(groups.getData().get(0).getName());
            assertNotNull(groups.getData().get(0).getOwner());
            assertNotNull(groups.getData().get(0).getOwnerId());
            assertNotNull(groups.getData().get(0).getCreatedAt());
            assertNotNull(groups.getData().get(0).getModifiedAt());
            assertNotNull(groups.getData().get(0).getDescription());
            //assertNotNull(groups.getData().get(1).getId());

    }

    @Test
    public void testGetGroupById() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/getGroup.json"));

        Group group =  groupResources.getGroup(123l);
        assertNotNull(group.getId());
        assertNotNull(group.getName());
        assertNotNull(group.getOwner());
        assertNotNull(group.getOwnerId());
        assertNotNull(group.getCreatedAt());
        assertNotNull(group.getModifiedAt());
        assertNotNull(group.getDescription());
        assertNotNull(group.getId());

        for (GroupMember member : group.getMembers()) {
            assertNotNull(member.getFirstName());
            assertNotNull(member.getLastName());
            assertNotNull(member.getId());
            assertNotNull(member.getEmail());
        }
    }

    @Test
    public void testCreateGroup() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/createGroup.json"));

        CreateGroupBuilder builder = new CreateGroupBuilder();
        builder.setName("My Test Group")
            .setDescription("My awesome group")
            .setMembers(new ArrayList<GroupMember>());

        builder.getMembers().add(new GroupMember.AddGroupMemberBuilder().setEmail("test@test.com").build());
        builder.getMembers().add(new GroupMember.AddGroupMemberBuilder().setEmail("test2@test.com").build());
        builder.getMembers().add(new GroupMember.AddGroupMemberBuilder().setEmail("test3@test.com").build());


        Group group =  groupResources.createGroup(builder.build());
        assertNotNull(group.getId());
        assertNotNull(group.getName());
        assertNotNull(group.getOwner());
        assertNotNull(group.getOwnerId());
        assertNotNull(group.getCreatedAt());
        assertNotNull(group.getModifiedAt());
        assertNotNull(group.getDescription());
        assertNotNull(group.getId());

        for (GroupMember member : group.getMembers()) {
            assertNotNull(member.getFirstName());
            assertNotNull(member.getLastName());
            assertNotNull(member.getId());
            assertNotNull(member.getEmail());
        }

    }

    @Test
    public void testUpdateGroup() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/updateGroup.json"));

        UpdateGroupBuilder builder = new UpdateGroupBuilder();
        builder.setName("My Test Group - renamed ")
            .setDescription("My awesome group- redecribed")
            .setId(123L);

        Group group =  groupResources.updateGroup(builder.build());
        assertNotNull(group.getId());
        assertNotNull(group.getName());
        assertNotNull(group.getOwner());
        assertNotNull(group.getOwnerId());
        assertNotNull(group.getCreatedAt());
        assertNotNull(group.getModifiedAt());
        assertNotNull(group.getDescription());
        assertNotNull(group.getId());

    }

    @Test
    public void testDeleteGroup() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteGroup.json"));
        groupResources.deleteGroup(1234l);
    }

    @Test
    public void testAddMembersToGroup() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/addGroupMembers.json"));
        List<GroupMember> newMembers = new ArrayList<GroupMember>();
        newMembers.add(new GroupMember.AddGroupMemberBuilder().setEmail("test3@test.com").build());
        newMembers.add(new GroupMember.AddGroupMemberBuilder().setEmail("test4@test.com").build());
        List<GroupMember> addedMembers = groupResources.memberResources().addGroupMembers(1234l, newMembers);
        assertTrue(addedMembers.size() > 0);

        for(GroupMember member : addedMembers) {
            assertNotNull(member.getEmail());
            assertNotNull(member.getId());
        }
    }
    @Test
    public void testRemoveMemberFromGroup() throws SmartsheetException, IOException {
        server.setResponseBody(new File("src/test/resources/deleteMemberFromGroup.json"));
        groupResources.memberResources().deleteGroupMember(1234l, 1234l);
    }
}
