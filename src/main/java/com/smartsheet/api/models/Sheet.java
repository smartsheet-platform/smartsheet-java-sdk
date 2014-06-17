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

/**
 * Represents the Sheet object.
 */
public class Sheet extends NamedModel {
	/**
	 * Represents the columns for the sheet.
	 */
	private List<Column> columns;

	/**
	 * Represents the rows for the sheet.
	 */
	private List<Row> rows;

	/**
	 * Represents the access level for the sheet.
	 */
	private AccessLevel accessLevel;

	/**
	 * Represents the discussions for the sheet.
	 */
	private List<Discussion> discussions;

	/**
	 * Represents the attachments for the sheet.
	 */
	private List<Attachment> attachments;

	/**
	 * Represents the read only flag for the sheet.
	 */
	private Boolean readOnly;

	/**
	 * Represents the creation timestamp for the sheet.
	 */
	private Date createdAt;

	/**
	 * Represents the modification timestamp for the sheet.
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

	/**
	 * Represents the dependencies enabled flag.
	 * @see <a href="http://help.smartsheet.com/customer/portal/articles/765727-using-the-dependencies-functionality">
	 * Dependencies Functionality</a>
	 */
	private Boolean dependenciesEnabled;

	/**
	 * Represents the version for the sheet
	 */
	private Integer version;

	/**
	 * Represents the ID of the sheet/template from which the sheet was created.
	 */
	private Long fromId;

	/**
	 * The workspace the sheet belongs to.
	 */
	private Workspace workspace;
	
	/**
	 * Gets the dependencies enabled flag.
	 *
	 * @return the dependencies enabled
	 */
	public Boolean getDependenciesEnabled() {
		return dependenciesEnabled;
	}

	/**
	 * Sets the dependencies enabled flag.
	 *
	 * @param dependenciesEnabled the new dependencies enabled
	 */
	public void setDependenciesEnabled(Boolean dependenciesEnabled) {
		this.dependenciesEnabled = dependenciesEnabled;
	}

	/**
	 * Get a column by index.
	 * 
	 * @param index the column index
	 * @return the column by index
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
	 * Get a {@link Column} by ID.
	 * 
	 * @param columnId the column id
	 * @return the column by id
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
	 * Get a {@link Row} by row number.
	 *
	 * @param rowNumber the row number
	 * @return the row by row number
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

	/**
	 * Gets the columns for the sheet.
	 *
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * Sets the columns for the sheet.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * Gets the rows for the sheet.
	 *
	 * @return the rows
	 */
	public List<Row> getRows() {
		return rows;
	}

	/**
	 * Sets the rows for the sheet.
	 *
	 * @param rows the new rows
	 */
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	/**
	 * Gets the access level for the sheet.
	 *
	 * @return the access level
	 */
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets the access level for the sheet.
	 *
	 * @param accessLevel the new access level
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Gets the discussions for the sheet.
	 *
	 * @return the discussions
	 */
	public List<Discussion> getDiscussions() {
		return discussions;
	}

	/**
	 * Sets the discussions for the sheet.
	 *
	 * @param discussions the new discussions
	 */
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	/**
	 * Gets the attachments for the sheet.
	 *
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * Sets the attachments for the sheet.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Gets the read only flag for the sheet.
	 *
	 * @return the read only
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the read only flag for the sheet.
	 *
	 * @param readOnly the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Gets the date and time the sheet was created.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the date and time the sheet was created.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the date and time the sheet was last modified.
	 *
	 * @return the modified at
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * Sets the date and time the sheet was last modified.
	 *
	 * @param modifiedAt the new modified at
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * Gets the permalink for the sheet.
	 *
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * Sets the permalink for the sheet.
	 *
	 * @param permalink the new permalink
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * Gets the gantt enabled flag.
	 *
	 * @return the gantt enabled flag
	 */
	public Boolean getGanttEnabled() {
		return ganttEnabled;
	}

	/**
	 * Sets the gantt enabled flag.
	 *
	 * @param ganttEnabled the new gantt enabled flag
	 */
	public void setGanttEnabled(Boolean ganttEnabled) {
		this.ganttEnabled = ganttEnabled;
	}

	/**
	 * Gets the version for the sheet.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the version for the sheet.
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Gets the ID of the sheet/template from which the sheet was created.
	 *
	 * @return the from id
	 */
	public Long getFromId() {
		return fromId;
	}

	/**
	 * Sets the ID of the sheet/template from which the sheet was created.
	 *
	 * @param fromId the new from id
	 */
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}


	/**
	 * Returns the owning {@link Workspace}. Only present when the whole sheet is fetched.
	 * @return The workspace
	 */
	public Workspace getWorkspace() {
		return workspace;
	}

	
	/**
	 * Sets the workspace.
	 * @param workspace
	 */
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	
	/**
	 * A convenience class to make a {@link Sheet} object with the necessary fields to create the sheet by posting it 
	 * to smartsheet.
	 */
	public static class CreateSheetBuilder {
		private List<Column> columns;
		private String name;

		/**
		 * Sets the columns for the sheet being created.
		 *
		 * @param columns The columns to create with this sheet.
		 * @return the creates the builder
		 */
		public CreateSheetBuilder setColumns(List<Column> columns) {
			this.columns = columns;
			return this;
		}

		/**
		 * Sets the name for the sheet being created.
		 *
		 * @param name The name for the sheet being created.
		 * @return the creates the builder
		 */
		public CreateSheetBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Returns the list of columns.
		 *
		 * @return the columns
		 */
		public List<Column> getColumns() {
			return columns;
		}

		/**
		 * Returns the name for the sheet.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Creates a sheet by using the values from setters in this builder.
		 *
		 * @return the sheet
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
		 *
		 * @param name The name for the sheet being created.
		 * @return the creates the from template or sheet builder
		 */
		public CreateFromTemplateOrSheetBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Returns the name for the sheet.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Set the from Id.
		 *
		 * @param id the id
		 * @return the creates the from template or sheet builder
		 */
		public CreateFromTemplateOrSheetBuilder setFromId(Long id) {
			this.fromId = id;
			return this;
		}

		/**
		 * Gets the from id.
		 *
		 * @return the from id
		 */
		public Long getFromId() {
			return fromId;
		}

		/**
		 * Creates a sheet by using the values from setters in this builder.
		 *
		 * @return the sheet
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

	
	/**
	 * The Class UpdateSheetBuilder.
	 */
	public static class UpdateSheetBuilder {
		private String sheetName;
		private Long id;

		/**
		 * Get the id of the sheet
		 * @return
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Set the sheet id
		 * @param id
		 * @return the updateSheetBuilder object
		 */
		public UpdateSheetBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the update sheet builder
		 */
		public UpdateSheetBuilder setName(String name) {
			this.sheetName = name;
			return this;
		}

		/**
		 * Gets the sheet name.
		 *
		 * @return the sheet name
		 */
		public String getName() {
			return sheetName;
		}


		/**
		 * Builds the.
		 *
		 * @return the sheet
		 */
		public Sheet build() {
			if(sheetName == null){
				throw new InstantiationError();
			}
			
			Sheet sheet = new Sheet();
			sheet.setName(sheetName);
			sheet.setId(id);
			return sheet;
		}
	}
}
