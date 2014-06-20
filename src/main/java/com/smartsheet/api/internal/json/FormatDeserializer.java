package com.smartsheet.api.internal.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.smartsheet.api.models.format.Format;

public class FormatDeserializer extends JsonDeserializer<Format> {

	@Override
	public Format deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JsonProcessingException {
		return new Format(jsonParser.getValueAsString());
	}

}
