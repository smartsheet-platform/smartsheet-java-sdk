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
 * Represents an Email object.
 */
public abstract class Email {
    /**
     * Represents the list of recipients to send to
     */
    private List<Recipient> sendTo;

    /**
     * Represents the subject.
     */
    private String subject;

    /**
     * Represents the message.
     */
    private String message;

    /**
     * Represents the CC me flag.
     */
    private Boolean ccMe;

    /**
     * Gets the list of recipients to send to
     * @return the list of recipients
     */
    public List<Recipient> getSendTo() { return sendTo; }

    /**
     * Sets the list of recipients to send to
     * @param sendTo list of recipients
     */
    public Email setSendTo(List<Recipient> sendTo) {
        this.sendTo = sendTo;
        return this;
    }

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the new subject
     */
    public Email setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public Email setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Gets the carbon copy me flag.
     *
     * @return the cc me
     */
    public Boolean getCcMe() {
        return ccMe;
    }

    /**
     * Sets the carbon copy me flag.
     *
     * @param ccMe the new cc me
     */
    public Email setCcMe(Boolean ccMe) {
        this.ccMe = ccMe;
        return this;
    }
}
