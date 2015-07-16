package com.smartsheet.api;

import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.DiscussionInclusion;
import com.smartsheet.api.models.PaginationParameters;

import java.util.EnumSet;

/**
 * Created by anioding on 7/14/15.
 */
public interface DiscussionRowResources {

    /**
     * Create discussion on a row.
     *
     * It mirrors to the following Smartsheet REST API method: /sheets/{sheetId}/rows/{rowId}/discussions
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID
     * @param rowId the row ID
     * @param discussion the comment to add, limited to the following required attributes: text
     * @return the created comment
     * @throws SmartsheetException the smartsheet exception
     */
    public Discussion createDiscussionOnRow(long sheetId, long rowId, Discussion discussion) throws SmartsheetException;

    /**
     * Gets a list of all Discussions associated with the specified Row.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/rows/{rowId}/discussions
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheet ID
     * @param rowId the row ID
     * @param pagination the pagination pagination
     * @param includes the optional include parameters
     * @return the row discussions
     * @throws SmartsheetException the smartsheet exception
     */
    public DataWrapper<Discussion> getRowDiscussions(long sheetId, long rowId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException;
}
