package com.smartsheet.api.internal.json;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2016 Smartsheet
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
import com.smartsheet.api.models.Hyperlink;

public class HyperlinkSerializer extends JsonSerializer<Hyperlink>{

    @Override
    public void serialize(Hyperlink value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if(value.isNull()) {
            gen.writeNull();
        }
        else {
            gen.writeStartObject();
            if(value.getUrl() != null)
                gen.writeStringField("url", value.getUrl());
            if(value.getReportId() != null)
                gen.writeNumberField("reportId", value.getReportId());
            if(value.getSheetId() != null)
                gen.writeNumberField("sheetId", value.getSheetId());
            gen.writeEndObject();
        }
    }

}
