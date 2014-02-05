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
import com.smartsheet.api.models.Discussion;

/**
 * This is the implementation of the AssociatedDiscussionResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AssociatedDiscussionResourcesImpl extends AbstractAssociatedResources 
	implements AssociatedDiscussionResources {
	
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl - masterResourceType : the master resource type (e.g. "sheet",
	 * "workspace").
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet, masterResourceType);
	 * 
	 * @param masterResourceType
	 * @param smartsheet
	 */
	public AssociatedDiscussionResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
		super(smartsheet,masterResourceType);
	}

	/**
	 * Create a discussion.
	 * 
	 * It mirrors to the following Smartsheet REST API method: POST /sheet/{id}/discussions POST /row/{id}/discussions
	 * 
	 * Parameters: - objectId : the ID of the object - discussion : the discussion object limited to the following
	 * attributes: * title * comment
	 * 
	 * Returns: the created discussion
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - InvalidRequestException : if there is any
	 * problem with the REST API request - AuthorizationException : if there is any problem with the REST API
	 * authorization(access token) - ResourceNotFoundException : if the resource can not be found -
	 * ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting) -
	 * SmartsheetRestException : if there is any other REST API related error occurred during the operation -
	 * SmartsheetException : if there is any other error occurred during the operation
	 * 
	 * Implementation: return this.createResource(getMasterResourceType() + "/" + objectId + "/discussions",
	 * Discussion.class, discussion);
	 * 
	 * @param discussion
	 * @param objectId
	 * @return
	 * @throws SmartsheetException 
	 */
	public Discussion createDiscussion(long objectId, Discussion discussion) throws SmartsheetException {
		return this.createResource(getMasterResourceType() + "/" + objectId + "/discussions", 
				Discussion.class, discussion);
	}
}
