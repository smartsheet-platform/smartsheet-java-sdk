package com.smartsheet.api.internal;

import com.smartsheet.api.DiscussionResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.DataWrapper;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.DiscussionInclusion;
import com.smartsheet.api.models.PaginationParameters;

import java.util.EnumSet;
import java.util.HashMap;


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
public class DiscussionRowResourcesImpl extends AbstractResources {

    public DiscussionRowResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

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
    public Discussion createDiscussionOnRow(long sheetId, long rowId, Discussion discussion) throws SmartsheetException{
        return this.createResource("sheets/" + sheetId + "/rows/" + rowId + "/discussions", Discussion.class, discussion);
    }

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
    public DataWrapper<Discussion> getRowDiscussions(long sheetId, long rowId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException{
        String path = "sheets/" + sheetId + "/rows/" + rowId + "/discussions" ;
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        path += QueryUtil.generateUrl(null, parameters);

        if (pagination != null) {
            path += pagination.toQueryString();
        }

        return this.listResourcesWithWrapper(path, Discussion.class);
    }
}
