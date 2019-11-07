package com.smartsheet.api.models;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.models.format.Format;

import java.util.Date;
import java.util.List;

public class SummaryField extends IdentifiableModel<Long> {

    @Override
    @JsonIgnore(false)
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore(false)
    public IdentifiableModel<Long> setId(Long id) {
        super.setId(id);
        return this;
    }

    /**
     * Array of ContactOption objects to specify a pre-defined list of values for the column.
     * Column type must be CONTACT_LIST
     */
    private List<Contact> contactOptions;

    /**
     * Time of creation
     */
    private Date createdAt;

    /**
     * User object containing name and email of the summaryField's author
     */
    private User createdBy;

    /**
     * Visual representation of cell contents, as presented to the user in the UI. See Cell Reference.
     */
    private String displayValue;

    /**
     * The format descriptor (see Formatting)
     * Only returned if the include query string parameter contains format and this column has a non-default
     * format applied to it.
     */
    private Format format;

    /**
     * The formula for a cell, if set. NOTE: calculation errors or problems with a formula do not cause the API call
     * to return an error code. Instead, the response contains the same value as in the UI,
     * such as field.value = "#CIRCULAR REFERENCE".
     */
    private String formula;

    /**
     * A hyperlink to a URL, sheet, or report
     */
    private Hyperlink hyperlink;

    /**
     * The image that the field contains.
     * Only returned if the field contains an image.
     */
    private Image image;

    /**
     * Field index or position. This number is zero-based.
     */
    private Integer index;

    /**
     * Indicates whether the field is locked. In a response, a value of true indicates that the field has been
     * locked by the sheet owner or the admin.
     */
    private Boolean locked;

    /**
     * Indicates whether the field is locked for the requesting user. This attribute may be present in a response,
     * but cannot be specified in a request.
     */
    private Boolean lockedForUser;

    /**
     * Time of last modification
     */
    private Date modifiedAt;

    /**
     * User object containing name and email of the summaryField's author
     */
    private User modifiedBy;

    /**
     * 	Required for date and contact fields
     */
    private ObjectValue objectValue;

    /**
     * When applicable for PICKLIST column type. Array of the options available for the field
     */
    private List<String> options;

    /**
     * When applicable for PICKLIST column type. See Symbol Columns.
     */
    private String symbol;

    /**
     * Arbitrary name, must be unique within summary
     */
    private String title;

    /**
     * See Column Types.
     */
    private ColumnType type;

    /**
     * Indicates whether summary field values are restricted to the type
     */
    private Boolean validation;

    /**
     * Get contact options, only valid when type is CONTACT_LIST
     *
     * @return contactOptions
     */
    public List<Contact> getContactOptions() { return contactOptions; }

    /**
     * Set contact options
     *
     * @param contactOptions
     */
    public SummaryField setContactOptions(List<Contact> contactOptions) {
        this.contactOptions = contactOptions;
        return this;
    }

    /**
     * Get summary field time of creation
     *
     * @return createdAt
     */
    public Date getCreatedAt() { return createdAt; }

    /**
     * Set summary field time of creation
     *
     * @param createdAt
     */
    public SummaryField setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Get User object of summary field creator
     *
     * @return createdBy
     */
    public User getCreatedBy() { return createdBy; }

    /**
     * Set User object of summary field creator
     *
     * @param createdBy
     */
    public SummaryField setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Gets the display value of the summary field
     *
     * @return displayValue
     */
    public String getDisplayValue() { return displayValue; }

    /**
     * Sets the display value of the summary field
     *
     * @param displayValue
     */
    public SummaryField setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
        return this;
    }

    /**
     * Gets the format descriptor
     *
     * @return format
     */
    public Format getFormat() { return format; }

    /**
     * Sets the format descriptor
     *
     * @param format
     */
    public SummaryField setFormat(Format format) {
        this.format = format;
        return this;
    }

    /**
     * Gets the formula for the summary field
     *
     * @return formula
     */
    public String getFormula() { return formula; }

    /**
     * Sets the formula for the summary field
     *
     * @param formula
     */
    public SummaryField setFormula(String formula) {
        this.formula = formula;
        return this;
    }

    /**
     * Gets the hyperlink associated with the field, if any
     *
     * @return hyperlink
     */
    public Hyperlink getHyperlink() { return hyperlink; }

    /**
     * Sets the hyperlink associated with the field
     *
     * @param hyperlink
     */
    public SummaryField setHyperlink(Hyperlink hyperlink) {
        this.hyperlink = hyperlink;
        return this;
    }

    /**
     * Gets the image associated with the field, if any
     *
     * @return image
     */
    public Image getImage() { return image; }

    /**
     * Sets the image associated with the field
     *
     * @param image
     */
    public SummaryField setImage(Image image) {
        this.image = image;
        return this;
    }

    /**
     * Gets the field index or position
     *
     * @return index
     */
    public Integer getIndex() { return index; }

    /**
     * Sets the field index or position
     *
     * @param index
     */
    public SummaryField setIndex(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * Gets flag indicating if field is locked
     *
     * @return locked
     */
    public Boolean getLocked() { return locked; }

    /**
     * Sets flag indicating if field is locked
     *
     * @param locked
     */
    public SummaryField setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * Gets flag indicating whether field is locked for the requesting user
     *
     * @return lockedForUser
     */
    public Boolean getLockedForUser() { return lockedForUser; }

    /**
     * Sets flag indicating whether field is locked for the requesting user
     *
     * @param lockedForUser
     */
    public SummaryField setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
        return this;
    }

    /**
     * Gets the last modification date and time
     *
     * @return modifiedAt
     */
    public Date getModifiedAt() { return modifiedAt; }

    /**
     * Sets the last modification date and time
     *
     * @param modifiedAt
     */
    public SummaryField setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * Get the User who last modified the field
     *
     * @return modifiedBy
     */
    public User getModifiedBy() { return modifiedBy; }

    /**
     * Sets the User who last modified the field
     *
     * @param modifiedBy
     */
    public SummaryField setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    /**
     * Gets the field's objectValue
     *
     * @return objectValue
     */
    public ObjectValue getObjectValue() { return objectValue; }

    /**
     * Sets the field's objectValue
     *
     * @param objectValue
     */
    public SummaryField setObjectValue(ObjectValue objectValue) {
        this.objectValue = objectValue;
        return this;
    }

    /**
     * Gets PICKLIST options
     *
     * @return options
     */
    public List<String> getOptions() { return options; }

    /**
     * Sets PICKLIST options
     *
     * @param options
     */
    public SummaryField setOptions(List<String> options) {
        this.options = options;
        return this;
    }

    /**
     * Gets applicable symbol for PICKLIST type
     *
     * @return symbol
     */
    public String getSymbol() { return symbol; }

    /**
     * Sets applicable symbo for PICKLIST type
     *
     * @param symbol
     */
    public SummaryField setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Gets the field's title
     *
     * @return title
     */
    public String getTitle() { return title; }

    /**
     * Sets the field's title
     *
     * @param title
     */
    public SummaryField setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets the field's type - see Column Type
     *
     * @return type
     */
    public ColumnType getType() { return type; }

    /**
     * Sets the field's type - see Column Type
     *
     * @param type
     */
    public SummaryField setType(ColumnType type) {
        this.type = type;
        return  this;
    }

    /**
     * Gets flag indicating whether summary field values are restricted to type
     *
     * @return validation
     */
    public Boolean getValidation() { return validation; }

    /**
     * Sets flag indicating whether summary field values are restricted to type
     *
     * @param validation
     */
    public SummaryField setValidation(Boolean validation) {
        this.validation = validation;
        return this;
    }
}
