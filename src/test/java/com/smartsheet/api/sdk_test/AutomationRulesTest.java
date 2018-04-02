package com.smartsheet.api.sdk_test;

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

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetException;
import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.AutomationActionFrequency;
import com.smartsheet.api.models.enums.AutomationActionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import java.util.ArrayList;
import java.util.List;

public class AutomationRulesTest {

    @Test
    public void ListAutomationRules() {
        try {
            Smartsheet ss = HelperFunctions.SetupClient("List Automation Rules");
            PagedResult<AutomationRule> automationRules = ss.sheetResources().automationRuleResources().listAutomationRules(324, null);
            Assert.assertEquals(2, (long)automationRules.getTotalCount());
        } catch (Exception ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void GetAutomationRule() {
        Smartsheet ss = HelperFunctions.SetupClient("Get Automation Rule");
        try {
            AutomationRule automationRule = ss.sheetResources().automationRuleResources().getAutomationRule(324, 284);
            Assert.assertEquals(284, (long)automationRule.getId());
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Ignore("awaiting API update to return Result object")
    @Test
    public void UpdateAutomationRule() {
        Smartsheet ss = HelperFunctions.SetupClient("Update Automation Rule");
        AutomationAction autoRuleAction = new AutomationAction();
        RecipientEmail recipient = new RecipientEmail();
        recipient.setEmail("jane@example.com");
        List<Recipient> recipients = new ArrayList<Recipient>();
        recipients.add(recipient);
        autoRuleAction.setRecipients(recipients);
        autoRuleAction.setType(AutomationActionType.NOTIFICATION_ACTION);
        autoRuleAction.setFrequency(AutomationActionFrequency.WEEKLY);
        AutomationRule autoRule = new AutomationRule();
        autoRule.setId(284L);
        autoRule.setAction(autoRuleAction);
        try {
            AutomationRule automationRule = ss.sheetResources().automationRuleResources().updateAutomationRule(324, autoRule);
        } catch (SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void DeleteAutomationRule() {
        Smartsheet ss = HelperFunctions.SetupClient("Delete Automation Rule");
        try {
            ss.sheetResources().automationRuleResources().deleteAutomationRule(324, 284);
        } catch (SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }
}
