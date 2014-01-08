package com.smartsheet.api.models;

/**
 * Represents an object with name.
 */
public abstract class NamedModel extends IdentifiableModel {
	/**
	 * Represents the name.
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
