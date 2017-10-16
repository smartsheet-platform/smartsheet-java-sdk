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


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.models.CopyOrMoveRowResult;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.PartialRowUpdateResult;
import com.smartsheet.api.models.Result;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * This interface defines methods to handle JSON serialization/de-serialization.
 * 
 * Thread Safety: Implementation of this interface must be thread safe.
 */
public interface JsonSerializer {

    /**
     * Serialize an object to JSON.
     *
     * Parameters: - object : the object to serialize - outputStream : the output stream to which the JSON will be
     * written
     *
     * Returns: None
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param object the object
     * @param outputStream the output stream
     * @throws JSONSerializerException the JSON serializer exception
     */
    public <T> void serialize(T object, java.io.OutputStream outputStream) throws JSONSerializerException;

    /**
     * Serialize an object to JSON.
     *
     * Parameters: - object : the object to serialize - outputStream : the output stream to which the JSON will be
     * written
     *
     * Returns: None
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param object the object
     * @throws JSONSerializerException the JSON serializer exception
     */
    public <T> String serialize(T object) throws JSONSerializerException;

    /**
     * De-serialize json to PagedResult.
     * @param objectClass the object class
     * @param inputStream the input stream
     * @param <T> the generic type
     * @return the PagedResult containing a list of type T
     * @throws JSONSerializerException
     */
    public <T> PagedResult<T> deserializeDataWrapper(Class<T> objectClass, java.io.InputStream inputStream) throws JSONSerializerException;

    /**
     * De-serialize an object from JSON.
     *
     * Parameters: - objectClass : the class of the object to de-serialize - inputStream : the input stream from which
     * the JSON will be read
     *
     * Returns: the de-serialized object
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param objectClass the object class
     * @param inputStream the input stream
     * @return the t
     * @throws JsonParseException the json parse exception
     * @throws JsonMappingException the json mapping exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public <T> T deserialize(Class<T> objectClass, java.io.InputStream inputStream) throws JsonParseException,
            JsonMappingException, IOException;

    /**
     * De-serialize an object list from JSON.
     *
     * Parameters: - objectClass : the class of the object (of the list) to de-serialize - inputStream : the input
     * stream from which the JSON will be read
     *
     * Returns: the de-serialized list
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param objectClass the object class
     * @param inputStream the input stream
     * @return the list
     * @throws JSONSerializerException the JSON serializer exception
     */
    public <T> List<T> deserializeList(Class<T> objectClass, java.io.InputStream inputStream)
            throws JSONSerializerException;


    /**
     * De-serialize an object list from JSON to a Map.
     *
     * @param inputStream the input stream
     * @return the map
     * @throws JSONSerializerException the JSON serializer exception
     */
    public Map<String, Object> deserializeMap(InputStream inputStream) throws JSONSerializerException;

    /**
     * De-serialize a Result<T> object from JSON.
     *
     * Parameters: - objectClass : the class of the object (of the Result) to de-serialize - inputStream : the input
     * stream from which the JSON will be read
     *
     * Returns: the de-serialized result
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param objectClass the object class
     * @param inputStream the input stream
     * @return the result
     * @throws JSONSerializerException the JSON serializer exception
     */
    public <T> Result<T> deserializeResult(Class<T> objectClass, java.io.InputStream inputStream)
            throws JSONSerializerException;

    /**
     * De-serialize a Result<List<T>> object from JSON.
     *
     * Parameters: - objectClass : the class of the object (of the Result) to de-serialize - inputStream : the input
     * stream from which the JSON will be read
     *
     * Returns: the de-serialized result
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param <T> the generic type
     * @param objectClass the object class
     * @param inputStream the input stream
     * @return the result
     * @throws JSONSerializerException the JSON serializer exception
     */
    public <T> Result<List<T>> deserializeListResult(Class<T> objectClass, java.io.InputStream inputStream)
            throws JSONSerializerException;
    /**
     * De-serialize a PartialRowUpdateResult object from JSON.
     *
     * Parameters:
     * - inputStream : the input stream from which the JSON will be read
     *
     * Returns: the de-serialized result
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param inputStream the input stream
     * @return the result
     * @throws JSONSerializerException the JSON serializer exception
     */
    public PartialRowUpdateResult deserializePartialRowUpdateResult(java.io.InputStream inputStream)
            throws JSONSerializerException;

    /**
     * De-serialize a Result object from JSON.
     *
     * Parameters: - objectClass : the class of the object (of the Result) to de-serialize - inputStream : the input
     * stream from which the JSON will be read
     *
     * Returns: the de-serialized result
     *
     * Exceptions: - IllegalArgumentException : if any argument is null - JSONSerializerException : if there is any
     * other error occurred during the operation
     *
     * @param inputStream the input stream
     * @return the result
     * @throws JSONSerializerException the JSON serializer exception
     */
    public CopyOrMoveRowResult deserializeCopyOrMoveRow(java.io.InputStream inputStream)
            throws JSONSerializerException;

}
