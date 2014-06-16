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
public class Group extends NamedModel {
	
	
	/**
	 *	The description of the group.
	 */
	private String description;
	
	/**
	 *	The email address of the owner of the group.
	 */
	private String owner;
	
	/**
	 *	The the id of the owner of the group.
	 */
	private Long ownerId;
	
	/**
	 *	The date when the group was created.
	 */
	private Date createdAt;
	
	
	/**
	 *	The date when the group was last modified.
	 */
	private Date modifiedAt;

	
	/**
	 * The list of members in the group.
	 */
	private List<User> members;

	/**
	 * @return the description of the group
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	public void setOwner(String owner) {
		this.owner = owner;
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
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	/**
	 * @return the {@link List} of {@link Group}s
	 */
	public List<User> getMembers() {
		return members;
	}


	/**
	 * @param members the {@link List} of {@link User}s to set
	 */
	public void setMembers(List<User> members) {
		this.members = members;
	}
		
}
