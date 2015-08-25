package com.smartsheet.api.internal;

import com.smartsheet.api.RowDiscussionResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Discussion;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.enums.DiscussionInclusion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
public class RowDiscussionResourcesImpl extends AbstractResources implements RowDiscussionResources {

    public RowDiscussionResourcesImpl(SmartsheetImpl smartsheet) {
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
    public Discussion createDiscussion(long sheetId, long rowId, Discussion discussion) throws SmartsheetException{
        return this.createResource("sheets/" + sheetId + "/rows/" + rowId + "/discussions", Discussion.class, discussion);
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
     * @param file the file to be attached
     * @param contentType the type of file
     * @return the created discussion
     * @throws SmartsheetException the smartsheet exception
     * @throws IOException is there is an I/O exception
     */
    public Discussion createDiscussionWithAttachment(long sheetId, long rowId, Discussion discussion, File file, String contentType) throws SmartsheetException, IOException{
        String path = "sheets/" + sheetId + "/rows/" + rowId + "/discussions";
        Util.throwIfNull(sheetId, discussion, file, contentType);

        return this.createResourceWithAttachment(path, Discussion.class, discussion, "discussion", new FileInputStream(file), contentType, file.getName());
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
    public PagedResult<Discussion> listDiscussions(long sheetId, long rowId, PaginationParameters pagination, EnumSet<DiscussionInclusion> includes) throws SmartsheetException{
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
