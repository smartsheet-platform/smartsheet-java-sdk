package com.smartsheet.api.internal;

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
 * This is the base class of the Smartsheet REST API resources that are associated to other resources.
 * 
 * Thread Safety: This class is thread safe because it is immutable and its base class is thread safe.
 */
public abstract class AbstractAssociatedResources extends AbstractResources {
	/**
	 * Represents the master resource type (e.g. "sheet", "workspace").
	 * 
	 * It will be initialized in constructor and will not change afterwards.
	 */
	private String masterResourceType;

	/**
	 * Constructor.
	 * 
	 * Parameters: - smartsheet : the SmartsheetImpl - masterResourceType : the master resource type (e.g. "sheet",
	 * "workspace").
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null or empty string
	 * 
	 * Implementation: super(smartsheet); this.masterResourceType = masterResourceType;
	 * 
	 * @param masterResourceType
	 * @param smartsheet
	 */
	public AbstractAssociatedResources(SmartsheetImpl smartsheet, String masterResourceType) {
		super(smartsheet);
		this.masterResourceType = masterResourceType;
	}

	/**
	 * Getter of corresponding field.
	 * 
	 * Returns: corresponding field.
	 * 
	 * Implementation: Simply return corresponding field.
	 * 
	 * @return
	 */
	protected String getMasterResourceType() {
		return masterResourceType;
	}
}
