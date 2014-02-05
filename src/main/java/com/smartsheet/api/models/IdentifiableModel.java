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
 * Represents an object with ID.
 */
public abstract class IdentifiableModel {
	/**
	 * Represents the ID.
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Check if the given object equals to this object.
	 * 
	 * Parameters: - object : the object to compare
	 * 
	 * Returns: true if given object equals to this object, false otherwise
	 * 
	 * Implementation: return object != null && (object == this || (object.getClass() == this.getClass() &&
	 * object.getId() == this.getId()));
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public boolean equals(Object object) {
		return object != null && (object == this || (object.getClass() == this.getClass() && 
				((IdentifiableModel) object).getId() == this.getId()));
	}

	/**
	 * Return the hash code of this object.
	 * 
	 * Parameters: None
	 * 
	 * Returns: the hash code
	 * 
	 * Implementation: int result = 17; result = 31 * result + (int) (this.id.longValue() ^ (this.id.longValue() >>>
	 * 32));
	 * 
	 * return result;
	 * 
	 * @return
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (int) (this.id.longValue() ^ (this.id.longValue() >>> 32));
		return result;
	}
}
