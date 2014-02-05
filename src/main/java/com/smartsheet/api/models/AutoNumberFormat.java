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



/**
 * Represents the AutoNumberFormat object.
 */
public class AutoNumberFormat {
	/**
	 * Represents the prefix.
	 */
	private String prefix;

	/**
	 * Represents the suffix.
	 */
	private String suffix;

	/**
	 * Represents the fill.
	 */
	private String fill;

	/**
	 * Represents the starting number.
	 */
	private Long startingNumber;
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	public Long getStartingNumber() {
		return startingNumber;
	}

	public void setStartingNumber(Long startingNumber) {
		this.startingNumber = startingNumber;
	}

}
