package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2015 Smartsheet
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

import com.smartsheet.api.models.enums.AccessLevel;
import com.smartsheet.api.models.enums.AttachmentType;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;


public class AbstractSheet<TRow extends AbstractRow<TColumn , TCell>, TColumn extends Column, TCell extends Cell> extends NamedModel<Long>{

    /**
     * Represents the ID of the sheet/template from which the sheet was created.
     */
    private Long fromId;

    /**
     * Represents the owner id of the owner.
     */
    private Long ownerId;

    /**
     * Represents the access level for the sheet.
     */
    private AccessLevel accessLevel;

    /**
     * Represents the attachments for the sheet.
     */
    private List<Attachment> attachments;

    /**
     * Represents the columns for the sheet.
     */
    private List<TColumn> columns;

    /**
     * Get a list of contact references used by MULTI_CONTACT columns in this sheet
     */
    private List<ContactObjectValue> contactReferences;

    /**
     * Represents the creation timestamp for the sheet.
     */
    private Date createdAt;

    /**
     * Get a list of cross sheet references used by this sheet
     */
    private List<CrossSheetReference> crossSheetReferences;

    /**
     * Represents the dependencies enabled flag.
     * @see <a href="http://help.smartsheet.com/customer/portal/articles/765727-using-the-dependencies-functionality">
     * Dependencies Functionality</a>
     */
    private Boolean dependenciesEnabled;

    /**
     * Represents the discussions for the sheet.
     */
    private List<Discussion> discussions;

    /**
     * Represents effective attachment options.
     */
    private EnumSet<AttachmentType> effectiveAttachmentOptions;

    /**
     * Identifies if the sheet is marked as favorite.
     */
    private Boolean favorite;

    /**
     * List of sheet filters
     */
    private List<SheetFilter> filters;

    /**
     * Represents the Gantt enabled flag.
     */
    private Boolean ganttEnabled;

    /**
     * Indicates whether a sheet summary is present
     */
    private Boolean hasSummaryFields;

    /**
     * Represents the modification timestamp for the sheet.
     */
    private Date modifiedAt;

    /**
     * Represents the owner of the sheet.
     */
    private String owner;

    /**
     * Represents the direct URL to the sheet.
     */
    private String permalink;

    /**
     * Represents projects settings for a dependency-enabled sheet
     */
    private ProjectSettings projectSettings;

    /**
     * Represents the read only flag for the sheet.
     */
    private Boolean readOnly;

    /**
     * A flag that indicates if resource management is enabled for a sheet.
     */
    private Boolean resourceManagementEnabled;

    /**
     * Represents the rows for the sheet.
     */
    private List<TRow> rows;

    /**
     * Identifies if it is enabled to show parent rows for filters.
     */
    private Boolean showParentRowsForFilters;

    /**
     * Represents the source of the sheet.
     */
    private Source source;

    /**
     * A SheetSummary object containing a list of defined fields and values for the sheet.
     */
    private SheetSummary summary;

    /**
     * Represents the total number of rows in the sheet.
     */
    private Integer totalRowCount;

    /**
     * A SheetUserPermissions object indicating what summary operations are possible for the current user
     */
    private SheetUserPermissions userPermissions;

    /**
     * Represents the user settings.
     */
    private SheetUserSettings userSettings;

    /**
     * Represents the version for the sheet
     */
    private Integer version;

    /**
     * A Workspace object containing Id and Name (if this sheet is in a Workspace)
     */
    private Workspace workspace;

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
    public AbstractSheet<TRow, TColumn, TCell> setFromId(Long fromId) {
        this.fromId = fromId;
        return this;
    }

    /**
     * Gets the owner id.
     *
     * @return the ownerid
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the owner id.
     *
     * @param ownerId the owner id
     */
    public AbstractSheet<TRow, TColumn, TCell> setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * Gets the columns for the sheet.
     *
     * @return the columns
     */
    public List<TColumn> getColumns() {
        return columns;
    }

    /**
     * Sets the columns for the sheet.
     *
     * @param columns the new columns
     */
    public AbstractSheet<TRow, TColumn, TCell> setColumns(List<TColumn> columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Sets contact references used by MULTI_CONTACT_LIST columns
     *
     * @return contact references
     */
    public List<ContactObjectValue> getContactReferences() { return contactReferences; }

    /**
     * Sets contact references used by MULTI_CONTACT_LIST columns
     *
     * @param contactReferences
     */
    public AbstractSheet<TRow, TColumn, TCell> setContactReferences(List<ContactObjectValue> contactReferences) {
        this.contactReferences = contactReferences;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Gets the list of cross sheet references used by this sheet
     *
     * @return the cross sheet references
     */
    public List<CrossSheetReference> getCrossSheetReferences() { return crossSheetReferences; }

    /**
     * Sets the list of cross sheet references used by this sheet
     *
     * @param crossSheetReferences the cross sheet references
     */
    public AbstractSheet<TRow, TColumn, TCell> setCrossSheetReferences(List<CrossSheetReference> crossSheetReferences) {
        this.crossSheetReferences = crossSheetReferences;
        return this;
    }

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
    public AbstractSheet<TRow, TColumn, TCell> setDependenciesEnabled(Boolean dependenciesEnabled) {
        this.dependenciesEnabled = dependenciesEnabled;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
        return this;
    }

    /**
     * Gets the effective attachment options.
     *
     * @return list of attachment types
     */
    public EnumSet<AttachmentType> getEffectiveAttachmentOptions() {
        return effectiveAttachmentOptions;
    }

    /**
     * Sets the effective attachment options.
     *
     * @param effectiveAttachmentOptions the effective attachment options
     */
    public AbstractSheet<TRow, TColumn, TCell> setEffectiveAttachmentOptions(EnumSet<AttachmentType> effectiveAttachmentOptions) {
        this.effectiveAttachmentOptions = effectiveAttachmentOptions;
        return this;
    }

    /**
     * True if the sheet is a favorite sheet.
     *
     * @return the favorite
     */
    public Boolean isFavorite() {
        return favorite;
    }

    /**
     * Sets the favorite sheet
     *
     * @param favorite the favorite
     */
    public AbstractSheet<TRow, TColumn, TCell> setFavorite(Boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    /**
     * Get the list of sheet filters for this sheet.
     *
     * @return the list of SheetFilters
     */
    public List<SheetFilter> getFilters() { return filters; }

    /**
     * Sets the list of sheet filters for this sheet.
     *
     * @param filters the list of SheetFilters
     */
    public AbstractSheet<TRow, TColumn, TCell> setFilters(List<SheetFilter> filters) {
        this.filters = filters;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setGanttEnabled(Boolean ganttEnabled) {
        this.ganttEnabled = ganttEnabled;
        return this;
    }

    /**
     * Gets flag indicating whether a sheet summary is present
     *
     * @return hasSheetSummary
     */
    public Boolean getHasSummaryFields() { return hasSummaryFields; }

    /**
     * Sets flag indicating whether a sheet summary is present
     *
     * @param hasSummaryFields
     */
    public AbstractSheet<TRow, TColumn, TCell>  setHasSummaryFields(Boolean hasSummaryFields) {
        this.hasSummaryFields = hasSummaryFields;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    /**
     * Provide an 'override' of setName (returns AbstractSheet not NamedModel)
     *
     * @param name the new name
     */
    public AbstractSheet<TRow, TColumn, TCell> setName(String name){
        super.setName(name);
        return this;
    }

    /**
     * Gets the owner email.
     *
     * @return the owner email
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner
     *
     * @param owner the owner email
     */
    public AbstractSheet<TRow, TColumn, TCell> setOwner(String owner) {
        this.owner = owner;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    /**
     * Gets the project settings.
     *
     * @return the project settings
     */
    public ProjectSettings getProjectSettings() {
        return projectSettings;
    }

    /**
     * Sets the project settings.
     *
     * @param projectSettings the project settings
     */
    public AbstractSheet<TRow, TColumn, TCell> setProjectSettings(ProjectSettings projectSettings) {
        this.projectSettings = projectSettings;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    /**
     * @return the flag indicating if resource management is enabled.
     */
    public Boolean getResourceManagementEnabled() {
        return resourceManagementEnabled;
    }

    /**
     * @param resourceManagementEnabled the resourceManagementEnabled to set
     */
    public AbstractSheet<TRow, TColumn, TCell> setResourceManagementEnabled(Boolean resourceManagementEnabled) {
        this.resourceManagementEnabled = resourceManagementEnabled;
        return this;
    }

    /**
     * Gets the rows for the sheet.
     *
     * @return the rows
     */
    public List<TRow> getRows() {
        return rows;
    }

    /**
     * Sets the rows for the sheet.
     *
     * @param rows the new rows
     */
    public AbstractSheet<TRow, TColumn, TCell> setRows(List<TRow> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * True if show parent rows for filters.
     *
     * @return the show parent row for filters
     */
    public Boolean getShowParentRowsForFilters() {
        return showParentRowsForFilters;
    }

    /**
     * Sets the show parent rows for filters.
     *
     * @param showParentRowsForFilters the show parent rows for filters
     */
    public AbstractSheet<TRow, TColumn, TCell> setShowParentRowsForFilters(Boolean showParentRowsForFilters) {
        this.showParentRowsForFilters = showParentRowsForFilters;
        return this;
    }

    /**
     * Gets the source.
     *
     * @return the source
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the source.
     *
     * @param source the source
     */
    public AbstractSheet<TRow, TColumn, TCell> setSource(Source source) {
        this.source = source;
        return this;
    }

    /**
     * Gets the sheet summary if one exists for this sheet
     *
     * @return sheetSummary
     */
    public SheetSummary getSummary() { return summary; }

    /**
     * Sets the sheet summary if one exists for this sheet
     *
     * @param summary
     */
    public AbstractSheet<TRow, TColumn, TCell> setSummary(SheetSummary summary) {
        this.summary = summary;
        return this;
    }

    /**
     * Gets the total row count for the sheet.
     *
     * @return the total row count
     */
    public Integer getTotalRowCount() {
        return totalRowCount;
    }

    /**
     * Sets the total row count.
     *
     * @param totalRowCount the total row count
     */
    public AbstractSheet<TRow, TColumn, TCell> setTotalRowCount(Integer totalRowCount) {
        this.totalRowCount = totalRowCount;
        return this;
    }

    /**
     * Gets the sheet user permissions
     *
     * @return userPermissions
     */
    public SheetUserPermissions getUserPermissions() { return userPermissions; }

    /**
     * Sets the sheet user permissions
     *
     * @param userPermissions
     */
    public AbstractSheet<TRow, TColumn, TCell> setUserPermissions(SheetUserPermissions userPermissions) {
        this.userPermissions = userPermissions;
        return this;
    }

    /**
     * Gets the sheet user settings.
     *
     * @return the user settings
     */
    public SheetUserSettings getUserSettings() {
        return userSettings;
    }

    /**
     * Sets the user settings.
     *
     * @param userSettings the user settings
     */
    public AbstractSheet<TRow, TColumn, TCell> setUserSettings(SheetUserSettings userSettings) {
        this.userSettings = userSettings;
        return this;
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
    public AbstractSheet<TRow, TColumn, TCell> setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets a workspace object containing the name and id of the workspace containing this sheet
     *
     * @return workspace
     */
    public Workspace getWorkspace() { return workspace; }

    /**
     * Sets a workspace object containing the name and id of the workspace containing this sheet
     *
     * @param workspace
     */
    public AbstractSheet<TRow, TColumn, TCell> setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        return this;
    }

    /**
     * Get a column by index.
     *
     * @param index the column index
     * @return the column by index
     */
    public TColumn getColumnByIndex(int index) {
        if (columns == null) {
            return null;
        }

        TColumn result = null;
        for (TColumn column : columns) {
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
    public TColumn getColumnById(long columnId) {
        if (columns == null) {
            return null;
        }

        TColumn result = null;
        for (TColumn column : columns) {
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
    public TRow getRowByRowNumber(int rowNumber) {
        if (rows == null) {
            return null;
        }

        TRow result = null;
        for (TRow row : rows) {
            if (row.getRowNumber() == rowNumber) {
                result = row;
                break;
            }
        }
        return result;
    }
}
