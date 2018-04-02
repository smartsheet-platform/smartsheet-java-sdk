package com.smartsheet.api;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.smartsheet.api.models.AutomationRule;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PaginationParameters;

public interface SheetAutomationRuleResources {
    /**
     * <p>Get all automation rules for this sheet</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/automationrules</p>
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
     * @param pagination the pagination parameters
     * @return a list of automation rules
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<AutomationRule> listAutomationRules(long sheetId, PaginationParameters pagination) throws SmartsheetException;

    /**
     * <p>Get a automation rule.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/automationrules/{automationRuleId}</p>
     *
     * @param sheetId the sheet id
     * @param automationRuleId the automation rule id
     * @return the automation rule (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public AutomationRule getAutomationRule(long sheetId, long automationRuleId) throws SmartsheetException;

    /**
     * <p>Updates an automation rule.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/automationrules/{automationRuleId}</p>
     *
     * @param sheetId the sheet id
     * @param automationRule the updated automation rule
     * @return the automation rule (note that if there is no such resource, this method will throw ResourceNotFoundException
     * rather than returning null).
     * @throws IllegalArgumentException if any argument is null or empty string
     * @throws InvalidRequestException if there is any problem with the REST API request
     * @throws AuthorizationException if there is any problem with  the REST API authorization (access token)
     * @throws ResourceNotFoundException if the resource cannot be found
     * @throws ServiceUnavailableException if the REST API service is not available (possibly due to rate limiting)
     * @throws SmartsheetException if there is any other error during the operation
     */
    public AutomationRule updateAutomationRule(long sheetId, AutomationRule automationRule) throws SmartsheetException;

    /**
     * <p>Delete an automation rule.</p>
     *
     * <p>It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/automationrules/{automationRuleId}</p>
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
     * @param automationRuleId the automation rule ID
     * @throws SmartsheetException the smartsheet exception
     */
    public void deleteAutomationRule(long sheetId, long automationRuleId) throws SmartsheetException;
}
