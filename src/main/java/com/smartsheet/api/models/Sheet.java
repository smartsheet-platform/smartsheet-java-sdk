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
import java.util.EnumSet;

/**
 * Represents the Sheet object.
 */
public class Sheet extends AbstractSheet<Row, Column, Cell> {

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
