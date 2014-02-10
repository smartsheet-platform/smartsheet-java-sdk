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

import java.util.Date;
import java.util.List;

import com.smartsheet.api.SheetResources;
import com.smartsheet.api.internal.SheetResourcesImpl;
import com.smartsheet.api.models.Workspace.UpdateWorkspaceBuilder;

/**
 * Represents Sheet object in the Smartsheet REST API.
 */
public class Sheet extends NamedModel {
	/**
	 * Represents the columns.
	 */
	private List<Column> columns;

	/**
	 * Represents the rows.
	 */
	private List<Row> rows;

	/**
	 * Represents the access level.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the discussions.
	 */
	private List<Discussion> discussions;

	/**
	 * Represents the attachments.
	 */
	private List<Attachment> attachments;

	/**
	 * Represents the read only flag.
	 */
	private Boolean readOnly;

	/**
	 * Represents the creation timestamp.
	 */
	private Date createdAt;

	/**
	 * Represents the modification timestamp.
	 */
	private Date modifiedAt;

	/**
	 * Represents the direct URL to the sheet.
	 */
	private String permalink;

	/**
	 * Represents the Gantt enabled flag.
	 */
	private Boolean ganttEnabled;

	private Boolean dependenciesEnabled;

	/**
	 * Represents the version.
	 */
	private Integer version;

	/**
	 * Represents the ID of the sheet/template from which the sheet was created.
	 */
	private Long fromId;

	public Boolean getDependenciesEnabled() {
		return dependenciesEnabled;
	}

	public void setDependenciesEnabled(Boolean dependenciesEnabled) {
		this.dependenciesEnabled = dependenciesEnabled;
	}

	/**
	 * Get column by index.
	 * 
	 * Parameters: - index : the column index
	 * 
	 * Returns: the column
	 * 
	 * Implementation: for (Column column : columns) { if (column.getIndex() == index) { return column; } } return null;
	 * 
	 * @param index
	 * @return
	 */
	public Column getColumnByIndex(int index) {
		if (columns == null) {
			return null;
		}

		Column result = null;
		for (Column column : columns) {
			if (column.getIndex() == index) {
				result = column;
				break;
			}
		}
		return result;
	}

	/**
	 * Get column by ID.
	 * 
	 * Parameters: - columnId : the column ID
	 * 
	 * Returns: the column
	 * 
	 * Implementation: for (Column column : columns) { if (column.getId() == columnId) { return column; } } return null;
	 * 
	 * @param columnId
	 * @return
	 */
	public Column getColumnById(long columnId) {
		if (columns == null) {
			return null;
		}

		Column result = null;
		for (Column column : columns) {
			if (column.getId() == columnId) {
				result = column;
				break;
			}
		}
		return result;
	}

	/**
	 * Get row by row number.
	 * 
	 * Parameters: - rowNumber : the row number
	 * 
	 * Returns: the row
	 * 
	 * Implementation: for (Row row : rows) { if (row.getRowNumber() == rowNumber) { return row; } } return null;
	 * 
	 * @param rowNumber
	 * @return
	 */
	public Row getRowByRowNumber(int rowNumber) {
		if (rows == null) {
			return null;
		}

		Row result = null;
		for (Row row : rows) {
			if (row.getRowNumber() == rowNumber) {
				result = row;
				break;
			}
		}
		return result;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public Boolean getGanttEnabled() {
		return ganttEnabled;
	}

	public void setGanttEnabled(Boolean ganttEnabled) {
		this.ganttEnabled = ganttEnabled;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	/**
	 * A class to simplify the creation of a sheet.
	 * @author brett
	 *
	 */
	public static class CreateBuilder {
		private List<Column> columns;
		private String name;

		/**
		 * Sets the columns for the sheet being created.
		 * @param columns The columns to create with this sheet.
		 * @return
		 */
		public CreateBuilder setColumns(List<Column> columns) {
			this.columns = columns;
			return this;
		}

		/**
		 * Sets the name for the sheet being created.
		 * @param name The name for the sheet being created.
		 * @return
		 */
		public CreateBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Returns the list of columns.
		 * @return
		 */
		public List<Column> getColumns() {
			return columns;
		}

		/**
		 * Returns the name for the sheet.
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * Creates a sheet by using the values from setters in this builder.
		 * @return
		 */
		public Sheet build() {
			Sheet sheet = new Sheet();

			if (columns == null || name == null) {
				throw new InstantiationError();
			}

			sheet.setColumns(columns);
			sheet.setName(name);
			return sheet;
		}
	}
	
	
	/**
	 * A class to simplify the creation of a sheet from another sheet or another template.
	 * @author brett
	 *
	 */
	public static class CreateFromTemplateOrSheetBuilder {
		private String name;
		private Long fromId;

		/**
		 * Sets the name for the sheet being created.
		 * @param name The name for the sheet being created.
		 * @return
		 */
		public CreateFromTemplateOrSheetBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * 
		 * Returns the name for the sheet.
		 * @return
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Set the from Id
		 * @param id
		 * @return
		 */
		public CreateFromTemplateOrSheetBuilder setFromId(Long id) {
			this.fromId = id;
			return this;
		}

		/**
		 * 
		 * Creates a sheet by using the values from setters in this builder.
		 * @return
		 */
		public Sheet build() {
			Sheet sheet = new Sheet();

			if (fromId == null || name == null) {
				throw new InstantiationError();
			}

			sheet.setFromId(fromId);
			sheet.setName(name);
			return sheet;
		}
	}

	
	public static class UpdateSheetBuilder {
		private String sheetName;

		public UpdateSheetBuilder name(String name) {
			this.sheetName = name;
			return this;
		}

		public Sheet build() {
			Sheet sheet = new Sheet();
			sheet.setName(sheetName);
			return sheet;
		}
	}
}
