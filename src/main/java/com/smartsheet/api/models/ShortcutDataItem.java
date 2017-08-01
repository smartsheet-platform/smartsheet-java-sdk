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

public class ShortcutDataItem {

	/**
	 * Lable for the data point
	 */
	private String label;
	
	/** 
	 * format Descriptor
	 */
	private String labelFormat;
	
	/**
	 * Attachment type(one of FILE, GOOGLE_DRIVE, LINK, BOX_COM, DROPBOX, EVERNOTE, or EGNYTE)
	 */
	private String mimeType;
	
	/**
	 * Hyperlink object
	 */
	private Link hyperlink;
	
	/**
	 * The display order for the Shortcut
	 */
	private Integer order;
	
	/**
	 * Get the label for the data point.
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Set the label for the data point.
	 *
	 * @param label
	 */
	public ShortcutDataItem setLabel(String label) {
		this.label = label;
		return this;
	}
	
	/** 
	 * Get the label format string
	 * 
	 * @return labelFormat;
	 */
	public String getLabelFormat() {
		return labelFormat;
	}
	
	/**
	 * Set the label format string
	 *
	 * @param labelFormat
	 */
	public ShortcutDataItem setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
		return this;
	}
	
	/**
	 * Get the MIME type
	 * (one of FILE, GOOGLE_DRIVE, LINK, BOX_COM, DROPBOX, EVERNOTE, or EGNYTE)
	 * 
	 * @return mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}
	
	/**
	 * Set the MIME Type
	 * (one of FILE, GOOGLE_DRIVE, LINK, BOX_COM, DROPBOX, EVERNOTE, or EGNYTE).
	 *
	 * @param mimeType
	 */
	public ShortcutDataItem setMimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}
	
	/**
	 * Get the hyperlink object
	 * 
	 * @return hyperlink
	 */
	public Link getHyperlink() {
		return hyperlink;
	}
	
	/** 
	 * Set the hyperlink object
	 *
	 * @param hyperlink
	 */
	public ShortcutDataItem setHyperlink(Link hyperlink) {
		this.hyperlink = hyperlink;
		return this;
	}
	
	/**
	 * Get the display order for this shortcut data item
	 * 
	 * @return order
	 */
	public Integer getOrder() {
		return order;
	}
	
	/**
	 * Set the display order for this shortcut data item
	 *
	 * @param order
	 */
	public ShortcutDataItem setOrder(Integer order) {
		this.order = order;
		return this;
	}
}
