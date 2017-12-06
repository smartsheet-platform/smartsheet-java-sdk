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



import java.util.List;

/**
 * Represents a folder.
 */
public class Folder extends NamedModel<Long> {

    /**
     * Represents the sheets contained in the folder.
     */
    private List<Sheet> sheets;

    /**
     * Represents the child folders contained in the folder.
     */
    private List<Folder> folders;

    /**
     * Represents the reports.
     */
     private List<Report> reports;

    /**
     * Represents the templates contained in the folder.
     */
    private List<Template> templates;

    /**
     * Represents the Sights contained in the folder.
     */
    private List<Sight> sights;

    /**
     * Returns if the user has marked the Folder as a Favorite in their Home tab.
     */
    private Boolean favorite;

    /**
     * Represents the Direct URL to Folder.
     */
    private String permalink;

    /**
     * Default constructor
     */
    public Folder() {
    }

    /**
     * Construct a Folder with specified id
     *
     * @param id Folder id
     */
    public Folder(Long id) {
        this.setId(id);
    }

    /**
     * Gets the Direct URL to Folder.
     *
     * @return the sheets
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * Sets the Direct URL to Folder.
     *
     * @param permalink the new sheets
     */
    public Folder setPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    /**
     * Gets the favorite boolean value of folder in Home.
     *
     * @return the sheets
     */
    public Boolean getFavorite() {
        return favorite;
    }

    /**
     * Sets the favorite boolean value of folder in Home.
     *
     * @param favorite the new sheets
     */
    public Folder setFavorite(Boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    /**
     * Gets the sheets in the folder.
     *
     * @return the sheets
     */
    public List<Sheet> getSheets() {
        return sheets;
    }

    /**
     * Sets the sheets contained in the folder.
     *
     * @param sheets the new sheets
     */
    public Folder setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
        return this;
    }

    /**
     * Gets the folders contained in this folder.
     *
     * @return the folders
     */
    public List<Folder> getFolders() {
        return folders;
    }

    /**
     * Sets the folders contained in this folder.
     *
     * @param folders the new folders
     */
    public Folder setFolders(List<Folder> folders) {
        this.folders = folders;
        return this;
    }

    /**
     * Gets the reports.
     *
     * @return the reports
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * Sets the reports.
     *
     * @param reports the new reports
     */
    public Folder setReports(List<Report> reports) {
        this.reports = reports;
        return this;
    }

    /**
     * Gets the templates contained in this folder.
     *
     * @return the templates
     */
    public List<Template> getTemplates() {
        return templates;
    }

    /**
     * Sets the templates contained in this folder.
     *
     * @param templates the new templates
     */
    public Folder setTemplates(List<Template> templates) {
        this.templates = templates;
        return this;
    }

    /**
     * Get the sights contained in this folder
     *
     * @return the sights
     */
    public List<Sight> getSights() {
        return sights;
    }

    /**
     * Sets the sights contained in this folder.
     *
     * @param sights
     */
    public Folder setSights(List<Sight> sights) {
        this.sights = sights;
        return this;
    }

    /**
     * A convenience class for setting up a folder with the appropriate fields for updating the folder.
     */
    public static class UpdateFolderBuilder {
        private String folderName;
        private Long id;

        /**
         * Name.
         *
         * @param name the name
         * @return the update folder builder
         */
        public UpdateFolderBuilder setName(String name) {
            this.folderName = name;
            return this;
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName(){
            return folderName;
        }

        /**
         * Gets the folder id.
         *
         * @return the folder id.
         */
        public Long getId() {
            return id;
        }

        /**
         * Sets the folder id.
         *
         * @param id the id of the folder.
         * @return the builder
         */
        public UpdateFolderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Builds the folder.
         *
         * @return the folder
         */
        public Folder build() {
            if(folderName == null){
                throw new IllegalStateException("A folder name is required.");
            }

            Folder folder = new Folder();
            folder.setId(id);
            folder.setName(folderName);
            return folder;
        }
    }

    /**
     * A convenience class for setting up a folder with the appropriate fields for updating the folder.
     */
    public static class CreateFolderBuilder {
        private String folderName;

        /**
         * Name.
         *
         * @param name the name
         * @return the create folder builder
         */
        public CreateFolderBuilder setName(String name) {
            this.folderName = name;
            return this;
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName(){
            return folderName;
        }

        /**
         * Builds the folder.
         *
         * @return the folder
         */
        public Folder build() {
            if(folderName == null){
                throw new IllegalStateException("A folder name is required.");
            }

            Folder folder = new Folder();
            folder.setName(folderName);
            return folder;
        }
    }
}
