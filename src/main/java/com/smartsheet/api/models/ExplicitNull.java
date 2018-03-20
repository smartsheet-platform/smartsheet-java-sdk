package com.smartsheet.api.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.smartsheet.api.models.enums.ObjectValueType;

import java.io.IOException;

public class ExplicitNull implements PrimitiveObjectValue<Object> {

    public Object getValue() {
        return null;
    }

    public ExplicitNull setValue(Object value) {
        return this;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeNull();
    }

    @Override
    public ObjectValueType getObjectType() {
        return ObjectValueType.NULL;
    }
}
