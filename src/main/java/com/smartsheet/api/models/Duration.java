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

import com.smartsheet.api.models.enums.ObjectValueType;

public class Duration implements ObjectValue {

    /**
     * When used as a predecessor's lag value, indicates whether the lag is negative(true) or positive(false)
     */
    private Boolean negative;

    /**
     * True indicates this duration represents elapsed time, which ignores non-working time
     */
    private Boolean elapsed;

    /**
     * The number of weeks for this duration
     */
    private Double weeks;

    /**
     * The number of days for this duration
     */
    private Double days;

    /**
     * The number of hours for this duration
     */
    private Double hours;

    /**
     * The number of minutes for this duration
     */
    private Double minutes;

    /**
     * The number of seconds for this duration
     */
    private Double seconds;

    /**
     * The number of milliseconds for this duration
     */
    private Double milliseconds;

    /**
     * Default constructor for serialization
     */
    public Duration() {
        // For serialization
    }

    /**
     * Constructor with all possible attributes specified.
     * @param negative
     * @param elapsed
     * @param weeks
     * @param days
     * @param hours
     * @param minutes
     * @param seconds
     * @param milliseconds
     */
    public Duration(Boolean negative, Boolean elapsed, Double weeks, Double days, Double hours, Double minutes, Double seconds, Double milliseconds) {
        this.negative = negative;
        this.elapsed = elapsed;
        this.weeks = weeks;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    /**
     * "DURATION"
     *
     * @return objectType
     */
    @Override
    public ObjectValueType getObjectType() {
        return ObjectValueType.DURATION;
    }

    /**
     * Get flag indicating whether the lag is negative(true) or positive(false)
     *
     * @return negative
     */
    public Boolean getNegative() {
        return negative;
    }

    /**
     * Set flag indicating whether the lag is negative(true) or positive(false)
     *
     * @param negative
     */
    public Duration setNegative(Boolean negative) {
        this.negative = negative;
        return this;
    }

    /**
     * Indicates this duration represents elapsed time, which ignores non-working time
     *
     * @return elapsed
     */
    public Boolean getElapsed() {
        return elapsed;
    }

    /**
     * Set flag indicating this duration represents elapsed time.
     *
     * @param elapsed
     */
    public Duration setElapsed(Boolean elapsed) {
        this.elapsed = elapsed;
        return this;
    }

    /**
     * Get the number of weeks in duration.
     *
     * @return weeks
     */
    public Double getWeeks() {
        return weeks;
    }

    /**
     * Set the number of weeks in duration.
     *
     * @param weeks
     */
    public Duration setWeeks(Double weeks) {
        this.weeks = weeks;
        return this;
    }

    /**
     * Get the number of days for this duration.
     *
     * @return days
     */
    public Double getDays() {
        return days;
    }

    /**
     * Set the number of days for this duration.
     *
     * @param days
     */
    public Duration setDays(Double days) {
        this.days = days;
        return this;
    }

    /**
     * Get the number of hours for this duration.
     *
     * @return hours
     */
    public Double getHours() {
        return hours;
    }

    /**
     * Set the number of hours for this duration.
     *
     * @param hours
     */
    public Duration setHours(Double hours) {
        this.hours = hours;
        return this;
    }

    /**
     * Get the number of minutes for this duration.
     *
     * @return minutes
     */
    public Double getMinutes() {
        return minutes;
    }

    /**
     * Set the number of minutes for this duration.
     *
     * @param minutes
     */
    public Duration setMinutes(Double minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * Get the number of seconds for this duration.
     *
     * @return seconds
     */
    public Double getSeconds() {
        return seconds;
    }

    /**
     * Set the number of seconds for this duration.
     *
     * @param seconds
     */
    public Duration setSeconds(Double seconds) {
        this.seconds = seconds;
        return this;
    }

    /**
     * Get the number of milliseconds for this duration.
     *
     * @return milliseconds
     */
    public Double getMilliseconds() {
        return milliseconds;
    }

    /**
     * Set the number of milliseconds for this duration.
     *
     * @param milliseconds
     */
    public Duration setMilliseconds(Double milliseconds) {
        this.milliseconds = milliseconds;
        return this;
    }
}
