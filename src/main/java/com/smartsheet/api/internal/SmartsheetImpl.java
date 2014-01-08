package com.smartsheet.api.internal;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import com.smartsheet.api.AttachmentResources;
import com.smartsheet.api.ColumnResources;
import com.smartsheet.api.CommentResources;
import com.smartsheet.api.DiscussionResources;
import com.smartsheet.api.FolderResources;
import com.smartsheet.api.HomeResources;
import com.smartsheet.api.RowResources;
import com.smartsheet.api.SearchResources;
import com.smartsheet.api.SheetResources;
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.TemplateResources;
import com.smartsheet.api.UserResources;
import com.smartsheet.api.WorkspaceResources;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JsonSerializer;

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
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<HomeResources> home;

	/**
	 * Represents the AtomicReference to WorkspaceResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<WorkspaceResources> workspaces;

	/**
	 * Represents the AtomicReference to FolderResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<FolderResources> folders;

	/**
	 * Represents the AtomicReference to TemplateResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<TemplateResources> templates;

	/**
	 * Represents the AtomicReference to SheetResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<SheetResources> sheets;

	/**
	 * Represents the AtomicReference to ColumnResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<ColumnResources> columns;

	/**
	 * Represents the AtomicReference to RowResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<RowResources> rows;

	/**
	 * Represents the AtomicReference to AttachmentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<AttachmentResources> attachments;

	/**
	 * Represents the AtomicReference to DiscussionResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<DiscussionResources> discussions;

	/**
	 * Represents the AtomicReference to CommentResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<CommentResources> comments;

	/**
	 * Represents the AtomicReference to UserResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<UserResources> users;

	/**
	 * Represents the AtomicReference to SearchResources.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
	 * effectively the underlying value is lazily created in a thread safe manner.
	 */
	private AtomicReference<SearchResources> search;

	/**
	 * Represents the AtomicReference for assumed user email.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and can be set via corresponding setter, therefore effectively the assumed user can be updated in the
	 * SmartsheetImpl in thread safe manner.
	 */
	private final AtomicReference<String> assumedUser;

	/**
	 * Represents the AtomicReference for access token.
	 * 
	 * It will be initialized in constructor and will not change afterwards. The underlying value will be initiallly set
	 * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
	 * SmartsheetImpl in thread safe manner.
	 */
	private final AtomicReference<String> accessToken;

	/**
	 * Create an instance with given server URI, HttpClient (optional) and JsonSerializer (optional)
	 * 
	 * Parameters: - serverURI : the server URI - accessToken : the access token - httpClient : the HttpClient
	 * (optional) - jsonSerializer : the JsonSerializer (optional)
	 * 
	 * Exceptions: - IllegalArgumentException : if serverURI/version/accessToken is null/empty
	 * 
	 * Implementation: this.baseURI = URI.create(baseURI); this.httpClient = httpClient == null ? new
	 * DefaultHttpClient() : httpClient; this.jsonSerializer = jsonSerializer == null ? new JacksonJsonSerializer :
	 * jsonSerializer; this.home = new AtomicReference<HomeResources>(); this.workspaces = new
	 * AtomicReference<WorkspaceResources>(); this.folders = new AtomicReference<FolderResources>(); this.templates =
	 * new AtomicReference<TemplateResources>(); this.sheets = new AtomicReference<SheetResources>(); this.columns = new
	 * AtomicReference<ColumnResources>(); this.rows = new AtomicReference<RowResources>(); this.attachments = new
	 * AtomicReference<AttachmentResources>(); this.discussions = new AtomicReference<DiscussionResources>();
	 * this.comments = new AtomicReference<CommentResources>(); this.users = new AtomicReference<UserResources>();
	 * this.search = new AtomicReference<SearchResources>(); this.assumedUser = new AtomicReference<String>();
	 * this.accessToken = new AtomicReference<String>(accessToken);
	 * 
	 * @param jsonSerializer
	 * @param accessToken
	 * @param httpClient
	 * @param baseURI
	 */
	public SmartsheetImpl(String baseURI, String accessToken, HttpClient httpClient, JsonSerializer jsonSerializer) {
		// FIXME: update below variables with appropriate values
		this.httpClient = null;
		this.accessToken = null;
		this.assumedUser = null;
	}

	/**
	 * Finalize the object, this method is overridden to close the HttpClient.
	 * 
	 * Parameters: None
	 * 
	 * Returns: None
	 * 
	 * Implementation: this.httpClient.close();
	 */
	protected void finalize() {
	}

	/**
	 * Getter of corresponding field.
	 * 
	 * Returns: corresponding field.
	 * 
	 * Implementation: Simply return corresponding field.
	 * 
	 * @return
	 */
	HttpClient getHttpClient() {
		return null;
	}

	/**
	 * Getter of corresponding field.
	 * 
	 * Returns: corresponding field.
	 * 
	 * Implementation: Simply return corresponding field.
	 * 
	 * @return
	 */
	JsonSerializer getJsonSerializer() {
		return null;
	}

	/**
	 * Getter of corresponding field.
	 * 
	 * Returns: corresponding field.
	 * 
	 * Implementation: Simply return corresponding field.
	 * 
	 * @return
	 */
	URI getBaseURI() {
		return null;
	}

	/**
	 * Return the assumed user.
	 * 
	 * Returns: the assumed user
	 * 
	 * Implementation: return this.assumedUser.get();
	 * 
	 * @return
	 */
	String getAssumedUser() {
		return null;
	}

	/**
	 * Return the access token
	 * 
	 * Returns: the access token
	 * 
	 * Implementation: return this.accessToken.get();
	 * 
	 * @return
	 */
	String getAccessToken() {
		return null;
	}

	/**
	 * Returns the HomeResources instance that provides access to Home resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: home.compareAndSet(null, new HomeResourcesImpl(this)); return home.get();
	 * 
	 * @return
	 */
	public HomeResources home() {
		return null;
	}

	/**
	 * Returns the WorkspaceResources instance that provides access to Workspace resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: workspaces.compareAndSet(null, new WorkspaceResourcesImpl(this)); return workspaces.get();
	 * 
	 * @return
	 */
	public WorkspaceResources workspaces() {
		return null;
	}

	/**
	 * Returns the FolderResources instance that provides access to Folder resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: folders.compareAndSet(null, new FolderResourcesImpl(this)); return folders.get();
	 * 
	 * @return
	 */
	public FolderResources folders() {
		return null;
	}

	/**
	 * Returns the TemplateResources instance that provides access to Template resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: templates.compareAndSet(null, new TemplateResourcesImpl(this)); return templates.get();
	 * 
	 * @return
	 */
	public TemplateResources templates() {
		return null;
	}

	/**
	 * Returns the SheetResources instance that provides access to Sheet resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: sheets.compareAndSet(null, new SheetResourcesImpl(this)); return sheets.get();
	 * 
	 * @return
	 */
	public SheetResources sheets() {
		return null;
	}

	/**
	 * Returns the ColumnResources instance that provides access to Column resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: columns.compareAndSet(null, new ColumnResourcesImpl(this)); return columns.get();
	 * 
	 * @return
	 */
	public ColumnResources columns() {
		return null;
	}

	/**
	 * Returns the RowResources instance that provides access to Row resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: rows.compareAndSet(null, new RowResourcesImpl(this)); return rows.get();
	 * 
	 * @return
	 */
	public RowResources rows() {
		return null;
	}

	/**
	 * Returns the AttachmentResources instance that provides access to Attachment resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: attachments.compareAndSet(null, new AttachmentResourcesImpl(this)); return attachments.get();
	 * 
	 * @return
	 */
	public AttachmentResources attachments() {
		return null;
	}

	/**
	 * Returns the DiscussionResources instance that provides access to Discussion resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: discussions.compareAndSet(null, new DiscussionResourcesImpl(this)); return discussions.get();
	 * 
	 * @return
	 */
	public DiscussionResources discussions() {
		return null;
	}

	/**
	 * Returns the CommentResources instance that provides access to Comment resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: comments.compareAndSet(null, new CommentResourcesImpl(this)); return comments.get();
	 * 
	 * @return
	 */
	public CommentResources comments() {
		return null;
	}

	/**
	 * Returns the UserResources instance that provides access to User resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: users.compareAndSet(null, new UserResourcesImpl(this)); return users.get();
	 * 
	 * @return
	 */
	public UserResources users() {
		return null;
	}

	/**
	 * Returns the SearchResources instance that provides access to searching resources.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the resources instance
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: search.compareAndSet(null, new SearchResourcesImpl(this)); return search.get();
	 * 
	 * @return
	 */
	public SearchResources search() {
		return null;
	}

	/**
	 * Set the email of the user to assume. Null/empty string indicates no user is assumed.
	 * 
	 * Parameters: - assumedUser : the email of the user to assume
	 * 
	 * Returns: None
	 * 
	 * Exceptions: None
	 * 
	 * Implementation: this.assumedUser.set(assumedUser);
	 * 
	 * @param assumedUser
	 */
	public void setAssumedUser(String assumedUser) {
	}

	/**
	 * Set the access token to use.
	 * 
	 * Parameters: - accessToken : the access token
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null/empty string
	 * 
	 * Implementation: this.accessToken.set(accessToken);
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
	}
}
