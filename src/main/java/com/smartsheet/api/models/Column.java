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

import java.util.List;

/**
 * Represents the Column object.
 */
public class Column extends IdentifiableModel {
	/**
	 * Represents the position.
	 */
	private int index;

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
	 * Represents the tags to indicate a special type of column.
	 */
	private List<ColumnTag> tags;

	/**
	 * Represents the sheet ID.
	 */
	private Long sheetId;

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
	public void setIndex(int index) {
		this.index = index;
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
	public void setTitle(String title) {
		this.title = title;
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
	public void setPrimary(Boolean primary) {
		this.primary = primary;
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
	public void setType(ColumnType type) {
		this.type = type;
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
	public void setOptions(List<String> options) {
		this.options = options;
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
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
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
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
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
	public void setSystemColumnType(SystemColumnType systemColumnType) {
		this.systemColumnType = systemColumnType;
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
	public void setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
		this.autoNumberFormat = autoNumberFormat;
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
	public void setTags(List<ColumnTag> tags) {
		this.tags = tags;
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
	 * Sets the sheet id.
	 *
	 * @param sheetId the new sheet id
	 */
	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	/**
	 * A convenience class to help create a column object with the appropriate fields for adding to a sheet.
	 */
	public static class AddColumnToSheetBuilder {
		
		/** The index. */
		private int index;
		
		/** The title. */
		private String title;
		
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

		/**
		 * Sets the position for the column.
		 *
		 * @param index the index
		 * @return the adds the column to sheet builder
		 */
		public AddColumnToSheetBuilder setIndex(int index) {
			this.index = index;
			return this;
		}
		
		/**
		 * Gets the index.
		 * @return the index
		 */
		public int getIndex(){
			return index;
		}

		/**
		 * Sets the title for the column.
		 *
		 * @param title the title
		 * @return the adds the column to sheet builder
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
		 * Builds the column.
		 *
		 * @return the column
		 */
		public Column build() {
			if (title == null || type == null) {
				throw new InstantiationError();
			}

			Column column = new Column();
			column.index = index;
			column.title = title;
			column.type = type;
			column.options = options;
			column.symbol = symbol;
			column.systemColumnType = systemColumnType;
			column.autoNumberFormat = autoNumberFormat;
			return column;
		}
	}

	/**
	 * The Class ModifyColumnBuilder.
	 */
	public static class ModifyColumnBuilder {
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

		/**
		 * Sets the position for the column.
		 *
		 * @param index the position
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setIndex(int index) {
			this.index = index;
			return this;
		}

		/**
		 * Sets the title for the column.
		 *
		 * @param title the title
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Sets the type for the column.
		 *
		 * @param type the type
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setType(ColumnType type) {
			this.type = type;
			return this;
		}

		/**
		 * Sets the options for the column.
		 *
		 * @param options the options
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setOptions(List<String> options) {
			this.options = options;
			return this;
		}

		/**
		 * Sets the symbol for the column.
		 *
		 * @param symbol the symbol
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setSymbol(Symbol symbol) {
			this.symbol = symbol;
			return this;
		}

		/**
		 * Sets the system column type for the column.
		 *
		 * @param systemColumnType the system column type
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setSystemColumnType(SystemColumnType systemColumnType) {
			this.systemColumnType = systemColumnType;
			return this;
		}

		/**
		 * Sets the format for an auto number column.
		 *
		 * @param autoNumberFormat the auto number format
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
			this.autoNumberFormat = autoNumberFormat;
			return this;
		}

		/**
		 * Sets the sheet id.
		 *
		 * @param sheetId the sheet id
		 * @return the modify column builder
		 */
		public ModifyColumnBuilder setSheetId(Long sheetId) {
			this.sheetId = sheetId;
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
		 * Builds the column.
		 *
		 * @return the column
		 */
		public Column build() {
			if(title == null || sheetId == null) {
				throw new InstantiationError("A title and sheetId are required");
			}
			
			Column column = new Column();
			column.index = index;
			column.title = title;
			column.type = type;
			column.options = options;
			column.symbol = symbol;
			column.systemColumnType = systemColumnType;
			column.autoNumberFormat = autoNumberFormat;
			column.sheetId = sheetId;
			return column;
		}
	}
}
