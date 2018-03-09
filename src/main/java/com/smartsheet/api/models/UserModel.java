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

import com.smartsheet.api.models.enums.UserStatus;

import java.util.Date;
import java.util.List;

public abstract class UserModel extends IdentifiableModel<Long> {
    /**
     * Represents the email address.
     */
    private String email;

    /**
     * Represents the first name.
     */
    private String firstName;

    /**
     * Represents the last name.
     */
    private String lastName;

    /**
     * Represents the admin flag which allows managing users and accounts.
     */
    private Boolean admin;

    /**
     * Represents the licensed sheet creator flag which allows creating and owning sheets.
     */
    private Boolean licensedSheetCreator;

    /**
     * Represents the resource manager flag which allows the user access to the Resource Manager functionality.
     */
    private Boolean resourceViewer;

    /**
     * Represents the group admin flag which allows users to create and modify groups.
     */
    private Boolean groupAdmin;

    /**
     * Represents the user status (active, pending, declined).
     */
    private UserStatus status;

    /**
     * An array of AlternateEmail Objects representing the alternate email addresses associated with the User account
     */
    private List<AlternateEmail> alternateEmails;

    /**
     * The number of sheets owned by the current user within the organization
     */
    private Integer sheetCount;

    /**
     * Last login time of the current user
     */
    private Date lastLogin;

    /**
     * Timestamp of viewing an Enterprise Custom Welcome Screen by the current user
     */
    private Date customWelcomeScreenViewed;

    /**
     * Company name from the user's profile
     */
    private String company;

    /**
     * Department name from the user's profile
     */
    private String department;

    /**
     * User's mobile phone number from the profile
     */
    private String mobilePhone;

    /**
     * Link to the user's profile image
     */
    private Image profileImage;

    /**
     * User's role
     */
    private String role;

    /**
     * User's title
     */
    private String title;

    /**
     * Work phone number from the user's profile
     */
    private String workPhone;

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the new email address
     */
    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Gets the admin flag which allows managing users and accounts.
     *
     * @return the admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * Sets the admin flag which allows managing users and accounts.
     *
     * @param admin the new admin
     */
    public UserModel setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    /**
     * Gets the licensed sheet creator flag that allows creating and owning sheets.
     *
     * @return the licensed sheet creator
     */
    public Boolean getLicensedSheetCreator() {
        return licensedSheetCreator;
    }

    /**
     * Sets the licensed sheet creator flag that allows creating and owning sheets.
     *
     * @param licensedSheetCreator the new licensed sheet creator
     */
    public UserModel setLicensedSheetCreator(Boolean licensedSheetCreator) {
        this.licensedSheetCreator = licensedSheetCreator;
        return this;
    }

    /**
     * Gets the status of the user (active, pending, declined).
     *
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status the new status
     */
    public UserModel setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    /**
     * @return the flag indicating if someone is a resource manager
     */
    public Boolean getResourceViewer() {
        return resourceViewer;
    }

    /**
     * Sets the resource manager flag.
     * @param resourceViewer the flag
     */
    public UserModel setResourceViewer(Boolean resourceViewer) {
        this.resourceViewer = resourceViewer;
        return this;
    }

    /**
     *
     * @return the flag indicating if the user is able to administer group.
     */
    public Boolean getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * @param groupAdmin sets the flag that indicates if someone is a groupAdmin
     */
    public UserModel setGroupAdmin(Boolean groupAdmin) {
        this.groupAdmin = groupAdmin;
        return this;
    }

    /**
     * Gets the list of alternateEmails
     *
     * @return the list of alternateEmails
     */
    public List<AlternateEmail> getAlternateEmails() { return alternateEmails; }

    /**
     * Sets the list of alternateEmails
     *
     * @param alternateEmails the new list of alternateEmails
     * @return the UserModel
     */
    public UserModel setAlternateEmails(List<AlternateEmail> alternateEmails) {
        this.alternateEmails = alternateEmails;
        return this;
    }

    /**
     * Gets the sheetCount.
     *
     * @return sheetCount
     */
    public Integer getSheetCount() { return sheetCount; }

    /**
     * Sets the sheetCount
     *
     * @param sheetCount the new sheetCount
     * @return the UserModel
     */
    public UserModel setSheetCount(Integer sheetCount) {
        this.sheetCount = sheetCount;
        return this;
    }

    /**
     * Gets the lastLogin date
     *
     * @return the lastLogin date
     */
    public Date getLastLogin() { return lastLogin; }

    /** Sets the lastLogin date
     *
     * @param lastLogin the new lastLogin date
     * @return the UserModel
     */
    public UserModel setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    /**
     * Gets the customWelcomeScreenViewed date
     *
     * @return the customerWelcomeScreenViewed date
     */
    public Date getCustomWelcomeScreenViewed() { return customWelcomeScreenViewed; }

    /**
     * Sets the customWelcomeScreenViewed date
     *
     * @param customWelcomeScreenViewed the new customWelcomeScreenViewed date
     * @return the UserModel
     */
    public UserModel setCustomWelcomeScreenViewed(Date customWelcomeScreenViewed) {
        this.customWelcomeScreenViewed = customWelcomeScreenViewed;
        return this;
    }

    /**
     * Gets the user's company name
     *
     * @return the company name
     */
    public String getCompany() { return company; }

    /**
     * Sets the user's company name
     *
     * @param company the company name
     */
    public UserModel setCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * Gets the user's department
     *
     * @return the department
     */
    public String getDepartment() { return department; }

    /**
     * Sets the user's department
     *
     * @param department the department
     */
    public UserModel setDepartment(String department) {
        this.department = department;
        return this;
    }

    /**
     * Gets the user's mobile phone number
     *
     * @return the phone number
     */
    public String getMobilePhone() { return mobilePhone; }

    /**
     * Sets the user's mobile phone number
     *
     * @param mobilePhone the phone number
     */
    public UserModel setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    /**
     * Gets the user's profile image
     *
     * @return the Image
     */
    public Image getProfileImage() { return profileImage; }

    /**
     * Sets the user's profile image
     *
     * @param profileImage the Image
     */
    public UserModel setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    /**
     * Gets the user's role
     *
     * @return the role
     */
    public String getRole() { return role; }

    /**
     * Sets the user's role
     *
     * @param role the role
     */
    public UserModel setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Gets the user's title
     *
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Sets the user's title
     *
     * @param title the title
     */
    public UserModel setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets the user's work phone number
     *
     * @return the phone number
     */
    public String getWorkPhone() { return workPhone; }

    /**
     * Sets the user's work phone number
     *
     * @param workPhone the phone number
     */
    public UserModel setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
}
