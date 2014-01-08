package com.smartsheet.api.internal.json;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.smartsheet.api.models.Result;

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
	 * @param outputStream
	 * @param object
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws JSONSerializerException
	 */
	public <T> void serialize(T object, java.io.OutputStream outputStream) throws JSONSerializerException;

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
	 * @param inputStream
	 * @param objectClass
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
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
	 * @param inputStream
	 * @param objectClass
	 * @return
	 * @throws JSONSerializerException
	 */
	public <T> List<T> deserializeList(Class<T> objectClass, java.io.InputStream inputStream)
			throws JSONSerializerException;

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
	 * @param inputStream
	 * @param objectClass
	 * @return
	 * @throws JSONSerializerException
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
	 * @param inputStream
	 * @param objectClass
	 * @return
	 * @throws JSONSerializerException
	 */
	public <T> Result<List<T>> deserializeListResult(Class<T> objectClass, java.io.InputStream inputStream)
			throws JSONSerializerException;
}
