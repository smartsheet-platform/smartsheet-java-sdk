package com.smartsheet.api.models;

/**
 * Represents SheetEmail object.
 */
public class SheetEmail extends Email {
	/**
	 * Represents the sheet email format.
	 */
	private SheetEmailFormat format;

	/**
	 * Represents the format details.
	 */
	private FormatDetails formatDetails;

	public SheetEmailFormat getFormat() {
		return format;
	}

	public void setFormat(SheetEmailFormat format) {
		this.format = format;
	}

	public FormatDetails getFormatDetails() {
		return formatDetails;
	}

	public void setFormatDetails(FormatDetails formatDetails) {
		this.formatDetails = formatDetails;
	}
	
	
}
