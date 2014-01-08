package com.smartsheet.api.internal;

import com.smartsheet.api.AssociatedAttachmentResources;
import com.smartsheet.api.CommentResources;
import com.smartsheet.api.models.Comment;

/**
 * This is the implementation of the CommentResources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentResourcesImpl implements CommentResources {
	/**
	 * Represents the AssociatedAttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private AssociatedAttachmentResources attachments;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null
	 * 
	 * Implementation: super(smartsheet); this.attachments = new CommentAttachmentResources(smartsheet);
	 * 
	 * @param smartsheet
	 */
	public CommentResourcesImpl(SmartsheetImpl smartsheet) {
	}

	/**
	 * Get a comment.
	 * 
	 * It mirrors to the following Smartsheet REST API method: GET /comment/{id}
	 * 
	 * Parameters: - id : the ID
	 * 
	 * Returns: the resource (note that if there is no such resource, this method will throw ResourceNotFoundException
	 * rather than returning null).
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: return this.getResource("comment/" + id", Comment.class);
	 * 
	 * @param id
	 * @return
	 */
	public Comment getComment(long id) {
		return null;
	}

	/**
	 * Delete a comment.
	 * 
	 * It mirrors to the following Smartsheet REST API method: DELETE /comment{id}
	 * 
	 * Parameters: - id : the ID of the comment
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - InvalidRequestException : if there is any problem with the REST API request -
	 * AuthorizationException : if there is any problem with the REST API authorization(access token) -
	 * ResourceNotFoundException : if the resource can not be found - ServiceUnavailableException : if the REST API
	 * service is not available (possibly due to rate limiting) - SmartsheetRestException : if there is any other REST
	 * API related error occurred during the operation - SmartsheetException : if there is any other error occurred
	 * during the operation
	 * 
	 * Implementation: this.deleteResource("comment/" + id, Comment.class);
	 * 
	 * @param id
	 */
	public void deleteComment(long id) {
	}

	/**
	 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with
	 * Comment resources.
	 * 
	 * Returns: the AssociatedAttachmentResources object
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: return this.attachments;
	 * 
	 * @return
	 */
	public AssociatedAttachmentResources attachments() {
		return null;
	}
}
