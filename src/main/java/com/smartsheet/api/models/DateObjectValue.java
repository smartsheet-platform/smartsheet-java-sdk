package com.smartsheet.api.models;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateObjectValue implements ObjectValue {
    public static final String ABSTRACT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private ObjectValueType objectType;
    private String value;
    private Date date;

    public static DateObjectValue fromDate(ObjectValueType objectValueType, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getFormatPattern(objectValueType));
        String value = simpleDateFormat.format(date);

        return new DateObjectValue(objectValueType, value);
    }

    public DateObjectValue(ObjectValueType objectType, String value) {
        this.objectType = objectType;
        this.value = value;
    }

    @Override
    public ObjectValueType getObjectType() {
        return objectType;
    }

    public DateObjectValue setObjectType(ObjectValueType objectType) {
        this.objectType = objectType;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DateObjectValue setValue(String value) {
        this.value = value;
        return this;
    }

    public Date toDate() throws ParseException {
        if (date == null && value != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getFormatPattern(objectType));
            date = simpleDateFormat.parse(value);
        }
        return date;
    }

    private static String getFormatPattern(ObjectValueType objectValueType) {
        if (objectValueType != null) {
            switch (objectValueType) {
                case ABSTRACT_DATETIME:
                    return ABSTRACT_DATETIME_FORMAT;

                case DATETIME:
                    return DATETIME_FORMAT;

                case DATE:
                    return DATE_FORMAT;
            }
        }

        throw new IllegalArgumentException("Unsupported objectValueType: " + objectValueType);
    }
}
