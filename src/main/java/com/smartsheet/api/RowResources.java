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


import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ObjectInclusion;

import java.util.EnumSet;
import java.util.List;

/**
 * <p>This interface provides methods to access Row resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface RowResources {

    /**
     * <p>Get a row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /row/{id}</p>
     *
     * @param id the id
     * @param includes used to specify the optional objects to include.
     * @return the row resource (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Row getRow(long id, EnumSet<ObjectInclusion> includes) throws SmartsheetException;

    /**
     * <p>Move a row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /row/{id}</p>
     *
     * @param id the id of the row to move
     * @param rowWrapper the row wrapper that specifies where to move the row.
     * @return the list of rows that have been moved by this operation.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Row> moveRow(long id, RowWrapper rowWrapper) throws SmartsheetException;

    /**
     * <p>Delete a row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /row{id}</p>
     *
     * @param id the id of the row
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void deleteRow(long id) throws SmartsheetException;

    /**
     * <p>Send a row via email to the designated recipients.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /row/{id}/emails</p>
     *
     * @param id the id
     * @param email the email
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public void sendRow(long id, RowEmail email) throws SmartsheetException;

    /**
     * <p>Update the values of the Cells in a Row.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /row/{id}/cells</p>
     *
     * @param rowId the row id
     * @param cells the cells to update
     * @return the list
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Cell> updateCells(long rowId, List<Cell> cells) throws SmartsheetException;

    /**
     * <p>Get the cell modification history.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /row/{rowId}/column/{columnId}/history</p>
     *
     * @param rowId the row id
     * @param columnId the column id
     * @return the cell modification history (note that if there is no such resource, this method will throw
     * ResourceeNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<CellHistory> getCellHistory(long rowId, long columnId) throws SmartsheetException;

    /**
     * <p>Return the AssociatedAttachmentResources object that provides access to attachment resources associated with Row
     * resources.</p>
     *
     * @return the associated attachment resources
     */
    public AssociatedAttachmentResources attachments();

    /**
     * <p>Return the AssociatedDiscussionResources object that provides access to discussion resources associated with
     * Row resources.</p>
     *
     * @return the associated discussion resources
     */
    public AssociatedDiscussionResources discussions();
}