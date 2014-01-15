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
