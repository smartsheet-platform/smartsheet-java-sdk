package com.smartsheet.api.internal.json;
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

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.smartsheet.api.models.Recipient;
import com.smartsheet.api.models.RecipientEmail;
import com.smartsheet.api.models.RecipientGroup;

public class RecipientDeserializer extends JsonDeserializer<Recipient> {

    @Override
    public Recipient deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            JsonNode node = jp.getCodec().readTree(jp);
            if(node.get("email") != null) {
                RecipientEmail email = new
                        RecipientEmail.AddRecipientEmailBuilder().setEmail(node.get("email").asText()).build();
                return email;
            }
            else if(node.get("groupId") != null) {
                RecipientGroup group = new
                        RecipientGroup.AddRecipientGroupBuilder().setGroupId(node.get("groupId").asLong()).build();
                return group;
            }
        }
        return null;
    }
}
