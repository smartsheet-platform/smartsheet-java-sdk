package com.smartsheet.api.internal.json;
import java.util.List;

import com.smartsheet.api.models.*;
/**
 * This interface defines methods to handle JSON serialization/de-serialization.
 * 
 * Thread Safety:
 * Implementation of this interface must be thread safe.
*/
public interface JsonSerializer {
/**
 * Serialize an object to JSON.
 * 
 * Parameters:
 * - object : the object to serialize
 * - outputStream : the output stream to which the JSON will be written 
 * 
 * Returns:
 * None
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * @param outputStream 
 * @param object 
*/
public <T> void serialize(T object, java.io.OutputStream outputStream);
/**
 * De-serialize an object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized object
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> T deserialize(Class<T> objectClass, java.io.InputStream inputStream);
/**
 * De-serialize an object list from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the list) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized list
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> List<T> deserializeList(Class<T> objectClass, java.io.InputStream inputStream);
/**
 * De-serialize a Result<T> object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the Result) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized result
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> Result<T> deserializeResult(Class<T> objectClass, java.io.InputStream inputStream);
/**
 * De-serialize a Result<List<T>> object from JSON.
 * 
 * Parameters:
 * - objectClass : the class of the object (of the Result) to de-serialize
 * - inputStream : the input stream from which the JSON will be read 
 * 
 * Returns:
 * the de-serialized result
 * 
 * Exceptions:
 * - IllegalArgumentException : if any argument is null
 * - JSONSerializerException : if there is any other error occurred during the operation
 * @param inputStream 
 * @param objectClass 
 * @return 
*/
public <T> Result<List<T>> deserializeListResult(Class<T> objectClass, java.io.InputStream inputStream);
}

