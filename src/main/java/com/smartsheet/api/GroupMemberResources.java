package com.smartsheet.api;

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


import com.smartsheet.api.models.GroupMember;

import java.util.List;

/**
 * <p>This interface provides methods to access Share resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface GroupMemberResources {

    /**
     * <p>Add members to a group.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /group/{id}/members</p>
     *
     * @param groupId the ID of the object to add members to.
     * @param members the list of members to add. Users that are already members will be ignored.
     * @return the list of newly added members. Users that were added to the group. Pre-existing members are not included in the result.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<GroupMember> addGroupMembers(long groupId, List<GroupMember> members) throws SmartsheetException;

    /**
     * <p>Remove a member from a group.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /group/{id}/member/{userId}</p>
     *
     * @param groupId the ID of the object to remove the member from
     * @param userId the ID of the user to remove.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteGroupMember(long groupId, long userId) throws SmartsheetException;
}
