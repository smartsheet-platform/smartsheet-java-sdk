/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GroupResourcesIT extends ITResourcesImpl{
    Smartsheet smartsheet;
    Long groupId;
    long groupMemberId;
    PagedResult<Group> groups;

    @Before
    public void setUp() throws Exception {
        smartsheet = createAuthentication();
    }

    @Test
    public void testGroupMethods() throws SmartsheetException, IOException {
        testCreateGroup();
        testListGroups();
        testGetGroupById();
        testUpdateGroup();
        testAddMembersToGroup();
        testRemoveMemberFromGroup();
        testDeleteGroup();
    }

    public void testCreateGroup() throws SmartsheetException, IOException {
        UserProfile user = smartsheet.userResources().getCurrentUser();

        if (user.getGroupAdmin()) {
            GroupMember member = new GroupMember.AddGroupMemberBuilder().setEmail("aditi.nioding@gmail.com").build();

            Group group = new Group.CreateGroupBuilder().setName("Test Group").setDescription("Test group").setMembers(Arrays.asList(member)).build();

            try {
                group =  smartsheet.groupResources().createGroup(group);
                assertNotNull(group.getId());
            } catch (AuthorizationException e) {
                fail("Not authorized.");
            }
        }
    }

    public void testListGroups() throws SmartsheetException, IOException {

        PaginationParameters parameters = new PaginationParameters.PaginationParametersBuilder().setIncludeAll(true).build();
        groups =  smartsheet.groupResources().listGroups(parameters);

        assertNotNull(groups);
        groupId = groups.getData().get(0).getId();
    }

    public void testGetGroupById() throws SmartsheetException, IOException {

        Group group =  smartsheet.groupResources().getGroup(groupId);
        assertNotNull(group);
    }

    //not executed in test due to permission issue
    public void testUpdateGroup() throws SmartsheetException, IOException {
        UserProfile user = smartsheet.userResources().getCurrentUser();

        if (user.getGroupAdmin()) {
            try {
                Group groupUpdated = new Group.UpdateGroupBuilder().setName("Renamed Group").setId(groupId).setDescription("Some description").build();
                assertNotNull(smartsheet.groupResources().updateGroup(groupUpdated));
            } catch (AuthorizationException e) {
                fail("Not authorized.");
            }
        }
    }

    //not executed in test due to permission issue
    public void testDeleteGroup() throws SmartsheetException, IOException {
        UserProfile user = smartsheet.userResources().getCurrentUser();

        if (user.getGroupAdmin()) {
            try {
                smartsheet.groupResources().deleteGroup(groupId);
            } catch (AuthorizationException e) {
                fail("Not authorized.");
            }
        }
    }

    //not executed in test due to permission issue
    public void testAddMembersToGroup() throws SmartsheetException, IOException {
        UserProfile user = smartsheet.userResources().getCurrentUser();

        if (user.getGroupAdmin()) {
            try {
                GroupMember member = new GroupMember.AddGroupMemberBuilder().setEmail("jane.doe@smartsheet.com").build();

                List<GroupMember> addedMembers = smartsheet.groupResources().memberResources().addGroupMembers(groupId, Arrays.asList(member));
                assertTrue(addedMembers.size() > 0);
                groupMemberId = addedMembers.get(0).getId();
            } catch (AuthorizationException e) {
                fail("Not authorized.");
            }
        }
    }

    //not executed in test due to permission issue
    public void testRemoveMemberFromGroup() throws SmartsheetException, IOException {
        UserProfile user = smartsheet.userResources().getCurrentUser();

        if (user.getGroupAdmin()) {
            try {
                smartsheet.groupResources().memberResources().deleteGroupMember(groupId, groupMemberId);
            } catch (AuthorizationException e) {
                fail("Not authorized.");
            } catch (ResourceNotFoundException e) {
                fail("Resource not found.");
            }
        }
    }
}
