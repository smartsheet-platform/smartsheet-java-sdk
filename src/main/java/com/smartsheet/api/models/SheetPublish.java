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
 * Represents the SheetPublish object.
 */
public class SheetPublish {
	/**
	 * Represents the read-only lite enabled flag.
	 */
	private Boolean readOnlyLiteEnabled;

	/**
	 * Represents the read-only full enabled flag.
	 */
	private Boolean readOnlyFullEnabled;

	/**
	 * Represents the read-write enabled flag.
	 */
	private Boolean readWriteEnabled;

	/**
	 * Represents the iCal enabled flag.
	 */
	private Boolean icalEnabled;

	/**
	 * Represents the read-only lite URL.
	 */
	private String readOnlyLiteUrl;

	/**
	 * Represents the read-only full URL.
	 */
	private String readOnlyFullUrl;

	/**
	 * Represents the read-write URL.
	 */
	private String readWriteUrl;

	/**
	 * Represents the iCal URL.
	 */
	private String icalUrl;

	public Boolean getReadOnlyLiteEnabled() {
		return readOnlyLiteEnabled;
	}

	public void setReadOnlyLiteEnabled(Boolean readOnlyLiteEnabled) {
		this.readOnlyLiteEnabled = readOnlyLiteEnabled;
	}

	public Boolean getReadOnlyFullEnabled() {
		return readOnlyFullEnabled;
	}

	public void setReadOnlyFullEnabled(Boolean readOnlyFullEnabled) {
		this.readOnlyFullEnabled = readOnlyFullEnabled;
	}

	public Boolean getReadWriteEnabled() {
		return readWriteEnabled;
	}

	public void setReadWriteEnabled(Boolean readWriteEnabled) {
		this.readWriteEnabled = readWriteEnabled;
	}

	public Boolean getIcalEnabled() {
		return icalEnabled;
	}

	public void setIcalEnabled(Boolean icalEnabled) {
		this.icalEnabled = icalEnabled;
	}

	public String getReadOnlyLiteUrl() {
		return readOnlyLiteUrl;
	}

	public void setReadOnlyLiteUrl(String readOnlyLiteUrl) {
		this.readOnlyLiteUrl = readOnlyLiteUrl;
	}

	public String getReadOnlyFullUrl() {
		return readOnlyFullUrl;
	}

	public void setReadOnlyFullUrl(String readOnlyFullUrl) {
		this.readOnlyFullUrl = readOnlyFullUrl;
	}

	public String getReadWriteUrl() {
		return readWriteUrl;
	}

	public void setReadWriteUrl(String readWriteUrl) {
		this.readWriteUrl = readWriteUrl;
	}

	public String getIcalUrl() {
		return icalUrl;
	}

	public void setIcalUrl(String icalUrl) {
		this.icalUrl = icalUrl;
	}
}
