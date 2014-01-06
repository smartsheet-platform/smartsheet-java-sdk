package com.smartsheet.api;

import java.util.List;

import com.smartsheet.api.models.*;

/**
 * This interface provides methods to access row resources that are associated to a sheet object.
 * 
 * Currently the following resources are supported, please refer to http://publish.smartsheet.com/6f44714480de47c1a8cb72375864a7de for full listing of the resources, and http://www.smartsheet.com/developers/api-documentation for full API documentation:
 * 
 * POST /sheet/{id}/rows
 * GET /sheet/{id}/row/{number}
 * 
 * Thread Safety:
 * Implementation of this interface must be thread safe.
 */
public interface SheetRowResources {
    /**
     * Insert rows to a sheet.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/rows
     * 
     * Parameters:
     * - sheetId : the ID of the sheet
     * - rowWrapper : the RowWrapper object, one of the following attributes should be specified:
     * * toTop : Inserts the rows at the top of the sheet.
     * * toBottom : Inserts the rows at the bottom of the sheet
     * * parentId : Inserts the rows as the first child row of the parent. toBottom=true can also be set to add the row as the last child of the parent.
     * * siblingId : Inserts the row as the next sibling of the row ID provided.
     * 
     * Returns:
     * the created rows
     * 
     * Exceptions:
     * - IllegalArgumentException : if any argument is null
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param sheetId 
     * @param rowWrapper 
     * @return 
     */
    public List<Row> insertRows(long sheetId, RowWrapper rowWrapper);

    /**
     * Get a row.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /sheet/{id}/row/{number}
     * 
     * Parameters:
     * - sheetId : the ID of the sheet
     * - rowNumber : the row number
     * 
     * Returns:
     * the row (note that if there is no such resource, this method will throw ResourceNotFoundException rather than returning null).
     * 
     * Exceptions:
     * - InvalidRequestException : if there is any problem with the REST API request
     * - AuthorizationException : if there is any problem with the REST API authorization(access token)
     * - ResourceNotFoundException : if the resource can not be found
     * - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     * - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     * - SmartsheetException : if there is any other error occurred during the operation
     * @param id 
     * @param rowNumber 
     * @return 
     */
    public Row getRow(long id, int rowNumber);
}

