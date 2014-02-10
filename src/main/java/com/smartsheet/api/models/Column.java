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
	 * Represents the index.
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
	 * Represents the type.
	 */
	private ColumnType type;

	/**
	 * Represents the options.
	 */
	private List<String> options;

	/**
	 * Represents the hidden flag.
	 */
	private Boolean hidden;

	/**
	 * Represents the symbol.
	 */
	private Symbol symbol;

	/**
	 * Represents the system column type.
	 */
	private SystemColumnType systemColumnType;

	/**
	 * Represents the auto number format.
	 */
	private AutoNumberFormat autoNumberFormat;

	/**
	 * Represents the tags.
	 */
	private List<ColumnTag> tags;

	/**
	 * Represents the sheet ID.
	 */
	private Long sheetId;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public SystemColumnType getSystemColumnType() {
		return systemColumnType;
	}

	public void setSystemColumnType(SystemColumnType systemColumnType) {
		this.systemColumnType = systemColumnType;
	}

	public AutoNumberFormat getAutoNumberFormat() {
		return autoNumberFormat;
	}

	public void setAutoNumberFormat(AutoNumberFormat autoNumberFormat) {
		this.autoNumberFormat = autoNumberFormat;
	}

	public List<ColumnTag> getTags() {
		return tags;
	}

	public void setTags(List<ColumnTag> tags) {
		this.tags = tags;
	}

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public static class AddColumnToSheetBuilder {
		private int index;
		private String title;
		private ColumnType type;
		private List<String> options;
		private Symbol symbol;
		private SystemColumnType systemColumnType;
		private AutoNumberFormat autoNumberFormat;

		public AddColumnToSheetBuilder index(int index) {
			this.index = index;
			return this;
		}

		public AddColumnToSheetBuilder title(String title) {
			this.title = title;
			return this;
		}

		public AddColumnToSheetBuilder type(ColumnType type) {
			this.type = type;
			return this;
		}

		public AddColumnToSheetBuilder options(List<String> options) {
			this.options = options;
			return this;
		}

		public AddColumnToSheetBuilder symbol(Symbol symbol) {
			this.symbol = symbol;
			return this;
		}

		public AddColumnToSheetBuilder systemColumnType(SystemColumnType systemColumnType) {
			this.systemColumnType = systemColumnType;
			return this;
		}

		public AddColumnToSheetBuilder autoNumberFormat(AutoNumberFormat autoNumberFormat) {
			this.autoNumberFormat = autoNumberFormat;
			return this;
		}

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

	public static class ModifyColumnBuilder {
		private int index;
		private String title;
		private ColumnType type;
		private List<String> options;
		private Symbol symbol;
		private SystemColumnType systemColumnType;
		private AutoNumberFormat autoNumberFormat;
		private Long sheetId;

		public ModifyColumnBuilder index(int index) {
			this.index = index;
			return this;
		}

		public ModifyColumnBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ModifyColumnBuilder type(ColumnType type) {
			this.type = type;
			return this;
		}

		public ModifyColumnBuilder options(List<String> options) {
			this.options = options;
			return this;
		}

		public ModifyColumnBuilder symbol(Symbol symbol) {
			this.symbol = symbol;
			return this;
		}

		public ModifyColumnBuilder systemColumnType(SystemColumnType systemColumnType) {
			this.systemColumnType = systemColumnType;
			return this;
		}

		public ModifyColumnBuilder autoNumberFormat(AutoNumberFormat autoNumberFormat) {
			this.autoNumberFormat = autoNumberFormat;
			return this;
		}

		public ModifyColumnBuilder sheetId(Long sheetId) {
			this.sheetId = sheetId;
			return this;
		}

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
