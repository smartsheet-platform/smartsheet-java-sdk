package com.smartsheet.api;

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


import com.smartsheet.api.models.Comment;

/**
 * <p>This interface is the entry point of the Smartsheet SDK, it provides convenient methods to get XXXResources instances
 * for accessing different types of resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
//TODO: could put some example code in the documentation.
public interface Smartsheet {
	
	/**
	 * <p>Returns the HomeResources instance that provides access to Home resources.</p>
	 *
	 * @return the home resources instance
	 */
	public HomeResources home();

	/**
	 * <p>Returns the WorkspaceResources instance that provides access to Workspace resources.</p>
	 *
	 * @return the workspace resources instance
	 */
	public WorkspaceResources workspaces();

	/**
	 * <p>Returns the FolderResources instance that provides access to Folder resources.</p>
	 *
	 * @return the folder resources instance
	 */
	public FolderResources folders();

	/**
	 * <p>Returns the TemplateResources instance that provides access to Template resources.</p>
	 *
	 * @return the template resources instance
	 */
	public TemplateResources templates();

	/**
	 * <p>Returns the SheetResources instance that provides access to Sheet resources.</p>
	 *
	 * @return the sheet resources instance
	 */
	public SheetResources sheets();

	/**
	 * <p>Returns the AttachmentResources instance that provides access to Attachment resources.</p>
	 *
	 * @return the attachment resources instance
	 */
	public AttachmentResources attachments();

	/**
	 * <p>Returns the AttachmentVersioningResources instance that provides access to Attachment Versioning resources.</p>
	 *
	 * @return the attachment resources instance
	 */
	public AttachmentVersioningResources attachmentVersionings();

	/**
	 * <p>Returns the CommentAttachmentResources instance that provides access to Comment Attachment resources.</p>
	 *
	 * @return the comment attachment resources instance
	 */
	public CommentAttachmentResources commentAttachments();

	/**
	 * <p>Returns the DiscussionAttachmentResources instance that provides access to Discussion Attachment resources.</p>
	 *
	 * @return the discussion attachment resources instance
	 */
	public DiscussionAttachmentResources discussionAttachments();

//	/**
//	 * <p>Returns the FavoriteResources instance that provides access to Favorite resources.</p>
//	 *
//	 * @return the favorite resources instance
//	 */
//	public FavoriteResources favorites();

	/**
	 * <p>Returns the RowAttachmentResources instance that provides access to Row Attachment resources.</p>
	 *
	 * @return the row attachment resources instance
	 */
	public RowAttachmentResources rowAttachments();

	/**
	 * <p>Returns the SheetAttachmentResources instance that provides access to Sheet Attachment resources.</p>
	 *
	 * @return the sheet attachment resources instance
	 */
	public SheetAttachmentResources sheetAttachments();

	/**
	 * <p>Returns the SheetRowResources instance that provides access to Sheet Attachment resources.</p>
	 *
	 * @return the sheet attachment resources instance
	 */
	public SheetRowResources sheetRows();

	/**
	 * <p>Returns the DiscussionResources instance that provides access to Discussion resources.</p>
	 *
	 * @return the discussion resources instance
	 */
	public DiscussionResources discussions();

	/**
	 * <p>Returns the DiscussionCommentResources instance that provides access to Discussion comment resources.</p>
	 *
	 * @return the discussion comment resources instance
	 */
	public DiscussionCommentResources discussionComments();

	/**
	 * <p>Returns the SheetDiscussionResources instance that provides access to Sheet Discussion resources.</p>
	 *
	 * @return the sheet discussion resources instance
	 */
	public SheetDiscussionResources sheetDiscussions();

	/**
	 * <p>Returns the CommentResources instance that provides access to Comment resources.</p>
	 *
	 * @return the comment resources instance
	 */
	public CommentResources comments();

	/**
	 * <p>Returns the UserResources instance that provides access to User resources.</p>
	 *
	 * @return the user resources instance
	 */
	public UserResources users();

	/**
	 * <p>Returns the {@link GroupResources} instance that provides access to Group resources. </p>
	 *
	 * @return the group resources instance
	 */
	public GroupResources groups();

	
	/**
	 * <p>Returns the SearchResources instance that provides access to searching resources.</p>
	 *
	 * @return the search resources instance
	 */
	public SearchResources search();

	/**
	 * <p>Set the email of the user to assume.</p>
	 *
	 * @param assumedUser the new assumed user
	 * @throws IllegalArgumentException if any argument is null/empty string
	 */
	public void setAssumedUser(String assumedUser);

	/**
	 * <p>Set the access token to use.</p>
	 *
	 * @param accessToken the new access token
	 * @throws IllegalArgumentException if any argument is null/empty string
	 */
	public void setAccessToken(String accessToken);
}
