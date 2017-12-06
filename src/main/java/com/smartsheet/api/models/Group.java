package com.smartsheet.api.models;

import java.util.Date;
import java.util.List;

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
 * Represents a Group Object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/1554165-managing-groups-team-enterprise-only-">Managing groups</a>
 */
public class Group extends NamedModel<Long> {


    /**
     *    The description of the group.
     */
    private String description;

    /**
     *    The email address of the owner of the group.
     */
    private String owner;

    /**
     *    The the id of the owner of the group.
     */
    private Long ownerId;

    /**
     *    The date when the group was created.
     */
    private Date createdAt;


    /**
     *    The date when the group was last modified.
     */
    private Date modifiedAt;


    /**
     * The list of members in the group.
     */
    private List<GroupMember> members;

    /**
     * Default constructor
     */
    public Group() {
    }

    /**
     * Construct a Group with specified id
     *
     * @param id Group id
     */
    public Group(Long id) {
        this.setId(id);
    }

    /**
     * @return the description of the group
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description the description to set
     */
    public Group setDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * @return the email address of the owner
     */
    public String getOwner() {
        return owner;
    }


    /**
     * @param owner the owner email address to set
     */
    public Group setOwner(String owner) {
        this.owner = owner;
        return this;
    }


    /**
     * @return the id of the owner of the group.
     */
    public Long getOwnerId() {
        return ownerId;
    }


    /**
     * @param ownerId the owner Id to set
     */
    public Group setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }


    /**
     * @return the createdAt {@link Date}
     */
    public Date getCreatedAt() {
        return createdAt;
    }


    /**
     * @param createdAt the createdAt {@link Date} to set
     */
    public Group setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }


    /**
     * @return the modifiedAt {@link Date}
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }


    /**
     * @param modifiedAt the modifiedAt {@link Date} to set
     */
    public Group setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }


    /**
     * @return the {@link List} of {@link Group}s
     */
    public List<GroupMember> getMembers() {
        return members;
    }


    /**
     * @param members the {@link List} of {@link User}s to set
     */
    public Group setMembers(List<GroupMember> members) {
        this.members = members;
        return this;
    }

    /**
     * A convenience class to make a {@link Group} object with the necessary fields to create the group by posting it
     * to smartsheet.
     */
    public static class CreateGroupBuilder {
        private List<GroupMember> members;
        private String name;
        private String description;

        /**
         * Sets the members for the group being created.
         *
         * @param members The {@link List} of {@link Group}s to add as members of this group.
         * @return the creates the builder
         */
        public CreateGroupBuilder setMembers (List<GroupMember> members) {
            this.members = members;
            return this;
        }

        /**
         * Sets the name for the {@link Group} being created.
         *
         * @param name The name for the {@link Group} being created.
         * @return the creates the builder
         */
        public CreateGroupBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Returns the list of members.
         *
         * @return the columns
         */
        public List<GroupMember> getMembers() {
            return members;
        }

        /**
         * Returns the name for the group.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Creates a user by using the values from setters in this builder.
         *
         * @return the sheet
         */
        public Group build() {
            Group group = new Group();

            if ( name == null ) {
                throw new InstantiationError();
            }
            group.setName(name);
            group.setMembers(members);
            group.setDescription(description);
            return group;
        }

        /**
         * @return the description of the group
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         * @return the builder
         */
        public CreateGroupBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
    }


    /**
     * A convenience class to update a {@link Group} object with the necessary fields to create the group by putting it
     * to smartsheet.
     */
    public static class UpdateGroupBuilder {
        private String name;
        private String description;
        private Long id;

        /**
         * Sets the name for the {@link Group} being created.
         *
         * @param name The name for the {@link Group} being created.
         * @return the creates the builder
         */
        public UpdateGroupBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Returns the name for the group.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Creates a user by using the values from setters in this builder.
         *
         * @return the sheet
         */
        public Group build() {
            Group group = new Group();

            if (name == null || id == null) {
                throw new InstantiationError();
            }
            group.setDescription(description);
            group.setName(name);
            group.setId(id);
            return group;
        }

        /**
         * @return the description of the group
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         * @return the builder
         */
        public UpdateGroupBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * @return the id of the {@link Group}
         */
        public Long getId() {
            return id;
        }

        /**
         * @param id the id to set
         * @return the builder
         */
        public UpdateGroupBuilder setId(Long id) {
            this.id = id;
            return this;
        }
    }
}
