package com.smartsheet.api.models;

import java.util.Date;

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

/**
 * Represents the UpdateRequest object.
 * @see <a href="http://help.smartsheet.com/customer/portal/articles/504779-using-update-requests">Using Update Request Help</a>
 */
public class UpdateRequest extends MultiRowEmail {

    /**
     * Represents the ID of the update request.
     */
    private Long id;

    /**
     * User object containing name and email of the sender.
     */
    private User sentBy;

    /**
     * The schedule for which update requests will be sent out.
     */
    private Schedule schedule;

    /**
     * The date and time for when this request was originally created.
     */
    private Date createdAt;

    /**
     * The date and time for when the last change was made to this request.
     */
    private Date modifiedAt;

    /**
     * Get the Id of the update request.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Id of the update request.
     * 
     * @param id
     */
    public UpdateRequest setId(Long id) {
        this.id = id;
        return this;
    }
    
    /**
     * Gets the User object containing the name and email of the sender
     * 
     * @return sentBy
     */
    public User getSentBy() {
        return sentBy;
    }
    
    /**
     * Sets the User object containing the name and email of the sender
     * 
     * @param sentBy
     */
    public UpdateRequest setSentBy(User sentBy) {
        this.sentBy = sentBy;
        return this;
    }
    
    /**
     * Gets the schedule for which update requests will be sent out.
     * 
     * @return schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }
    
    /**
     * Sets the schedule for which update requests will be sent out.
     * 
     * @param schedule
     */
    public UpdateRequest setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }
    
    /**
     * Gets the date and time for when this request was originally created.
     * 
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    
    /** 
     * Sets the date and time for when this request was originally created.
     *  
     * @param createdAt
     */
    public UpdateRequest setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    /**
     * Get the date and time for when the last change was made to this request.
     *  
     * @return modifiedAt
     */
    public Date getModifiedAt() {
        return modifiedAt;
    }
    
    /**
     * Set the date and time for when the last change was made to this request.
     * 
     * @param modifiedAt
     */
    public UpdateRequest setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }
}
