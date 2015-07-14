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


import com.smartsheet.api.ReportResources;
import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.models.*;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * This is the implementation of the ReportResources.
 *
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */

public class ReportResourcesImpl extends AbstractResources implements ReportResources{

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
     * @return the report (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null)
     * @throws SmartsheetException the smartsheet exception
     */
    public Report getReport(long reportId, EnumSet<ReportInclusion> includes, Integer pageSize, Integer page) throws SmartsheetException{
        String path = "reports/" + reportId;
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("pageSize", pageSize.toString());
        parameters.put("page", page.toString());

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
    public void sendSheet(long reportId, SheetEmail email) throws SmartsheetException{
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
    public DataWrapper<Report> listReports(PaginationParameters parameters) throws SmartsheetException {
        String path= "reports";
        if (parameters != null) {
            path += parameters.toQueryString();
            }
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
     * @param includes the optional objects to include in response
     * @param pageSize Number of rows per page
     * @param page page number to return
     * @param outputStream the OutputStream to which the Excel file will be written
     * @return the sheet as csv
     * @throws SmartsheetException the smartsheet exception
     */
    public void getReportAsExcel(long id, EnumSet<ReportInclusion> includes, Integer pageSize, Integer page, OutputStream outputStream) throws SmartsheetException {
        String path = "/reports";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));

        parameters.put("pageSize", pageSize.toString());
        parameters.put("page", page.toString());

        path += QueryUtil.generateUrl(null, parameters);
        getResourceAsFile(path, "application/vnd.ms-excel",outputStream);
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
     * @param includes the optional objects to include in response
     * @param pageSize Number of rows per page
     * @param page page number to return
     * @param outputStream the OutputStream to which the Excel file will be written
     * @return the sheet as csv
     * @throws SmartsheetException the smartsheet exception
     */
    public void getReportAsCsv(long id, EnumSet<ReportInclusion> includes, Integer pageSize, Integer page, OutputStream outputStream) throws SmartsheetException {
        String path = "/reports";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("pageSize", pageSize.toString());
        parameters.put("page", page.toString());

        path += QueryUtil.generateUrl(null, parameters);
        getResourceAsFile(path, "text/csv",outputStream);
    }

}
