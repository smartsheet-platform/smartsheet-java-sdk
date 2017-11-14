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

import java.io.Writer;

/**
 * <p>This interface is the entry point of the Smartsheet SDK, it provides convenient methods to get XXXResources instances
 * for accessing different types of resources.</p>
 * 
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface Smartsheet {

    /**
     * <p>Returns the HomeResources instance that provides access to Home resources.</p>
     *
     * @return the home resources instance
     */
    public HomeResources homeResources();

    /**
     * <p>Returns the WorkspaceResources instance that provides access to Workspace resources.</p>
     *
     * @return the workspace resources instance
     */
    public WorkspaceResources workspaceResources();

    /**
     * <p>Returns the FolderResources instance that provides access to Folder resources.</p>
     *
     * @return the folder resources instance
     */
    public FolderResources folderResources();

    /**
     * <p>Returns the TemplateResources instance that provides access to Template resources.</p>
     *
     * @return the template resources instance
     */
    public TemplateResources templateResources();

    /**
     * <p>Returns the SheetResources instance that provides access to Sheet resources.</p>
     *
     * @return the sheet resources instance
     */
    public SheetResources sheetResources();

    /**
     * <p>Returns the SightResources instance that provides access to Sight resources.</p>
     *
     * @return the sight resources instance
     */
    public SightResources sightResources();

    /**
     * <p>Returns the FavoriteResources instance that provides access to Favorite resources.</p>
     *
     * @return the favorite resources instance
     */
    public FavoriteResources favoriteResources();

    /**
     * <p>Returns the UserResources instance that provides access to User resources.</p>
     *
     * @return the user resources instance
     */
    public UserResources userResources();

    /**
     * <p>Returns the {@link GroupResources} instance that provides access to Group resources. </p>
     *
     * @return the group resources instance
     */
    public GroupResources groupResources();

    /**
     * <p>Returns the {@link ServerInfoResources} instance that provides access to Server Info resources. </p>
     *
     * @return the serverinfo resources instance
     */
    public ServerInfoResources serverInfoResources();

    /**
     * <p>Returns the SearchResources instance that provides access to searching resources.</p>
     *
     * @return the search resources instance
     */
    public SearchResources searchResources();

    /**
     * <p>Returns the ReportResources instance that provides access to report resources.</p>
     *
     * @return the report resources instance
     */
    public ReportResources reportResources();

    /**
     * <p>Returns the TokenResources instance that provides access to token resources.</p>
     *
     * @return the token resources instance
     */
    public TokenResources tokenResources();

    /**
     * <p>Returns the ContactResources instance that provides access to contact resources.</p>
     *
     * @return the contact resources instance
     */
    public ContactResources contactResources();

    /**
     * Returns the ImageUrlResources instance that provides access to image Url resources
     *
     * @return the image Url resources instance
     */
    public ImageUrlResources imageUrlResources();

    /**
     * Returns the WebhookResources instance that provides access to webhook resources
     *
     * @return the webhook resources instance
     */
    public WebhookResources webhookResources();

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

    /**
     * <p>Set the SDK API test scenario.</p>
     *
     * @param apiScenario the new API scenario name
     * @throws IllegalArgumentException if any argument is null/empty string
     */
    public void setAPIScenario(String apiScenario);

    /**
     * <p>Enable request/response tracing in client</p>
     * @param levels - what to trace (if anything; null if not tracing at all)
     */
    public void setTraces(Trace... levels);

    /**
     * enable/disable pretty JSON trace logging
     * @param pretty if the JSON is nicely-formatted or compact
     */
    public void setTracePrettyPrint(boolean pretty);

    /**
     * @deprecated As of release 2.0, replaced by {@link #homeResources()}
     */
    @Deprecated
    public void home();

    /**
     * @deprecated As of release 2.0, replaced by {@link #workspaceResources()}
     */
    @Deprecated
    public void workspaces();

    /**
     * @deprecated As of release 2.0, replaced by {@link #folderResources()}
     */
    @Deprecated
    public void folders();

    /**
     * @deprecated As of release 2.0, replaced by {@link #templateResources()}
     */
    @Deprecated
    public void templates();

    /**
     * @deprecated As of release 2.0, replaced by {@link #sheetResources()}
     */
    @Deprecated
    public void sheets();

    /**
     * @deprecated As of release 2.0, use sheetResources().columnResources()
     * @return the associates resource
     */
    @Deprecated
    public ColumnResources columns();

    /**
     * @deprecated As of release 2.0, use sheetResources().rowResources()
     */
    @Deprecated
    public void rows();

    /**
     * @deprecated As of release 2.0; example: use sheetResources().attachmentResources() for sheet-level attachments
     * @return the associates resource
     */
    @Deprecated
    public AttachmentResources attachments();

    /**
     * @deprecated As of release 2.0; example: use sheetResources().discussionResources() for sheet-level discussions
     */
    @Deprecated
    public void discussions();

    /**
     * @deprecated As of release 2.0; example: use sheetResources().discussionResources().commentResources() for discussion-level comments
     * @return the associates resource
     */
    @Deprecated
    public CommentResources comments();

    /**
     * @deprecated As of release 2.0, replaced by {@link #userResources()}
     */
    @Deprecated
    public void users();

    /**
     * @deprecated As of release 2.0, replaced by {@link #groupResources()}
     */
    @Deprecated
    public void groups();

    /**
     * @deprecated As of release 2.0, replaced by {@link #searchResources()}
     */
    @Deprecated
    public void search();
}
