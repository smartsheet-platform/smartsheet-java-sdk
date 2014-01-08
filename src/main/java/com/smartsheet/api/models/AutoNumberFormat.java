package com.smartsheet.api.models;

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
	
	
	public AutoNumberFormat(String prefix, String suffix, String fill, Long startingNumber){
		this.prefix = prefix;
		this.suffix = suffix;
		this.fill = fill;
		this.startingNumber = startingNumber;
	}
	
	
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
