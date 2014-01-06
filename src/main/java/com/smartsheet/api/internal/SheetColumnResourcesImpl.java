package com.smartsheet.api.internal;

import java.util.List;

import com.smartsheet.api.SheetColumnResources;
import com.smartsheet.api.internal.*;
import com.smartsheet.api.models.*;

/**
 * This is the implementation of the SheetColumnResources.
 * 
 * Thread Safety:
 * This class is thread safe because it is immutable and its base class is thread safe.
 */
public class SheetColumnResourcesImpl implements SheetColumnResources {
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
     * @param smartsheet 
     */
    public SheetColumnResourcesImpl(SmartsheetImpl smartsheet) {
    }

    /**
     * List columns of a given sheet.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * GET /sheet/{id}/columns
     * 
     * Parameters:
     * - sheetId : the ID of the sheet
     * 
     * Returns:
     * the columns (note that empty list will be returned if there is none)
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
     * return this.listResources("sheet/" + sheetId + "/columns", Column.class);
     * @param sheetId 
     * @return 
     */
    public List<Column> listColumns(long sheetId) {
        return null;
    }

    /**
     * Add column to a sheet.
     * 
     * It mirrors to the following Smartsheet REST API method:
     * POST /sheet/{id}/columns
     * 
     * Parameters:
     * - sheetId : the ID of the sheet
     * - column : the coluimn object limited to the following attributes:
     * * title
     * * type
     * * symbol (optional)
     * * options (optional) - array of options
     * * index (zero-based)
     * * systemColumnType (optional)
     * * autoNumberFormat (optional)
     * 
     * Returns:
     * the created column
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
     * return this.createResource("sheet/" + sheetId + "/columns", Column.class, column);
     * @param sheetId 
     * @param column 
     * @return 
     */
    public Column addColumn(long sheetId, Column column) {
        return null;
    }
}

