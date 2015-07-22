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



import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import com.smartsheet.api.*;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.util.Util;

/**
 * This is the implementation of Smartsheet interface.
 * 
 * Thread Safety: This class is thread safe because all its mutable fields are safe-guarded using AtomicReference to
 * ensure atomic modifications, and also the underlying HttpClient and JsonSerializer interfaces are thread safe.
 */
public class SmartsheetImpl implements Smartsheet {
	/**
	 * Represents the HttpClient.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private final HttpClient httpClient;

	/**
	 * Represents the JsonSerializer.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private JsonSerializer jsonSerializer;

	/**
	 * Represents the base URI of the Smartsheet REST API.
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private URI baseURI;

	/**
	 * Represents the AtomicReference to HomeResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<HomeResources> home;

	/**
	 * Represents the AtomicReference to WorkspaceResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<WorkspaceResources> workspaces;

	/**
	 * Represents the AtomicReference to FolderResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<FolderResources> folders;

	/**
	 * Represents the AtomicReference to TemplateResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<TemplateResources> templates;

	/**
	 * Represents the AtomicReference to SheetResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<SheetResources> sheets;

	/**
	 * Represents the AtomicReference to AttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<AttachmentResources> attachments;

	/**
	 * Represents the AtomicReference to DiscussionResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<DiscussionResources> discussions;

	/**
	 * Represents the AtomicReference to CommentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<CommentResources> comments;

	/**
	 * Represents the AtomicReference to UserResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<UserResources> users;
	
	/**
	 * Represents the AtomicReference to {@link GroupResources}.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<GroupResources> groups;

	/**
	 * Represents the AtomicReference to SearchResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<SearchResources> search;

	/**
	 * Represents the AtomicReference to ReportResources.
	 *
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<ReportResources> reports;
	/**
	 * Represents the AtomicReference for assumed user email.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and can be set via corresponding setter, therefore effectively the assumed user can be updated in the
	 * SmartsheetImpl in thread safe manner.
	 */
	private final AtomicReference<String> assumedUser;

	/**
	 * Represents the AtomicReference for access token.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
	 * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
	 * SmartsheetImpl in thread safe manner.
	 */
	private final AtomicReference<String> accessToken;

	/**
	 * Create an instance with given server URI, HttpClient (optional) and JsonSerializer (optional)
	 * 
	 * Exceptions: - IllegalArgumentException : if serverURI/version/accessToken is null/empty
	 *
	 * @param baseURI the server uri
	 * @param accessToken the access token
	 * @param httpClient the http client (optional)
	 * @param jsonSerializer the json serializer (optional)
	 */
	public SmartsheetImpl(String baseURI, String accessToken, HttpClient httpClient, JsonSerializer jsonSerializer) {
		Util.throwIfNull(baseURI);
		Util.throwIfEmpty(baseURI);
		
		this.baseURI = URI.create(baseURI);
		this.httpClient = httpClient == null ? new DefaultHttpClient() : httpClient;
		this.jsonSerializer = jsonSerializer == null ? new JacksonJsonSerializer() : jsonSerializer;
		this.home = new AtomicReference<HomeResources>();
		this.workspaces = new AtomicReference<WorkspaceResources>();
		this.folders = new AtomicReference<FolderResources>();
		this.templates = new AtomicReference<TemplateResources>();
		this.sheets = new AtomicReference<SheetResources>();
		this.attachments = new AtomicReference<AttachmentResources>();
		this.discussions = new AtomicReference<DiscussionResources>();
		this.comments = new AtomicReference<CommentResources>();
		this.users = new AtomicReference<UserResources>();
		this.groups = new AtomicReference<GroupResources>();
		this.search = new AtomicReference<SearchResources>();
		this.assumedUser = new AtomicReference<String>();
		this.accessToken = new AtomicReference<String>(accessToken);
		this.reports = new AtomicReference<ReportResources>();
	}

	/**
	 * Finalize the object, this method is overridden to close the HttpClient.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void finalize() throws IOException {
		this.httpClient.close();
	}

	/**
	 * Getter of corresponding field.
	 *
	 * @return corresponding field.
	 */
	HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * Getter of corresponding field.
	 *
	 * @return corresponding field
	 */
	JsonSerializer getJsonSerializer() {
		return jsonSerializer;
	}

	/**
	 * Getter of corresponding field.
	 * 
	 * Returns: corresponding field.
	 *
	 * @return the base uri
	 */
	URI getBaseURI() {
		return baseURI;
	}

	/**
	 * Return the assumed user.
	 *
	 * @return the assumed user
	 */
	String getAssumedUser() {
		return assumedUser.get();
	}

	/**
	 * Return the access token
	 * 
	 * @return the access token
	 */
	String getAccessToken() {
		return accessToken.get();
	}

	/**
	 * Returns the HomeResources instance that provides access to Home resources.
	 * 
	 * @return the home resources
	 */
	public HomeResources home() {
		home.compareAndSet(null, new HomeResourcesImpl(this));
		return home.get();
	}

	/**
	 * Returns the WorkspaceResources instance that provides access to Workspace resources.
	 * 
	 * @return the workspace resources
	 */
	public WorkspaceResources workspaces() {
		workspaces.compareAndSet(null, new WorkspaceResourcesImpl(this));
		return workspaces.get();
	}

	/**
	 * Returns the FolderResources instance that provides access to Folder resources.
	 * 
	 * @return the folder resources
	 */
	public FolderResources folders() {
		folders.compareAndSet(null, new FolderResourcesImpl(this));
		return folders.get();
	}

	/**
	 * Returns the TemplateResources instance that provides access to Template resources.
	 * 
	 * @return the template resources
	 */
	public TemplateResources templates() {
		templates.compareAndSet(null, new TemplateResourcesImpl(this));
		return templates.get();
	}

	/**
	 * Returns the SheetResources instance that provides access to Sheet resources.
	 * 
	 * @return the sheet resources
	 */
	public SheetResources sheets() {
		sheets.compareAndSet(null, new SheetResourcesImpl(this));
		return sheets.get();
	}

	/**
	 * Returns the AttachmentResources instance that provides access to Attachment resources.
	 * 
	 * @return the attachment resources
	 */
	public AttachmentResources attachments() {
		attachments.compareAndSet(null, new AttachmentResourcesImpl(this));
		return attachments.get();
	}

	/**
	 * Returns the DiscussionResources instance that provides access to Discussion resources.
	 * 
	 * @return the discussion resources
	 */
	public DiscussionResources discussions() {
		discussions.compareAndSet(null, new DiscussionResourcesImpl(this));
		return discussions.get();
	}

	/**
	 * Returns the CommentResources instance that provides access to Comment resources.
	 * 
	 * @return the comment resources
	 */
	public CommentResources comments() {
		comments.compareAndSet(null, new CommentResourcesImpl(this));
		return comments.get();
	}

	/**
	 * Returns the {@link UserResources} instance that provides access to User resources.
	 * 
	 * @return the user resources
	 */
	public UserResources users() {
		users.compareAndSet(null, new UserResourcesImpl(this));
		return users.get();
	}

	/**
	 * Returns the {@link GroupResources} instance that provides access to User resources.
	 * 
	 * @return the user resources
	 */
	public GroupResources groups() {
		groups.compareAndSet(null, new GroupResourcesImpl(this));
		return groups.get();
	}

	/**
	 * Returns the {@link SearchResources} instance that provides access to searching resources.
	 * 
	 * @return the search resources
	 */
	public SearchResources search() {
		search.compareAndSet(null, new SearchResourcesImpl(this));
		return search.get();
	}

	/**
	 * Returns the {@link ReportResources} instance that provides access to Report resources.
	 *
	 * @return the report resources
	 */
	public ReportResources reports() {
		reports.compareAndSet(null, new ReportResourcesImpl(this));
		return reports.get();
	}
	/**
	 * Set the email of the user to assume. Null/empty string indicates no user is assumed.
	 * 
	 * @param assumedUser the email of the user to assume
	 */
	public void setAssumedUser(String assumedUser) {
		this.assumedUser.set(assumedUser);
	}

	/**
	 * Set the access token to use.
	 * 
	 * Parameters: - accessToken : the access token
	 * 
	 * Returns: None
	 * 
	 *
	 * @param accessToken the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken.set(accessToken);
	}
}
