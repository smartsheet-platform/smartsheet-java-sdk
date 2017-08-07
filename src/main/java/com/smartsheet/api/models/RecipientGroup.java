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

public class RecipientGroup implements Recipient {

    /**
     * The ID of a group recipient.
     */
    private Long groupId;

    /**
     * Get the group ID
     * @return the group ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Set the group ID
     * @param groupId the group ID
     */
    public RecipientGroup setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * A convenience class to help create a RecipientGroup object with the appropriate fields.
     */
    public static class AddRecipientGroupBuilder {
        /**
         * The ID of a group recipient.
         */
        private Long groupId;

        /**
         * Get the group ID
         * @return the group ID
         */
        public Long getGroupId() {
            return groupId;
        }

        /**
         * Set the group ID
         * @param groupId the group ID
         * @return  the builder
         */
        public AddRecipientGroupBuilder setGroupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }


        /**
         * Builds the RecipientGroup.
         *
         * @return the RecipientGroup
         */
        public RecipientGroup build() {
            RecipientGroup recipientGroup = new RecipientGroup();
            recipientGroup.groupId = groupId;
            return recipientGroup;
        }

    }
}
