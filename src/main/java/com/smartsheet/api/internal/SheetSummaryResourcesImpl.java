package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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


import com.smartsheet.api.*;
import com.smartsheet.api.internal.http.HttpEntity;
import com.smartsheet.api.internal.http.HttpMethod;
import com.smartsheet.api.internal.http.HttpRequest;
import com.smartsheet.api.internal.http.HttpResponse;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.SummaryFieldExclusion;
import com.smartsheet.api.models.enums.SummaryFieldInclusion;

import java.io.*;
import java.net.URLEncoder;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SheetSummaryResourcesImpl extends AbstractResources implements SheetSummaryResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SheetSummaryResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Gets the sheet summary
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{id}/summary
     *
     * @param sheetId the sheet id
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @return the sheet summary
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws com.smartsheet.api.InvalidRequestException if there is any problem with the REST API request
     * @throws com.smartsheet.api.AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws com.smartsheet.api.ResourceNotFoundException if the resource cannot be found
     * @throws com.smartsheet.api.ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     **/
    @Override
    public SheetSummary getSheetSummary(long sheetId, EnumSet<SummaryFieldInclusion> includes, EnumSet<SummaryFieldExclusion> excludes) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/summary";

        // Add the parameters to a map and build the query string at the end
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        // Iterate through the map of parameters and generate the query string
        path += QueryUtil.generateUrl(null, parameters);

        return this.getResource(path, SheetSummary.class);
    }

    /**
     * Gets the sheet summary fields
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{id}/summary/fields
     *
     * @param sheetId the sheet id
     * @param includes optional objects to include
     * @param excludes optional objects to exclude
     * @param pagination pagination parameters for the response
     * @return the list of sheet summary fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public PagedResult<SummaryField> getSheetSummaryFields(long sheetId, EnumSet<SummaryFieldInclusion> includes,
                                                           EnumSet<SummaryFieldExclusion> excludes,
                                                           PaginationParameters pagination) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/summary/fields";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        parameters.put("include", QueryUtil.generateCommaSeparatedList(includes));
        parameters.put("exclude", QueryUtil.generateCommaSeparatedList(excludes));

        path += QueryUtil.generateUrl(null, parameters);
        return this.listResourcesWithWrapper(path, SummaryField.class);
    }

    /**
     * Insert fields into a sheet summary.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields
     *
     * @param sheetId the sheet id
     * @param fields a list of summary fields to add
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return the list of created fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<SummaryField> addSheetSummaryFields(long sheetId, List<SummaryField> fields, Boolean renameIfConflict) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/summary/fields";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("renameIfConflict", renameIfConflict);

        path += QueryUtil.generateUrl(null, parameters);
        return this.postAndReceiveList(path, fields, SummaryField.class);
    }

    /**
     * Insert fields into a sheet summary.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields
     *
     * @param sheetId the sheet id
     * @param fields the list of summary fields to add
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return a bulk item result containing the created fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public BulkItemResult<SummaryField> addSheetSummaryFieldsWithPartialSuccess(long sheetId, List<SummaryField> fields,
                                                                                Boolean renameIfConflict) throws SmartsheetException {
        return this.doBulkOperation(sheetId, fields, renameIfConflict, HttpMethod.POST);
    }

    /**
     * Update fields in a sheet summary.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/summary/fields
     *
     * @param sheetId the sheet id
     * @param fields a list of summary fields to update
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return the updated fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<SummaryField> updateSheetSummaryFields(long sheetId, List<SummaryField> fields, Boolean renameIfConflict) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/summary/fields";

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("renameIfConflict", renameIfConflict);

        path += QueryUtil.generateUrl(null, parameters);
        return this.putAndReceiveList(path, fields, SummaryField.class);
    }

    /**
     * Update fields in a sheet summary.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/summary/fields
     *
     * @param sheetId the sheet id
     * @param fields a list of summary fields to update
     * @param renameIfConflict true if the call should rename conflicting field titles
     * @return a bulk item result containing the updated fields
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public BulkItemResult<SummaryField> updateSheetSummaryFieldsWithPartialSuccess(long sheetId, List<SummaryField> fields,
                                                                                   Boolean renameIfConflict) throws SmartsheetException {
        return this.doBulkOperation(sheetId, fields, renameIfConflict, HttpMethod.PUT);
    }

    /**
     * Delete fields in a sheet summary.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/summary/fields
     *
     * @param sheetId the sheet id
     * @param fieldIds List of field Ids
     * @param ignoreSummaryFieldsNotFound true if the call should ignore fields not found
     * @return List of field Ids deleted
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public List<Long> deleteSheetSummaryFields(long sheetId, Set<Long> fieldIds, Boolean ignoreSummaryFieldsNotFound) throws SmartsheetException {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        String path = "sheets/" + sheetId + "/summary/fields";
        parameters.put("ids", QueryUtil.generateCommaSeparatedList(fieldIds));
        parameters.put("ignoreSummaryFieldsNotFound", ignoreSummaryFieldsNotFound);

        path += QueryUtil.generateUrl(null, parameters);

        return this.deleteListResources(path, Long.class);
    }

    /**
     * Adds an image to the sheet summary field.
     *
     * It mirrors to the following Smartsheet REST API method: POST /sheets/{sheetId}/summary/fields/{fieldId}/images
     *
     * @param sheetId the sheet id
     * @param fieldId the summary field id
     * @param file path to image file to upload
     * @param contentType content-type of the file being uploaded
     * @param altText alternate text for the image
     * @return Result
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public Result<SummaryField> addSheetSummaryFieldImage(long sheetId, long fieldId, String file, String contentType, String altText)
        throws SmartsheetException, FileNotFoundException {

        Util.throwIfNull(file);

        String path = "sheets/" + sheetId + "/summary/fields/" + fieldId + "/images";
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (altText != null) {
            parameters.put("altText", altText);
        }
        path += QueryUtil.generateUrl(null, parameters);

        HttpRequest request = createHttpRequest(this.smartsheet.getBaseURI().resolve(path), HttpMethod.POST);
        try {
            request.getHeaders().put("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file, "UTF-8") + "\"");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        File f = new File(file);
        InputStream is = new FileInputStream(f);

        HttpEntity entity = new HttpEntity();
        entity.setContentType(contentType);
        entity.setContent(is);
        entity.setContentLength(f.length());
        request.setEntity(entity);

        Result<SummaryField> obj = null;
        HttpResponse response = this.smartsheet.getHttpClient().request(request);
        switch (response.getStatusCode()) {
            case 200:
                obj = this.smartsheet.getJsonSerializer().deserializeResult(SummaryField.class,
                        response.getEntity().getContent());
                break;
            default:
                handleError(response);
        }
        smartsheet.getHttpClient().releaseConnection();
        return obj;
    }

    private BulkItemResult<SummaryField> doBulkOperation (long sheetId, List<SummaryField> fields,
                                                          Boolean renameIfConflict, HttpMethod method) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/summary/fields";
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("allowPartialSuccess", "true");
        parameters.put("renameIfConflict", renameIfConflict);

        path = QueryUtil.generateUrl(path, parameters);

        HttpRequest request;
        request = createHttpRequest(smartsheet.getBaseURI().resolve(path), method);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        this.smartsheet.getJsonSerializer().serialize(fields, baos);

        HttpEntity entity = new HttpEntity();
        entity.setContentType("application/json");
        entity.setContent(new ByteArrayInputStream(baos.toByteArray()));
        entity.setContentLength(baos.size());
        request.setEntity(entity);

        HttpResponse response = this.smartsheet.getHttpClient().request(request);

        BulkItemResult<SummaryField> result = null;
        switch (response.getStatusCode()) {
            case 200:
                result = this.smartsheet.getJsonSerializer().deserializeBulkItemResult(SummaryField.class,
                        response.getEntity().getContent());
                break;
            default:
                handleError(response);
        }

        smartsheet.getHttpClient().releaseConnection();

        return result;
    }
}