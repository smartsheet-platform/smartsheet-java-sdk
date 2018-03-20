package com.smartsheet.api.internal.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.CellLink;

import java.io.IOException;

public class CellLinkSerializer extends JsonSerializer<CellLink> {

    private final com.fasterxml.jackson.databind.JsonSerializer<Object> defaultSerializer;

    public CellLinkSerializer(JsonSerializer<Object> defaultSerializer) {
        Util.throwIfNull(defaultSerializer);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(CellLink cellLink, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        if(cellLink.isNull()) {
            gen.writeNull();
        }
        else {
            defaultSerializer.serialize(cellLink, gen, serializers);
        }
    }
}
