package com.smartsheet.api.internal;

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

import com.smartsheet.api.*;
import com.smartsheet.api.internal.util.QueryUtil;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.*;
import java.util.HashMap;

public class SheetAutomationRuleResourcesImpl extends AbstractResources implements SheetAutomationRuleResources {

    /**
     * Constructor.
     *
     * Exceptions: - IllegalArgumentException : if any argument is null
     *
     * @param smartsheet the smartsheet
     */
    public SheetAutomationRuleResourcesImpl(SmartsheetImpl smartsheet) {
        super(smartsheet);
    }

    /**
     * Get all automation rules for this sheet
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/automationrules
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
     * @param pagination the pagination pagination
     * @return all the automation rules
     * @throws SmartsheetException the smartsheet exception
     */
    public PagedResult<AutomationRule> listAutomationRules(long sheetId, PaginationParameters pagination) throws SmartsheetException {
        String path = "sheets/" + sheetId + "/automationrules";
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        if (pagination != null) {
            parameters = pagination.toHashMap();
        }
        path += QueryUtil.generateUrl(null, parameters);

        return this.listResourcesWithWrapper(path, AutomationRule.class);
    }

    /**
     * Get a automation rule.
     *
     * It mirrors to the following Smartsheet REST API method: GET /sheets/{sheetId}/automationrules/{automationRuleId}
     *
     * Exceptions:
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheetId
     * @param automationRuleId the automation rule ID
     * @return the automation rule
     * @throws SmartsheetException the smartsheet exception
     */
    public AutomationRule getAutomationRule(long sheetId, long automationRuleId) throws SmartsheetException {
        return this.getResource("sheets/" + sheetId + "/automationrules/" + automationRuleId, AutomationRule.class);
    }

    /**
     * Updates an automation rule.
     *
     * It mirrors to the following Smartsheet REST API method: PUT /sheets/{sheetId}/automationrules/{automationRuleId}
     *
     * Exceptions:
     *   IllegalArgumentException : if any argument is null
     *   InvalidRequestException : if there is any problem with the REST API request
     *   AuthorizationException : if there is any problem with the REST API authorization(access token)
     *   ResourceNotFoundException : if the resource can not be found
     *   ServiceUnavailableException : if the REST API service is not available (possibly due to rate limiting)
     *   SmartsheetRestException : if there is any other REST API related error occurred during the operation
     *   SmartsheetException : if there is any other error occurred during the operation
     *
     * @param sheetId the sheetId
     * @param automationRule the automation rule to update
     * @return the updated automation rule (note that if there is no such resource, this method will throw
     * ResourceNotFoundException rather than returning null).
     * @throws SmartsheetException the smartsheet exception
     */
    public AutomationRule updateAutomationRule(long sheetId, AutomationRule automationRule) throws SmartsheetException {
        Util.throwIfNull(automationRule);
        return this.updateResource("sheets/" + sheetId + "/automationrules/" + automationRule.getId(),
                AutomationRule.class, automationRule);
    }

    /**
     * Delete an automation rule.
     *
     * It mirrors to the following Smartsheet REST API method: DELETE /sheets/{sheetId}/automationrules/{automationRuleId}
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
    public void deleteAutomationRule(long sheetId, long automationRuleId) throws SmartsheetException {
        this.deleteResource("sheets/" + sheetId + "/automationrules/" + automationRuleId, AutomationRule.class);
    }
}
