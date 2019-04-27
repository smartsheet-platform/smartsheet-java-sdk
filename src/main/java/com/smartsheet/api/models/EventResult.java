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


import java.util.List;

public class EventResult {

    /**
     * Array of event information. See event reference documentation for details on each event type.
     */
    private List<Event> data;

    /**
     * True if more results are available. This is typically due to event counts exceeding the maxCount parameter passed in
     */
    private Boolean moreAvailable;

    /**
     * This string should be passed back to the next GET events call to obtain subsequent events
     */
    private String nextStreamPosition;

    /**
     * Gets the list of events
     *
     * @return the list of events
     */
    public List<Event> getData() {
        return data;
    }

    /**
     * Sets the list of events
     *
     * @param data the list of events
     */
    public void setData(List<Event> data) {
        this.data = data;
    }

    /***
     * Returns if more events are available for retrieval
     *
     * @return true if more events are available, false otherwise
     */
    public Boolean getMoreAvailable() {
        return moreAvailable;
    }

    /**
     * Sets the flag indicating if more events are available
     *
     * @param moreAvailable the flag
     */
    public void setMoreAvailable(Boolean moreAvailable) {
        this.moreAvailable = moreAvailable;
    }

    /**
     * Gets a string indicating the streamPosition of the next set of events
     *
     * @return the string streamPosition
     */
    public String getNextStreamPosition() {
        return nextStreamPosition;
    }

    /**
     * Sets a string indicating the streamPosition for the next set of events
     *
     * @param nextStreamPosition the string for the next set of events
     */
    public void setNextStreamPosition(String nextStreamPosition) {
        this.nextStreamPosition = nextStreamPosition;
    }
}
