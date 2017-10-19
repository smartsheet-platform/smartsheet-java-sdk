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

import com.smartsheet.api.models.enums.DayDescriptor;
import com.smartsheet.api.models.enums.DayOrdinal;
import com.smartsheet.api.models.enums.ScheduleType;

import java.util.Date;
import java.util.List;

public class Schedule {

    /**
     * Schedule type
     */
    private ScheduleType type;

    /**
     * The date, time and time zone at which the first delivery will start.
     */
    private Date startAt;

    /**
     * The date, time and time zone at which the delivery schedule will end.
     */
    private Date endAt;

    /**
     * The day within the month
     */
    private Integer dayOfMonth;

    /**
     * The day ordinal
     */
    private DayOrdinal dayOrdinal;

    /**
     * An array of day descriptors
     */
    private List<DayDescriptor> dayDescriptors;

    /**
     * Frequency on which the request will be delivered
     */
    private Integer repeatEvery;

    /**
     * The date and time for when the last request was sent.
     */
    private Date lastSentAt;

    /**
     * The date and time for when the next request is scheduled to send.
     */
    private Date nextSendAt;

    /**
     * Get the schedule type
     *
     * @return type
     */
    public ScheduleType getType() {
        return type;
    }

    /**
     * Set the schedule type
     *
     * @param type
     */
    public Schedule setType(ScheduleType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the date, time and time zone at which the first delivery will start
     *
     * @return startAt
     */
    public Date getStartAt() {
        return startAt;
    }

    /**
     * Set the date, time and time zone at which the first delivery will start
     *
     * @param startAt
     */
    public Schedule setStartAt(Date startAt) {
        this.startAt = startAt;
        return this;
    }

    /**
     * Get the date, time and time zone at which the delivery schedule will end
     *
     * @return endAt
     */
    public Date getEndAt() {
        return endAt;
    }

    /**
     * Set the date, time and time zone at which the delivery schedule will end
     * @param endAt
     */
    public Schedule setEndAt(Date endAt) {
        this.endAt = endAt;
        return this;
    }

    /**
     * Get the day within the month
     *
     * @return dayOfMonth
     */
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Set the day within the month
     *
     * @param dayOfMonth
     */
    public Schedule setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    /**
     * Get the day ordinal
     *
     * @return dayOrdinal
     */
    public DayOrdinal getDayOrdinal() {
        return dayOrdinal;
    }

    /**
     * Set the day ordinal
     *
     * @param dayOrdinal
     */
    public Schedule setDayOrdinal(DayOrdinal dayOrdinal) {
        this.dayOrdinal = dayOrdinal;
        return this;
    }

    /**
     * Get an array of day descriptors
     *
     * @return dayDescriptors
     */
    public List<DayDescriptor> getDayDescriptors() {
        return dayDescriptors;
    }

    /**
     * Set the array of day descriptors
     *
     * @param dayDescriptors
     */
    public Schedule setDayDescriptors(List<DayDescriptor> dayDescriptors) {
        this.dayDescriptors = dayDescriptors;
        return this;
    }

    /**
     * Get the frequency on which the request will be delivered.
     *
     * @return repeatEvery
     */
    public Integer getRepeatEvery() {
        return repeatEvery;
    }

    /**
     * Set the frequency on which the request will be delivered.
     *
     * @param repeatEvery
     */
    public Schedule setRepeatEvery(Integer repeatEvery) {
        this.repeatEvery = repeatEvery;
        return this;
    }

    /**
     * Get the date and time for when the last request was sent.
     *
     * @return lastSentAt
     */
    public Date getLastSentAt() {
        return lastSentAt;
    }

    /**
     * Set the date and time for when the last request was sent.
     *
     * @param lastSentAt
     */
    public Schedule setLastSentAt(Date lastSentAt) {
        this.lastSentAt = lastSentAt;
        return this;
    }

    /**
     * Get the date and time for when the next request is scheduled to send.
     *
     * @return nextSendAt
     */
    public Date getNextSendAt() {
        return nextSendAt;
    }

    /**
     * Set the date and time for when the next request is schedule to send.
     *
     * @param nextSendAt
     */
    public Schedule sentNextSendAt(Date nextSendAt) {
        this.nextSendAt = nextSendAt;
        return this;
    }
}
