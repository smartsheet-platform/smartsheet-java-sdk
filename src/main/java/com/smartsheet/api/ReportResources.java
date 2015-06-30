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

import java.util.EnumSet;

/**
 * <p>This interface provides methods to access Report resources.</p>
 *
 * <p>Thread Safety: Implementation of this interface must be thread safe.</p>
 */
public interface ReportResources {

    /**
     * <p>Get a report.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * GET /report/{reportId}<br/>
     *
     * @param reportId the reportId of the report
     * @param includes used To specify the optional objects to include.
     * @param pagination parametrs for pagination
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
     Report getReport(long reportId, EnumSet<ObjectInclusion> includes, PaginationParameters pagination) throws SmartsheetException;

    /**
     * <p>Send a sheet as a PDF attachment via Email To the designated recipients.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method:<br />
     * POST /reports/{reportId}/emails<br/>
     *
     * @param reportId the reportId of the report
     * @param email email of designated recipient.
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
     void sendSheet(long reportId, SheetEmail email) throws SmartsheetException;

    /**
     * List all reports.
     *
     * It mirrors to the following Smartsheet REST API method: GET /reports
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @return all sheets (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
     DataWrapper<Report> listReports(PaginationParameters parameters) throws SmartsheetException;


}
