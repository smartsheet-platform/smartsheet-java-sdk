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
 * Represents the publish status of a sheet.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/522078-publishing-sheets">Publishing Sheets</a>
 */
public class SheetPublish {
	/**
	 * Represents the read-only lite (static HTML UI) flag.
	 */
	private Boolean readOnlyLiteEnabled;

	/**
	 * Represents the read-only full (fancy UI) flag.
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
	 * Represents the read-only lite (static HTML UI) URL.
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

	/**
	 * Gets the read only lite enabled flag.
	 *
	 * @return the read only lite enabled flag
	 */
	public Boolean getReadOnlyLiteEnabled() {
		return readOnlyLiteEnabled;
	}

	/**
	 * Sets the read only lite (static html UI) enabled flag.
	 *
	 * @param readOnlyLiteEnabled the new read only lite enabled flag
	 */
	public void setReadOnlyLiteEnabled(Boolean readOnlyLiteEnabled) {
		this.readOnlyLiteEnabled = readOnlyLiteEnabled;
	}

	/**
	 * Gets the read only full (fancy UI) enabled flag.
	 *
	 * @return the read only full enabled flag
	 */
	public Boolean getReadOnlyFullEnabled() {
		return readOnlyFullEnabled;
	}

	/**
	 * Sets the read only full (fancy UI) enabled flag.
	 *
	 * @param readOnlyFullEnabled the new read only full enabled flag
	 */
	public void setReadOnlyFullEnabled(Boolean readOnlyFullEnabled) {
		this.readOnlyFullEnabled = readOnlyFullEnabled;
	}

	/**
	 * Gets the read write enabled flag.
	 *
	 * @return the read write enabled flag
	 */
	public Boolean getReadWriteEnabled() {
		return readWriteEnabled;
	}

	/**
	 * Sets the read write enabled flag.
	 *
	 * @param readWriteEnabled the new read write enabled flag
	 */
	public void setReadWriteEnabled(Boolean readWriteEnabled) {
		this.readWriteEnabled = readWriteEnabled;
	}

	/**
	 * Gets the ical enabled flag.
	 *
	 * @return the ical enabled flag
	 */
	public Boolean getIcalEnabled() {
		return icalEnabled;
	}

	/**
	 * Sets the ical enabled flag.
	 *
	 * @param icalEnabled the new ical enabled flag
	 */
	public void setIcalEnabled(Boolean icalEnabled) {
		this.icalEnabled = icalEnabled;
	}

	/**
	 * Gets the read only lite url flag.
	 *
	 * @return the read only lite url flag
	 */
	public String getReadOnlyLiteUrl() {
		return readOnlyLiteUrl;
	}

	/**
	 * Sets the read only lite (static html UI) url.
	 *
	 * @param readOnlyLiteUrl the new read only lite url
	 */
	public void setReadOnlyLiteUrl(String readOnlyLiteUrl) {
		this.readOnlyLiteUrl = readOnlyLiteUrl;
	}

	/**
	 * Gets the read only full (fancy UI) url.
	 *
	 * @return the read only full url
	 */
	public String getReadOnlyFullUrl() {
		return readOnlyFullUrl;
	}

	/**
	 * Sets the read only full (fancy UI) url.
	 *
	 * @param readOnlyFullUrl the new read only full url
	 */
	public void setReadOnlyFullUrl(String readOnlyFullUrl) {
		this.readOnlyFullUrl = readOnlyFullUrl;
	}

	/**
	 * Gets the read write url.
	 *
	 * @return the read write url
	 */
	public String getReadWriteUrl() {
		return readWriteUrl;
	}

	/**
	 * Sets the read write url.
	 *
	 * @param readWriteUrl the new read write url
	 */
	public void setReadWriteUrl(String readWriteUrl) {
		this.readWriteUrl = readWriteUrl;
	}

	/**
	 * Gets the ical url.
	 *
	 * @return the ical url
	 */
	public String getIcalUrl() {
		return icalUrl;
	}

	/**
	 * Sets the ical url.
	 *
	 * @param icalUrl the new ical url
	 */
	public void setIcalUrl(String icalUrl) {
		this.icalUrl = icalUrl;
	}

	/**
	 * A convenience class for making a {@link SheetPublish} object with the necessary fields to publish a sheet.
	 */
	public static class PublishStatusBuilder {
		private Boolean readOnlyLiteEnabled;
		private Boolean readOnlyFullEnabled;
		private Boolean readWriteEnabled;
		private Boolean icalEnabled;

		/**
		 * Read only lite enabled.
		 *
		 * @param readOnlyLiteEnabled the read only lite (static html UI) enabled
		 * @return the publish status builder
		 */
		public PublishStatusBuilder readOnlyLiteEnabled(Boolean readOnlyLiteEnabled) {
			this.readOnlyLiteEnabled = readOnlyLiteEnabled;
			return this;
		}

		/**
		 * Read only full (fancy UI) enabled.
		 *
		 * @param readOnlyFullEnabled the read only full enabled
		 * @return the publish status builder
		 */
		public PublishStatusBuilder readOnlyFullEnabled(Boolean readOnlyFullEnabled) {
			this.readOnlyFullEnabled = readOnlyFullEnabled;
			return this;
		}

		/**
		 * Read write enabled.
		 *
		 * @param readWriteEnabled the read write enabled
		 * @return the publish status builder
		 */
		public PublishStatusBuilder readWriteEnabled(Boolean readWriteEnabled) {
			this.readWriteEnabled = readWriteEnabled;
			return this;
		}

		/**
		 * Ical enabled.
		 *
		 * @param icalEnabled the ical enabled
		 * @return the publish status builder
		 */
		public PublishStatusBuilder icalEnabled(Boolean icalEnabled) {
			this.icalEnabled = icalEnabled;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the sheet publish
		 */
		public SheetPublish build() {
			
			if(readOnlyLiteEnabled == null || readOnlyFullEnabled == null || readWriteEnabled == null){
				throw new InstantiationError("'Read only lite', 'read only full' and 'read write' must be set to "
						+ "update the publishing status.");
			}
			
			SheetPublish sheetPublish = new SheetPublish();
			sheetPublish.readOnlyLiteEnabled = readOnlyLiteEnabled;
			sheetPublish.readOnlyFullEnabled = readOnlyFullEnabled;
			sheetPublish.readWriteEnabled = readWriteEnabled;
			sheetPublish.icalEnabled = icalEnabled;
			return sheetPublish;
		}
	}
}
