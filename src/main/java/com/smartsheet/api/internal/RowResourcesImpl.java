package com.smartsheet.api.internal;
import java.util.EnumSet;
import java.util.List;

import com.smartsheet.api.*;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.*;
/**
 * This is the implementation of the RowResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
*/
public class RowResourcesImpl implements RowResources {
/**
 * Represents the AssociatedAttachmentResources.
 * 
 * It will be initialized in constructor and will not change afterwards.
*/
private AssociatedAttachmentResources attachments;
/**
 * Represents the AssociatedDiscussionResources.
 * 
 * It will be initialized in constructor and will not change afterwards.
*/
private AssociatedDiscussionResources discussions;
/**
 * Constructor.
 * 
 * Parameters:
 * - smartsheet : the SmartsheetImpl
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * 
 * Implementation:
 * super(smartsheet);
 * this.attachments = new AssociatedAttachmentResourcesImpl(smartsheet, "row");
 * this.discussions = new AssociatedDiscussionResourcesImpl(smartsheet, "row");
 * @param smartsheet 
*/
public RowResourcesImpl(SmartsheetImpl smartsheet) {
}
/**
 * Get a row.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * GET /row/{id}
 * 
 * Parameters:
 * - id : the ID
 * - includes : used to specify the optional objects to include, currently DISCUSSIONS and ATTACHMENTS are supported.
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
 * String path = "row/" + id;
 * if (includes != null && !includes.isEmpty()) {
 *     path += "?include=";
 *     for (ObjectInclusion oi : includes) {
 *         path += oi.name().toLowerCase() + ",";
 *     }
 * }
 * 
 * return this.getResource(path, Row.class);
 * @param id 
 * @return 
*/
public Row getRow(long id, EnumSet<ObjectInclusion> includes) {
    return null;
}
/**
 * Move a row.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * PUT /row/{id}
 * 
 * Parameters:
 * - rowWrapper : the RowWrapper with one of the following attributes:
 * * toTop : Moves  the row (and children rows, if any) to the top of the sheet.
 * * toBottom : Moves the row to the bottom of the sheet
 * * parentId : Moves the row as the first child row of the parent. toBottom=true can also be set to add the row as the last child of the parent.
 * * siblingId : Moves the row as the next sibling of the row ID provided.
 * 
 * Returns:
 * the rows that have been moved by the operaetion
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
 * return this.putAndReceiveList("row/" + id, rowWrapper, Row.class);
 * @param rowWrapper 
 * @return 
*/
public List<Row> moveRow(RowWrapper rowWrapper) {
    return null;
}
/**
 * Delete a row.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * DELETE /row{id}
 * 
 * Parameters:
 * - id : the ID of the row
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
 * this.deleteResource("row/" + id, Row.class);
 * @param id 
*/
public void deleteRow(long id) {
}
/**
 * Send a row via email to the designated recipients.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * POST /row/{id}/emails
 * 
 * Parameters:
 * - id : the ID of the row
 * - email : the RowEmail
 * 
 * Returns:
 * None
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
 * this.createResource("row/" + id + "/emails", RowEmail.class, email);
 * @param id 
 * @param email 
*/
public void sendRow(long id, RowEmail email) {
}
/**
 * Update the values of the Cells in a Row.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * PUT /row/{id}/cells
 * 
 * Parameters:
 * - id : the row ID
 * - cells : the cells to update (Cells must have the following attributes set: 
 * * columnId
 * * value
 * * strict (optional)
 * 
 * Returns:
 * the updated cells (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
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
 * return this.putAndReceiveList("row/" + id + "/cells", cells, Cell.class);
 * @param rowId 
 * @return 
*/
public List<Cell> updateCells(long rowId, List<Cell> cells) {
    return null;
}
/**
 * Get the cell modification history.
 * 
 * It mirrors to the following Smartsheet REST API method:
 * GET /row/{rowId}/column/{columnId}/history
 * 
 * Parameters:
 * - rowId : the row ID
 * - columnId : the column ID
 * 
 * Returns:
 * the modification history (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
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
 * return this.listResource("row/" + rowId + "/column/" + columnId + "/history", CellHistory.class);
 * @param columnId 
 * @param rowId 
 * @return 
*/
public List<CellHistory> getCellHistory(long rowId, long columnId) {
    return null;
}
/**
 * Return the AssociatedAttachmentResources object that provides access to attachment resources associated with Row resources.
 * 
 * Returns:
 * the AssociatedAttachmentResources object
 * 
 * Exceptions:
 * None
 * 
 * Implementation:
 * return this.attachments;
 * @return 
*/
public AssociatedAttachmentResources attachments() {
    return null;
}
/**
 * Return the AssociatedDiscussionResources object that provides access to discussion resources associated with Row resources.
 * 
 * Returns:
 * the AssociatedDiscussionResources object
 * 
 * Exceptions:
 * None
 * 
 * Implementation:
 * return this.discussions;
 * @return 
*/
public AssociatedDiscussionResources discussions() {
    return null;
}
}

