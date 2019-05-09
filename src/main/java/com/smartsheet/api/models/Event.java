package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2019 Smartsheet
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


import com.smartsheet.api.models.enums.EventAction;
import com.smartsheet.api.models.enums.EventObjectType;
import com.smartsheet.api.models.enums.EventSource;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

public class Event {

    /**
     * Name of the access token embedded in the request.
     */
    private String accessTokenName;

    /**
     * The action applied to the specified object
     */
    private EventAction action;

    /**
     * Container object for additional event-specific properties.
     */
    private HashMap<String, Object> additionalDetails;

    /**
     * Unique event identifier
     */
    private String eventId;

    /**
     * Date and time of the event
     */
    private Object eventTimestamp;

    /**
     * The identifier of the object impacted by the event
     */
    private Object objectId;

    /**
     * The Smartsheet resource impacted by the event
     */
    private EventObjectType objectType;

    /**
     * ser whose authentication credential is embedded in the request that initiated the event.
     */
    private Long requestUserId;

    /**
     * Identifies the type of action that triggered the event
     */
    private EventSource source;

    /**
     * User assumed as the one who initiated the event.
     */
    private Long userId;

    /**
     * Gets the access token name associated with the event, can be null
     *
     * @return the access token name associated with the event
     */
    public String getAccessTokenName() {
        return accessTokenName;
    }

    /**
     * Sets the access token name associated with the event
     *
     * @param accessTokenName the access token name
     */
    public Event setAccessTokenName(String accessTokenName) {
        this.accessTokenName = accessTokenName;
        return this;
    }

    /**
     * Gets the action associated with the event (EventAction enumeration)
     *
     * @return the event action
     */
    public EventAction getAction() {
        return action;
    }

    /**
     * Sets the action associated with the event (EventAction enumeration)
     *
     * @param action
     */
    public Event setAction(EventAction action) {
        this.action = action;
        return this;
    }

    /**
     * Returns a hashmap of additional details associated with the event
     *
     * @return the hashmap
     */
    public HashMap<String, Object> getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * Set the hashmap of additional details associated with the event
     *
     * @param additionalDetails the hashmap
     */
    public Event setAdditionalDetails(HashMap<String, Object> additionalDetails) {
        this.additionalDetails = additionalDetails;
        return this;
    }

    /**
     * Gets a unique event ID
     *
     * @return the event ID
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Sets a unique event ID
     *
     * @param eventId the event ID
     */
    public Event setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    /**
     * Gets an event timestamp, either a Date object, or Long if numericDates parameter is true on API call.
     *
     * @return the event timestamp
     */
    public Object getEventTimestamp() {
        return eventTimestamp;
    }

    /**
     * Sets an event timestamp
     *
     * @param eventTimestamp String if Date, Long if numericDate true on API call.
     */
    public Event setEventTimestamp(Object eventTimestamp) {
        if (eventTimestamp instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                this.eventTimestamp = sdf.parse(eventTimestamp.toString());
            }
            catch(Exception e) { }
        }
        else {
            this.eventTimestamp = eventTimestamp;
        }
        return this;
    }

    /**
     * Get the object ID of the object associated with the event
     *
     * @return the object ID
     */
    public Object getObjectId() {
        return objectId;
    }

    /**
     * Sets an object ID for the object associated with the event
     *
     * @param objectId the object ID
     */
    public Event setObjectId(Object objectId) {
        this.objectId = objectId;
        return this;
    }

    /**
     * Gets the object type of the object associated with the event (EventObjectType enumeration)
     *
     * @return the object type
     */
    public EventObjectType getObjectType() {
        return objectType;
    }

    /**
     * Sets the object type of the object associated with the event
     *
     * @param objectType the object type
     */
    public Event setObjectType(EventObjectType objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * Get the user ID of the user whose credential initiated the request
     *
     * @return the request user ID
     */
    public Long getRequestUserId() {
        return requestUserId;
    }

    /**
     * Sets the user ID of the user whose credential initiated the request
     *
     * @param requestUserId the request user ID
     */
    public Event setRequestUserId(Long requestUserId) {
        this.requestUserId = requestUserId;
        return this;
    }

    /**
     * Gets the source of the event request (EventSource enumeration)
     *
     * @return the event source
     */
    public EventSource getSource() {
        return source;
    }

    /**
     * Sets the source of the event request
     *
     * @param source the event source
     */
    public Event setSource(EventSource source) {
        this.source = source;
        return this;
    }

    /**
     * Gets the assumed user ID for the event request
     *
     * @return the assumed user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the assumed user ID for the event request
     *
     * @param userId the assumed user ID
     */
    public Event setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
