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
 * Represents RowEmail object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504773-sending-sheets-rows-via-email">Help Sending 
 * Sheets & Rows via Email</a>
 */
public class RowEmail extends Email {
	/**
	 * A flag to indicate if attachments should be included in the email.
	 */
	private Boolean includeAttachments;

	/**
	 * A flag to indicate if discussions should be included in the email.
	 */
	private Boolean includeDiscussions;

	/**
	 * Gets the flag that indicates if attachments should be included in the email.
	 *
	 * @return the include attachments
	 */
	public Boolean getIncludeAttachments() {
		return includeAttachments;
	}

	/**
	 * Sets the flag that indicates if attachments should be included in the email.
	 *
	 * @param includeAttachments the new include attachments
	 */
	public void setIncludeAttachments(Boolean includeAttachments) {
		this.includeAttachments = includeAttachments;
	}

	/**
	 * Gets the flag that indicates if discussions should be included in the email.
	 *
	 * @return the include discussions
	 */
	public Boolean getIncludeDiscussions() {
		return includeDiscussions;
	}

	/**
	 * Sets the flag that indicates if discussions should be included in the email.
	 *
	 * @param includeDiscussions the new include discussions
	 */
	public void setIncludeDiscussions(Boolean includeDiscussions) {
		this.includeDiscussions = includeDiscussions;
	}

	/**
	 * A convenience class to help create a RowEmail object with the appropriate fields.
	 */
	public static class AddRowEmailBuilder {
		/**
		 * A flag to indicate if attachments should be included in the email.
		 */
		private Boolean includeAttachments;

		/**
		 * A flag to indicate if discussions should be included in the email.
		 */
		private Boolean includeDiscussions;

		/**
		 * Gets the flag that indicates if attachments should be included in the email.
		 *
		 * @return the include attachments
		 */
		public Boolean getIncludeAttachments() {
			return includeAttachments;
		}

		/**
		 * Sets the flag that indicates if attachments should be included in the email.
		 *
		 * @param includeAttachments the new include attachments
		 */
		public AddRowEmailBuilder setIncludeAttachments(Boolean includeAttachments) {
			this.includeAttachments = includeAttachments;
			return this;
		}

		/**
		 * Gets the flag that indicates if discussions should be included in the email.
		 *
		 * @return the include discussions
		 */
		public Boolean getIncludeDiscussions() {
			return includeDiscussions;
		}

		/**
		 * Sets the flag that indicates if discussions should be included in the email.
		 *
		 * @param includeDiscussions the new include discussions
		 */
		public AddRowEmailBuilder setIncludeDiscussions(Boolean includeDiscussions) {
			this.includeDiscussions = includeDiscussions;
			return this;
		}


		/**
		 * Builds the sheetEmail.
		 *
		 * @return the sheetEmail
		 */
		public RowEmail build() {
			RowEmail rowEmail = new RowEmail();
			rowEmail.includeAttachments = includeAttachments;
			rowEmail.includeDiscussions = includeDiscussions;
			return rowEmail;
		}

	}
}
