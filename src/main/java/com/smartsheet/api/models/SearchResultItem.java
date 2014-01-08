package com.smartsheet.api.models;

import java.util.List;

/**
 * Represents SearchResultItem object.
 */
public class SearchResultItem {
	/**
	 * Represents the text.
	 */
	private String text;

	/**
	 * Represents the object ID.
	 */
	private Long objectId;

	/**
	 * Represents the object type.
	 */
	private String objectType;

	/**
	 * Represents the parent object ID.
	 */
	private Long parentObjectId;

	/**
	 * Represents the parent object type.
	 */
	private String parentObjectType;

	/**
	 * Represents the parent object name.
	 */
	private String parentObjectName;

	/**
	 * Represents the context data.
	 */
	private List<String> contextData;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getParentObjectId() {
		return parentObjectId;
	}

	public void setParentObjectId(Long parentObjectId) {
		this.parentObjectId = parentObjectId;
	}

	public String getParentObjectType() {
		return parentObjectType;
	}

	public void setParentObjectType(String parentObjectType) {
		this.parentObjectType = parentObjectType;
	}

	public String getParentObjectName() {
		return parentObjectName;
	}

	public void setParentObjectName(String parentObjectName) {
		this.parentObjectName = parentObjectName;
	}

	public List<String> getContextData() {
		return contextData;
	}

	public void setContextData(List<String> contextData) {
		this.contextData = contextData;
	}
}
