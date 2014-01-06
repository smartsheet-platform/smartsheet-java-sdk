package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.*;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.*;

/**
 * This is the implementation of the WorkspaceResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class WorkspaceResourcesImpl implements WorkspaceResources {
    /**
     * Represents the WorkspaceFolderResources.
     * 
     * It will be initialized in constructor and will not change afterwards.
     */
    private WorkspaceFolderResources folders;

    /**
     * Represents the ShareResources.
     * 
     * It will be initialized in constructor and will not change afterwards.
     */
    private ShareResources shares;

    /**
     * Constructor.
     * 
     * Parameters:
     * - smartsheet : the SmartsheetImpl
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is
     * 
     * Implementation:
     * super(smartsheet);
     * this.folders = new WorkspaceFolderResourcesImpl(smartsheet);
     * this.shares = new ShareResourcesImpl(smartsheet, "workspace");
     * @param smartsheet 
     */
    public WorkspaceResourcesImpl(SmartsheetImpl smartsheet) {
    }

    /**
     * List all workspaces.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /workspaces
     * 
     * Parameters:
     * None
     * 
     * Returns:
     * all workspaces (note that empty list will be returned if there is none)
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.listResources("workspaces", Workspace.class);
     * @return 
     */
    public List<Workspace> listWorkspaces() {
        return null;
    }

    /**
     * Get a workspace.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /workspace/{id}
     * 
     * Parameters:
     * - id : the ID
     * 
     * Returns:
     * the resource (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.getResource("workspace/" + id, Workspace.class);
     * @param id 
     * @return 
     */
    public Workspace getWorkspace(long id) {
        return null;
    }

    /**
     * Create a workspace.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /workspaces
     * 
     * Parameters:
     * - worksapace : the workspace to create, limited to the following required attributes:
     * * name (string)
     * 
     * Returns:
     * the created workspace
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.createResource("workspaces", Workspace.class, workspace);
     * @param workspace 
     * @return 
     */
    public Workspace createWorkspace(Workspace workspace) {
        return null;
    }

    /**
     * Update a workspace.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * PUT /workspace/{id}
     * 
     * Parameters:
     * - workspace : the workspace to update limited to the following attribute:
     * * name (string)
     * 
     * Returns:
     * the updated workspace (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.updateResource("workspace/" + workspace.getId(), Workspace.class, workspace);
     * @param workspace 
     * @return 
     */
    public Workspace updateWorkspace(Workspace workspace) {
        return null;
    }

    /**
     * Delete a workspace.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * DELETE /workspace{id}
     * 
     * Parameters:
     * - id : the ID of the workspace
     * 
     * Returns:
     * None
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * 
     * Implementation:
     * return this.deleteResource("workspace/" + workspace.getId(), Workspace.class);
     * @param id 
     */
    public void deleteWorkspace(long id) {
    }

    /**
     * Return the WorkspaceFolderResources object that provides access to Folder resources associated with Workspace resources.
     * 
     * Returns:
     * the WorkspaceFolderResources object
     * 
     * Exceptions:
     * None
     * 
     * Implementation:
     * return this.folders;
     * @return 
     */
    public WorkspaceFolderResources folders() {
        return null;
    }

    /**
     * Return the ShareResources object that provides access to Share resources associated with Workspace resources.
     * 
     * Returns:
     * the ShareResources object
     * 
     * Exceptions:
     * None
     * 
     * Implementation:
     * return this.shares;
     * @return 
     */
    public ShareResources shares() {
        return null;
    }
}

