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

import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Report;
import com.smartsheet.api.models.ReportPublish;
import com.smartsheet.api.models.SheetEmail;
import com.smartsheet.api.models.SheetPublish;
import com.smartsheet.api.models.enums.ReportInclusion;

import java.io.OutputStream;
import java.util.Date;
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
     * <p>It mirrors to the following Smartsheet REST API method: GET /report/{reportId}</p>
     *
     * @param reportId the reportId of the report
     * @param includes used To specify the optional objects to include.
     * @param pageSize page size parameter for pagination
     * @param page page parameter for pagination
     * @return  the report (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
     Report getReport(long reportId, EnumSet<ReportInclusion> includes, Integer pageSize, Integer page) throws SmartsheetException;

    /**
     * <p>Send a sheet as a PDF attachment via Email To the designated recipients.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: POST /reports/{reportId}/emails</p>
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
     void sendReport(long reportId, SheetEmail email) throws SmartsheetException;

    /**
     * <p>List all reports.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /reports</p>
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parameters pagination parameters for paging result
     * @param modifiedSince restrict results to sheets modified on or after this date
     * @return all sheets (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
     PagedResult<Report> listReports(PaginationParameters parameters, Date modifiedSince) throws SmartsheetException;

    /**
     * <p>List all reports.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /reports</p>
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param parameters pagination parameters for paging result
     * @return all sheets (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
     @Deprecated
     PagedResult<Report> listReports(PaginationParameters parameters) throws SmartsheetException;

    /**
     * <p>Get a Report as an excel file.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /reports/{id} with "application/vnd.ms-excel" Accept
     * HTTP header</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if outputStream is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param id the id
     * @param outputStream the OutputStream to which the Excel file will be written
     * @throws SmartsheetException the smartsheet exception
     * */
    void getReportAsExcel(long id, OutputStream outputStream) throws SmartsheetException;

    /**
     * <p>Get a Report as an csv file.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /reports/{id} with "application/vnd.ms-excel" Accept
     * HTTP header</p>
     *
     * Exceptions:
     *   IllegalArgumentException : if outputStream is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param id the id
     * @param outputStream the OutputStream to which the Excel file will be written
     * @throws SmartsheetException the smartsheet exception
     * */
    void getReportAsCsv(long id, OutputStream outputStream) throws SmartsheetException;

    /**
     * <p>Get the publish status of a report.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /reports/{id}/publish</p>
     *
     * @param id the ID of the report
     * @return the report publish status (note that if there is no such resource, this method will
     *     throw ResourceNotFoundException rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    ReportPublish getPublishStatus(long id) throws SmartsheetException;
    
    /**
     * <p>Sets the publish status of a report and returns the new status, including the URLs of any
     * enabled publishing.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /reports/{id}/publish</p>
     *
     * @param id the ID of the report
     * @param reportPublish the ReportPublish object
     * @return the updated ReportPublish (note that if there is no such resource, this method will
     *     throw ResourceNotFoundException rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public ReportPublish updatePublishStatus(long id, ReportPublish reportPublish) throws SmartsheetException;
    
    /**
     * <p>Creates an object of ShareResources.</p>
     *
     * @return the created ShareResources object
     */
    ShareResources shareResources();
}
