package com.smartsheet.api.internal.json;

/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2017 Smartsheet
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

import com.smartsheet.api.models.*;
import com.smartsheet.api.models.enums.ObjectValueType;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObjectValueDeserializerTest {
    private static final double DELTA = 0.000001;
    private JacksonJsonSerializer jacksonJsonSerializer = new JacksonJsonSerializer();

    @Test
    public void unknownObjectType() throws IOException, JSONSerializerException {
        // If a new object type is introduced to the Smartsheet API, it shouldn't break existing integrations.
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"FUTURE_OBJECT_TYPE\"," +
                "                        \"value\": 1\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertEquals(null, objectValue);
    }

    @Test
    public void unknownAttribute() throws IOException, JSONSerializerException {
        // Verify that unknown attributes are ignored by the SDK
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DURATION\",\n" +
                "                        \"someNewAttributeAddedInFutureVersionOfAPI\": 1,\n" +
                "                        \"hours\": 7,\n" +
                "                        \"minutes\": 30\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertTrue(objectValue instanceof Duration);

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DURATION.name()),
                new ExpectedAttributeValue("hours", 7.0),
                new ExpectedAttributeValue("minutes", 30.0));
    }

    @Test
    public void duration_someAttributes() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DURATION\",\n" +
                "                        \"days\": 1,\n" +
                "                        \"hours\": 7,\n" +
                "                        \"minutes\": 30\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertTrue(objectValue instanceof Duration);

        Duration durationObjectValue = (Duration) objectValue;
        assertEquals(1, durationObjectValue.getDays().intValue());
        assertEquals(7, durationObjectValue.getHours().intValue());
        assertEquals(30, durationObjectValue.getMinutes().intValue());

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DURATION.name()),
                new ExpectedAttributeValue("days", 1.0),
                new ExpectedAttributeValue("hours", 7.0),
                new ExpectedAttributeValue("minutes", 30.0));
    }

    @Test
    public void duration_allAttributes() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DURATION\",\n" +
                "                        \"negative\": true,\n" +
                "                        \"elapsed\": false,\n" +
                "                        \"weeks\": 2,\n" +
                "                        \"days\": 3,\n" +
                "                        \"hours\": 7,\n" +
                "                        \"minutes\": 30,\n" +
                "                        \"seconds\": 45,\n" +
                "                        \"milliseconds\": 500\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertTrue(objectValue instanceof Duration);
        Duration durationObjectValue = (Duration) objectValue;
        assertEquals(true, durationObjectValue.getNegative());
        assertEquals(false, durationObjectValue.getElapsed());
        assertEquals(2, durationObjectValue.getWeeks().intValue());
        assertEquals(3, durationObjectValue.getDays().intValue());
        assertEquals(7, durationObjectValue.getHours().intValue());
        assertEquals(30, durationObjectValue.getMinutes().intValue());
        assertEquals(45, durationObjectValue.getSeconds().intValue());
        assertEquals(500, durationObjectValue.getMilliseconds().intValue());

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DURATION.name()),
                new ExpectedAttributeValue("negative", true),
                new ExpectedAttributeValue("elapsed", false),
                new ExpectedAttributeValue("weeks", 2.0),
                new ExpectedAttributeValue("days", 3.0),
                new ExpectedAttributeValue("hours", 7.0),
                new ExpectedAttributeValue("minutes", 30.0),
                new ExpectedAttributeValue("seconds", 45.0),
                new ExpectedAttributeValue("milliseconds", 500.0));
    }

    @Test
    public void duration_floatingPointValues() throws JSONSerializerException, IOException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DURATION\",\n" +
                "                        \"negative\": true,\n" +
                "                        \"elapsed\": false,\n" +
                "                        \"weeks\": 2.3,\n" +
                "                        \"days\": 3.4,\n" +
                "                        \"hours\": 7.5,\n" +
                "                        \"minutes\": 30.6,\n" +
                "                        \"seconds\": 45.7,\n" +
                "                        \"milliseconds\": 500.8\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertTrue(objectValue instanceof Duration);
        Duration durationObjectValue = (Duration) objectValue;
        assertEquals(true, durationObjectValue.getNegative());
        assertEquals(false, durationObjectValue.getElapsed());
        assertEquals(2.3, durationObjectValue.getWeeks(), DELTA);
        assertEquals(3.4, durationObjectValue.getDays(), DELTA);
        assertEquals(7.5, durationObjectValue.getHours(), DELTA);
        assertEquals(30.6, durationObjectValue.getMinutes(), DELTA);
        assertEquals(45.7, durationObjectValue.getSeconds(), DELTA);
        assertEquals(500.8, durationObjectValue.getMilliseconds(), DELTA);

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DURATION.name()),
                new ExpectedAttributeValue("negative", true),
                new ExpectedAttributeValue("elapsed", false),
                new ExpectedAttributeValue("weeks", 2.3),
                new ExpectedAttributeValue("days", 3.4),
                new ExpectedAttributeValue("hours", 7.5),
                new ExpectedAttributeValue("minutes", 30.6),
                new ExpectedAttributeValue("seconds", 45.7),
                new ExpectedAttributeValue("milliseconds", 500.8));
    }

    @Test
    public void abstractDateTime() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"ABSTRACT_DATETIME\",\n" +
                "                        \"value\": \"2017-07-01T16:30:07\"\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertEquals(ObjectValueType.ABSTRACT_DATETIME, objectValue.getObjectType());

        DateObjectValue dateObjectValue = (DateObjectValue) objectValue;
        assertEquals("2017-07-01T16:30:07", dateObjectValue.getValue());

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.ABSTRACT_DATETIME.name()),
                new ExpectedAttributeValue("value", "2017-07-01T16:30:07"));
    }

    @Test
    public void date() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DATE\",\n" +
                "                        \"value\": \"2017-07-17\"\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertEquals(ObjectValueType.DATE, objectValue.getObjectType());

        DateObjectValue dateObjectValue = (DateObjectValue) objectValue;
        assertEquals("2017-07-17", dateObjectValue.getValue());

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DATE.name()),
                new ExpectedAttributeValue("value", "2017-07-17"));
    }

    @Test
    public void dateObjectValue_toDate() throws IOException, JSONSerializerException, ParseException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DATETIME\",\n" +
                "                        \"value\": \"2017-07-17T20:27:57Z\"\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertEquals(ObjectValueType.DATETIME, objectValue.getObjectType());

        DateObjectValue dateObjectValue = (DateObjectValue) objectValue;

        Date date = dateObjectValue.toDate();
        assertEquals(2017, date.getYear() + 1900);
        assertEquals(7, date.getMonth() + 1);
        assertEquals(17, date.getDate());
        assertEquals(20, date.getHours());
        assertEquals(27, date.getMinutes());
        assertEquals(57, date.getSeconds());
    }

    @Test
    public void dateTime() throws IOException, JSONSerializerException, ParseException {
        String json = "{\"objectValue\": {\n" +
                "                        \"objectType\": \"DATETIME\",\n" +
                "                        \"value\": \"2017-07-17T20:27:57Z\"\n" +
                "                    }}";

        ObjectValue objectValue = getObjectValue(json);

        assertEquals(ObjectValueType.DATETIME, objectValue.getObjectType());

        DateObjectValue dateObjectValue = (DateObjectValue) objectValue;

        assertEquals("2017-07-17T20:27:57Z", dateObjectValue.getValue());

        Date date = dateObjectValue.toDate();
        assertEquals(2017, date.getYear() + 1900);
        assertEquals(7, date.getMonth() + 1);
        assertEquals(17, date.getDate());
        assertEquals(20, date.getHours());
        assertEquals(27, date.getMinutes());
        assertEquals(57, date.getSeconds());

        assertSerializedAttributes(objectValue,
                new ExpectedAttributeValue("objectType", ObjectValueType.DATETIME.name()),
                new ExpectedAttributeValue("value", "2017-07-17T20:27:57Z"));

    }

    @Test
    public void longObjectValue() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\":123456}";
        ObjectValue actual = getObjectValue(json);
        PrimitiveObjectValue<Number> numberPrimitiveObjectValue = (PrimitiveObjectValue<Number>) actual;
        assertEquals(123456L, numberPrimitiveObjectValue.getValue().longValue());

        assertEquals("123456", jacksonJsonSerializer.serialize(actual));
    }

    @Test
    public void numberObjectValue() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\":123456.789}";
        ObjectValue actual = getObjectValue(json);
        PrimitiveObjectValue<Number> numberPrimitiveObjectValue = (PrimitiveObjectValue<Number>) actual;
        assertEquals(123456.789f, numberPrimitiveObjectValue.getValue().floatValue(), 0.001);

        assertEquals("123456.789", jacksonJsonSerializer.serialize(actual));
    }

    @Test
    public void stringObjectValue() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\":\"foo\"}";
        ObjectValue actual = getObjectValue(json);
        PrimitiveObjectValue<String> primitiveObjectValue = (PrimitiveObjectValue<String>) actual;
        assertEquals("foo", primitiveObjectValue.getValue());

        assertEquals("\"foo\"", jacksonJsonSerializer.serialize(actual));
    }

    @Test
    public void booleanObjectValue() throws IOException, JSONSerializerException {
        String json = "{\"objectValue\":true}";
        ObjectValue actual = getObjectValue(json);
        PrimitiveObjectValue<Boolean> primitiveObjectValue = (PrimitiveObjectValue<Boolean>) actual;
        assertEquals(true, primitiveObjectValue.getValue());
        assertEquals("true", jacksonJsonSerializer.serialize(actual));
    }

    private static class ExpectedAttributeValue {
        final String attributeName;
        final Object attributeValue;

        ExpectedAttributeValue(String attributeName, Object attributeValue) {
            this.attributeName = attributeName;
            this.attributeValue = attributeValue;
        }
    }

    /**
     * Serializes the objectValue and verifies that all expected attributes are serialized out and that no extra elements exist.
     */
    private void assertSerializedAttributes(ObjectValue objectValue, ExpectedAttributeValue... expected) throws JSONSerializerException {
        String json = jacksonJsonSerializer.serialize(objectValue);
        Map<String, Object> serializedMap = jacksonJsonSerializer.deserializeMap(new ByteArrayInputStream(json.getBytes()));

        for (ExpectedAttributeValue expectedAttribute : expected) {
            assertTrue("Expected attribute '" + expectedAttribute.attributeName + "' in serialized object was missing!\n" + json, serializedMap.containsKey(expectedAttribute.attributeName));
            assertEquals("Expected value for attribute '"+ expectedAttribute.attributeName +"'", expectedAttribute.attributeValue, serializedMap.get(expectedAttribute.attributeName));
        }

        assertEquals("Number of serialized attributes did not equal the expected value.\n" + json, serializedMap.keySet().size(), expected.length);
    }


    /**
     * Serializes in an ObjectValue using the jacksonJsonSerializer as configured by the rest of the SDK.
     */
    private ObjectValue getObjectValue(String json) throws IOException, JSONSerializerException {
        ContainingClass deserializedResult = jacksonJsonSerializer.deserialize(ContainingClass.class, new ByteArrayInputStream(json.getBytes()));

        // Since we are here, serialize it back out again to automatically test that it can be serialized out.
        String json2 = jacksonJsonSerializer.serialize(deserializedResult);
        ContainingClass deserializedResult2 = jacksonJsonSerializer.deserialize(ContainingClass.class, new ByteArrayInputStream(json2.getBytes()));

        return deserializedResult.getObjectValue();
    }

    /**
     * Simple class simulating an object value as a member
     */
    private static class ContainingClass {
        public ObjectValue objectValue;

        public ContainingClass() {
        }

        public ContainingClass(ObjectValue objectValue) {
            this.objectValue = objectValue;
        }

        public ObjectValue getObjectValue() {
            return objectValue;
        }

        public void setObjectValue(ObjectValue objectValue) {
            this.objectValue = objectValue;
        }
    }
}
