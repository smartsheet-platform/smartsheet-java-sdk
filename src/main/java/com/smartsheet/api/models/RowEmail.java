package com.smartsheet.api.models;

/**
 * Represents RowEmail object.
 */
public class RowEmail extends Email {
	/**
	 * Represents include attachments flag.
	 */
	private Boolean includeAttachments;

	/**
	 * Represents include discussions flag.
	 */
	private Boolean includeDiscussions;

	public Boolean getIncludeAttachments() {
		return includeAttachments;
	}

	public void setIncludeAttachments(Boolean includeAttachments) {
		this.includeAttachments = includeAttachments;
	}

	public Boolean getIncludeDiscussions() {
		return includeDiscussions;
	}

	public void setIncludeDiscussions(Boolean includeDiscussions) {
		this.includeDiscussions = includeDiscussions;
	}
}
