package com.smartsheet.api.internal;

/**
 * This is the implementation of the AssociatedAttachmentResources for comments.
 * 
 * It extends AssociatedAttachmentResourcesImpl and overrides listAttachments method by throwing
 * UnsupportedOperationException (since it's not supported for comments).
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public class CommentAttachmentResources extends AssociatedAttachmentResourcesImpl {
	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet, "comment");
	 * 
	 * @param smartsheet
	 */
	public CommentAttachmentResources(SmartsheetImpl smartsheet) {
		super(smartsheet, "comment");

		// public AssociatedAttachmentResourcesImpl(SmartsheetImpl smartsheet, String masterResourceType) {
		// sheet or workspace
	}
}
