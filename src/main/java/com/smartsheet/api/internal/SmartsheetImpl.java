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


import com.smartsheet.api.*;
import com.smartsheet.api.internal.http.DefaultShouldRetry;
import com.smartsheet.api.retry.CalcBackoff;
import com.smartsheet.api.internal.http.DefaultHttpClient;
import com.smartsheet.api.internal.http.HttpClient;
import com.smartsheet.api.internal.json.JacksonJsonSerializer;
import com.smartsheet.api.internal.json.JsonSerializer;
import com.smartsheet.api.internal.util.Util;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

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
     * Keep a reference to the defaultShouldRetry created by this implementation in order to pass along
     * CalcBackoff references instantiated by the caller.
     */
    private DefaultShouldRetry defaultShouldRetry = null;

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
     * Represents the AtomicReference to SightResources
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and will be initialized to non-null at the first time it is accessed via corresponding getter, therefore
     * effectively the underlying value is lazily created in a thread safe manner.
     */
    private AtomicReference<SightResources> sights;

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
     * Represents the AtomicReference for API scenario.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<String> apiScenario;

    /**
     * Represents the AtomicReference for change agent
     *
     * It will be initialized in constructor and will not change afterwards.
     *
     */
    private final AtomicReference<String> changeAgent;

    /**
     * Represents the AtomicReference for ServerInfoResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<ServerInfoResources> serverInfo;

    /**
     * Represents the AtomicReference for FavoriteResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<FavoriteResources> favorites;

    /**
     * Represents the AtomicReference for TokenResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<TokenResources> tokens;

    /**
     * Represents the AtomicReference for ContactResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<ContactResources> contacts;

    /**
     * Represents the AtomicReference for ImageUrlResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<ImageUrlResources> imageUrls;

    /**
     * Represents the AtomicReference for WebhookResources.
     *
     * It will be initialized in constructor and will not change afterwards. The underlying value will be initially set
     * as null, and can be set via corresponding setter, therefore effectively the access token can be updated in the
     * SmartsheetImpl in thread safe manner.
     */
    private final AtomicReference<WebhookResources> webhooks;

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
        this(baseURI, accessToken, httpClient, jsonSerializer, null, null);
    }

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
    public SmartsheetImpl(String baseURI, String accessToken, HttpClient httpClient, JsonSerializer jsonSerializer, String changeAgent, String apiScenario) {
        Util.throwIfNull(baseURI);
        Util.throwIfEmpty(baseURI);

        this.baseURI = URI.create(baseURI);
        if(httpClient == null) {
            this.defaultShouldRetry = new DefaultShouldRetry(jsonSerializer);
            this.httpClient = new DefaultHttpClient(HttpClients.createDefault(), this.defaultShouldRetry);
        }
        else {
            this.httpClient = httpClient;
        }
        this.jsonSerializer = jsonSerializer == null ? new JacksonJsonSerializer() : jsonSerializer;
        this.home = new AtomicReference<HomeResources>();
        this.workspaces = new AtomicReference<WorkspaceResources>();
        this.folders = new AtomicReference<FolderResources>();
        this.templates = new AtomicReference<TemplateResources>();
        this.sheets = new AtomicReference<SheetResources>();
        this.sights = new AtomicReference<SightResources>();
        this.favorites = new AtomicReference<FavoriteResources>();
        this.users = new AtomicReference<UserResources>();
        this.groups = new AtomicReference<GroupResources>();
        this.search = new AtomicReference<SearchResources>();
        this.assumedUser = new AtomicReference<String>();
        this.accessToken = new AtomicReference<String>(accessToken);
        this.apiScenario = new AtomicReference<String>(apiScenario);
        this.changeAgent = new AtomicReference<String>(changeAgent);
        this.reports = new AtomicReference<ReportResources>();
        this.serverInfo = new AtomicReference<ServerInfoResources>();
        this.tokens = new AtomicReference<TokenResources>();
        this.contacts = new AtomicReference<ContactResources>();
        this.imageUrls = new AtomicReference<ImageUrlResources>();
        this.webhooks = new AtomicReference<WebhookResources>();
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
     * Return the API scenario
     *
     * @return the API scenario
     */
    String getAPIScenario() {
        return apiScenario.get();
    }

    /**
     * Return the change agent identifier.
     *
     * @return the access token
     */
    String getChangeAgent() {
        return changeAgent.get();
    }

    /**
     * Returns the HomeResources instance that provides access to Home resources.
     *
     * @return the home resources
     */
    public HomeResources homeResources() {
        if (home.get() == null) {
            home.compareAndSet(null, new HomeResourcesImpl(this));
        }
        return home.get();
    }

    /**
     * Returns the WorkspaceResources instance that provides access to Workspace resources.
     *
     * @return the workspace resources
     */
    public WorkspaceResources workspaceResources() {
        if (workspaces.get() == null) {
            workspaces.compareAndSet(null, new WorkspaceResourcesImpl(this));
        }
        return workspaces.get();
    }

    /**
     * Returns the FolderResources instance that provides access to Folder resources.
     *
     * @return the folder resources
     */
    public FolderResources folderResources() {
        if (folders.get() == null) {
            folders.compareAndSet(null, new FolderResourcesImpl(this));
        }
        return folders.get();
    }

    /**
     * Returns the TemplateResources instance that provides access to Template resources.
     *
     * @return the template resources
     */
    public TemplateResources templateResources() {
        if (templates.get() == null) {
            templates.compareAndSet(null, new TemplateResourcesImpl(this));
        }
        return templates.get();
    }

    /**
     * Returns the SheetResources instance that provides access to Sheet resources.
     *
     * @return the sheet resources
     */
    public SheetResources sheetResources() {
        if (sheets.get() == null) {
            sheets.compareAndSet(null, new SheetResourcesImpl(this));
        }
        return sheets.get();
    }

    /**
     * Returns the SightResources instance that provides access to Sight resources.
     *
     * @return the sight resources
     */
    public SightResources sightResources() {
        if (sights.get() == null) {
            sights.compareAndSet(null, new SightResourcesImpl(this));
        }
        return sights.get();
    }
    /**
     * Returns the FavoriteResources instance that provides access to Favorite resources.
     *
     * @return the favorite resources
     */
    public FavoriteResources favoriteResources() {
        if (favorites.get() == null) {
            favorites.compareAndSet(null, new FavoriteResourcesImpl(this));
        }
        return favorites.get();
    }

    /**
     * Returns the {@link UserResources} instance that provides access to User resources.
     *
     * @return the user resources
     */
    public UserResources userResources() {
        if (users.get() == null) {
            users.compareAndSet(null, new UserResourcesImpl(this));
        }
        return users.get();
    }

    /**
     * Returns the {@link GroupResources} instance that provides access to User resources.
     *
     * @return the user resources
     */
    public GroupResources groupResources() {
        if (groups.get() == null) {
            groups.compareAndSet(null, new GroupResourcesImpl(this));
        }
        return groups.get();
    }

    /**
     * Returns the {@link SearchResources} instance that provides access to searching resources.
     *
     * @return the search resources
     */
    public SearchResources searchResources() {
        if (search.get() == null) {
            search.compareAndSet(null, new SearchResourcesImpl(this));
        }
        return search.get();
    }

    /**
     * Returns the {@link ReportResources} instance that provides access to Report resources.
     *
     * @return the report resources
     */
    public ReportResources reportResources() {
        if (reports.get() == null) {
            reports.compareAndSet(null, new ReportResourcesImpl(this));
        }
        return reports.get();
    }

    /**
     * Returns the {@link ServerInfoResources} instance that provides access to ServerInfo resources.
     *
     * @return the ServerInfo resources
     */
    public ServerInfoResources serverInfoResources() {
        if (serverInfo.get() == null) {
            serverInfo.compareAndSet(null, new ServerInfoResourcesImpl(this));
        }
        return serverInfo.get();
    }

    /**
     * Returns the TokenResources instance that provides access to token resources.
     *
     * @return the token resources
     */
    public TokenResources tokenResources() {
        if (tokens.get() == null) {
            tokens.compareAndSet(null, new TokenResourcesImpl(this));
        }
        return tokens.get();
    }

    /**
     * Returns the ContactResources instance that provides access to contact resources.
     *
     * @return the contact resources
     */
    public ContactResources contactResources() {
        if (contacts.get() == null) {
            contacts.compareAndSet(null, new ContactResourcesImpl(this));
        }
        return contacts.get();
    }

    /**
     * Returns the ImageUrlResources instance that provides access to image url resources.
     *
     * @return the image url resources
     */
    public ImageUrlResources imageUrlResources() {
        if (imageUrls.get() == null) {
            imageUrls.compareAndSet(null, new ImageUrlResourcesImpl(this));
        }
        return imageUrls.get();
    }

    /**
     * Returns the WebhookResources instance that provides access to webhook resources.
     *
     * @return the webhook resources
     */
    public WebhookResources webhookResources() {
        if (webhooks.get() == null) {
            webhooks.compareAndSet(null, new WebhookResourcesImpl(this));
        }
        return webhooks.get();
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
    public void setAccessToken(String accessToken) { this.accessToken.set(accessToken); }

    /**
     * Set the API Scenario to use.
     *
     * Parameters: - apiScenario : the API Scenario
     *
     * Returns: None
     *
     *
     * @param apiScenario the new API Scenario
     */
    public void setAPIScenario(String apiScenario) {
        this.apiScenario.set(apiScenario);
    }

    public void setCalcBackoff(CalcBackoff calcBackoff) {
        if(defaultShouldRetry != null) {
            defaultShouldRetry.setCalcBackoff(calcBackoff);
        }
    }

    /** set what request/response fields to log in trace-logging */
    @Override
    public void setTraces(Trace... traces) {
        getHttpClient().setTraces(traces);
    }

    /** set whether or not to generate "pretty formatted" JSON in trace-logging */
    @Override
    public void setTracePrettyPrint(boolean pretty) {
        getHttpClient().setTracePrettyPrint(pretty);
    }


    /**
     * @deprecated As of release 2.0, use sheetResources().columnResources()
     */
    @Deprecated
    public ColumnResources columns() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, use sheetResources().rowResources()
     */
    @Deprecated
    public void rows() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0; example: use sheetResources().attachmentResources() for sheet-level attachments
     */
    @Deprecated
    public AttachmentResources attachments() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0; example: use sheetResources().discussionResources() for sheet-level discussions
     */
    @Deprecated
    public void discussions() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0; example: use sheetResources().discussionResources().commentResources() for discussion-level comments
     */
    @Deprecated
    public CommentResources comments() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #userResources()}
     */
    @Deprecated
    public void users() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #groupResources()}
     */
    @Deprecated
    public void groups() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #searchResources()}
     */
    @Deprecated
    public void search() {
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #homeResources()}
     */
    @Deprecated
    public void home(){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #workspaceResources()}
     */
    @Deprecated
    public void workspaces(){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #folderResources()}
     */
    @Deprecated
    public void folders(){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #templateResources()}
     */
    @Deprecated
    public void templates(){
        throw new UnsupportedOperationException();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #sheetResources()}
     */
    @Deprecated
    public void sheets(){
        throw new UnsupportedOperationException();
    }
}
