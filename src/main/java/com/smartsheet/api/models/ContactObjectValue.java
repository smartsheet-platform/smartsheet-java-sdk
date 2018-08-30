package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
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

import com.smartsheet.api.models.enums.ObjectValueType;

public class ContactObjectValue extends Contact implements ObjectValue {

    /**
     * Part of the ContactOverlay, if contactReferences is present in the sheet, refIndex will indicate
     * the offset into the list containing this Contact
     */
    private Integer refIndex;

    /**
     * ID of contact image
     */
    private String imageId;

    /**
     * Gets the offset in contactReferences for this Contact
     * @return the refIndex
     */
    public Integer getRefIndex() {
        return refIndex;
    }

    /**
     * Sets the offset in contactReferences for this Contact
     * @param refIndex
     * @return ContactObjectValue
     */
    public ContactObjectValue setRefIndex(Integer refIndex) {
        this.refIndex = refIndex;
        return this;
    }

    /**
     * Gets the ID for the contact image
     * @return the imageId
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * Sets the contact image for this Contact
     * @param imgeId the imageId
     * @return ContactObjectValue
     */
    public ContactObjectValue setImgeId(String imgeId) {
        this.imageId = imgeId;
        return this;
    }

    /**
     * Gets the objectValueType
     */
    @Override
    public ObjectValueType getObjectType() {
        return ObjectValueType.CONTACT;
    }
}
