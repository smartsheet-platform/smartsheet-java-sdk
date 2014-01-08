package com.smartsheet.api;

/**
 * This interface is the entry point of the Smartsheet SDK, it provides convenient methods to get XXXResources instances
 * for accessing different types of resources.
 * 
 * Currently the following resources are supported, please refer to
 * http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources. /hom /workspaces
 * /folders /templates /sheets /columns /rows /attachments /discussions /comments /users /search
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface Smartsheet {
	/**
	 * Returns the HomeResources instance that provides access to Home resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public HomeResources home();

	/**
	 * Returns the WorkspaceResources instance that provides access to Workspace resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public WorkspaceResources workspaces();

	/**
	 * Returns the FolderResources instance that provides access to Folder resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public FolderResources folders();

	/**
	 * Returns the TemplateResources instance that provides access to Template resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public TemplateResources templates();

	/**
	 * Returns the SheetResources instance that provides access to Sheet resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public SheetResources sheets();

	/**
	 * Returns the ColumnResources instance that provides access to Column resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public ColumnResources columns();

	/**
	 * Returns the RowResources instance that provides access to Row resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public RowResources rows();

	/**
	 * Returns the AttachmentResources instance that provides access to Attachment resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public AttachmentResources attachments();

	/**
	 * Returns the DiscussionResources instance that provides access to Discussion resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public DiscussionResources discussions();

	/**
	 * Returns the CommentResources instance that provides access to Comment resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public CommentResources comments();

	/**
	 * Returns the UserResources instance that provides access to User resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public UserResources users();

	/**
	 * Returns the SearchResources instance that provides access to searching resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * @return
	 */
	public SearchResources search();

	/**
	 * Set the email of the user to assume.
	 * 
	 * Parameters: - assumedUser : the email of the user to assume
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * @param assumedUser
	 */
	public void setAssumedUser(String assumedUser);

	/**
	 * Set the access token to use.
	 * 
	 * Parameters: - accessToken : the access token
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken);
}
