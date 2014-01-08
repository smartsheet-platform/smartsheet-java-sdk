package com.smartsheet.api.internal.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a Jackson Mixin class that excludes "id" field from being serialized to JSON. This is needed because when
 * updating a resource, the resource ID should be present in the resource model but it shouldn't be serialized and sent
 * to Smartsheet REST API.
 * 
 * It defines two abstract methods ("id" getter and setter) and annotates them as @JsonIgnore and @JsonProperty
 * respectively.
 * 
 * It is a static private inner class of JacksonJsonSerializer class.
 * 
 * Thread Safety: This class is thread safe since it's immutable.
 */
public abstract class IdFieldExclusionMixin {
	/**
	 * Getter of the "id" field.
	 * 
	 * @return
	 */
	@JsonProperty
	public abstract long getId();

	/**
	 * Setter of the "id" field.
	 * 
	 * @param id
	 */
	@JsonIgnore
	public abstract void setId(long id);
}
