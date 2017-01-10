package com.smartsheet.api.models;

import java.util.List;

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

public class WidgetContent {
	/**
	 * Represents the CellLinkWidgetContent object
	 */
	private Link hyperlink;
	private List<CellDataItem> cellData;
	private List<Column> column;
	
	/**
	 * Represents the RichTextWidgetContent object.
	 */
	private String html;
	
	/**
	 * Represents the ShortcutWidgetContent object.
	 */
	private List<ShortcutDataItem> shortcutData;
	
	/**
	 * Represents the ReportWidgetContent object.
	 */
	private String htmlContent;
	
	/**
	 * Represents the ImageWidgetContent object.
	 */
	private String privateId;
	private Integer height;
	private Integer width;
	private String fileName;
	private String format;
	
	/**
	 * Get "when clicked" hyperlink
	 * 
	 * @return hyperlink
	 */
	public Link getHyperlink() {
		return hyperlink;
	}
	
	/**
	 * Set "when clicked" hyperlink
	 * 
	 * @param hyperlink
	 */
	public void setHyperlink(Link hyperlink) {
		this.hyperlink = hyperlink;
	}
	
	/**
	 * Get array of cell data
	 * 
	 * @return cellData
	 */
	public List<CellDataItem> getCellData() {
		return cellData;
	}
	
	/**
	 * Set array of cell data
	 * 
	 * @param cellData
	 */
	public void setCellData(List<CellDataItem> cellData) {
		this.cellData = cellData;
	}
	
	/**
	 * Get array of column objects
	 * 
	 * @return column
	 */
	public List<Column> getColumn() {
		return column;
	}
	
	/**
	 * Set array of column objects
	 * 
	 * @param column
	 */
	public void setColumn(List<Column> column) {
		this.column = column;
	}
	
	/**
	 * Get widget content as HTML
	 * 
	 * @return html
	 */
	public String getHtml() {
		return html;
	}
	
	/**
	 * Set widget content as HTML
	 * 
	 * @param html
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	
	/**
	 * Get array of ShortcutDataItem objects.
	 * 
	 * @return shortcutData
	 */
	public List<ShortcutDataItem> getShortcutData() {
		return shortcutData;
	}
	
	/**
	 * Set the array of ShortcutDataItems 
	 * 
	 * @param shortcutData
	 */
	public void setShortcutData(List<ShortcutDataItem> shortcutData) {
		this.shortcutData = shortcutData;
	}
	
	/**
	 * Get HTML snippet to render Report
	 * 
	 * @return htmlContent
	 */
	public String getHtmlContent() {
		return htmlContent;
	}
	
	/** 
	 * Set HTML snippet to render Report
	 * 
	 * @param htmlContent
	 */
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	/**
	 * Get the image private Id
	 * 
	 * @return privateId
	 */
	public String getPrivateId() {
		return privateId;
	}
	
	/**
	 * Set the image private Id
	 * 
	 * @param privateId
	 */
	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}
	
	/**
	 * Get the original height of the image in pixels
	 * 
	 * @return height
	 */
	public Integer getHeight() {
		return height;
	}
	
	/**
	 * Set the original height of the image in pixels
	 * 
	 * @param height
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	/**
	 * Get the original width of the image in pixels
	 * 
	 * @return width
	 */
	public Integer getWidth() {
		return width;
	}
	
	/**
	 * Set the original width of the image in pixels
	 * 
	 * @param width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	/**
	 * Get the file name of the image file
	 * 
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Set the file name of the image file
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Get the format descriptor of the image
	 * 
	 * @return format
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * Set the format descriptor of the image
	 * 
	 * @param format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
}
