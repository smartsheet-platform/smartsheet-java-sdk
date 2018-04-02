package com.smartsheet.api.models;

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

import com.smartsheet.api.models.enums.ColumnTag;
import com.smartsheet.api.models.enums.ColumnType;
import com.smartsheet.api.models.enums.Symbol;
import com.smartsheet.api.models.enums.SystemColumnType;
import com.smartsheet.api.models.format.Format;

import java.util.List;

/**
 * Represents the Column object.
 */
public class Column extends IdentifiableModel<Long> {
    /**
     * Represents the position.
     */
    private Integer index;

    /**
     * Represents the title.
     */
    private String title;

    /**
     * Represents the primary flag.
     */
    private Boolean primary;

    /**
     * Represents the column type.
     */
    private ColumnType type;

    /**
     * Represents the list of options for the column.
     */
    private List<String> options;

    /**
     * Represents the hidden flag for the column.
     */
    private Boolean hidden;

    /**
     * Represents the symbol used for the column.
     */
    private Symbol symbol;

    /**
     * Represents the system column type.
     */
    private SystemColumnType systemColumnType;

    /**
     * Represents the format for the auto generated numbers (if the SystemColumnType is an AUTO_NUMBER).
     */
    private AutoNumberFormat autoNumberFormat;

    /**
     * List containing contact options
     */
    private List<Contact> contactOptions;

    /**
     * Represents the tags to indicate a special type of column.
     */
    private List<ColumnTag> tags;

    /**
     * Represents if the column is locked
     */
    private Boolean locked;

    /**
     * Represents if the column is locked for the user
     */
    private Boolean lockedForUser;

    /**
     * The width of the cell.
     * */
    private Integer width;

    /**
     * Represents the {@link Format} for this column.
     */
    private Format format;

    /**
     * Flag indicating whether validation has been enabled for the column
     */
    private Boolean validation;

    /**
     * Default constructor
     */
    public Column() {
    }

    /**
     * Construct a Column with specified id
     *
     * @param id Column id
     */
    public Column(Long id) {
        this.setId(id);
    }

    /**
     * Gets the position of the column.
     *
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the position of the column.
     *
     * @param index the new index
     */
    public Column setIndex(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * Gets the title for the column.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the column.
     *
     * @param title the new title
     */
    public Column setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets the primary flag for the column.
     *
     * @return the primary flag
     */
    public Boolean getPrimary() {
        return primary;
    }

    /**
     * Sets the primary flag for the column.
     *
     * @param primary the new primary flag
     */
    public Column setPrimary(Boolean primary) {
        this.primary = primary;
        return this;
    }

    /**
     * Gets the column type.
     *
     * @return the type
     */
    public ColumnType getType() {
        return type;
    }

    /**
     * Sets the column type.
     *
     * @param type the new type
     */
    public Column setType(ColumnType type) {
        this.type = type;
        return this;
    }

    /**
     * Gets the list of options for the column.
     *
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Sets the list of options for the column.
     *
     * @param options the new options
     */
    public Column setOptions(List<String> options) {
        this.options = options;
        return this;
    }

    /**
     * Gets the hidden flag.
     *
     * @return the hidden flag
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * Sets the hidden flag.
     *
     * @param hidden the new hidden flag
     */
    public Column setHidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    /**
     * Gets the symbol for the column.
     *
     * @return the symbol
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol for the column.
     *
     * @param symbol the new symbol
     */
    public Column setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * Gets the system column type.
     *
     * @return the system column type
     */
    public SystemColumnType getSystemColumnType() {
        return systemColumnType;
    }

    /**
     * Sets the system column type.
     *
     * @param systemColumnType the new system column type
     */
    public Column setSystemColumnType(SystemColumnType systemColumnType) {
        this.systemColumnType = systemColumnType;
        return this;
    }

    /**
     * Gets the format for the auto generated numbers.
     *
     * @return the auto number format
     */
    public AutoNumberFormat getAutoNumberFormat() {
        return autoNumberFormat;
    }

    /**
     * Sets the format for the auto generated numbers.
     *
     * @param autoNumberFormat the new auto number format
     */
    public Column setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
        this.autoNumberFormat = autoNumberFormat;
        return this;
    }

    /**
     * Gets a list of contact options
     *
     * @return list of contact options
     */
    public List<Contact> getContactOptions() { return contactOptions; }

    /**
     * Sets the list of contact options
     *
     * @param contactOptions the new list of contact options
     * @return Column
     */
    public Column setContactOptions(List<Contact> contactOptions) {
        this.contactOptions = contactOptions;
        return this;
    }

    /**
     * Gets the tags that indicate a special type of column.
     *
     * @return the tags
     */
    public List<ColumnTag> getTags() {
        return tags;
    }

    /**
     * Sets the tags to indicate a special type of column.
     *
     * @param tags the new tags
     */
    public Column setTags(List<ColumnTag> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Gets the locked flag
     * @return the locked flag
     */
    public Boolean isLocked() {
        return locked;
    }

    /**
     * Sets the locked flag
     * @param locked the locked flag
     */
    public Column setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * Gets the locked for user flag
     * @return the locked for user flag
     */
    public Boolean isLockedForUser() {
        return lockedForUser;
    }

    /**
     * Sets the locked for user flag
     * @param lockedForUser the locked for user flag
     */
    public Column setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
        return this;
    }

    /**
     * Gets the width
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the width
     * @param width the width
     */
    public Column setWidth(Integer width) {
        this.width = width;
        return this;
    }

    /**
     * @return the {@link Format}
     */
    public Format getFormat() {
        return format;
    }

    /**
     * @param format the {@link Format} to set
     */
    public Column setFormat(Format format) {
        this.format = format;
        return this;
    }

    /**
     * Gets the value of the validation flag
     * @return the validation flag
     */
    public Boolean getValidation() { return validation; }

    /**
     * Sets the value of the validation flag
     * @param validation
     * @return the Column
     */
    public Column setValidation(Boolean validation) {
        this.validation = validation;
        return this;
    }

    /**
     * A convenience class to help create a column object with the appropriate fields for adding to a sheet.
     */
    public static class AddColumnToSheetBuilder {
        /** The title. */
        private String title;

        /** The index **/
        private Integer index;

        /** The type. */
        private ColumnType type;

        /** The options. */
        private List<String> options;

        /** The symbol. */
        private Symbol symbol;

        /** The system column type. */
        private SystemColumnType systemColumnType;

        /** The auto number format. */
        private AutoNumberFormat autoNumberFormat;

        /** The column width. */
        private Integer width;

        /**Represents the primary flag.*/
        private Boolean primary;

        /** The column format */
        private Format format;

        /** Flag indicating whether validation has been enabled for the column */
        private Boolean validation;

        /**
         * Gets the width for the column.
         *
         * @return the width
         */
        public Integer getWidth() {
            return width;
        }

        /**
         * Sets the width for the column.
         *
         * @param width the width
         * @return the column to sheet builder
         */
        public AddColumnToSheetBuilder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        /**
         * Gets the primary status for the column.
         *
         * @return the boolean primary
         */
        public Boolean getPrimary() {
            return primary;
        }

        /**
         * Sets the primary status for the column.
         *
         * @param primary the boolean primary
         * @return the column to sheet builder
         */
        public AddColumnToSheetBuilder setPrimary(Boolean primary) {
            this.primary = primary;
            return this;
        }

        /**
         * Sets the title for the column.
         *
         * @param title the title
         * @return the add the column to sheet builder
         */
        public AddColumnToSheetBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Gets the title.
         * @return the title
         */
        public String getTitle(){
            return title;
        }

        /**
         * Sets the type for the column.
         *
         * @param type the type
         * @return the adds the column to sheet builder
         */
        public AddColumnToSheetBuilder setType(ColumnType type) {
            this.type = type;
            return this;
        }

        /**
         * Gets the type for the column.
         * @return the type
         */
        public ColumnType getType(){
            return type;
        }

        /**
         * Sets the options for the column.
         *
         * @param options the options
         * @return the adds the column to sheet builder
         */
        public AddColumnToSheetBuilder setOptions(List<String> options) {
            this.options = options;
            return this;
        }

        /**
         * Gets the option for the column.
         * @return the option
         */
        public List<String> getOptions(){
            return options;
        }

        /**
         * Sets the symbol for the column.
         *
         * @param symbol the symbol
         * @return the adds the column to sheet builder
         */
        public AddColumnToSheetBuilder setSymbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * Gets the symbol for the column.
         * @return the symbol
         */
        public Symbol getSymbol(){
            return symbol;
        }

        /**
         * Sets the system column type.
         *
         * @param systemColumnType the system column type
         * @return the adds the column to sheet builder
         */
        public AddColumnToSheetBuilder setSystemColumnType(SystemColumnType systemColumnType) {
            this.systemColumnType = systemColumnType;
            return this;
        }

        /**
         * Gets the system column type.
         * @return the system column type
         */
        public SystemColumnType getSystemColumnType(){
            return systemColumnType;
        }

        /**
         * Sets the format for an auto number column.
         *
         * @param autoNumberFormat the auto number format
         * @return the adds the column to sheet builder
         */
        public AddColumnToSheetBuilder setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
            this.autoNumberFormat = autoNumberFormat;
            return this;
        }

        /**
         * Gets the format for an auto number column.
         * @return the format for an auto number column
         */
        public AutoNumberFormat getAutoNumberFormat(){
            return autoNumberFormat;
        }


        /**
         * Gets the index specified for the new column.
         * @return the index
         */
        public Integer getIndex() {
            return index;
        }

        /**
         * Sets the index for the column. Set this to any value greater than the index of
         * the last column to add it as the last column.
         *
         * @param index the index
         * @return the index
         */
        public AddColumnToSheetBuilder setIndex(Integer index) {
            this.index = index;
            return this;
        }

        /**
         * Gets the format for a column.
         * @return the format for a column
         */
        public Format getFormat() {
            return format;
        }

        /**
         * @param format the format.
         * Sets the format for a column.
         * @return the AddColumnToSheetBuilder.
         */
        public AddColumnToSheetBuilder setFormat(Format format) {
            this.format = format;
            return this;
        }

        /**
         * Gets the value of the validation flag
         * @return
         */
        public Boolean getValidation() { return validation; }

        /**
         * Sets the value of the validation flag
         * @param validation
         * @return the AddColumn builder
         */
        public AddColumnToSheetBuilder setValidation(Boolean validation) {
            this.validation = validation;
            return this;
        }

        /**
         * Builds the column.
         *
         * @return the column
         */
        public Column build() {
            if (title == null || type == null) {
                throw new InstantiationError();
            }

            Column column = new Column();
            column.title = title;
            column.type = type;
            column.options = options;
            column.symbol = symbol;
            column.index = index;
            column.width = width;
            column.systemColumnType = systemColumnType;
            column.autoNumberFormat = autoNumberFormat;
            column.primary = primary;
            column.format = format;
            column.validation = validation;
            return column;
        }

    }

    /**
     * The Class UpdateColumnBuilder.
     */
    public static class UpdateColumnBuilder {
        /** The position of the column. */
        private int index;

        /** The title for the column. */
        private String title;

        /** The type of the column. */
        private ColumnType type;

        /** The options for the column. */
        private List<String> options;

        /** The symbol for the column. */
        private Symbol symbol;

        /** The system column type. */
        private SystemColumnType systemColumnType;

        /** The format for an auto number column. */
        private AutoNumberFormat autoNumberFormat;

        /** The sheet id. */
        private Long sheetId;

        /** The width */
        private Integer width;

        /** The format */
        private Format format;

        /** The column id */
        private Long id;

        /** Flag indicating whether validation has been enabled for the column */
        private Boolean validation;

        /**
         * Gets the column id.
         *
         * @return the column id
         */
        public Long getColumnId() {
            return id;
        }

        /**
         * Sets the position for the column.
         *
         * @param columnId the columnId
         * @return the modify column builder
         */
        public UpdateColumnBuilder setColumnId(Long columnId) {
            this.id = columnId;
            return this;
        }

        /**
         * Sets the position for the column.
         *
         * @param index the position
         * @return the modify column builder
         */
        public UpdateColumnBuilder setIndex(int index) {
            this.index = index;
            return this;
        }

        /**
         * Sets the title for the column.
         *
         * @param title the title
         * @return the modify column builder
         */
        public UpdateColumnBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the type for the column.
         *
         * @param type the type
         * @return the modify column builder
         */
        public UpdateColumnBuilder setType(ColumnType type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the options for the column.
         *
         * @param options the options
         * @return the modify column builder
         */
        public UpdateColumnBuilder setOptions(List<String> options) {
            this.options = options;
            return this;
        }

        /**
         * Sets the symbol for the column.
         *
         * @param symbol the symbol
         * @return the modify column builder
         */
        public UpdateColumnBuilder setSymbol(Symbol symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * Sets the system column type for the column.
         *
         * @param systemColumnType the system column type
         * @return the modify column builder
         */
        public UpdateColumnBuilder setSystemColumnType(SystemColumnType systemColumnType) {
            this.systemColumnType = systemColumnType;
            return this;
        }

        /**
         * Sets the format for an auto number column.
         *
         * @param autoNumberFormat the auto number format
         * @return the modify column builder
         */
        public UpdateColumnBuilder setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
            this.autoNumberFormat = autoNumberFormat;
            return this;
        }

        /**
         * Sets the sheet id.
         *
         * @param sheetId the sheet id
         * @return the modify column builder
         */
        public UpdateColumnBuilder setSheetId(Long sheetId) {
            this.sheetId = sheetId;
            return this;
        }

        /**
         * Sets the format
         * @param format the format
         * @return the modify column builder
         */
        public UpdateColumnBuilder setFormat(Format format) {
            this.format = format;
            return this;
        }

        /**
         * Sets the width
         * @param width the width
         * @return the modify column builder
         */
        public UpdateColumnBuilder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        /**
         * Sets the value of the validation flag
         * @param validation
         * @return the modify column builder
         */
        public UpdateColumnBuilder setValidation(Boolean validation) {
            this.validation = validation;
            return this;
        }

        /**
         * Gets the index.
         *
         * @return the index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Gets the title.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Gets the type.
         *
         * @return the type
         */
        public ColumnType getType() {
            return type;
        }

        /**
         * Gets the options.
         *
         * @return the options
         */
        public List<String> getOptions() {
            return options;
        }

        /**
         * Gets the symbol.
         *
         * @return the symbol
         */
        public Symbol getSymbol() {
            return symbol;
        }

        /**
         * Gets the system column type.
         *
         * @return the system column type
         */
        public SystemColumnType getSystemColumnType() {
            return systemColumnType;
        }

        /**
         * Gets the auto number format.
         *
         * @return the auto number format
         */
        public AutoNumberFormat getAutoNumberFormat() {
            return autoNumberFormat;
        }

        /**
         * Gets the sheet id.
         *
         * @return the sheet id
         */
        public Long getSheetId() {
            return sheetId;
        }

        /**
         * Gets the width
         * @return the width
         */
        public Integer getWidth() {
            return width;
        }

        /**
         * Gets the format
         * @return the format
         */
        public Format getFormat() {
            return format;
        }

        /**
         * Gets the value of the validation flag
         * @return the validation flag
         */
        public Boolean getValidation() { return validation; }

        /**
         * Builds the column.
         *
         * @return the column
         */
        public Column build() {
            if(title == null || id == null) {
                throw new InstantiationError("A title and a column id are required");
            }

            Column column = new Column();
            column.index = index;
            column.title = title;
            column.type = type;
            column.options = options;
            column.symbol = symbol;
            column.systemColumnType = systemColumnType;
            column.autoNumberFormat = autoNumberFormat;
            column.width = width;
            column.format = format;
            column.validation = validation;
            column.setId(id);
            return column;
        }
    }
}
