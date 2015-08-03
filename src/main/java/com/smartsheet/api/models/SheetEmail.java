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
 * Represents Sheet Email object used for sending a sheet by email.
 */
public class SheetEmail extends Email {
	/**
	 * Represents the sheet email format (PDF or Excel).
	 */
	private SheetEmailFormat format;

	/**
	 * Represents the format details (paper dimensions).
	 */
	private FormatDetails formatDetails;

	/**
	 * Gets the sheet email format (PDF or Excel).
	 *
	 * @return the format
	 */
	public SheetEmailFormat getFormat() {
		return format;
	}

	/**
	 * Sets the sheet email format (PDF or Excel).
	 *
	 * @param format the new format
	 */
	public void setFormat(SheetEmailFormat format) {
		this.format = format;
	}

	/**
	 * Gets the format details (paper dimensions).
	 *
	 * @return the format details
	 */
	public FormatDetails getFormatDetails() {
		return formatDetails;
	}

	/**
	 * Sets the format details (paper dimensions).
	 *
	 * @param formatDetails the new format details
	 */
	public void setFormatDetails(FormatDetails formatDetails) {
		this.formatDetails = formatDetails;
	}

	/**
	 * A convenience class to help create a SheetEmail object with the appropriate fields.
	 */
	public static class AddSheetEmailBuilder {
		/**
		 * Represents the sheet email format (PDF or Excel).
		 */
		private SheetEmailFormat format;

		/**
		 * Represents the format details (paper dimensions).
		 */
		private FormatDetails formatDetails;

		/**
		 * Gets the sheet email format (PDF or Excel).
		 *
		 * @return the format
		 */
		public SheetEmailFormat getFormat() {
			return format;
		}

		/**
		 * Sets the sheet email format (PDF or Excel).
		 *
		 * @param format the new format
		 * @return the builder
		 */
		public AddSheetEmailBuilder setFormat(SheetEmailFormat format) {
			this.format = format;
			return this;
		}

		/**
		 * Gets the format details (paper dimensions).
		 *
		 * @return the format details
		 */
		public FormatDetails getFormatDetails() {
			return formatDetails;
		}

		/**
		 * Sets the format details (paper dimensions).
		 *
		 * @param formatDetails the new format details
		 * @return the builder
		 */
		public AddSheetEmailBuilder setFormatDetails(FormatDetails formatDetails) {
			this.formatDetails = formatDetails;
			return this;
		}

		/**
		 * Builds the sheetEmail.
		 *
		 * @return the sheetEmail
		 */
		public SheetEmail build() {
			SheetEmail sheetEmail = new SheetEmail();
			sheetEmail.format = format;
			sheetEmail.formatDetails = formatDetails;
			return sheetEmail;
		}

	}
	
	
}
