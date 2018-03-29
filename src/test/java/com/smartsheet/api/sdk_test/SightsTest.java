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
import com.smartsheet.api.models.enums.DestinationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;

public class SightsTest {

    @Test
    public void ListSights() {
        try {
            Smartsheet ss = HelperFunctions.SetupClient("List Sights");
            PagedResult<Sight> sights = ss.sightResources().listSights(null, null);
            Assert.assertEquals(6, (long)sights.getTotalCount());
        } catch (Exception ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void GetSight() {
        Smartsheet ss = HelperFunctions.SetupClient("Get Sight");
        try {
            Sight sight = ss.sightResources().getSight(52);
            Assert.assertEquals(52, (long)sight.getId());
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Ignore("destination types differ because of case")
    @Test
    public void CopySight() {
        Smartsheet ss = HelperFunctions.SetupClient("Copy Sight");
        try {
            ContainerDestination dest = new ContainerDestination();
            dest.setDestinationType(DestinationType.FOLDER);
            dest.setDestinationId(484L);
            dest.setNewName("new sight");
            Sight sight = ss.sightResources().copySight(52, dest);
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void UpdateSight() {
        Smartsheet ss = HelperFunctions.SetupClient("Update Sight");
        try {
            Sight sight = new Sight();
            sight.setId(812L);
            sight.setName("new new sight");
            ss.sightResources().updateSight(sight);
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void SetPublishStatus() {
        Smartsheet ss = HelperFunctions.SetupClient("Set Sight Publish Status");
        try {
            SightPublish publish = new SightPublish();
            publish.setReadOnlyFullEnabled(true);
            publish.setReadOnlyFullAccessibleBy("ALL");
            ss.sightResources().setPublishStatus(812, publish);
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void GetPublishStatus() {
        Smartsheet ss = HelperFunctions.SetupClient("Get Sight Publish Status");
        try {
            SightPublish publish = ss.sightResources().getPublishStatus(812L);
            Assert.assertEquals(publish.getReadOnlyFullEnabled(), true);
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }

    @Test
    public void DeleteSight() {
        Smartsheet ss = HelperFunctions.SetupClient("Delete Sight");
        try {
            ss.sightResources().deleteSight(700L);
        }
        catch(SmartsheetException ex) {
            HelperFunctions.ExceptionMessage(ex.getMessage(), ex.getCause());
        }
    }
}
