package com.smartsheet.api.models;

import com.smartsheet.api.models.*;

/**
 * Represents the object inclusion types.
 */
public enum ObjectInclusion {
	DISCUSSIONS ("discussions"),
	ATTACHMENTS ("attachments"),
	DATA ("data"),
	COLUMNS ("columns"),
	TEMPLATES ("templates");
	
	String inclusion;
	
	ObjectInclusion(String inclusion){
		this.inclusion = inclusion;
	}
}

