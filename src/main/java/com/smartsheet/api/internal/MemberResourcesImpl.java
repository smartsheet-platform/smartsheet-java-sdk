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

import java.util.List;

import com.smartsheet.api.MemberResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.User;

/**
 * This is the implementation of the ShareResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class MemberResourcesImpl extends AbstractAssociatedResources implements MemberResources {
	
	/**
	 * Constructor.
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 *
	 * @param smartsheet the smartsheet
	 * @param masterResourceType the master resource type (e.g. "sheet", "workspace")
	 */
	public MemberResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
		super(smartsheet, masterResourceType);
	}

	@Override
	public List<User> addMembers(long objectId, List<User> members) throws SmartsheetException {
		Util.throwIfNull(members);
		if (members.size() == 0) {
			return members;
		}
		return this.postAndReceiveList(this.getMasterResourceType() +"/" + objectId + "/members", members, User.class);
	}

	@Override
	public void deleteMember(long objectId, long userId) throws SmartsheetException {
		this.deleteResource(this.getMasterResourceType() +"/" + objectId + "/member/" + userId, User.class);
	}

}
