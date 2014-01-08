package com.smartsheet.api.internal;

import com.smartsheet.api.AssociatedDiscussionResources;
import com.smartsheet.api.models.Discussion;

/**
 * This is the implementation of the AssociatedDiscussionResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class AssociatedDiscussionResourcesImpl implements AssociatedDiscussionResources {
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
	 */
	public Discussion createDiscussion(long objectId, Discussion discussion) {
		return null;
	}
}
