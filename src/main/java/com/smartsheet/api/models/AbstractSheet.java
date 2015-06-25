package com.smartsheet.api.models;

import java.util.Date;
import java.util.List;


public class AbstractSheet<TRow extends AbstractRow<TColumn , TCell>, TColumn extends Column, TCell extends Cell> extends NamedModel<Long>{

    /**
     * Represents the columns for the sheet.
     */
    private List<TColumn> columns;

    /**
     * Represents the rows for the sheet.
     */
    private List<TRow> rows;

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
     * A flag that indicates if resource management is enabled for a sheet.
     */
    private Boolean resourceManagementEnabled;

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

    private int totalRowCount;

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
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
    public void setDependenciesEnabled(Boolean dependenciesEnabled) {
        this.dependenciesEnabled = dependenciesEnabled;
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
    public void setColumns(List<TColumn> columns) {
        this.columns = columns;
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
    public void setRows(List<TRow> rows) {
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
}
