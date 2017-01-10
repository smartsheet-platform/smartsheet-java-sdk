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

public class Predecessor {

	/**
	 * The Id of the predecessor row
	 */
	private Long rowId;
	
	/**
	 * The row number of the predecessor row
	 */
	private Integer rowNumber;
	
	/**
	 * The type of the predecessor - one of FS, FF, SS, or SF
	 */
	private String type;
	
	/**
	 * The lag value of this predecessor. Omitted if there is no lag.
	 */
	private Duration lag;
	
	/**
	 * True if the row referenced by rowId is not a valid row in this sheet, or there is a circular reference
	 */
	private Boolean invalid;
	
	/** 
	 * True if this predecessor is in the critical path
	 */
	private Boolean inCriticalPath;
	
	/**
	 * Get the Id of the predecessor row
	 * 
	 * @return rowId
	 */
	public Long getRowId() {
		return rowId;
	}
	
	/**
	 * Set the Id of the predecessor row
	 * 
	 * @param rowId
	 */
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	
	/**
	 * Get the number of the predecessor row
	 * 
	 * @return rowNumber
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}
	
	/**
	 * Set the number of the predecessor row
	 * 
	 * @param rowNumber
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	/**
	 * Get the type of the predecessor
	 * 
	 * @return type (FS, FF, SS, or SF)
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the type of the predecessor
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get the lag value of this predecessor
	 * 
	 * @return lag
	 */
	public Duration getLag() {
		return lag;
	}
	
	/**
	 * Set the lag value of this predecessor
	 * 
	 * @param lag
	 */
	public void setLag(Duration lag) {
		this.lag = lag;
	}
	
	/**
	 * Get flag indicating if the row referenced by rowId is not a valid row in this sheet,
	 * or if there is a circular reference
	 * 
	 * @return invalid
	 */
	public Boolean isInvalid() {
		return invalid;
	}
	
	/**
	 * Set flag indicating if the row referenced by rowId is not a valid row in this sheet,
	 * or if there is a circular reference
	 * 
	 * @param invalid
	 */
	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}
	
	/**
	 * Get flag indicating if this predecessor is in the critical path
	 * 
	 * @return inCriticalPath
	 */
	public Boolean isInCriticalPath() {
		return inCriticalPath;
	}
	
	/**
	 * Set flag indicating if this predecessor is in the critical path
	 * 
	 * @param inCriticalPath
	 */
	public void setInCriticalPath(Boolean inCriticalPath) {
		this.inCriticalPath = inCriticalPath;
	}
}

