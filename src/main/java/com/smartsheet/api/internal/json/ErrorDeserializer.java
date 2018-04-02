package com.smartsheet.api.internal.json;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2018 Smartsheet
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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.smartsheet.api.models.*;

import java.io.IOException;
import java.util.List;

public class ErrorDeserializer extends JsonDeserializer<com.smartsheet.api.models.Error> {

    @Override
    public com.smartsheet.api.models.Error deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        final com.smartsheet.api.models.Error error = new com.smartsheet.api.models.Error();

        if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            JsonNode node = jp.getCodec().readTree(jp);
            if(node.get("errorCode") != null)
                error.setErrorCode(node.get("errorCode").asInt());
            if(node.get("message") != null)
                error.setMessage(node.get("message").asText());
            if(node.get("refId") != null)
                error.setRefId(node.get("refId").asText());
            JsonNode detail = node.get("detail");
            if(detail != null) {
                if(detail.isArray()) {
                    String _as_text = detail.toString();
                    List<ErrorDetail> details = mapper.readValue(_as_text,
                            new TypeReference<List<ErrorDetail>>(){});
                    error.setDetail(details);
                }
                else
                    error.setDetail(mapper.treeToValue(detail, ErrorDetail.class));
            }
            return error;
        }
        return null;
    }
}
