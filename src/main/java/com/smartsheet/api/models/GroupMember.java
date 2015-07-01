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

/**
 * Represents the Group object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/1554165-managing-groups-team-enterprise-only-">Help
 * Managing Groups</a>
 */
public class GroupMember extends NamedModel<Long> {

    /**
     * Represents the Group Member’s email address
     */
    private String email;

    /**
     * Represents the Group Member’s first name
     */
    private String firstName;

    /**
     * Represents the Group Member’s last name
     */
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * A convenience class for making a {@link User} object with the appropriate fields for adding to a {@link Group}. See {@link CreateGroupBuilder}
     */
    public static class NewGroupMemberBuilder {
        private String email;

        /**
         * Get the email of the group member
         * @return
         */
        public String getEmail() {
            return email;
        }

        /**
         * Set the id of the group member
         * @param email
         */
        public NewGroupMemberBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public GroupMember build() {
            if(email == null){
                throw new InstantiationError("An email address must be set.");
            }

            GroupMember groupMember = new GroupMember();
            groupMember.setEmail(email);
            return groupMember;
        }
    }
}
