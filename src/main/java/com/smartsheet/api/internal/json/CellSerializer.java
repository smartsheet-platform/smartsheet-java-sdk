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

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.Cell;
import com.smartsheet.api.models.CellLink;

/**
 * Special case serializer to handle the linkInFromCell case. Normally we wouldn't serialize a null property,
 * but in this case we must serialize a null for 'value'.
 *
 * We'll special case the linkInFromCell case but pass the rest along to the default serializer.
 */
public class CellSerializer extends JsonSerializer<Cell> {

    private final JsonSerializer<Object> defaultSerializer;

    public CellSerializer(JsonSerializer<Object> defaultSerializer) {
        Util.throwIfNull(defaultSerializer);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(Cell cell, JsonGenerator gen, SerializerProvider serializers)
            throws IOException  {

        CellLink cell_link = cell.getLinkInFromCell();
        if(cell_link == null) {
            defaultSerializer.serialize(cell, gen, serializers);
        }
        else {
            gen.writeStartObject();
            gen.writeNumberField("columnId", cell.getColumnId());
            gen.writeFieldName("linkInFromCell");
            gen.writeObject(cell_link);
            gen.writeNullField("value");
            gen.writeEndObject();
        }
    }
}
