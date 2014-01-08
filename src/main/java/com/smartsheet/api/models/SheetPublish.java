package com.smartsheet.api.models;

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
