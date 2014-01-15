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
public class Column {
	/**
	 * Represents the index.
	 */
	private Integer index;

	/**
	 * Represents the title.
	 */
	private String title;

	/**
	 * Represents the primrary flag.
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
	 * Represents the hiddenn flag.
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

	public void setIndex(Integer index) {
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
}
