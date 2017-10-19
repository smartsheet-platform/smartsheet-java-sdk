package com.smartsheet.api.internal;

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


import com.smartsheet.api.AuthorizationException;
import com.smartsheet.api.InvalidRequestException;
import com.smartsheet.api.ReportResources;
import com.smartsheet.api.ResourceNotFoundException;
import com.smartsheet.api.ServiceUnavailableException;
import com.smartsheet.api.ShareResources;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.Folder;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;
import com.smartsheet.api.models.Report;
import com.smartsheet.api.models.ReportPublish;
import com.smartsheet.api.models.SheetEmail;
import com.smartsheet.api.models.SheetPublish;
import com.smartsheet.api.models.enums.ReportInclusion;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the ReportResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */

public class ReportResourcesImpl extends AbstractResources implements ReportResources{

    /**
     * Represents the ShareResources.
     *
     * It will be initialized in constructor and will not change afterwards.
     */
    private ShareResources shares;

    /**
     * Constructor.
     *
     * Parameters: - smartsheet : the SmartsheetImpl
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public ReportResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
        this.shares = new ShareResourcesImpl(smartsheet, "reports");
    }

    /**
     * Get a report.
     *
     * It mirrors to the following Smartsheet REST API method: GET /reports/{id}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param reportId the folder id
     * @param includes the optional objects to include in response
     * @param pageSize Number of rows per page
     * @param page page number to return
     * @return  the report (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws SmartsheetException the smartsheet exception
     */
    public Report getReport(long reportId, EnumSet<ReportInclusion> includes, Integer pageSize, Integer page) throws SmartsheetException{
        String path = "reports/" + reportId;
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        if (pageSize != null) {
            parameters.put("pageSize", pageSize.toString());
        }

        if (page != null) {
            parameters.put("page", page.toString());
        }

        path += QueryUtil.generateUrl(null, parameters);
        return this.getResource(path, Report.class);
    }

    /**
     * Sends a report as a PDF attachment via email to the designated recipients.
     *
     * It mirrors to the following Smartsheet REST API method: POST /reports/{id}/emails
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param reportId the report id
     * @param email the recipient email
     * @return the report (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws SmartsheetException the smartsheet exception
     */
    public void sendReport(long reportId, SheetEmail email) throws SmartsheetException{
         this.createResource("reports/" + reportId + "/emails", SheetEmail.class, email);
    };

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
     * @param parameters pagination parameters for paging result
     * @return all sheets (note that empty list will be returned if there is none)
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<Report> listReports(PaginationParameters pagination) throws SmartsheetException {
        return this.listReports(pagination, null);
    }
    public PagedResult<Report> listReports(PaginationParameters pagination, Date modifiedSince) throws SmartsheetException {
        String path= "reports";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        if (modifiedSince != null) {
            String isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(modifiedSince);
            parameters.put("modifiedSince", isoDate);
        }

        path += QueryUtil.generateUrl(null, parameters);
        return this.listResourcesWithWrapper(path, Report.class);
    }

    /**
     * Get a Report as an Excel file.
     *
     * It mirrors to the following Smartsheet REST API method: GET /reports/{id} with "application/vnd.ms-excel" Accept
     * HTTP header
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
     */
    public void getReportAsExcel(long id, OutputStream outputStream) throws SmartsheetException {
        getResourceAsFile("reports/" + id, "application/vnd.ms-excel",outputStream);
    }

    /**
     * Get a Report as an csv file.
     *
     * It mirrors to the following Smartsheet REST API method: GET /reports/{id} with "text/csv" Accept
     * HTTP header
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
     */
    public void getReportAsCsv(long id, OutputStream outputStream) throws SmartsheetException {
        getResourceAsFile("reports/" + id, "text/csv",outputStream);
    }

    /**
     * Get the publish status of a report.
     *
     * It mirrors to the following Smartsheet REST API method: GET /reports/{id}/publish
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
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
    public ReportPublish getPublishStatus(long id) throws SmartsheetException {
        return this.getResource("reports/" + id + "/publish", ReportPublish.class);
    }

    /**
     * Sets the publish status of a report and returns the new status, including the URLs of any
     * enabled publishing.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /reports/{id}/publish
     *
     * Exceptions:
     *   - InvalidRequestException : if there is any problem with the REST API request
     *   - AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   - ResourceNotFoundException : if the resource can not be found
     *   - ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   - SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   - SmartsheetException : if there is any other error occurred during the operation
     *
     * @param id the ID of the report
     * @param publish the ReportPublish object
     * @return the updated ReportPublish (note that if there is no such resource, this method will
     *     throw ResourceNotFoundException rather than returning null)
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public ReportPublish updatePublishStatus(long id, ReportPublish reportPublish) throws SmartsheetException{
        return this.updateResource("reports/" + id + "/publish", ReportPublish.class, reportPublish);
    }

        /**
     * <p>Creates an object of ShareResources.</p>
     *
     * @return the created ShareResources object
     */
    public ShareResources shareResources(){
        return this.shares;
    }
}
