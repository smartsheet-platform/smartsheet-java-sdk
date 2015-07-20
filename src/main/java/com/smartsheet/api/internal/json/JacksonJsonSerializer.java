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
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.smartsheet.api.internal.util.Util;
import com.smartsheet.api.models.IdentifiableModel;
import com.smartsheet.api.models.Result;
import com.smartsheet.api.models.format.Format;

/**
 * This is the Jackson based JsonSerializer implementation.
 * 
 * Thread Safety: This class is thread safe because it is immutable and the underlying Jackson ObjectMapper is thread
 * safe as long as it is not re-configured.
 */
public class JacksonJsonSerializer implements JsonSerializer {
	/**
	 * Represents the ObjectMapper used to serialize/de-serialize JSON.
	 * 
	 * It will be initialized in a static initializer and will not change afterwards.
	 * 
	 * Because ObjectMapper is thread-safe as long as it's not reconfigured, a static final class-level ObjectMapper is
	 * used to achieve best performance.
	 */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		// Indent for pretty printing
		//OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		
		// Allow deserialization if there are properties that can't be deserialized
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OBJECT_MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		
		// Only include non-null properties in when serializing java beans
		OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
		
		// Excludes "id" field from being serialized to JSON for any IdentifiableModel class
		OBJECT_MAPPER.addMixInAnnotations(IdentifiableModel.class, IdFieldExclusionMixin.class);
		
		//Add custom deserializer and serializer that will convert a string to a Format object and vice versa.
		SimpleModule module = new SimpleModule("FormatSerializationModule", Version.unknownVersion());
		module.addDeserializer(Format.class, new FormatDeserializer());
		module.addSerializer(Format.class, new FormatSerializer());
		OBJECT_MAPPER.registerModule(module);
	}

	/**
	 * Sets if the OBJECT MAPPER should ignore unknown properties or fail when de-serializing the JSON data.
	 * 
	 * @param value
	 *            true if it should fail, false otherwise.
	 */
	public static void setFailOnUnknownProperties(boolean value) {
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value);
	}

	/**
	 * Constructor.
	 * 
	 * Parameters: None
	 * 
	 * Exceptions: None
	 */
	public JacksonJsonSerializer() {
	}

	/**
	 * Serialize an object to JSON.
	 * 
	 * Parameters: 
	 *   object : the object to serialize
	 *   outputStream : the output stream to which the JSON will be written
	 * 
	 * Returns: None
	 * 
	 * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
	 * other error occurred during the operation
	 * 
	 * @param outputStream
	 * @param object
	 * @throws JSONSerializerException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	// @Override
	public <T> void serialize(T object, java.io.OutputStream outputStream) throws JSONSerializerException {
		Util.throwIfNull(object, outputStream);

		try {
			OBJECT_MAPPER.writeValue(outputStream, object);
		} catch (JsonGenerationException e) {
			throw new JSONSerializerException(e);
		} catch (JsonMappingException e) {
			throw new JSONSerializerException(e);
		} catch (IOException e) {
			throw new JSONSerializerException(e);
		}
	}

	/**
	 * De-serialize an object from JSON.
	 * 
	 * Returns: the de-serialized object
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - JSONSerializerException : if there is any other error occurred during the operation
	 * 
	 * @param inputStream the input stream from which the JSON will be read
	 * @param objectClass the class of the object to de-serialize
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	// @Override
	public <T> T deserialize(Class<T> objectClass, java.io.InputStream inputStream) throws JsonParseException,
			JsonMappingException, IOException {
		Util.throwIfNull(objectClass, inputStream);

		return OBJECT_MAPPER.readValue(inputStream, objectClass);
	}

	/**
	 * De-serialize an object list from JSON. 
	 * 
	 * Returns: the de-serialized list
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - JSONSerializerException : if there is any other error occurred during the operation
	 * 
	 * @param inputStream the input stream from which the JSON will be read
	 * @param objectClass the class of the object (of the list) to de-serialize
	 * @return
	 * @throws JSONSerializerException
	 */
	// @Override
	public <T> List<T> deserializeList(Class<T> objectClass, java.io.InputStream inputStream)
			throws JSONSerializerException {
		Util.throwIfNull(objectClass, inputStream);

		List<T> list = null;

		try {
			// Read the json input stream into a List.
			list = OBJECT_MAPPER.readValue(inputStream,
					OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, objectClass));
			// list = OBJECT_MAPPER.readValue(inputStream, new TypeReference<List<T>>() {});
		} catch (JsonParseException e) {
			throw new JSONSerializerException(e);
		} catch (JsonMappingException e) {
			throw new JSONSerializerException(e);
		} catch (IOException e) {
			throw new JSONSerializerException(e);
		}

		return list;
	}

	/**
	 * De-serialize to a map from JSON.
	 * 
	 * @param objectClass
	 * @param inputStream
	 * @return
	 * @throws JSONSerializerException
	 */
	// @Override
	public Map<String, Object> deserializeMap(InputStream inputStream) throws JSONSerializerException {
		Util.throwIfNull(inputStream);

		Map<String, Object> map = null;

		try {
			map = OBJECT_MAPPER.readValue(inputStream, new TypeReference<Map<String, Object>>() {
			});
			// map = OBJECT_MAPPER.readValue(inputStream, OBJECT_MAPPER.getTypeFactory().constructParametricType(
			// Map.class, OBJECT_MAPPER.getTypeFactory().constructParametricType(String.class, Object.class)));
		} catch (JsonParseException e) {
			throw new JSONSerializerException(e);
		} catch (JsonMappingException e) {
			throw new JSONSerializerException(e);
		} catch (IOException e) {
			throw new JSONSerializerException(e);
		}

		return map;
	}

	/**
	 * De-serialize a Result<T> object from JSON. 
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - JSONSerializerException : if there is any other error occurred during the operation
	 * 
	 * @param inputStream the input stream from which the JSON will be read
	 * @param objectClass the class of the object (of the Result) to de-serialize
	 * @return the de-serialized result
	 * @throws JSONSerializerException
	 */
	// @Override
	public <T> Result<T> deserializeResult(Class<T> objectClass, java.io.InputStream inputStream)
			throws JSONSerializerException {
		Util.throwIfNull(objectClass, inputStream);

		Result<T> result = null;

		try {
			result = OBJECT_MAPPER.readValue(inputStream,
					OBJECT_MAPPER.getTypeFactory().constructParametricType(Result.class, objectClass));
		} catch (JsonParseException e) {
			throw new JSONSerializerException(e);
		} catch (JsonMappingException e) {
			throw new JSONSerializerException(e);
		} catch (IOException e) {
			throw new JSONSerializerException(e);
		}

		return result;
	}

	/**
	 * De-serialize a Result<List<T>> object from JSON.
	 * 
	 * Parameters: - objectClass :  - inputStream : 
	 * 
	 * Returns: the de-serialized result
	 * 
	 * Exceptions: 
	 *   - IllegalArgumentException : if any argument is null 
	 *   - JSONSerializerException : if there is any other error occurred during the operation
	 * 
	 * @param inputStream the input stream from which the JSON will be read
	 * @param objectClass the class of the object (of the Result) to de-serialize
	 * @return
	 * @throws JSONSerializerException
	 */
	// @Override
	public <T> Result<List<T>> deserializeListResult(Class<T> objectClass, java.io.InputStream inputStream)
			throws JSONSerializerException {
		Util.throwIfNull(objectClass, inputStream);

		Result<List<T>> result = null;

		try {
			result = OBJECT_MAPPER.readValue(
					inputStream,
					OBJECT_MAPPER.getTypeFactory().constructParametricType(Result.class,
							OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, objectClass)));

			// result = OBJECT_MAPPER.readValue(inputStream, new TypeReference<Result<List<T>>>() {});
		} catch (JsonParseException e) {
			throw new JSONSerializerException(e);
		} catch (JsonMappingException e) {
			throw new JSONSerializerException(e);
		} catch (IOException e) {
			throw new JSONSerializerException(e);
		}
		return result;
	}
}
